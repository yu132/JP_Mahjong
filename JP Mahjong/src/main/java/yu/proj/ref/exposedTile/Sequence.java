package yu.proj.ref.exposedTile;

import cn.hutool.core.util.ArrayUtil;
import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: Sequence  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@ToString
public class Sequence extends Meld {

    private Tile specialTile;

    private Sequence(Tile[] tiles, MeldSource src, Tile specialTile) {
        super(tiles, src);
        this.specialTile = specialTile;
    }

    public static Sequence of(Tile[] tiles, MeldSource src, Tile specialTile) {

        assert src == MeldSource.LAST_PLAYER && tiles.length == 3;

        return new Sequence(tiles, src, specialTile);
    }

    public static boolean checkTilesOrder(Tile[] tiles) {
        return checkIndex2IsNextOfIndex1(tiles, 0, 1) && checkIndex2IsNextOfIndex1(tiles, 1, 2);
    }

    private static boolean checkIndex2IsNextOfIndex1(Tile[] tiles, int index1, int index2) {
        return ArrayUtil.contains(tiles[index1].getNextTile(), tiles[index2].getTileType());
    }
}
