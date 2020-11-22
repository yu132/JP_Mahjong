package yu.proj.ref.ops.tilesRelated;

import lombok.Getter;
import yu.proj.ref.tile.Tile;



/**  
 * @ClassName: AbstractGainAndExposefSelfTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */

@Getter
public class AbstractGainAndExposefSelfTileOperation implements ExposedTileOperation, GainTileOperation {

    private Tile[] exposedTiles;
    private Tile gainTile;

    public AbstractGainAndExposefSelfTileOperation(Tile[] exposedTiles, Tile gainTile) {
        super();
        this.exposedTiles = exposedTiles;
        this.gainTile     = gainTile;
    }
}