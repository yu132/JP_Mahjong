package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.nineGate;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeNineGate  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeNineGate implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeNineGate();

    private AnalyzeNineGate() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        if (countUtil.hasNotMakeCall() && isNineGate(countUtil)) {
            if (isTrueNineGate(data, countUtil)) {
                yakuManager.both(Yaku.TRUE_NINE_GATES);
            } else {
                yakuManager.both(Yaku.NINE_GATES);
            }
        }
    }

    // 如果听的牌最后有2张或者4张，那么就是纯正九莲宝灯，因为此种情况下保证必须为1112345678999，才有可能
    // 听的牌是2张或者4张，当听的牌为1和9时，就为4张，2-8时为2张
    // 除了2张和4张，还有可能为1和3张，1张时例如1113456789999，3张时例如1123456789999
    private boolean isTrueNineGate(YakuAnalyzeData data, TilesCounterUtilForPatternAnalyze countUtil) {
        int tileToWinCount = countUtil.count(data.getTileToWin());
        return tileToWinCount == 2 || tileToWinCount == 4;
    }

    private boolean isNineGate(TilesCounterUtilForPatternAnalyze countUtil) {
        return isNineGate(countUtil.count(MAN_1), countUtil.count(MAN_9), countUtil.differentMans(),
            countUtil.allMans())
            || isNineGate(countUtil.count(PIN_1), countUtil.count(PIN_9), countUtil.differentPins(),
                countUtil.allPins())
            || isNineGate(countUtil.count(SOU_1), countUtil.count(SOU_9), countUtil.differentSous(),
                countUtil.allSous());
    }

    // 九莲宝灯要求1-9都必须有，其中1和9必须有3张，2-8至少要有1张，并且还有一张1-9的此种类型的牌，即总共有14张本种牌
    private boolean isNineGate(int _1Num, int _9Num, int _1to9Num, boolean isFullFlush) {
        return _1Num >= 3 && _9Num >= 3 && _1to9Num == 9 && isFullFlush;
    }
}
