package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;

/**  
 * @ClassName: HalfSequence  
 *
 * @Description: 双面听或者边张听 
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class Wait2Side implements NotCompleted {

    private Tile[] tiles;

    private boolean wait2;

    private int[] tileToWin;

    public Wait2Side(Tile[] tiles) {
        super();
        assert tiles.length == 2;
        this.tiles = tiles;
        this.wait2 = tiles[0].getIntId() != 1 && tiles[1].getIntId() != 9;
        if (wait2) {
            tileToWin = new int[2];
            int index = CountNum.numberTileIndex(tiles[0]);
            tileToWin[0] = index - 1;
            tileToWin[1] = index + 2;
        } else {
            tileToWin = new int[1];
            if (tiles[0].getIntId() == 1) {
                tileToWin[0] = tiles[0].getType().ordinal() * 9 + 3 - 1;
            } else {
                tileToWin[0] = tiles[0].getType().ordinal() * 9 + 7 - 1;
            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public boolean isWait2() {
        return wait2;
    }

    @Override
    public int[] getTileToWin() {
        return tileToWin;
    }
}
