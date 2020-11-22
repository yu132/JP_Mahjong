package yu.proj.ref.gameLogicChain.game.shared.analyze.kita;

import static yu.proj.ref.tile.TileType.*;

import java.util.Collections;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;

/**  
 * @ClassName: AnalyzeKita  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class AnalyzeKita {

    public List<Kitable> analyze(PlayerTileManager playerTileManager) {
        if (playerTileManager.countInHand(NORTH) == 0) {
            return Collections.emptyList();
        }
        return Collections.singletonList(Kitable.of());
    }

}
