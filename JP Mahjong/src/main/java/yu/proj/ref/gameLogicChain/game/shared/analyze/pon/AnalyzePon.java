package yu.proj.ref.gameLogicChain.game.shared.analyze.pon;

import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;

/**  
 * @ClassName: AbalyzePon  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class AnalyzePon {

    public List<Ponable> analyze(PlayerTileManager playerTileManager) {
        assert playerTileManager != null;

        List<Ponable> ponables = new ArrayList<>();

        analyzePon(playerTileManager, ponables);

        return ponables;
    }

    private void analyzePon(PlayerTileManager playerTileManager, List<Ponable> ponables) {

        forEachNormalTileType((tileType) -> {// 对于所有牌型中的普通牌型进行遍历

            // 其他类型的牌如果调用getRed会返回None，而None的计数一定为0，因此结果是正确的
            int red    = playerTileManager.countInHand(tileType.getRed());
            int normal = playerTileManager.countInHand(tileType);

            ponables.addAll(Ponable.of(tileType, normal, red));
        });
    }
}
