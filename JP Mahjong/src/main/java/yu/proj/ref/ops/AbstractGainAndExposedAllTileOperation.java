package yu.proj.ref.ops;

import lombok.Getter;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: AbstractGainAndExposedTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月9日  
 *  
 */

@Getter
public abstract class AbstractGainAndExposedAllTileOperation implements GainExposedTileOperation, ExposedTileOperation {

    private Tile[] exposedTiles;

    private Tile gainExposedTile;

    protected abstract boolean checkArgForExposedTilesAndGainExposedTile(Tile[] exposedTiles, Tile gainExposedTile);

    public AbstractGainAndExposedAllTileOperation(Tile[] exposedTiles, Tile gainExposedTile) {
        super();

        assert checkArgForExposedTilesAndGainExposedTile(exposedTiles, gainExposedTile);

        this.exposedTiles    = exposedTiles;
        this.gainExposedTile = gainExposedTile;
    }

}
