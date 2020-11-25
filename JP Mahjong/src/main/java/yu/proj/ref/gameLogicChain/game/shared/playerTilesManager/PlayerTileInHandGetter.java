package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;


/**  
 * @ClassName: PlayerTileInHandGetter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */
public interface PlayerTileInHandGetter {

    Tile claim(TileType tileType);

    void reclaim(TileType tileType);

}