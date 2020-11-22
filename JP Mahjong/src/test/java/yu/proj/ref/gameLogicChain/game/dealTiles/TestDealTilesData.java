package yu.proj.ref.gameLogicChain.game.dealTiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.GameLogicData;
import yu.proj.ref.gameLogicChain.game.shared.gameTilesManager.FourPlayerGameTilesManagerImpl;
import yu.proj.ref.gameLogicChain.game.shared.gameTilesManager.GameTilesManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManagerImpl;
import yu.proj.ref.player.Player;
import yu.proj.ref.testUtils.InitTiles;
import yu.proj.ref.tile.Tile;

/**
 * @ClassName : TestDealTilesData  
 * @Description : TODO(这里用一句话描述这个类的作用)  
 * @author  余定邦  
 * @date  2020年11月19日  
 */
public class TestDealTilesData {

    Tile[] tiles;
    GameLogicData mainData;
    DealTilesTaskSharedData taskData;

    public TestDealTilesData() {
        tiles    = InitTiles.initTilesAndShuffle(1, 1, 1);
        mainData = initMainData(tiles);
        taskData = new DealTilesTaskSharedData();
    }

    private GameLogicData initMainData(Tile[] tiles) {
        GameLogicData mainData = new GameLogicData();
        initTilesManagerInMainData(mainData, tiles);
        return mainData;
    }

    private void initTilesManagerInMainData(GameLogicData mainData, Tile[] tiles) {
        GameTilesManager gameTilesManager = new FourPlayerGameTilesManagerImpl(tiles);
        mainData.setGameTilesManager(gameTilesManager);
    }

    List<Player> init4PlayersAndGetDealOrder() {
        List<Player> players = Arrays.asList(new Player(), new Player(), new Player(), new Player());

        mainData.setPlayers(players);

        List<Player> reOrder = Arrays.asList(players.get(2), players.get(3), players.get(0), players.get(1));

        mainData.setDealerIndex(2);

        return reOrder;
    }

    void initPlayersTilesManager() {
        mainData.setPlayersTilesManagers(new HashMap<>());

        for (Player player : mainData.getPlayers()) {
            mainData.getPlayersTilesManagers().put(player, new PlayerTileManagerImpl());
        }
    }
}