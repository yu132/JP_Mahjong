package yu.proj.ref.ops;

import lombok.Getter;
import yu.proj.ref.exposedTile.Triplet;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: AddKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月10日  
 *  
 */

@Getter
public class AddKanOperation implements ExposedTileOperation, GainTileOperation {

    private Tile[] exposedTiles;

    private Triplet triplet;

    private Tile gainTile;

    public AddKanOperation(Tile[] exposedTiles, Triplet triplet, Tile gainTile) {
        super();

        assert checkArgIfTilesInExposedTilesAndTripletIsSameType(exposedTiles, triplet) && exposedTiles.length == 1;

        this.exposedTiles = exposedTiles;
        this.triplet      = triplet;
        this.gainTile     = gainTile;
    }

    private boolean checkArgIfTilesInExposedTilesAndTripletIsSameType(Tile[] exposedTiles, Triplet triplet) {
        return exposedTiles[0].getTileType() == triplet.getSpecialTile().getTileType();
    }
}
