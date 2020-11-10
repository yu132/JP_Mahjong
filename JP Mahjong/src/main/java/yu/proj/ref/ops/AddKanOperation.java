package yu.proj.ref.ops;

import lombok.Getter;
import yu.proj.ref.Tile;
import yu.proj.ref.meld.Triplet;

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
public class AddKanOperation implements ExposedTileOperation {

    private Tile[] exposedTiles;

    private Triplet triplet;

    public AddKanOperation(Tile[] exposedTiles, Triplet triplet) {
        super();

        assert checkArgIfTilesInExposedTilesAndTripletIsSameType(exposedTiles, triplet) && exposedTiles.length == 1;

        this.exposedTiles = exposedTiles;
        this.triplet      = triplet;
    }

    private boolean checkArgIfTilesInExposedTilesAndTripletIsSameType(Tile[] exposedTiles, Triplet triplet) {
        return exposedTiles[0].getTileType() == triplet.getSpecialTile().getTileType();
    }
}
