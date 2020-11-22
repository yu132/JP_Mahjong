package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Chii2Side  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月20日  
 *  
 */
final class Chii2Side {

    private Chii2Side() {}// 工具类

    // toChii可能会位于lower之前或者upper之后，但是lower只能是upper的前一个
    public static List<Chiiable> of2Side(TileType lower, TileType upper) {
        assert lower != null && upper != null && lower.previousOf(upper);

        List<Chiiable> ans = new ArrayList<>();

        addLowerToChii(lower, upper, ans);
        addUpperToChii(lower, upper, ans);

        return ans;
    }

    private static void addUpperToChii(TileType lower, TileType upper, List<Chiiable> ans) {
        for (TileType upperNextTile : upper.getNextTiles()) {
            ans.add(new Chiiable(lower, upper, upperNextTile, getLowerTender(lower)));
        }
    }

    private static void addLowerToChii(TileType lower, TileType upper, List<Chiiable> ans) {
        for (TileType lowerLastTile : lower.getLastTiles()) {
            ans.add(new Chiiable(lower, upper, lowerLastTile, getUpperTender(upper)));
        }
    }

    private static TileType getUpperTender(TileType upper) {
        List<TileType> tenderTileTypes = upper.getNextTiles();
        return getTender(tenderTileTypes);
    }

    private static TileType getLowerTender(TileType lower) {
        List<TileType> tenderTileTypes = lower.getLastTiles();
        return getTender(tenderTileTypes);
    }

    private static TileType getTender(List<TileType> tenderTileTypes) {
        if (tenderTileTypes.isEmpty()) {
            return null;
        }
        return tenderTileTypes.get(0).getNormalType();
    }

}
