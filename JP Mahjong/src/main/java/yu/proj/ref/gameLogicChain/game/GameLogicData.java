package yu.proj.ref.gameLogicChain.game;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import yu.proj.ref.gameLogicChain.game.shared.gameTilesManager.GameTilesManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.player.Player;

/**  
 * @ClassName: GameLogicData  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */

@Getter
@Setter
public class GameLogicData {

    private GameTilesManager gameTilesManager;

    private List<Player> players;

    private int nowPlayerIndex;

    private int dealerIndex;

    private Map<Player, PlayerTileManager> playersTilesManagers;

    public PlayerTileManager getPlayersTilesManager(Player player) {
        return playersTilesManagers.get(player);
    }

}
