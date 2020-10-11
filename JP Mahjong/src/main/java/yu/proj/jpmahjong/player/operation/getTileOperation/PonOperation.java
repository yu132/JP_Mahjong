package yu.proj.jpmahjong.player.operation.getTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.Pon;

/**  
 * @ClassName: PonOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class PonOperation implements GetTileOperation {

    private Pon pon;

    private boolean useRedTile;

    private int useRedTileNumber;

    public PonOperation(Pon pon, boolean useRedTile, int useRedTileNumber) {
        super();
        this.pon              = pon;
        this.useRedTile       = useRedTile;
        this.useRedTileNumber = useRedTileNumber;
    }

    public Pon getPon() {
        return pon;
    }

    public boolean isUseRedTile() {
        return useRedTile;
    }

    public int getUseRedTileNumber() {
        return useRedTileNumber;
    }

    @Override
    public int index() {
        return 3;
    }
}
