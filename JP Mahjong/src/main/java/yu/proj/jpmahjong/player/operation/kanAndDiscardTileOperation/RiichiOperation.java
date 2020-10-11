package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: RiichiOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class RiichiOperation implements KanAndDiscardTileOperation {

    private Tile tileToDiscard;

    public RiichiOperation(Tile tileToDiscard) {
        super();
        this.tileToDiscard = tileToDiscard;
    }

    public Tile getTileToDiscard() {
        return tileToDiscard;
    }
}
