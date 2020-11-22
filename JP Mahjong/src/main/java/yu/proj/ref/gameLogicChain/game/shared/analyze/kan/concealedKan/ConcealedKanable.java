package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.concealedKan;

import static yu.proj.ref.utils.CheckTileNum.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: ConcealedKanable  
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
public class ConcealedKanable {

    private TileType toConcealedKan;

    public static List<ConcealedKanable> of(TileType type, int num) {
        assert type != null && checkTileNum(num);

        if (num == 4) {
            return Arrays.asList(new ConcealedKanable(type));
        }

        // num<4
        return Collections.emptyList();
    }

}
