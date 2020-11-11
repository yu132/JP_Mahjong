package yu.proj.ref.ops;

import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: KitaOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */
public class KitaOperation extends AbstractGainAndExposefSelfTileOperation {

    public KitaOperation(Tile[] exposedTiles, Tile gainTile) {
        super(exposedTiles, gainTile);

        assert exposedTiles.length == 1 && exposedTiles[0].getTileType() == TileType.NORTH;
    }
}
