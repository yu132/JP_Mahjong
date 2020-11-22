package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.addKan;

import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;

/**  
 * @ClassName: AnalyzeAddKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class AnalyzeAddKan {

    public List<AddKanable> analyze(PlayerTileManager playerTileManager) {
        List<AddKanable> addKanables = new ArrayList<>();

        analyzeAddKan(playerTileManager, addKanables);

        return addKanables;
    }

    private void analyzeAddKan(PlayerTileManager playerTileManager, List<AddKanable> addKanables) {

        forEachNormalTileType((tileType) -> {

            boolean hasTriplet             = playerTileManager.getPlayerExposedTilesManager().containTriplet(tileType);
            boolean hasAnotherSameTypeTile = playerTileManager.countNormalAndRedInHand(tileType) == 1;

            if (hasTriplet && hasAnotherSameTypeTile) {
                addKanables.addAll(AddKanable.of(tileType));
            }
        });
    }

}
