package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: ChiiMiddle  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月20日  
 *  
 */
final class ChiiMiddle {

    private ChiiMiddle() {}

    // toChii只会在lower和upper中间，并且lower必须为upper后两个
    public static List<Chiiable> ofMiddle(TileType lower, TileType upper) {
        assert lower != null && upper != null && isLowerSecondPreviousOfUpper(lower, upper);

        List<Chiiable> ans = new ArrayList<>();

        addMiddleToChii(lower, upper, ans);

        return ans;
    }

    // lower是upper前二种花色
    private static boolean isLowerSecondPreviousOfUpper(TileType lower, TileType upper) {
        return lower.previousOf(upper.getLastTiles().get(0));
    }

    private static void addMiddleToChii(TileType lower, TileType upper, List<Chiiable> ans) {
        for (TileType midTileType : lower.getNextTiles()) {
            ans.add(new Chiiable(lower, upper, midTileType, null));
        }
    }

}
