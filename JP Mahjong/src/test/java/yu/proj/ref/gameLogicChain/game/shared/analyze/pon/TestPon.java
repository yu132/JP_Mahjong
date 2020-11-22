package yu.proj.ref.gameLogicChain.game.shared.analyze.pon;

import static yu.proj.ref.gameLogicChain.game.shared.analyze.pon.Ponable.NumOfRedInPon.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.pon.Ponable.NumOfRedInPon;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestPon  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月20日  
 *  
 */
public class TestPon {

    @Test
    public void testPon() {
        TileType      tileType = SOU_7;
        int           num      = 2;
        List<Ponable> ponables = Ponable.of(tileType, num, 0);
        Assert.assertEquals(tileType, ponables.get(0).getToPon());
        Assert.assertEquals(USE_0, ponables.get(0).getNumOfRedInPon());
    }

    @Test
    public void testPonRed() {
        TileType        tileType = SOU_5;
        int             normal   = 1, red = 1;

        NumOfRedInPon[] test     = {USE_1};

        testRed(tileType, normal, red, test);
    }

    @Test
    public void testPonRed2() {
        TileType        tileType = SOU_5;
        int             normal   = 0, red = 2;
        NumOfRedInPon[] test     = {USE_2};

        testRed(tileType, normal, red, test);
    }

    @Test
    public void testPonRed3() {
        TileType        tileType = SOU_5;
        int             normal   = 1, red = 2;
        NumOfRedInPon[] test     = {USE_1, USE_2};

        testRed(tileType, normal, red, test);
    }

    @Test
    public void testPonRed4() {
        TileType        tileType = SOU_5;
        int             normal   = 2, red = 1;
        NumOfRedInPon[] test     = {USE_0, USE_1};

        testRed(tileType, normal, red, test);
    }

    @Test
    public void testPonRed5() {
        TileType        tileType = SOU_5;
        int             normal   = 2, red = 2;
        NumOfRedInPon[] test     = {};

        testRed(tileType, normal, red, test);
    }

    @Test
    public void testNotPonRed() {
        TileType        tileType = SOU_5;
        int             normal   = 0, red = 1;
        NumOfRedInPon[] test     = {};

        testRed(tileType, normal, red, test);
    }

    private void testRed(TileType tileType, int normal, int red, NumOfRedInPon[] test) {
        List<Ponable> ponables = Ponable.of(tileType, normal, red);

        Assert.assertEquals(test.length, ponables.size());

        for (int i = 0; i < ponables.size(); ++i) {
            Assert.assertEquals(tileType, ponables.get(i).getToPon());
            Assert.assertEquals(test[i], ponables.get(i).getNumOfRedInPon());
        }
    }
}
