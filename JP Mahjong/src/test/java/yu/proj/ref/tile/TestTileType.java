package yu.proj.ref.tile;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Test
    public void testNext() {
        List<TileType> list = getNormalTileType();
        AtomicInteger index = new AtomicInteger(0);
        forEachNormalTileType((tileType) -> {
            assertEquals(list.get(index.getAndIncrement()), tileType);
        });
        assertEquals(34, index.intValue());
    }

    @Test
    public void testForEachNormalTile() {
        List<TileType> list = getNormalTileType();
        for (int i = 0; i < list.size() - 1; ++i) {
            assertEquals(list.get(i + 1), next(list.get(i)));
        }
    }

    private List<TileType> getNormalTileType() {
        List<TileType> list = new ArrayList<>(Arrays.asList(TileType.values()));
        list.remove(NONE);
        list.remove(MAN_5_RED);
        list.remove(PIN_5_RED);
        list.remove(SOU_5_RED);
        Collections.sort(list, (x, y) -> x.getOrder() - y.getOrder());

        return list;
    }
}