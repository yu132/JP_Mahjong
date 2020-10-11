package yu.proj.jpmahjong.player.operation.getTileOperation;

import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;

/**  
 * @ClassName: RonOpeartion  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class RonOperation implements GetTileOperation {

    private TenpaiAnsNode tenpaiAnsNode;

    public RonOperation(TenpaiAnsNode tenpaiAnsNode) {
        super();
        this.tenpaiAnsNode = tenpaiAnsNode;
    }

    public TenpaiAnsNode getTenpaiAnsNode() {
        return tenpaiAnsNode;
    }


    @Override
    public int index() {
        return 5;
    }
}
