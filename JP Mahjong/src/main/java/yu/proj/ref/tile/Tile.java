package yu.proj.ref.tile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
public class Tile implements Comparable<Tile> {

    public final static Tile NONE_TILE = new Tile(TileType.NONE, 0);

    private TileType tileType;

    private int index;

    private Tile(TileType tileType, int index) {
        super();

        assert tileType != null;

        this.tileType = tileType;
        this.index = index;
    }

    public boolean sameNormalType(Tile tile) {
        return tileType.sameNormalType(tile.tileType);
    }

    public List<TileType> getNextDora() {
        return tileType.getNextDora();
    }

    public boolean previousOf(Tile tile) {
        return tileType.previousOf(tile.tileType);
    }

    public boolean nextOf(Tile tile) {
        return tileType.nextOf(tile.tileType);
    }

    @Override
    public int compareTo(Tile o) {
        int comp = Integer.compare(tileType.getOrder(), o.tileType.getOrder());
        return comp != 0 ? comp : index - o.index;
    }

    public static Tile of(TileType tileType, int index) {

        assert tileType != null && tileType != TileType.NONE && index >= 0 && index <= 4;

        return TileFlyWeightFactory.FACTORY.getInstance(tileType, index);
    }

    private static class TileFlyWeightFactory {

        final static TileFlyWeightFactory FACTORY = new TileFlyWeightFactory();

        @EqualsAndHashCode
        @AllArgsConstructor
        private static class Key {
            TileType tileType;
            int index;
        }

        private Map<Key, Tile> flyweightPool = new HashMap<>();

        public Tile getInstance(TileType tileType, int index) {
            return createTileIfAbsentAndGetTile(new Key(tileType, index));
        }

        private Tile createTileIfAbsentAndGetTile(Key key) {
            flyweightPool.putIfAbsent(key, new Tile(key.tileType, key.index));
            return flyweightPool.get(key);
        }
    }
}
