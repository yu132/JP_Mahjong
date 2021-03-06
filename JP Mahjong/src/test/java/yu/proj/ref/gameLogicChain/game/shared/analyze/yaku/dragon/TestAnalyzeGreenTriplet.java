package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon;

import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.TestTripletUtil;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzePrevalentWindTriplet  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月28日  
 *  
 */
public class TestAnalyzeGreenTriplet {

    @Test
    public void noYaku() {
        new NoYaku().test(AnalyzeGreenTriplet.getInstance());
    }

    @Test
    public void test() {
        new TestTripletUtil(GREEN, Yaku.DRAGON_G, AnalyzeGreenTriplet.getInstance()).test();
    }

}
