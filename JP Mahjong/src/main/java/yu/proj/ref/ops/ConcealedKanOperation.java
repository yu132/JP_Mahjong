package yu.proj.ref.ops;

import yu.proj.ref.Tile;

/**  
 * @ClassName: ConcealedKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月10日  
 *  
 */
//
public class ConcealedKanOperation extends AbstractGainAndExposefSelfTileOperation {

    public ConcealedKanOperation(Tile[] exposedTiles, Tile gainTile) {
        super(exposedTiles, gainTile);

        assert checkArgIfTilesInExposedTilesIsSameType(exposedTiles) && exposedTiles.length == 4;
    }

    private boolean checkArgIfTilesInExposedTilesIsSameType(Tile[] exposedTiles) {
        for (int i = 1; i < exposedTiles.length; ++i) {
            if (exposedTiles[0].getTileType() != exposedTiles[i].getTileType()) {
                return false;
            }
        }
        return true;
    }

}
