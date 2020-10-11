package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;

/**  
 * @ClassName: Singleton  
 *
 * @Description: 单骑，没有来源问题
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class Singleton implements NotCompleted {

    private Tile tile;

    private int[] tileToWin = new int[1];

    public Singleton(Tile tile) {
        super();
        this.tile    = tile;
        tileToWin[0] = CountNum.getTileIndex(tile);
    }


    public Tile getTile() {
        return tile;
    }

    @Override
    public int[] getTileToWin() {
        return tileToWin;
    }

}
