package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.tripleTriplets;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeTripleTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeTripleTriplets implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {

        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        forEachManPinSou((man, pin, sou) -> {
            if (hasAllManPinSou(countUtil, man, pin, sou)) {
                yakuManager.both(Yaku.TRIPLE_TRIPLETS);
            }
        });
    }

    private boolean hasAllManPinSou(TilesCounterUtilForPatternAnalyze countUtil, TileType man, TileType pin,
        TileType sou) {
        return countUtil.hasTriplet(man) && countUtil.hasTriplet(pin) && countUtil.hasTriplet(sou);
    }

}
