package yu.proj.ref.ops.tilesRelated;

import lombok.Getter;
import yu.proj.ref.exposedTile.MeldSource;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: ExposedKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月10日  
 *  
 */

@Getter
public class ExposedKanOperation extends AbstractPonExposedKanOperation implements GainTileOperation {

    private Tile gainTile;

    public ExposedKanOperation(Tile[] exposedTiles, Tile gainExposedTile, Tile gainTile, MeldSource src) {
        super(exposedTiles, gainExposedTile, src);

        assert exposedTiles.length == 3;

        this.gainTile = gainTile;
    }

}
