package yu.proj.jpmahjong.player.operation.getTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.chi.Chi;

/**  
 * @ClassName: ChiOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class ChiOperation implements GetTileOperation {

    private Chi chi;

    private boolean useRedTile;

    public ChiOperation(Chi chi, boolean useRedTile) {
        super();
        this.chi        = chi;
        this.useRedTile = useRedTile;
    }

    public Chi getChi() {
        return chi;
    }

    public boolean isUseRedTile() {
        return useRedTile;
    }

    @Override
    public int index() {
        return 2;
    }

}
