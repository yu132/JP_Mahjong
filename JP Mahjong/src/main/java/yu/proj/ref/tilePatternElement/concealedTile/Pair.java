package yu.proj.ref.tilePatternElement.concealedTile;

import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Pair  
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
public class Pair implements ConcealedTile, NotCompletedElement {

    private Tile[] tiles;

    public static Pair of(Tile[] tiles) {

        assert tiles != null && tiles.length == 2;
        assert tiles[0].sameNormalType(tiles[1]);

        return new Pair(tiles);
    }

    @Override
    public List<TileType> getTilesToWin() {
        // 由于对子听的牌和自己包含的两张牌是一样的花色，因此直接获取第一张的花色返回
        return Collections.singletonList(tiles[0].getTileType());
    }

}
