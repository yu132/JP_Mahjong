package yu.proj.ref.tile;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Assert;
import org.junit.Test;

/**  
 * @ClassName: TestTileType  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月19日  
 *  
 */
public class TestTileType {

    @Test
    public void test() {
        Assert.assertTrue(MAN_1.previousOf(MAN_2));
        Assert.assertTrue(MAN_2.nextOf(MAN_1));
    }

    @Test
    public void testNormalTile() {
        Assert.assertEquals(MAN_1, MAN_1.getNormalType());
        Assert.assertEquals(MAN_5, MAN_5_RED.getNormalType());
    }

    @Test
    public void testGetRed() {
        Assert.assertEquals(MAN_5_RED, MAN_5.getRed());
    }

    @Test
    public void testSameNormalTileType() {
        assertTrue(MAN_5.sameNormalType(MAN_5));
        assertTrue(MAN_5_RED.sameNormalType(MAN_5_RED));
        assertTrue(MAN_5_RED.sameNormalType(MAN_5));
        assertTrue(MAN_5.sameNormalType(MAN_5_RED));

        assertTrue(NORTH.sameNormalType(NORTH));

        assertTrue(!NORTH.sameNormalType(SOU_1));
    }
}