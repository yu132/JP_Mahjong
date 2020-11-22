package yu.proj.ref.gameLogicChain.game.shared.analyze.draw.nineDifferntTerminalsandHonorsDraw;

import java.util.Collections;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;

/**  
 * @ClassName: NineDifferntTerminalsandHonorsDrawAnalyze  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class AnalyzeNineDifferntTerminalsandHonorsDraw {

    public List<NineDifferntTerminalsandHonorsDrawable> analyze(PlayerTileManager playerTileManager) {
        TilesCounterUtilForPatternAnalyze tilesNumAnalyzer = new TilesCounterUtilForPatternAnalyze(playerTileManager);
        if (tilesNumAnalyzer.differentTerminalsAndHonors() >= 9) {
            return Collections.singletonList(NineDifferntTerminalsandHonorsDrawable.of());
        }
        return Collections.emptyList();
    }

}
