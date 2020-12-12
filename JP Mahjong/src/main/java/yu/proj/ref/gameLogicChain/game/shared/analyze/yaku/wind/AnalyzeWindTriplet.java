package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
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
public abstract class AnalyzeWindTriplet implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        if (hasWindTriplet(data, countUtil)) {
            yakuManager.both(toYaku(wind(data)));
        }
    }

    private boolean hasWindTriplet(YakuAnalyzeData data, TilesCounterUtilForPatternAnalyze countUtil) {
        return countUtil.hasTriplet(wind(data));
    }

    protected abstract TileType wind(YakuAnalyzeData data);

    protected abstract Yaku toYaku(TileType wind);

}
