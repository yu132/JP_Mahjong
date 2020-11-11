package yu.proj.ref.ops;

import yu.proj.ref.Tile;

/**  
 * @ClassName: DiscardTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */
public interface DiscardTileOperation extends Operation {

    Tile getDiscardTile();

}
