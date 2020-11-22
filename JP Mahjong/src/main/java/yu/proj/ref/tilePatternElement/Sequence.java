package yu.proj.ref.tilePatternElement;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tilePatternElement.concealedTile.ConcealedTile;
import yu.proj.ref.tilePatternElement.exposedTile.ExposedTile;

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
public class Sequence extends Meld implements ExposedTile, ConcealedTile {

    private Tile specialTile;

    private Sequence(Tile[] tiles, MeldSource src, Tile specialTile) {
        super(tiles, src);
        this.specialTile = specialTile;
    }

    public static Sequence of(Tile[] tiles, MeldSource src, Tile specialTile) {

        assert (src == MeldSource.LAST_PLAYER || src == MeldSource.SELF) && tiles.length == 3;

        return new Sequence(tiles, src, specialTile);
    }

    public static boolean checkTilesOrder(Tile[] tiles) {
        return checkIndex1IsPreviousOfIndex2(tiles, 0, 1) && checkIndex1IsPreviousOfIndex2(tiles, 1, 2);
    }

    private static boolean checkIndex1IsPreviousOfIndex2(Tile[] tiles, int index1, int index2) {
        return tiles[index1].previousOf(tiles[index2]);
    }
}
