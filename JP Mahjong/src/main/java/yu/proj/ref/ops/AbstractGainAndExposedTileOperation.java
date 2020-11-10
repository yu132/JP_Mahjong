package yu.proj.ref.ops;

import lombok.Getter;
import yu.proj.ref.Tile;

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
public abstract class AbstractGainAndExposedTileOperation implements GainExposedTileOperation, ExposedTileOperation {

    private Tile[] exposedTiles;

    private Tile gainExposedTile;

    protected abstract boolean checkArgForExposedTilesAndGainExposedTile(Tile[] exposedTiles, Tile gainExposedTile);

    public AbstractGainAndExposedTileOperation(Tile[] exposedTiles, Tile gainExposedTile) {
        super();

        assert checkArgForExposedTilesAndGainExposedTile(exposedTiles, gainExposedTile);

        this.exposedTiles    = exposedTiles;
        this.gainExposedTile = gainExposedTile;
    }

}
