package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.Kita;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: KitaOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class KitaOperation implements KanOperation {

    private Kita kita;

    private Tile kanDraw;

    public KitaOperation(Kita kita) {
        super();
        this.kita = kita;
    }

    public Kita getKita() {
        return kita;
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
        return CountNum.N;
    }
}
