package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import java.util.List;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Chiiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月19日  
 *  
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Chiiable {

    private TileType lower;

    private TileType upper;

    private TileType toChii;

    @Getter(AccessLevel.NONE) // 由于可能为null，需要包装Optional
    private TileType tender;

    public Optional<TileType> getTender() {
        return Optional.ofNullable(tender);
    }

    public static List<Chiiable> of2Side(TileType lower, TileType upper) {
        return Chii2Side.of2Side(lower, upper);
    }

    public static List<Chiiable> ofMiddle(TileType lower, TileType upper) {
        return ChiiMiddle.ofMiddle(lower, upper);
    }
}