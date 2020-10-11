package yu.proj.jpmahjong.player.opInterface;

import yu.proj.jpmahjong.player.TileSource;
import yu.proj.jpmahjong.player.operation.Operation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperationChoice;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperationChoice;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperationChoice;

/**  
 * @ClassName: UserOperationInterface  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年10月5日  
 *  
 */
public interface UserOperationInterface {

    GetTileOperation getUserGetTileOperationChoice(GetTileOperationChoice getTileOperationChoice);

    KanAndDiscardTileOperation
        getKanAndDiscardTileOperationChoice(KanAndDiscardTileOperationChoice kanAndDiscardTileOperationChoice);

    RobbingKanOperation getRobbingKanOperationChoice(RobbingKanOperationChoice kanTile);

    void announceOperation(TileSource player, Operation op);

}
