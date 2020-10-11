package yu.proj.jpmahjong.testUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.jpmahjong.define.Numbers;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: ManageHand  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月9日  
 *  
 */
public class ManageHand {

    public static void manageHand(List<Tile>[] statisticalNum, Tile tile) {
        if (tile.getIntId() != 0) {
            int index = tile.getType().ordinal() * 10 + tile.getIntId();
            if (tile.isRed()) {
                index -= 5;
            }
            statisticalNum[index].add(tile);
            Collections.sort(statisticalNum[index]);
        } else {
            int index = 30 + tile.getType().ordinal() - 3;
            statisticalNum[index].add(tile);
            Collections.sort(statisticalNum[index]);
        }
    }

    @Test
    public void test() {
        @SuppressWarnings("unchecked")
        List<Tile>[] statisticalNum = new List[Numbers.NUMBER_OF_DIFFERENT_TILE + 3];

        for (int i = 0; i < statisticalNum.length; ++i) {
            statisticalNum[i] = new ArrayList<>();
        }

        helper(statisticalNum, "m", 0);
        helper(statisticalNum, "p", 1);
        helper(statisticalNum, "s", 2);

        helper2(statisticalNum, "东", 0);
        helper2(statisticalNum, "南", 1);
        helper2(statisticalNum, "西", 2);
        helper2(statisticalNum, "北", 3);
        helper2(statisticalNum, "白", 4);
        helper2(statisticalNum, "发", 5);
        helper2(statisticalNum, "中", 6);
    }

    private void helper(List<Tile>[] statisticalNum, String type, int x) {
        for (int j = 4; j >= 1; --j) {
            for (int i = 0; i < 10; ++i) {
                Tile tile = StringToTile.str2Tile(i + type + "[" + j + "]");
                manageHand(statisticalNum, tile);
                Assert.assertEquals(statisticalNum[x * 10 + i].get(0), tile);
            }
        }
    }

    private void helper2(List<Tile>[] statisticalNum, String type, int x) {
        for (int j = 4; j >= 1; --j) {
            Tile tile = StringToTile.str2Tile(type + "[" + j + "]");
            manageHand(statisticalNum, tile);
            Assert.assertEquals(statisticalNum[30 + x].get(0), tile);
        }
    }

}
