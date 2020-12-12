package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: WindTriplet  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月28日  
 *  
 */
public class AnalyzePrevalentWindTriplet implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {

        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        if (hasPrevalentWindTriplet(data, countUtil)) {
            yakuManager.both(toYaku(data.getPrevalentWind()));
        }
    }

    private boolean hasPrevalentWindTriplet(YakuAnalyzeData data, TilesCounterUtilForPatternAnalyze countUtil) {
        return countUtil.hasTriplet(data.getPrevalentWind());
    }

    private Yaku toYaku(TileType wind) {
        assert wind != null;

        switch (wind) {
            case EAST:
                return Yaku.PREVALENT_WIND_E;
            case SOUTH:
                return Yaku.PREVALENT_WIND_S;
            case WEST:
                return Yaku.PREVALENT_WIND_W;
            case NORTH:
                return Yaku.PREVALENT_WIND_N;
            default:
                throw new RuntimeException();
        }
    }

}
