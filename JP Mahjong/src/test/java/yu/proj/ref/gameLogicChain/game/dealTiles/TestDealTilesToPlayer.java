package yu.proj.ref.gameLogicChain.game.dealTiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yu.proj.ref.player.Player;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.utils.KeyValuePair;

/**  
 * @ClassName: TestDealTilesToPlayer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月15日  
 *  
 */
public class TestDealTilesToPlayer {

    private DealTilesToPlayer logic;

    private TestDealTilesData data = new TestDealTilesData();

    @Before
    public void before() {
        logic = new DealTilesToPlayer(data.mainData, data.taskData);
    }

    @Test
    public void test4Player() {
        data.init4PlayersAndGetDealOrder();

        data.initPlayersTilesManager();

        doPreviousLogic();

        logic.doStepDuty();

        // 检查发的牌是在发牌完成后，玩家手中牌的数量是否正确
        checkTilesNum();
    }

    private void checkTilesNum() {
        for (KeyValuePair<Player, List<Tile>> playerAdnTiles : data.taskData.getPlayersTiles()) {
            Map<TileType, Integer> map    = new HashMap<>();

            List<Tile>             tiles  = playerAdnTiles.getValue();

            Player                 player = playerAdnTiles.getKey();

            count(map, tiles);

            checkNum(map, player);
        }
    }

    private void checkNum(Map<TileType, Integer> map, Player player) {
        for (TileType type : TileType.values()) {
            int numInTilesList         = map.getOrDefault(type, 0);
            int numInPlayerTileManager = data.mainData.getPlayersTilesManager(player).countInHand(type);
            Assert.assertEquals(numInTilesList, numInPlayerTileManager);
        }
    }

    private void count(Map<TileType, Integer> map, List<Tile> tiles) {
        for (Tile tile : tiles) {
            map.put(tile.getTileType(), map.getOrDefault(tile.getTileType(), 0) + 1);
        }
    }

    private void doPreviousLogic() {
        GetTilesToDeal lastLogic;
        lastLogic = new GetTilesToDeal(data.mainData, data.taskData);
        lastLogic.doStepDuty();
    }

    @Test
    public void testNextTaskStep() {
        Assert.assertTrue(logic.getNextTaskStep() instanceof AnalyzeAllPlayerTilesAfterDraw13Tiles);
    }

}
