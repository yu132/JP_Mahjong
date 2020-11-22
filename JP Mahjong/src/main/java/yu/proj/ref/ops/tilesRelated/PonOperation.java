package yu.proj.ref.ops.tilesRelated;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.exposedTile.MeldSource;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: PonOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月9日  
 *  
 */

@Getter
@ToString
public class PonOperation extends AbstractPonExposedKanOperation {

    public PonOperation(Tile[] exposedTiles, Tile gainExposedTile, MeldSource src) {
        super(exposedTiles, gainExposedTile, src);

        assert exposedTiles.length == 2;
    }


}
