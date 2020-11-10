package yu.proj.ref.ops;

import yu.proj.ref.Tile;
import yu.proj.ref.meld.MeldSource;

/**  
 * @ClassName: ExposedKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月10日  
 *  
 */
public class ExposedKanOperation extends AbstractPonExposedKanOperation {

    public ExposedKanOperation(Tile[] exposedTiles, Tile gainExposedTile, MeldSource src) {
        super(exposedTiles, gainExposedTile, src);

        assert exposedTiles.length == 3;
    }

}
