package yu.proj.ref.gameLogicChain.game.shared.analyze.utils;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TilesCounterUtil  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TilesCounterUtilForPatternAnalyze {

    private PlayerTileManager playerTileManager;

    public TilesCounterUtilForPatternAnalyze(PlayerTileManager playerTileManager) {
        super();

        assert playerTileManager != null;

        this.playerTileManager = playerTileManager;
    }

    int count(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            count += playerTileManager.countKanAs3TileAndRedAsNormal(tileType);
        }
        return count;
    }

    int has(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            boolean hasTile = playerTileManager.countKanAs3TileAndRedAsNormal(tileType) > 0;
            count += hasTile ? 1 : 0;
        }
        return count;
    }

    public int differentTerminals() {
        return has(MAN_1, MAN_9, PIN_1, PIN_9, SOU_1, SOU_9);
    }

    public int differentHonors() {
        return has(EAST, SOUTH, WEST, NORTH, WHITE, GREEN, RED);
    }

    public int differentTerminalsAndHonors() {
        return differentTerminals() + differentHonors();
    }
}
