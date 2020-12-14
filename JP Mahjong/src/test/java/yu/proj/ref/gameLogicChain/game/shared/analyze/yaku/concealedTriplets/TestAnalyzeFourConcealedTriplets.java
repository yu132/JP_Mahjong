package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeFourConcealedTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class TestAnalyzeFourConcealedTriplets {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = AnalyzeFourConcealedTriplets.getInstance();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(MAN_2, MAN_2, MAN_2);

        taData.draw(PIN_2, PIN_2, PIN_2);

        taData.draw(SOU_2, SOU_2, SOU_2);

        taData.draw(MAN_8, MAN_8);

        taData.draw(PIN_5, PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_5), PIN_5);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.yakuManager.getTsumoYakus().contains(Yaku.FOUR_CONCEALED_TRIPLETS));
        assertTrue(taData.yakuManager.getRonYakus().contains(Yaku.THREE_CONCEALED_TRIPLETS));
    }

}
