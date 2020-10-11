package yu.proj.jpmahjong.gamelogic.analyze.chi;

/**  
 * @ClassName: Chi  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月11日  
 *  
 */
public class Chi {

    private int tileToChi;

    private int[] indexOfTileInHand;

    private boolean hasNormalTile;

    private boolean hasRedTile;

    private int tendonReplace;

    public Chi(int tileToChi, int[] indexOfTileInHand, boolean hasNormalTile, boolean hasRedTile, int tendonReplace) {
        super();
        this.tileToChi         = tileToChi;
        this.indexOfTileInHand = indexOfTileInHand;
        this.hasNormalTile     = hasNormalTile;
        this.hasRedTile        = hasRedTile;
        this.tendonReplace     = tendonReplace;
    }

    public int getTileToChi() {
        return tileToChi;
    }

    public int[] getIndexOfTileInHand() {
        return indexOfTileInHand;
    }

    public boolean hasNormalTile() {
        return hasNormalTile;
    }

    public boolean hasRedTile() {
        return hasRedTile;
    }

    public int getTendonReplace() {
        return tendonReplace;
    }



}
