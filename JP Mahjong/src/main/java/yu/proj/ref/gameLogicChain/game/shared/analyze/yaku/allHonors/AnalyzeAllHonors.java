package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allHonors;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeAllHonors  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeAllHonors extends SimpleConditionYakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeAllHonors();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeAllHonors() {
        super((util) -> util.allHonors(), Yaku.ALL_HONORS);
    }

}
