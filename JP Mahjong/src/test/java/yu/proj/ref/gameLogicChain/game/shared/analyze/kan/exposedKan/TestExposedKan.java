package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.exposedKan;

import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestExposedKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class TestExposedKan {

    @Test
    public void test() {
        TileType             tileType = SOU_7;
        int                  num      = 3;
        List<ExposedKanable> kanables = ExposedKanable.of(tileType, num);
        Assert.assertEquals(tileType, kanables.get(0).getToExposedKan());
    }

    @Test
    public void test1() {
        TileType             tileType = SOU_7;
        int                  num      = 4;
        List<ExposedKanable> kanables = ExposedKanable.of(tileType, num);
        Assert.assertTrue(kanables.isEmpty());
    }

    @Test
    public void test3() {
        TileType             tileType = SOU_7;
        int                  num      = 2;
        List<ExposedKanable> kanables = ExposedKanable.of(tileType, num);
        Assert.assertTrue(kanables.isEmpty());
    }

}
