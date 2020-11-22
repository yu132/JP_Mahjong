package yu.proj.ref.gameLogicChain.game.dealTiles;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import yu.proj.ref.player.Player;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.utils.KeyValuePair;

/**  
 * @ClassName: DealTilesTaskSharedData  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */

@Getter
@Setter
public class DealTilesTaskSharedData {

    private List<KeyValuePair<Player, List<Tile>>> playersTiles;// 在 GetTilesToDeal 中创建，在 DealTilesToPlayer 中使用

}
