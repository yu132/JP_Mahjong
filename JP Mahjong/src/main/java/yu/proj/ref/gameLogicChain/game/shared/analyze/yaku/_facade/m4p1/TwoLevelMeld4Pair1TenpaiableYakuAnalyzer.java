package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.m4p1;

import java.util.Arrays;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Pair1Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allGreens.AnalyzeAllGreens;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allHonors.AnalyzeAllHonors;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTerminals.AnalyzeAllTerminals;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTerminals.AnalyzeAllTerminalsAndHonors;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTriplets.AnalyzeAllTriplets;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets.AnalyzeFourConcealedTriplets;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets.AnalyzeSingleWaitFourConcealedTriplets;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets.AnalyzeThreeConcealedTriplets;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.danyao.AnalyzeDanyao;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon.AnalyzeBigThreeDragons;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon.AnalyzeGreenTriplet;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon.AnalyzeLittleThreeDragons;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon.AnalyzeRedTriplet;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon.AnalyzeWhiteTriplet;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush.AnalyzeFullFlush;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush.AnalyzeHalfFlush;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.mixedTripleSequence.AnalyzeMixedTripletSequence;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.nineGate.AnalyzeNineGate;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.outsideHand.AnalyzeFullOutsideHand;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.outsideHand.AnalyzeHalfOutsideHand;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pinfu.AnalyzePinfu;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pureDoubleSequence.AnalyzePureDoubleSequence;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pureStraight.AnalyzePureStraight;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.quads.AnalyzeFourQuads;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.quads.AnalyzeThreeQuads;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.tripleTriplets.AnalyzeTripleTriplets;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind.AnalyzeFourBigWinds;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind.AnalyzeFourLittleWinds;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind.AnalyzePrevalentWindTriplet;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind.AnalyzeSeatWindTriplet;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TwoLevelMeld4Pair2TenpaiableYakuAnalyzer  
 *
 * @Description: 两层次的门面，只区分役满和非役满的情况，对于四暗刻，进行特殊处理
 *               比较简单，优化较少
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TwoLevelMeld4Pair1TenpaiableYakuAnalyzer extends Meld4Pair1TenpaiableYakuAnalyzer {

    //@formatter:off
    private final static List<YakuAnalyzer> NOT_YAKUMAN_ANALYZERS = Arrays.asList(
        AnalyzeDanyao.getInstance(),
        AnalyzePrevalentWindTriplet.getInstance(),
        AnalyzeSeatWindTriplet.getInstance(),
        AnalyzeWhiteTriplet.getInstance(),
        AnalyzeGreenTriplet.getInstance(),
        AnalyzeRedTriplet.getInstance(),
        AnalyzePinfu.getInstance(),
        
        AnalyzePureDoubleSequence.getInstance(),
        AnalyzeTripleTriplets.getInstance(),
        AnalyzeThreeQuads.getInstance(),
        AnalyzeAllTriplets.getInstance(),
        AnalyzeThreeConcealedTriplets.getInstance(),
        AnalyzeLittleThreeDragons.getInstance(),
        AnalyzeAllTerminalsAndHonors.getInstance(),
        AnalyzeHalfOutsideHand.getInstance(),
        AnalyzePureStraight.getInstance(),
        AnalyzeMixedTripletSequence.getInstance(),
        
        AnalyzeFullOutsideHand.getInstance(),
        AnalyzeHalfFlush.getInstance(),
        
        AnalyzeFullFlush.getInstance()
    );
    
    private final static List<YakuAnalyzer> YAKUMAN_ANALYZERS = Arrays.asList(
        AnalyzeBigThreeDragons.getInstance(),
        AnalyzeFourConcealedTriplets.getInstance(),
        AnalyzeAllHonors.getInstance(),
        AnalyzeAllGreens.getInstance(),
        AnalyzeAllTerminals.getInstance(),
        AnalyzeFourLittleWinds.getInstance(),
        AnalyzeFourQuads.getInstance(),
        AnalyzeNineGate.getInstance(),
        
        AnalyzeSingleWaitFourConcealedTriplets.getInstance(),
        AnalyzeFourBigWinds.getInstance()
    );
    //@formatter:on

    public TwoLevelMeld4Pair1TenpaiableYakuAnalyzer(GameRule rule, TileType prevalentWind, TileType seatWind,
        PlayerTileManager playerTileManager) {
        super(rule, prevalentWind, seatWind, playerTileManager);
    }

    @Override
    public PatternAndYaku analyze(Meld4Pair1Tenpaiable tenpaiable, TileType tileToWin) {
        PatternAndYaku patternAndYaku = new PatternAndYaku(tenpaiable, tileToWin);
        YakuAnalyzeData yakuAnalyzeData = new YakuAnalyzeData(rule, prevalentWind, seatWind, tenpaiable, tileToWin,
            new TilesCounterUtilForPatternAnalyze(playerTileManager, tenpaiable, tileToWin));

        analyzeYakumanYaku(patternAndYaku, yakuAnalyzeData);

        if (noYakumanYakuWhenRon(patternAndYaku)) {
            analyzeNotYakumanYakuWhenRon(tenpaiable, tileToWin, patternAndYaku, yakuAnalyzeData);
        } else if (NoAnyYakumanYaku(patternAndYaku)) {
            analyzeNotYakumanYaku(yakuAnalyzeData, patternAndYaku);
        }

        return patternAndYaku;
    }

    private void analyzeNotYakumanYakuWhenRon(Meld4Pair1Tenpaiable tenpaiable, TileType tileToWin,
        PatternAndYaku patternAndYaku, YakuAnalyzeData yakuAnalyzeData) {

        PatternAndYaku temp = new PatternAndYaku(tenpaiable, tileToWin);// 用于存储非役满的役的临时结构

        analyzeNotYakumanYaku(yakuAnalyzeData, temp);

        for (Yaku yaku : temp.getRonYakus()) {
            patternAndYaku.ron(yaku);// 由于自摸已经有役满，因此不计非役满的役，但是对于荣和就必须计
        }
    }

    // 没有任何役满的役
    private boolean NoAnyYakumanYaku(PatternAndYaku patternAndYaku) {
        return patternAndYaku.getTsumoYakus().size() == 0;
    }

    // 其实就是有且只有四暗刻单骑一个役满的时候，才可能出现这种情况
    private boolean noYakumanYakuWhenRon(PatternAndYaku patternAndYaku) {
        return patternAndYaku.getRonYakus().size() == 0;
    }

    private void analyzeNotYakumanYaku(YakuAnalyzeData yakuAnalyzeData, PatternAndYaku patternAndYaku) {
        analyzeYaku(NOT_YAKUMAN_ANALYZERS, patternAndYaku, yakuAnalyzeData);
    }

    private void analyzeYakumanYaku(PatternAndYaku patternAndYaku, YakuAnalyzeData yakuAnalyzeData) {
        analyzeYaku(YAKUMAN_ANALYZERS, patternAndYaku, yakuAnalyzeData);
    }

    private void analyzeYaku(List<YakuAnalyzer> analyzers, PatternAndYaku patternAndYaku,
        YakuAnalyzeData yakuAnalyzeData) {
        for (YakuAnalyzer analyzer : analyzers) {
            analyzer.analyzeYaku(yakuAnalyzeData, patternAndYaku);
        }
    }
}
