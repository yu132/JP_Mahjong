package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.ref.TestUtils;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestChiiMiddle  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月20日  
 *  
 */
public class TestChiiMiddle {

    @Test
    public void test() {
        TileType   lower  = MAN_3, upper = MAN_5;
        TileType[] toChii = {MAN_4};
        testChiiMiddle(lower, upper, toChii);
    }

    @Test
    public void testRed() {
        TileType   lower  = MAN_4, upper = MAN_6;
        TileType[] toChii = {MAN_5, MAN_5_RED};
        testChiiMiddle(lower, upper, toChii);
    }

    @Test
    public void testIllegal() {
        TileType   lower  = MAN_6, upper = MAN_9;
        TileType[] toChii = {MAN_7};
        TestUtils.expectExceptionOrError(() -> {
            testChiiMiddle(lower, upper, toChii);
        });
    }

    private void testChiiMiddle(TileType lower, TileType upper, TileType[] toChii) {
        List<Chiiable> chiiable = Chiiable.ofMiddle(lower, upper);
        for (int i = 0; i < toChii.length; ++i) {
            Assert.assertEquals(lower, chiiable.get(i).getLower());
            Assert.assertEquals(upper, chiiable.get(i).getUpper());
            Assert.assertEquals(toChii[i], chiiable.get(i).getToChii());
            Assert.assertTrue(!chiiable.get(i).getTender().isPresent());
        }
    }

}
