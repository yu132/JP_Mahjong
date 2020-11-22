package yu.proj.ref.tilePatternElement;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tilePatternElement.concealedTile.ConcealedTile;
import yu.proj.ref.tilePatternElement.exposedTile.ExposedTile;

/**  
 * @ClassName: Triplet  
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
public class Triplet extends SameTypeMeld implements ExposedTile, ConcealedTile {

    private Tile specialTile;

    private Triplet(Tile[] tiles, MeldSource src, Tile specialTile) {
        super(tiles, src);
        this.specialTile = specialTile;
    }

    public static Triplet of(Tile[] tiles, MeldSource src, Tile specialTile) {

        assert tiles.length == 3;

        return new Triplet(tiles, src, specialTile);
    }

}
