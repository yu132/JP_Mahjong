package yu.proj.ref.tilePatternElement.exposedTile;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;
import yu.proj.ref.tilePatternElement.SameTypeMeld;

/**  
 * @ClassName: ConcealedKanQuad  
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
public class ConcealedKanQuad extends SameTypeMeld implements ExposedTile {

    private ConcealedKanQuad(Tile[] tiles, MeldSource src) {
        super(tiles, src);
    }

    public static ConcealedKanQuad of(Tile[] tiles, MeldSource src) {

        assert src == MeldSource.SELF && tiles.length == 4;

        return new ConcealedKanQuad(tiles, src);
    }

    public TileType tileType() {
        return getTiles()[0].getTileType();
    }

}
