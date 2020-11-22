package yu.proj.ref.ops.tilesRelated;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tilePatternElement.exposedTile.MeldSource;

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
public abstract class AbstractPonExposedKanOperation extends AbstractGainAndExposedAllTileOperation {

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
            if (!tile.sameNormalType(gainExposedTile)) {
                allSameType = false;
            }
        }

        return allSameType;
    }

}
