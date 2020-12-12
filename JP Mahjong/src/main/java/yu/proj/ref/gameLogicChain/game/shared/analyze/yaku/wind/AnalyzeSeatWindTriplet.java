package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeSeatWindTriplet  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class AnalyzeSeatWindTriplet extends AnalyzeWindTriplet {

    @Override
    protected TileType wind(YakuAnalyzeData data) {
        return data.getSeatWind();
    }

    @Override
    protected Yaku toYaku(TileType wind) {
        assert wind != null;

        switch (wind) {
            case EAST:
                return Yaku.SEAT_WIND_E;
            case SOUTH:
                return Yaku.SEAT_WIND_S;
            case WEST:
                return Yaku.SEAT_WIND_W;
            case NORTH:
                return Yaku.SEAT_WIND_N;
            default:
                throw new RuntimeException();
        }
    }
}
