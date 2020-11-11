package yu.proj.ref.ops;

import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: ExposedTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */
public interface ExposedTileOperation extends Operation {

    Tile[] getExposedTiles();

}
