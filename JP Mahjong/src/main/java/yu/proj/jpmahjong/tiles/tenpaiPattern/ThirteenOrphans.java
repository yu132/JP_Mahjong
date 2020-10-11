package yu.proj.jpmahjong.tiles.tenpaiPattern;

import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: ThirteenOrphans  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class ThirteenOrphans implements TilePatternNode {

    private Tile[] tiles;

    private boolean wait13;

    private int[] tileToWin;

    public ThirteenOrphans(Tile[] tiles, boolean wait13, int[] tileToWin) {
        super();
        this.tiles     = tiles;
        this.wait13    = wait13;
        this.tileToWin = tileToWin;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public boolean isWait13() {
        return wait13;
    }

    public int[] getTileToWin() {
        return tileToWin;
    }

}
