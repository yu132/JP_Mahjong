package yu.proj.ref.gameLogicChain.game.shared.analyze.kan;

import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: AnalyzeKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class AnalyzeKan {

    protected <T> List<T> analyze(PlayerTileManager playerTileManager,
        BiFunction<TileType, Integer, List<T>> kanableFactory) {

        assert playerTileManager != null;

        List<T> kanables = new ArrayList<>();

        analyzeKan(playerTileManager, kanables, kanableFactory);

        return kanables;
    }

    private <T> void analyzeKan(PlayerTileManager playerTileManager, List<T> kanables,
        BiFunction<TileType, Integer, List<T>> kanableFactory) {
        forEachNormalTileType((tileType) -> {// 对于所有牌型中的普通牌型进行遍历

            // 杠牌的分析不区分是不是红宝牌，因为手上必须有3张，并且3张都必须用作杠，因此是不是红宝牌没什么关系
            int red    = playerTileManager.countInHand(tileType.getRed());
            int normal = playerTileManager.countInHand(tileType);

            // 分析时也只需要一种牌的总量，而不区分普通牌和红宝牌的数量
            int sum    = normal + red;

            // 此处依据不同的kanableFactory生产出不同的kanable，因为判断条件是在工厂方法中的，
            // 因此此处的逻辑是一致的，对于大明杠和暗杠都是如此，但是不能完成加杠的判断
            kanables.addAll(kanableFactory.apply(tileType, sum));
        });
    }

}
