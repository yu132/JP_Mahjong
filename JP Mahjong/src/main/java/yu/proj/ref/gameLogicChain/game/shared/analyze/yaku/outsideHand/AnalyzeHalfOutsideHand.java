package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.outsideHand;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeHalfOutsideHand  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeHalfOutsideHand extends SimpleConditionYakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeHalfOutsideHand();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeHalfOutsideHand() {
        // 混老头一定是混全带，因此不重复计算，纯全带也不再重复计混全带
        super((util) -> (util.allOutSideHandAndHonors() && !util.allTerminalsAndHonors() && !util.allOutSideHand()),
            Yaku.HALF_OUTSIDE_HAND);
    }

}
