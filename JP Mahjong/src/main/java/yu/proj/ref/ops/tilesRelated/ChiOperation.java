package yu.proj.ref.ops.tilesRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: ChiOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@ToString(callSuper = true)
public final class ChiOperation extends AbstractGainAndExposedAllTileOperation {

    public ChiOperation(Tile[] exposedTiles, Tile gainExposedTile) {
        super(exposedTiles, gainExposedTile);
    }

    @Override
    protected boolean checkArgForExposedTilesAndGainExposedTile(Tile[] exposedTiles, Tile gainExposedTile) {
        List<Tile> tiles = collectAndSortTiles(exposedTiles, gainExposedTile);
        return exposedTiles.length == 2 && checkIndex2IsNextOfIndex1(tiles, 0, 1)
            && checkIndex2IsNextOfIndex1(tiles, 1, 2);
    }

    private List<Tile> collectAndSortTiles(Tile[] exposedTiles, Tile gainExposedTile) {
        List<Tile> tiles = new ArrayList<>();
        tiles.add(exposedTiles[0]);
        tiles.add(exposedTiles[1]);
        tiles.add(gainExposedTile);
        Collections.sort(tiles);
        return tiles;
    }

    private boolean checkIndex2IsNextOfIndex1(List<Tile> tiles, int index1, int index2) {
        return tiles.get(index1).previousOf(tiles.get(index2));
    }
}
