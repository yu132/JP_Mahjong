package yu.proj.ref;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**  
 * @ClassName: Tile  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@ToString
@AllArgsConstructor
public class Tile implements Comparable<Tile> {

    private TileType tileType;

    private int index;

    public TileType[] getNextDora() {
        return tileType.getNextDora();
    }

    public TileType[] getNextTile() {
        return tileType.getNextTile();
    }

    @Override
    public int compareTo(Tile o) {
        int comp = Integer.compare(tileType.getOrder(), o.tileType.getOrder());
        return comp != 0 ? comp : index - o.index;
    }

}
