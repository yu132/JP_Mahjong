package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pureStraight;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzePureStraight  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzePureStraight implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzePureStraight();

    private AnalyzePureStraight() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        if (isPureStraight(data)) {
            yakuManager.both(Yaku.PURE_STRAIGHT);
        }
    }

    private boolean isPureStraight(YakuAnalyzeData data) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();
        return isPureStraight(MAN_1, MAN_4, MAN_7, countUtil) || isPureStraight(PIN_1, PIN_4, PIN_7, countUtil)
            || isPureStraight(SOU_1, SOU_4, SOU_7, countUtil);
    }

    private boolean isPureStraight(TileType _1, TileType _4, TileType _7, TilesCounterUtilForPatternAnalyze countUtil) {
        return countUtil.hasSequence(_1) && countUtil.hasSequence(_4) && countUtil.hasSequence(_7);
    }

}
