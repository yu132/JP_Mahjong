package yu.proj.ref.tilePatternElement.exposedTile;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;
import yu.proj.ref.tilePatternElement.SameTypeMeld;

/**  
 * @ClassName: ExposedKanQuad  
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
public class ExposedKanQuad extends SameTypeMeld implements ExposedTile {

    private Tile specialTile;

    private ExposedKanQuad(Tile[] tiles, MeldSource src, Tile specialTile) {
        super(tiles, src);
        this.specialTile = specialTile;
    }

    public static ExposedKanQuad of(Tile[] tiles, MeldSource src, Tile specialTile) {

        assert src != MeldSource.SELF && tiles.length == 4;

        return new ExposedKanQuad(tiles, src, specialTile);
    }

    public TileType tileType() {
        return specialTile.getTileType();
    }

}
