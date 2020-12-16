package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.sevenPairs;

import java.util.Arrays;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair.SevenPairTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allHonors.AnalyzeAllHonors;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTerminals.AnalyzeAllTerminalsAndHonors;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.danyao.AnalyzeDanyao;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush.AnalyzeFullFlush;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush.AnalyzeHalfFlush;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: SevenPairTenpaiableAnalyzer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月15日  
 *  
 */
public class SevenPairTenpaiableYakuAnalyzer {

    private final static YakuAnalyzer ALL_HONORS_ANALYZER = AnalyzeAllHonors.getInstance();

    //@formatter:off
    private final static List<YakuAnalyzer> NOT_YAKUMAN_ANALYZERS = Arrays.asList(
        AnalyzeDanyao.getInstance(),
        AnalyzeAllTerminalsAndHonors.getInstance(),
        AnalyzeHalfFlush.getInstance(),
        AnalyzeFullFlush.getInstance()
    );
    //@formatter:on

    private GameRule rule;
    private PlayerTileManager playerTileManager;

    public SevenPairTenpaiableYakuAnalyzer(GameRule rule, PlayerTileManager playerTileManager) {
        super();
        this.rule = rule;
        this.playerTileManager = playerTileManager;
    }

    public PatternAndYaku analyze(SevenPairTenpaiable tenpaiable) {

        PatternAndYaku patternAndYaku = new PatternAndYaku(tenpaiable, tenpaiable.getTileToWin());

        YakuAnalyzeData yakuAnalyzeData = new YakuAnalyzeData(rule, null, null, // 分析七对子不需要场风和自风判断
            tenpaiable, tenpaiable.getTileToWin(),
            new TilesCounterUtilForPatternAnalyze(playerTileManager, tenpaiable, tenpaiable.getTileToWin()));

        analyzeSevenStars(patternAndYaku, yakuAnalyzeData);// 分析七对子唯一的役满牌型——大七星 = 七对子+字一色

        if (isNotSevenStars(patternAndYaku)) {
            addSevenPairsYaku(patternAndYaku);
            addOtherNotYakumanYaku(patternAndYaku, yakuAnalyzeData);
        }

        return patternAndYaku;
    }

    private void addOtherNotYakumanYaku(PatternAndYaku patternAndYaku, YakuAnalyzeData yakuAnalyzeData) {
        for (YakuAnalyzer analyzer : NOT_YAKUMAN_ANALYZERS) {
            analyzer.analyzeYaku(yakuAnalyzeData, patternAndYaku);
        }
    }

    private void addSevenPairsYaku(PatternAndYaku patternAndYaku) {
        patternAndYaku.both(Yaku.SEVEN_PAIRS);
    }

    private boolean isNotSevenStars(PatternAndYaku patternAndYaku) {
        return patternAndYaku.getRonYakus().size() == 0;
    }

    private void analyzeSevenStars(PatternAndYaku patternAndYaku, YakuAnalyzeData yakuAnalyzeData) {
        ALL_HONORS_ANALYZER.analyzeYaku(yakuAnalyzeData, patternAndYaku);
    }


}
