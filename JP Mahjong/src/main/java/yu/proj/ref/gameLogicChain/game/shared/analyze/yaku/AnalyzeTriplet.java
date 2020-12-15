package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeTriplet  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public abstract class AnalyzeTriplet implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        if (hasTriplet(data, countUtil)) {
            yakuManager.both(getYaku(data));
        }
    }

    private boolean hasTriplet(YakuAnalyzeData data, TilesCounterUtilForPatternAnalyze countUtil) {
        return countUtil.hasTriplet(getTripletType(data));
    }

    protected abstract TileType getTripletType(YakuAnalyzeData data);

    protected abstract Yaku getYaku(YakuAnalyzeData data);

}
