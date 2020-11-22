package yu.proj.ref.gameLogicChain.game.dealTiles;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yu.proj.ref.player.Player;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.utils.KeyValuePair;

/**  
 * @ClassName: TestGetTilesToDeal  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */
public class TestGetTilesToDeal {

    private GetTilesToDeal logic;

    private TestDealTilesData data = new TestDealTilesData();

    @Before
    public void before() {
        logic = new GetTilesToDeal(data.mainData, data.taskData);
    }

    @Test
    public void test4Player() {

        List<Player> reOrder = data.init4PlayersAndGetDealOrder();

        logic.doStepDuty();

        List<KeyValuePair<Player, List<Tile>>> playersTiles = data.taskData.getPlayersTiles();

        checkPlayersOrder(reOrder, playersTiles);
        checkTop12TilesOfEachPlayer(playersTiles);
        check13thTileOfEachPlayer(playersTiles);
    }

    // 检查玩家的顺序是否正确
    private void checkPlayersOrder(List<Player> reOrder, List<KeyValuePair<Player, List<Tile>>> list) {
        for (int i = 0; i < reOrder.size(); ++i) {
            KeyValuePair<Player, List<Tile>> pair   = list.get(i);
            Player                           player = reOrder.get(i);

            Assert.assertEquals(player, pair.getKey());
        }
    }

    // 检查发牌顺序是否正确，检查前面4张4张摸的部分
    private void checkTop12TilesOfEachPlayer(List<KeyValuePair<Player, List<Tile>>> list) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    Assert.assertEquals(data.tiles[i * 16 + j * 4 + k], list.get(j).getValue().get(i * 4 + k));
                }
            }
        }
    }

    // 检查第13张的部分
    private void check13thTileOfEachPlayer(List<KeyValuePair<Player, List<Tile>>> list) {
        Assert.assertEquals(data.tiles[4 * 12], list.get(0).getValue().get(12));
        Assert.assertEquals(data.tiles[4 * 12 + 1], list.get(1).getValue().get(12));
        Assert.assertEquals(data.tiles[4 * 12 + 2], list.get(2).getValue().get(12));
        Assert.assertEquals(data.tiles[4 * 12 + 3], list.get(3).getValue().get(12));
    }

    @Test
    public void testNextTaskStep() {
        Assert.assertTrue(logic.getNextTaskStep() instanceof DealTilesToPlayer);
    }

}
