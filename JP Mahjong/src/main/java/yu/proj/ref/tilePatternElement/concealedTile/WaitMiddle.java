package yu.proj.ref.tilePatternElement.concealedTile;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: WaitMiddle  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WaitMiddle implements ConcealedTile, NotCompletedElement {

    private Tile lower;
    private Tile upper;

    public static WaitMiddle of(Tile lower, Tile upper) {

        assert lower != null && upper != null;
        assert lowerIsSecondPreviouseOfUpper(lower, upper);

        return new WaitMiddle(lower, upper);
    }

    private static boolean lowerIsSecondPreviouseOfUpper(Tile lower, Tile upper) {
        return lower.getTileType().nextNormalTile().previousOf(upper.getTileType());
    }

    @Override
    public List<TileType> getTilesToWin() {
        return lower.getTileType().getNextTiles();// 坎张听听的是lower的下一张牌，或者upper的前一张牌
    }
}
