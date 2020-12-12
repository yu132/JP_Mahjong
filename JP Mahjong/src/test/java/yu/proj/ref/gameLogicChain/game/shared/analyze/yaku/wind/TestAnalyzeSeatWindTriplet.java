package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
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
public class TestAnalyzeSeatWindTriplet {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private AnalyzeSeatWindTriplet analyzer = new AnalyzeSeatWindTriplet();

    @Test
    public void test() {
        taData.draw(EAST, EAST, EAST);

        taData.draw(MAN_4, MAN_4, MAN_4);

        taData.draw(MAN_6, MAN_6, MAN_6);

        taData.draw(MAN_8, MAN_8, MAN_8);

        taData.draw(PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_2), PIN_2);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.SEAT_WIND_E));
    }

    @Test
    public void test2() {
        taData.draw(WEST, WEST, WEST);

        taData.draw(MAN_4, MAN_4, MAN_4);

        taData.draw(MAN_6, MAN_6, MAN_6);

        taData.draw(MAN_8, MAN_8, MAN_8);

        taData.draw(PIN_2);

        YakuAnalyzeData yaData = taData.yaBuilder(taData.getFirstTenpai(PIN_2), PIN_2).seatWind(WEST).build();

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.SEAT_WIND_W));
    }

}
