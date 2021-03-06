package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.TestTripletUtil;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
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
public class TestAnalyzePrevalentWindTriplet {

    private YakuAnalyzer analyzer = AnalyzePrevalentWindTriplet.getInstance();

    @Test
    public void test() {
        new TestTripletUtil(EAST, Yaku.PREVALENT_WIND_E, analyzer).test();
    }

    @Test
    public void test2() {

        TestTripletUtil testTripletUtil = new TestTripletUtil(WEST, Yaku.PREVALENT_WIND_W, analyzer);

        testTripletUtil
            .test((taData) -> taData.yaBuilder(taData.getFirstTenpai(PIN_2), PIN_2).prevalentWind(WEST).build());
    }

}
