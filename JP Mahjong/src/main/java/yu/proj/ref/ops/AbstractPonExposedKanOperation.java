package yu.proj.ref.ops;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.Tile;
import yu.proj.ref.meld.MeldSource;

/**  
 * @ClassName: AbstractPonKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月10日  
 *  
 */
@Getter
@ToString(callSuper = true)
public abstract class AbstractPonExposedKanOperation extends AbstractGainAndExposedTileOperation {

    private MeldSource src;

    public AbstractPonExposedKanOperation(Tile[] exposedTiles, Tile gainExposedTile, MeldSource src) {
        super(exposedTiles, gainExposedTile);

        assert src != MeldSource.SELF;

        this.src = src;
    }

    @Override
    protected boolean checkArgForExposedTilesAndGainExposedTile(Tile[] exposedTiles, Tile gainExposedTile) {
        boolean allSameType = true;

        for (Tile tile : exposedTiles) {
            if (tile.getTileType() != gainExposedTile.getTileType()) {
                allSameType = false;
            }
        }

        return allSameType;
    }

}
