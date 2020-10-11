package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: DiscardTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class DiscardTileOperation implements KanAndDiscardTileOperation {

    private Tile tileToDiscard;

    public DiscardTileOperation(Tile tileToDiscard) {
        super();
        this.tileToDiscard = tileToDiscard;
    }

    public Tile getTileToDiscard() {
        return tileToDiscard;
    }

}
