package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.ConcealedKan;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: ConcealedKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class ConcealedKanOperation implements KanOperation {

    private ConcealedKan concealedKan;

    private Tile kanDraw;

    public ConcealedKanOperation(ConcealedKan concealedKan) {
        super();
        this.concealedKan = concealedKan;
    }

    public ConcealedKan getConcealedKan() {
        return concealedKan;
    }

    public Tile getKanDraw() {
        return kanDraw;
    }

    @Override
    public void setKanDraw(Tile kanDraw) {
        this.kanDraw = kanDraw;
    }

    @Override
    public int getKanTile() {
        return concealedKan.getTileIndex();
    }



}
