package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allGreens;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeAllGreens  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeAllGreens implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeAllGreens();

    private AnalyzeAllGreens() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();
        if (countUtil.allGreens()) {
            analyzeByRule(data, yakuManager, countUtil);
        }
    }

    private void analyzeByRule(YakuAnalyzeData data, PatternAndYaku yakuManager,
        TilesCounterUtilForPatternAnalyze countUtil) {
        switch (data.getRule().allGreenRule) {
            case NEED_GREEN_DRAGON_TO_BE_ALL_GREEN:
                if (countUtil.count(GREEN) != 0) {
                    yakuManager.both(Yaku.ALL_GREENS);
                }
                break;

            case NOT_GREEN_DRAGON_CAN_BE_ALL_GREEN:
                yakuManager.both(Yaku.ALL_GREENS);
                break;

            case WITHOUT_GREEN_DRAGON_AS_TRUE_ALL_GREEN:
                if (countUtil.count(GREEN) == 0) {
                    yakuManager.both(Yaku.TRUE_ALL_GREENS);
                } else {
                    yakuManager.both(Yaku.ALL_GREENS);
                }
                break;

            default:
                throw new RuntimeException("不可能到达");
        }
    }

}
