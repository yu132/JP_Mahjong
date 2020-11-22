package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.exposedKan;

import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.kan.AnalyzeKan;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;

/**  
 * @ClassName: AnalyzeExposedKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class AnalyzeExposedKan extends AnalyzeKan {

    public List<ExposedKanable> analyze(PlayerTileManager playerTileManager) {
        return analyze(playerTileManager, (tileType, sum) -> ExposedKanable.of(tileType, sum));
    }

}
