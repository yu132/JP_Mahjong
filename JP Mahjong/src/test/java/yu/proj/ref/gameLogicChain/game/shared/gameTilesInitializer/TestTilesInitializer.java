package yu.proj.ref.gameLogicChain.game.shared.gameTilesInitializer;

import java.util.EnumMap;

import org.junit.Assert;
import org.junit.Test;

import cn.hutool.core.util.ArrayUtil;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestTilesInitializer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月13日  
 *  
 */
public class TestTilesInitializer {

    @Test
    public void test() {
        GameTilesInitializer tilesInit = new FourPlayerGameTilesInitializerImpl(1, 1, 1);
        Tile[]               tiles     = tilesInit.initializeTiles();
        Assert.assertEquals(136, tiles.length);

        EnumMap<TileType, Integer> map = new EnumMap<>(TileType.class);

        for (Tile tile : tiles) {
            map.put(tile.getTileType(), map.getOrDefault(tile.getTileType(), 0) + 1);
        }

        tileNumberOfTileTypeShouldBe(TileType.MAN_5_RED, 1, map);
        tileNumberOfTileTypeShouldBe(TileType.PIN_5_RED, 1, map);
        tileNumberOfTileTypeShouldBe(TileType.SOU_5_RED, 1, map);

        tileNumberOfTileTypeShouldBe(TileType.MAN_5, 3, map);
        tileNumberOfTileTypeShouldBe(TileType.PIN_5, 3, map);
        tileNumberOfTileTypeShouldBe(TileType.SOU_5, 3, map);

        TileType[] array = new TileType[] {TileType.MAN_5_RED, TileType.PIN_5_RED, TileType.SOU_5_RED, TileType.MAN_5,
            TileType.PIN_5, TileType.SOU_5, TileType.NONE};

        for (TileType type : TileType.values()) {
            if (!ArrayUtil.contains(array, type)) {
                tileNumberOfTileTypeShouldBe(type, 4, map);
            }
        }
    }

    private void tileNumberOfTileTypeShouldBe(TileType type, int num, EnumMap<TileType, Integer> map) {
        Assert.assertEquals(Integer.valueOf(num), map.getOrDefault(type, 0));
    }

}
