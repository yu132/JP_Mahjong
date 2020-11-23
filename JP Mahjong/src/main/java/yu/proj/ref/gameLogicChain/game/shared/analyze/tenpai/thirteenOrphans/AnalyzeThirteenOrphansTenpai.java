package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileInHandGetter;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: AnalyzeThirteenOrphansTenpai  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */
public class AnalyzeThirteenOrphansTenpai {

    public List<ThirteenOrphansTenpaiable> analyze(PlayerTileManager playerTileManager) {

        if (isNotThirteenOrphans(playerTileManager)) {
            return Collections.emptyList();
        }

        return collectionTileAndGetPattern(playerTileManager);
    }

    private List<ThirteenOrphansTenpaiable> collectionTileAndGetPattern(PlayerTileManager playerTileManager) {
        List<Tile>             tiles  = new ArrayList<>();
        PlayerTileInHandGetter getter = playerTileManager.playerTileInHandGetter();

        for (TileType terminaOrHonorType : TileTypeGroup.TERMINALS_AND_HONORS) {
            for (int i = 0; i < playerTileManager.countInHand(terminaOrHonorType); ++i) {
                tiles.add(getter.claim(terminaOrHonorType));
            }
        }

        return Collections.singletonList(ThirteenOrphansTenpaiable.of(tiles));
    }

    private boolean isNotThirteenOrphans(PlayerTileManager playerTileManager) {
        int miss = 0;
        for (TileType terminaOrHonorType : TileTypeGroup.TERMINALS_AND_HONORS) {
            if (notHasThisTile(playerTileManager, terminaOrHonorType)) {
                if (++miss == 2) {// 超过两种九牌不存在，不是国士无双
                    return true;
                }
            }
        }
        return false;
    }

    private boolean notHasThisTile(PlayerTileManager playerTileManager, TileType terminaOrHonorType) {
        return playerTileManager.countInHand(terminaOrHonorType) == 0;
    }

}
