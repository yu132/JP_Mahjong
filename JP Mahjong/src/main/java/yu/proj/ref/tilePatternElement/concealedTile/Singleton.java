package yu.proj.ref.tilePatternElement.concealedTile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: Singleton  
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
public class Singleton {

    private Tile tile;

    public static Singleton of(Tile tile) {

        assert tile != null;

        return new Singleton(tile);
    }

}
