package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: KanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年10月6日  
 *  
 */
public interface KanOperation extends KanAndDiscardTileOperation {

    void setKanDraw(Tile kanDraw);

    int getKanTile();

}
