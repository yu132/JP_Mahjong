package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTriplets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeAllTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeAllTriplets extends SimpleConditionYakuAnalyzer {

    public AnalyzeAllTriplets() {
        super((util) -> util.tripletTotalNum() == 4, Yaku.ALL_TRIPLETS);
    }

}
