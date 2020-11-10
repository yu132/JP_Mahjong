package yu.proj.ref.ops;

import lombok.Getter;
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

@Getter
public class ConcealedKanOperation implements ExposedTileOperation {

    private Tile[] exposedTiles;

    public ConcealedKanOperation(Tile[] exposedTiles) {
        super();

        assert checkArgIfTilesInExposedTilesIsSameType(exposedTiles) && exposedTiles.length == 4;

        this.exposedTiles = exposedTiles;
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
