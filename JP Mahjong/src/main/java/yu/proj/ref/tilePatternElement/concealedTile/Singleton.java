package yu.proj.ref.tilePatternElement.concealedTile;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

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
public class Singleton implements ConcealedTile, NotCompletedElement {

    private Tile tile;

    public static Singleton of(Tile tile) {

        assert tile != null;

        return new Singleton(tile);
    }

    @Override
    public List<TileType> getTilesToWin() {
        // 单骑听的花色和自身相同
        return Collections.singletonList(tile.getTileType());
    }

}
