package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.TestUtils;
import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: TestPlayerTileGetter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */
public class TestPlayerTilenHandGetter {

    private TestAnalyzeData data = new TestAnalyzeData();

    private PlayerTileInHandGetter getter =
        new PlayerTileInHandGetterImpl((PlayerTileManagerImpl)data.playerTileManager);

    @Test
    public void test() {
        data.draw(MAN_1);

        Tile tile = getter.claim(MAN_1);

        assertEquals(MAN_1, tile.getTileType());
    }

    @Test
    public void testSame() {
        data.draw(MAN_1, MAN_1);

        Tile tile  = getter.claim(MAN_1);
        Tile tile1 = getter.claim(MAN_1);

        assertEquals(MAN_1, tile.getTileType());
        assertEquals(MAN_1, tile1.getTileType());

        assertNotEquals(tile, tile1);
    }

    @Test
    public void testReuse() {
        data.draw(MAN_1);

        Tile tile = getter.claim(MAN_1);

        getter.reclaim(MAN_1);

        Tile tile1 = getter.claim(MAN_1);

        assertEquals(MAN_1, tile.getTileType());
        assertEquals(MAN_1, tile1.getTileType());

        assertEquals(tile, tile1);
    }

    @Test
    public void testException() {
        TestUtils.expectExceptionOrError(() -> {
            getter.claim(MAN_1);
        });
        data.draw(MAN_1);
        getter.claim(MAN_1);
        TestUtils.expectExceptionOrError(() -> {
            getter.claim(MAN_1);
        });
    }

    @Test
    public void testRed() {
        data.draw(MAN_5_RED);

        Tile tile = getter.claim(MAN_5);

        assertEquals(MAN_5_RED, tile.getTileType());
    }

    @Test
    public void testRedReUse() {
        data.draw(MAN_5_RED);

        Tile tile = getter.claim(MAN_5);

        getter.reclaim(MAN_5);

        Tile tile2 = getter.claim(MAN_5);

        assertEquals(MAN_5_RED, tile.getTileType());
        assertEquals(tile, tile2);
    }

}
