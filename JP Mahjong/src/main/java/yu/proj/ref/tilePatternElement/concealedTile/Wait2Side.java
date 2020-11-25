package yu.proj.ref.tilePatternElement.concealedTile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;

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
public class Wait2Side implements ConcealedTile {

    private Tile lower;
    private Tile upper;

    public static Wait2Side of(Tile lower, Tile upper) {

        assert lower != null && upper != null;
        assert lower.previousOf(upper);

        return new Wait2Side(lower, upper);
    }

}
