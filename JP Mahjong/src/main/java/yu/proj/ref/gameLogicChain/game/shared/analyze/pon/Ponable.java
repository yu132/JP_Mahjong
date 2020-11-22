package yu.proj.ref.gameLogicChain.game.shared.analyze.pon;

import static yu.proj.ref.gameLogicChain.game.shared.analyze.pon.Ponable.NumOfRedInPon.*;
import static yu.proj.ref.utils.CheckTileNum.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Ponable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月20日  
 *  
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ponable {

    private TileType toPon;

    private NumOfRedInPon numOfRedInPon;

    public static List<Ponable> of(TileType toPon, int normal, int red) {
        assert toPon != null && checkTileNum(normal) && checkTileNum(red);

        if (noOtherTileToPon(normal, red)) {
            return Collections.emptyList();
        }

        return culPonable(toPon, normal, red);
    }

    // 因为手中的牌已经有4张了，因此不可能有别的人打出可碰的牌
    private static boolean noOtherTileToPon(int normal, int red) {
        return normal + red == 4;
    }

    private static List<Ponable> culPonable(TileType toPon, int normal, int red) {
        List<Ponable> ans = new ArrayList<>();
        for (NumOfRedInPon numOfRedInPon : culNumOfRedInPon(normal, red)) {
            ans.add(new Ponable(toPon, numOfRedInPon));
        }
        return ans;
    }

    private static List<NumOfRedInPon> culNumOfRedInPon(int normal, int red) {
        List<NumOfRedInPon> numOfRedInPon = new ArrayList<>();
        if (normal >= 2) {
            numOfRedInPon.add(USE_0);
        }
        if (normal >= 1 && red >= 1) {
            numOfRedInPon.add(USE_1);
        }
        if (red >= 2) {
            numOfRedInPon.add(USE_2);
        }
        return numOfRedInPon;
    }

    public static enum NumOfRedInPon {
        USE_0, USE_1, USE_2;
    }

}
