package yu.proj.jpmahjong.define.tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.initializer.RedFiveInitializer;

/**  
 * @ClassName: TestTile  
 *
 * @Description: 测试牌的排序能力
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class TestTile {

    @Test
    public void test() {

        RedFiveInitializer redInit = new RedFiveInitializer(1, 1, 1);
        Tile[]             tiles   = redInit.init();

        ArrayList<Tile>    list    = new ArrayList<>();

        for (Tile each : tiles) {
            list.add(each);
        }

        Collections.shuffle(list);

        Tile[] copy = list.toArray(new Tile[tiles.length]);

        Assert.assertFalse(Arrays.deepEquals(copy, tiles));

        Arrays.sort(copy);

        Assert.assertArrayEquals(tiles, copy);
    }

}
