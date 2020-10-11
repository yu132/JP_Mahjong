package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.win.AnalyzeTsumo.Tsumo;

/**  
 * @ClassName: TsumoOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class TsumoOperation implements KanAndDiscardTileOperation {

    private Tsumo tsumo;

    public TsumoOperation(Tsumo tsumo) {
        super();
        this.tsumo = tsumo;
    }

    public Tsumo getTsumo() {
        return tsumo;
    }
}
