package yu.proj.jpmahjong.player.operation.getTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.ExposedKan;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: KanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class ExposedKanOperation implements GetTileOperation {

    private ExposedKan kan;

    private Tile kanDraw;

    public ExposedKanOperation(ExposedKan kan) {
        super();
        this.kan = kan;
    }

    public ExposedKan getKan() {
        return kan;
    }

    public Tile getKanDraw() {
        return kanDraw;
    }

    public void setKanDraw(Tile kanDraw) {
        this.kanDraw = kanDraw;
    }

    @Override
    public int index() {
        return 4;
    }

}
