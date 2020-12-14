package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTerminals;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeAllTerminals  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeAllTerminals extends SimpleConditionYakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeAllTerminals();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeAllTerminals() {
        super((util) -> util.allTerminals(), Yaku.ALL_TERMINALS);
    }

}
