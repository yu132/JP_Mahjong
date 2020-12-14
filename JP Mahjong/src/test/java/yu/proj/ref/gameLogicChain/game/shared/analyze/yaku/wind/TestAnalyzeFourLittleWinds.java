package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeFourLittleWinds  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeFourLittleWinds {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = AnalyzeFourLittleWinds.getInstance();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(EAST, EAST, EAST);

        taData.draw(SOUTH, SOUTH, SOUTH);

        taData.concealedKan(NORTH, NORTH, NORTH, NORTH);

        taData.draw(WEST, WEST);

        taData.draw(PIN_5, PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_5), PIN_5);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.FOUR_LITTLE_WINDS));
    }

}
