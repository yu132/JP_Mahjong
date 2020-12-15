package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData.YakuAnalyzeDataBuilder;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: YakuAnalyzeDataForTest  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
public class YakuAnalyzeDataForTest {

    public static YakuAnalyzeData data(TestAnalyzeData tadata, Tenpaiable tenpaiable, TileType tileToWin) {
        return new YakuAnalyzeData(MahjongSoulRule.mahjongSoulDefaultFourPalyerRule, EAST, EAST, tenpaiable, tileToWin,
            new TilesCounterUtilForPatternAnalyze(tadata.playerTileManager, tenpaiable, tileToWin));
    }

    public static YakuAnalyzeDataBuilder builder(TestAnalyzeData tadata, Tenpaiable tenpaiable, TileType tileToWin) {
        return data(tadata, tenpaiable, tileToWin).toBuilder();
    }

}
