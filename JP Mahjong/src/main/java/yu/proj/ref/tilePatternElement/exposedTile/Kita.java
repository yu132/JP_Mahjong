package yu.proj.ref.tilePatternElement.exposedTile;

import lombok.Getter;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Kita  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */

@Getter
public class Kita implements ExposedTile {

    Tile tile;

    private Kita(Tile tile) {
        super();
        this.tile = tile;
    }

    public static Kita of(Tile tile) {

        assert tile.getTileType() == TileType.NORTH;

        return new Kita(tile);
    }

}
