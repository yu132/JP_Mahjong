package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.exposedKan;

import static yu.proj.ref.utils.CheckTileNum.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: ExposedKanable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExposedKanable {

    private TileType toExposedKan;

    public static List<ExposedKanable> of(TileType type, int num) {
        assert type != null && checkTileNum(num);

        if (num == 3) {
            return Arrays.asList(new ExposedKanable(type));
        }

        // num<3 || num==4
        return Collections.emptyList();
    }

}
