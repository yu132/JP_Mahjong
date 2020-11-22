package yu.proj.ref.rule.point;

import lombok.AllArgsConstructor;

/**  
 * @ClassName: PointRule  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月17日  
 *  
 */
public class PointRule {

    public final static PointLimit NO_LIMIT = new PointLimit(false, 0);

    private PointLimit lowerLimit;
    private PointLimit upperLimit;

    private PointLimit winMinLimit;

    @AllArgsConstructor
    public static class PointLimit {
        public final boolean enable;
        public final int point;
    }

    public PointRule(PointLimit lowerLimit, PointLimit upperLimit, PointLimit winMinLimit) {
        super();

        assert lowerLimit != null && upperLimit != null && winMinLimit != null;

        assert checkArg(lowerLimit, upperLimit, winMinLimit);

        this.lowerLimit  = lowerLimit;
        this.upperLimit  = upperLimit;
        this.winMinLimit = winMinLimit;
    }

    public boolean isGreaterThanOrEqualToUpperLimit(int point) {
        if (!upperLimit.enable) {// 返回假表示没有限制，如果返回真，则表示游戏结束
            return false;
        }
        return point >= upperLimit.point;
    }

    public boolean isLessThanLowerLimit(int point) {
        if (!lowerLimit.enable) {// 返回假表示没有限制，如果返回真，则表示游戏结束
            return false;
        }
        return point < lowerLimit.point;
    }

    public boolean isGreaterThanOrEqualToWinMinLimit(int point) {
        if (!winMinLimit.enable) {// 因为是完场分数，返回真才是没有限制，如果是返回假，那么不能赢，还得打下一局
            return true;
        }
        return point >= winMinLimit.point;
    }

    private boolean checkArg(PointLimit lowerLimit, PointLimit upperLimit, PointLimit winMinLimit) {
        if (winMinLimit.enable) {
            if (lowerLimit.enable && lowerLimit.point > winMinLimit.point) {
                return false;
            }
            if (upperLimit.enable && upperLimit.point < winMinLimit.point) {
                return false;
            }
        }
        if (lowerLimit.enable && winMinLimit.enable) {
            if (lowerLimit.point > winMinLimit.point) {
                return false;
            }
        }
        return true;
    }

}
