package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: YakuAnalyzeData  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class YakuAnalyzeData {
    private GameRule rule;
    private TileType prevalentWind;
    private TileType seatWind;
    private Tenpaiable tenpaiable;
    private PlayerTileManager playerTileManager;
    private TilesCounterUtilForPatternAnalyze tilesCountUtil;
}
