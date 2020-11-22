package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.ref.TestUtils;
import yu.proj.ref.tile.TileType;


/**  
 * @ClassName: TestChii  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月19日  
 *  
 */
public class TestChii2Side {

    @Test
    public void test2SideNormal() {
        TileType     lower    = MAN_2, upper = MAN_3;
        TileType[][] testPair = {{MAN_1, MAN_4}, {MAN_4, MAN_1}};
        check2Side(lower, upper, testPair);
    }

    @Test
    public void test2SideLowerEdge() {
        TileType     lower    = MAN_1, upper = MAN_2;
        TileType[][] testPair = {{MAN_3, null}};
        check2Side(lower, upper, testPair);
    }

    @Test
    public void test2SideRed() {
        TileType     lower    = MAN_3, upper = MAN_4;
        TileType[][] testPair = {{MAN_2, MAN_5}, {MAN_5, MAN_2}, {MAN_5_RED, MAN_2}};
        check2Side(lower, upper, testPair);
    }

    @Test
    public void test2SideNotContinue() {
        TileType     lower    = MAN_3, upper = MAN_6;
        TileType[][] testPair = {{MAN_2, MAN_7}, {MAN_7, MAN_2}};
        TestUtils.expectExceptionOrError(() -> {
            check2Side(lower, upper, testPair);
        });
    }

    private void check2Side(TileType lower, TileType upper, TileType[][] testPair) {
        List<Chiiable> chiiables = Chiiable.of2Side(lower, upper);
        for (int i = 0; i < testPair.length; ++i) {
            Assert.assertEquals(lower, chiiables.get(i).getLower());
            Assert.assertEquals(upper, chiiables.get(i).getUpper());

            Assert.assertEquals(testPair[i][0], chiiables.get(i).getToChii());
            if (testPair[i][1] != null) {
                Assert.assertEquals(testPair[i][1], chiiables.get(i).getTender().get());
            } else {
                Assert.assertTrue(!chiiables.get(i).getTender().isPresent());
            }
        }
    }


}
