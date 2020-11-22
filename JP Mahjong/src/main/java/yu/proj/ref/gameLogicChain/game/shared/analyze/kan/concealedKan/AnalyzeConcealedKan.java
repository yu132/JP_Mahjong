package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.concealedKan;

import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.kan.AnalyzeKan;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;

/**  
 * @ClassName: AnalyzeConcealedKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class AnalyzeConcealedKan extends AnalyzeKan {

    public List<ConcealedKanable> analyze(PlayerTileManager playerTileManager) {
        return analyze(playerTileManager, (tileType, sum) -> ConcealedKanable.of(tileType, sum));
    }

}
