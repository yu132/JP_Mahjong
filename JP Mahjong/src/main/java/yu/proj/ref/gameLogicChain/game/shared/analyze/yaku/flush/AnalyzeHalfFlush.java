package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeHalfFlush  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeHalfFlush extends AnalyzeFlush {

    private final static YakuAnalyzer SINGLETON = new AnalyzeHalfFlush();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeHalfFlush() {
        // 混一色不能是清一色
        super((util) -> (isHalfFlush(util) && !isFullFlush(util)), Yaku.HALF_FLUSH);
    }

    private static boolean isHalfFlush(TilesCounterUtilForPatternAnalyze util) {
        return util.allMansAndHonors() || util.allPinsAndHonors() || util.allSousAndHonors();
    }

}
