package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.danyao;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.rule.danyao.DanyaoRule;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeDanyao  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 *  
 */
public class AnalyzeDanyao implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeDanyao();

    private AnalyzeDanyao() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {

        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        if (allowMakeCall(data) || countUtil.isMenzenchin()) {// 要不允许食断，要不就门清，否则不算断幺九
            if (countUtil.all2to8()) {
                yakuManager.both(Yaku.DANYAO);
            }
        }
    }

    private boolean allowMakeCall(YakuAnalyzeData data) {
        return data.getRule().danyaoRule == DanyaoRule.BOTH_MAKE_CALL_AND_MENZENCHIN;
    }

}
