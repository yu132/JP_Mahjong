package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.AddKan;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: AddKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class AddKanOperation implements KanOperation {

    private AddKan addKan;

    private Tile kanDraw;

    public AddKanOperation(AddKan addKan) {
        super();
        this.addKan = addKan;
    }

    public AddKan getAddKan() {
        return addKan;
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
        return addKan.getTileToKan();
    }

}
