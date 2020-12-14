package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTerminals;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeAllTerminalsAndHonors  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeAllTerminalsAndHonors extends SimpleConditionYakuAnalyzer {

    protected AnalyzeAllTerminalsAndHonors() {
        // 混老头不能同时是清老头或者字一色
        super((util) -> (util.allTreminalsAndHonors() && !(util.allHonors() || util.allTerminals())),
            Yaku.ALL_TERMINALS_AND_HONORS);
    }

}
