package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;

/**  
 * @ClassName: Pair  
 *
 * @Description: 对子，一定不可能是副露，不存在来源问题
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class Pair implements Completed, NotCompleted {

    private Tile[] tiles;

    private int[] tileToWin = new int[1];

    public Pair(Tile[] tiles) {
        super();
        assert tiles.length == 2;
        this.tiles   = tiles;
        tileToWin[0] = CountNum.getTileIndex(tiles[0]);
    }

    public Tile[] getTiles() {
        return tiles;
    }

    @Override
    public int[] getTileToWin() {
        return tileToWin;
    }

}
