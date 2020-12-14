package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush;

import java.util.function.Function;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeFlush  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public abstract class AnalyzeFlush extends SimpleConditionYakuAnalyzer {

    protected AnalyzeFlush(Function<TilesCounterUtilForPatternAnalyze, Boolean> condition, Yaku yaku) {
        super(condition, yaku);
    }

    protected static boolean isFullFlush(TilesCounterUtilForPatternAnalyze util) {
        return util.allMans() || util.allPins() || util.allSous();
    }

}
