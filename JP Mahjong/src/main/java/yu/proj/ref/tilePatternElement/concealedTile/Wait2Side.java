package yu.proj.ref.tilePatternElement.concealedTile;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Wait2side  
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
public class Wait2Side implements ConcealedTile, NotCompletedElement {

    private Tile lower;
    private Tile upper;

    public static Wait2Side of(Tile lower, Tile upper) {

        assert lower != null && upper != null;
        assert lower.previousOf(upper);

        return new Wait2Side(lower, upper);
    }

    // 两面听也兼职边张听的功能，本实现中不区分两种类型，由于边张时调用getLastTiles()和getNextTiles()时
    // 是不会返回任何牌的，因此保证边张听的正确性
    @Override
    public List<TileType> getTilesToWin() {
        List<TileType> tilesToWin = new ArrayList<>();
        tilesToWin.addAll(lower.getTileType().getLastTiles());// 两面听会听lower的前一张牌
        tilesToWin.addAll(upper.getTileType().getNextTiles());// 和upper的后一张牌
        return tilesToWin;
    }

}
