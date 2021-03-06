package yu.proj.ref.tilePatternElement.exposedTile;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;
import yu.proj.ref.tilePatternElement.SameTypeMeld;

/**  
 * @ClassName: AddKanQuad  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@ToString(callSuper = true)
public class AddKanQuad extends SameTypeMeld implements ExposedTile {

    // 加杠中两个特殊牌中，区分放在上下的两张牌
    private Tile lowTile;
    private Tile upperTile;

    private AddKanQuad(Tile[] tiles, MeldSource src, Tile lowTile, Tile upperTile) {
        // 加杠的src依然是原来的碰对应的src，目的是为了保留原有的碰的样式，这对于日麻鸣牌的摆放规则很重要
        super(tiles, src);
        this.lowTile = lowTile;
        this.upperTile = upperTile;
    }

    public static AddKanQuad of(Tile[] tiles, MeldSource src, Tile lowTile, Tile upperTile) {

        assert src != MeldSource.SELF && tiles.length == 4;

        return new AddKanQuad(tiles, src, lowTile, upperTile);
    }

    @Override
    public TileType tileType() {
        return lowTile.getTileType();
    }

}