package yu.proj.ref.tilePatternElement.concealedTile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;

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
public class Pair {

    private Tile[] tiles;

    public static Pair of(Tile[] tiles) {

        assert tiles != null && tiles.length == 2;
        assert tiles[0].sameNormalType(tiles[1]);

        return new Pair(tiles);
    }

}
