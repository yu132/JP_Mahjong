package yu.proj.jpmahjong.testUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.tiles.initializer.RedFiveInitializer;

/**  
 * @ClassName: StringToTile  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月9日  
 *  
 */
public class StringToTile {

    public static Tile str2Tile(String str) {
        char z = str.charAt(0);
        if (z >= '0' && z <= '9') {// 序数牌
            String   strId = str.substring(1, 2);
            TileType type  = TileType.str2TileType(strId);
            if (z != '0') {
                int intId = z - '0';
                return new Tile(type, strId, intId, str.charAt(3) - '0', false);
            }
            return new Tile(type, strId, 5, str.charAt(3) - '0', true);
        } else {
            String   strId = String.valueOf(z);
            TileType type  = TileType.str2TileType(strId);
            return new Tile(type, strId, 0, str.charAt(2) - '0', false);
        }
    }

    public static List<Tile> getList(String... strings) {
        List<Tile> list = new ArrayList<>();
        for (String each : strings) {
            list.add(StringToTile.str2Tile(each));
        }
        return list;
    }

    public static Tile[] getArray(String... strings) {
        Tile[] tile = new Tile[strings.length];
        for (int i = 0; i < strings.length; ++i) {
            tile[i] = StringToTile.str2Tile(strings[i]);
        }
        return tile;
    }

    @Test
    public void test() {
        RedFiveInitializer redInit = new RedFiveInitializer(1, 1, 1);
        Tile[]             tiles   = redInit.init();

        for (Tile tile : tiles) {
            Tile newTile = StringToTile.str2Tile(tile.toString());
            // System.out.println(newTile);
            Assert.assertTrue(tile + " " + newTile, tile.equals(newTile));
        }
    }

}
