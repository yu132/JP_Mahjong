package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.AnalyzeTriplet;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeWindTriplet  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public abstract class AnalyzeWindTriplet extends AnalyzeTriplet {

    @Override
    protected TileType getTripletType(YakuAnalyzeData data) {
        return wind(data);
    }

    @Override
    protected Yaku getYaku(YakuAnalyzeData data) {
        return toYaku(wind(data));
    }

    protected abstract TileType wind(YakuAnalyzeData data);

    protected abstract Yaku toYaku(TileType wind);

}
