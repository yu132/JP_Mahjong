package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade;

import java.util.function.BiConsumer;

import lombok.AllArgsConstructor;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.TenpaiManager;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Pair1Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair.SevenPairTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans.ThirteenOrphansTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.m4p1.TwoLevelMeld4Pair1TenpaiableYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.sevenPairs.SevenPairTenpaiableYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.thirteenOrphans.ThirteenOrphansTenpaiableYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: YakuAnalyzerFacade  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月16日  
 *  
 */

@AllArgsConstructor
public class YakuAnalyzerFacade {

    private GameRule rule;
    private TileType prevalentWind;
    private TileType seatWind;
    private PlayerTileManager playerTileManager;

    YakuManager analyze(TenpaiManager tenpaiManager) {

        YakuManager yakuManager = new YakuManager();

        forEach(tenpaiManager, (tenpaiable, tileToWin) -> {
            yakuManager.addPatternAndYaku(tileToWin, analyzeDifferentTypeOfTenpaiable(tenpaiable, tileToWin));
        });

        return yakuManager;
    }

    private PatternAndYaku analyzeDifferentTypeOfTenpaiable(Tenpaiable tenpaiable, TileType tileToWin) {

        PatternAndYaku patternAndYaku;

        if (tenpaiable instanceof Meld4Pair1Tenpaiable) {

            patternAndYaku =
                new TwoLevelMeld4Pair1TenpaiableYakuAnalyzer(rule, prevalentWind, seatWind, playerTileManager)
                    .analyze((Meld4Pair1Tenpaiable)tenpaiable, tileToWin);

        } else if (tenpaiable instanceof SevenPairTenpaiable) {

            patternAndYaku =
                new SevenPairTenpaiableYakuAnalyzer(rule, playerTileManager).analyze((SevenPairTenpaiable)tenpaiable);

        } else if (tenpaiable instanceof ThirteenOrphansTenpaiable) {

            patternAndYaku =
                new ThirteenOrphansTenpaiableYakuAnalyzer().analyze((ThirteenOrphansTenpaiable)tenpaiable, tileToWin);

        } else {
            throw new RuntimeException("不可到达");
        }

        return patternAndYaku;
    }

    private void forEach(TenpaiManager tenpaiManager, BiConsumer<Tenpaiable, TileType> consumer) {
        for (TileType tileToWin : tenpaiManager.getTilesToWin()) {
            for (Tenpaiable tenpaiable : tenpaiManager.getTenpaiable(tileToWin)) {
                consumer.accept(tenpaiable, tileToWin);
            }
        }
    }

}
