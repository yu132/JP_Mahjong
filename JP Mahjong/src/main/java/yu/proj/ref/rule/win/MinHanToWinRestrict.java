package yu.proj.ref.rule.win;

import lombok.Getter;

/**  
 * @ClassName: MinHanToWin  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月16日  
 *  
 */

@Getter
public class MinHanToWinRestrict {

    public final static MinHanToWinRestrict ONE_HAN = new MinHanToWinRestrict(MinHanToWinType.SUM, 1);

    public enum MinHanToWinType {
        SUM, // 牌型的番的总和能够满足番缚
        AT_LEST_ONE_YAKU;// 至少要有一个役达到番缚的大小，例如2番缚时，至少要有一个二番的役，两个一番的役是不被接受的
    }

    private MinHanToWinType type;

    private int minHanToWin;

    public MinHanToWinRestrict(MinHanToWinType type, int minHanToWin) {
        super();

        assert type != null && minHanToWin >= 0;

        this.type        = type;
        this.minHanToWin = minHanToWin;
    }
}
