package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;

/**  
 * @ClassName: WaitMiddle  
 *
 * @Description: 崁张听
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class WaitMiddle implements NotCompleted {

    private Tile[] tiles;

    private int[] tileToWin = new int[1];

    public WaitMiddle(Tile[] tiles) {
        super();
        assert tiles.length == 2;
        this.tiles   = tiles;
        tileToWin[0] = CountNum.numberTileIndex(tiles[0]) + 1;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    @Override
    public int[] getTileToWin() {
        return tileToWin;
    }

}
