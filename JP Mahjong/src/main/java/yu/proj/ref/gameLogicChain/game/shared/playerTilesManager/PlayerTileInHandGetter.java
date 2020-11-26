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

    /**
     * @Title: claim  
     * @Description: 从手牌（没有鸣牌的牌）中取出一张牌，取出后，如果不放回，那么就取不出这张牌了
     */
    Tile claim(TileType tileType);

    /**
     * @Title: reclaim  
     * @Description: 将手牌放回，只有当取出过手牌时，才能放回一张牌，放回后，又能重新取出这张牌
     */
    void reclaim(TileType tileType);

}