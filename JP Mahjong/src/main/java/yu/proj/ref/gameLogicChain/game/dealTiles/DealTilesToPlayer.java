package yu.proj.ref.gameLogicChain.game.dealTiles;

import java.util.List;

import yu.proj.ref.gameLogicChain.game.GameLogicData;
import yu.proj.ref.gameLogicChain.game.TaskStep;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.player.Player;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.utils.KeyValuePair;

/**  
 * @ClassName: DealTilesToPlayer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月13日  
 *  
 */
public class DealTilesToPlayer extends AbstractDealTilesTaskStep {

    public DealTilesToPlayer(GameLogicData mainData, DealTilesTaskSharedData taskData) {
        super(mainData, taskData);
    }

    @Override
    public void doStepDuty() {
        for (KeyValuePair<Player, List<Tile>> playerAndTiles : taskData.getPlayersTiles()) {
            Player            player            = playerAndTiles.getKey();
            List<Tile>        tiles             = playerAndTiles.getValue();

            PlayerTileManager playerTileManager = mainData.getPlayersTilesManager(player);

            for (Tile tile : tiles) {
                playerTileManager.draw(new DrawOperation(tile));
            }
        }
    }

    @Override
    protected TaskStep getNextTaskStep() {
        return new AnalyzeAllPlayerTilesAfterDraw13Tiles(mainData, taskData);
    }

}
