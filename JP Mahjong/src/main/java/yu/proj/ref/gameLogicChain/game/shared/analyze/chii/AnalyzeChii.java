package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.chii.ChiiRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: AnalyzeChii  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
@Getter
@AllArgsConstructor
public class AnalyzeChii {

    private GameRule gameRule;

    public List<Chiiable> analyze(PlayerTileManager playerTileManager) {

        assert playerTileManager != null;

        if (notAllowToChii()) {
            return Collections.emptyList();
        }

        List<Chiiable> chiiables = new ArrayList<>();

        analyzeChii2Side(playerTileManager, chiiables);
        analyzeChiiMiddle(playerTileManager, chiiables);

        return chiiables;
    }

    private boolean notAllowToChii() {
        return gameRule.chiiRule == ChiiRule.DISABLE_CHII;
    }

    // 坎张吃分析顺序是1 2 3 4 5 5r 6 7。8和9是不分析的，因为8没有后两张牌了
    private void analyzeChiiMiddle(PlayerTileManager playerTileManager, List<Chiiable> chiiables) {
        analyzeChiiMiddle(MAN_1, MAN_8, playerTileManager, chiiables);
        analyzeChiiMiddle(PIN_1, PIN_8, playerTileManager, chiiables);
        analyzeChiiMiddle(SOU_1, SOU_8, playerTileManager, chiiables);
    }

    // 顺序吃的分析顺序是1 2 3 4 5 5r 6 7 8。9是不分析的，因为9没有下一张牌
    private void analyzeChii2Side(PlayerTileManager playerTileManager, List<Chiiable> chiiables) {
        analyzeChii2Side(MAN_1, MAN_9, playerTileManager, chiiables);
        analyzeChii2Side(PIN_1, PIN_9, playerTileManager, chiiables);
        analyzeChii2Side(SOU_1, SOU_9, playerTileManager, chiiables);
    }

    private void analyzeChiiMiddle(TileType start, TileType end, PlayerTileManager playerTileManager,
        List<Chiiable> chiiables) {

        // 对于坎张吃，upper应当是下张牌的下张牌
        Function<TileType, List<TileType>>             howToGetUpper   = (now) -> now.nextNormalTile().getNextTiles();

        // ChiiMiddle工厂，分析坎张吃的lower和upper
        BiFunction<TileType, TileType, List<Chiiable>> chiiableFactory =
            (lower, upper) -> Chiiable.ofMiddle(lower, upper);

        analyzeChiiMiddle(start, end, playerTileManager, chiiables, howToGetUpper, chiiableFactory);
    }

    private void analyzeChii2Side(TileType start, TileType end, PlayerTileManager playerTileManager,
        List<Chiiable> chiiables) {

        // 对于顺序吃，upper就是下一张牌
        Function<TileType, List<TileType>>             howToGetUpper   = (now) -> now.getNextTiles();

        // Chii2Side工厂，分析顺序吃的lower和upper
        BiFunction<TileType, TileType, List<Chiiable>> chiiableFactory =
            (lower, upper) -> Chiiable.of2Side(lower, upper);

        analyzeChiiMiddle(start, end, playerTileManager, chiiables, howToGetUpper, chiiableFactory);
    }

    /*
     * 这个函数就是分析吃牌的函数，分析的逻辑是这样的
     * 
     * 由于吃牌手中必须有牌，而有牌的形式有两种，第一种是顺序吃，第二种是坎张吃
     * 
     * 顺序吃手中必须有连续的两张牌，吃的是两张牌中靠前的牌的前一张牌或者靠后的牌的后一张牌
     * 判断只需要考虑是不是有连续的两张牌，因此将当前的牌作为lower，获取upper的逻辑就是获取lower的下一张牌
     * 生成Chiiable的工厂方法需要选择Chiiable中的of2Side方法，其分析顺序吃的lower和upper，并生成对应的Chiiable
     * 
     * 坎张吃则是有隔一张牌的两张牌，吃的是两张牌中间的那张牌
     * 判断就是是不是有隔一张牌的两张牌同时存在，因此将当前的牌作为lower，获取upper的逻辑就是获取lower下一张牌的下一张牌
     * 同理，此时生成Chiiable则需要选择不同的方法，即Chiiable中的ofMiddle方法，其分析坎张吃的lower和upper
     */
    private void analyzeChiiMiddle(TileType start, TileType end, PlayerTileManager playerTileManager,
        List<Chiiable> chiiables, Function<TileType, List<TileType>> getUpper,
        BiFunction<TileType, TileType, List<Chiiable>> chiiableFactory) {

        for (TileType now = start; now != end; now = next(now)) {// 到end就不分析了，end是不包含的
            if (tileTypeNotExist(now, playerTileManager)) {
                continue;
            }
            for (TileType upper : getUpper.apply(now)) {
                if (tileTypeNotExist(upper, playerTileManager)) {
                    continue;
                }
                chiiables.addAll(chiiableFactory.apply(now, upper));
            }
        }
    }

    private boolean tileTypeNotExist(TileType now, PlayerTileManager playerTileManager) {
        return playerTileManager.countInHand(now) == 0;
    }

    private TileType next(TileType now) {
        if (now.isNormalFive()) {// 如果是普通的5牌的话，那么接下来分析的是5牌的红宝牌，然后才是6牌
            return now.getRed();
        }
        return now.nextNormalTile();
    }
}
