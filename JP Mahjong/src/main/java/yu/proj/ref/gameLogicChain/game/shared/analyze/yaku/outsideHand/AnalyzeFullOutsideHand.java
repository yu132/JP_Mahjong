package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.outsideHand;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeFullOutsideHand  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeFullOutsideHand extends SimpleConditionYakuAnalyzer {

    protected AnalyzeFullOutsideHand() {
        super((util) -> util.allOutSideHand(), Yaku.FULL_OUTSIDE_HAND);
    }

}
