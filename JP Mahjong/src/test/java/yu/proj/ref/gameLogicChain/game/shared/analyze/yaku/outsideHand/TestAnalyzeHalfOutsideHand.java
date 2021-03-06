package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.outsideHand;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeHalfOutsideHand  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeHalfOutsideHand {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = AnalyzeHalfOutsideHand.getInstance();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(PIN_1, PIN_1, PIN_1);

        taData.draw(SOU_1, SOU_1, SOU_1);

        taData.draw(MAN_9, MAN_9);

        taData.draw(WEST, WEST);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(MAN_9), MAN_9);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.HALF_OUTSIDE_HAND));
    }

    @Test
    public void testALL_TERMINALS_AND_HONORS() {
        taData.draw(MAN_1, MAN_1, MAN_1);

        taData.draw(PIN_1, PIN_1, PIN_1);

        taData.draw(SOU_1, SOU_1, SOU_1);

        taData.draw(MAN_9, MAN_9);

        taData.draw(WEST, WEST);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(MAN_9), MAN_9);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothNotContainYaku(Yaku.HALF_OUTSIDE_HAND));
    }

}
