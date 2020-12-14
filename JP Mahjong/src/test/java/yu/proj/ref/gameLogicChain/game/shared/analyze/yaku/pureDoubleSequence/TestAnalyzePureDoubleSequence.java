package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pureDoubleSequence;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Singleton1;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.rule.yaku.pureFourSequence.PureFourSequenceRule;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzePureDoubleSequence  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class TestAnalyzePureDoubleSequence {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = AnalyzePureDoubleSequence.getInstance();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_5, SOU_6);

        taData.draw(PIN_2, PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(SOU_4), SOU_4);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.PURE_DOUBLE_SEQUENCE));
    }

    @Test
    public void testWaitMiddle() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_3);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_5, SOU_6, SOU_7);

        taData.draw(PIN_2, PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(MAN_2), MAN_2);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.PURE_DOUBLE_SEQUENCE));
    }

    @Test
    public void testTwice() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_2, Meld4Singleton1.class), PIN_2);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.TWICE_PURE_DOUBLE_SEQUENCE));
        assertTrue(taData.bothNotContainYaku(Yaku.PURE_DOUBLE_SEQUENCE));
    }

    @Test
    public void testTwiceRuleEnable() {

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_2, (tenpaiable) -> {
            Meld4Singleton1 meld4Singleton1 = ((Meld4Singleton1)tenpaiable);
            return meld4Singleton1.getConcealedSequence().size() == 4;
        }), PIN_2);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.TWICE_PURE_DOUBLE_SEQUENCE));
        assertTrue(taData.bothNotContainYaku(Yaku.PURE_DOUBLE_SEQUENCE));
    }

    @Test
    public void testTwiceRuleDisable() {

        taData.gameRule =
            taData.gameRule.toBuilder().pureFourSequenceRule(PureFourSequenceRule.AS_ONE_PURE_DOUBLE_SEQUENCE).build();

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_2, (tenpaiable) -> {
            Meld4Singleton1 meld4Singleton1 = ((Meld4Singleton1)tenpaiable);
            return meld4Singleton1.getConcealedSequence().size() == 4;
        }), PIN_2);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothNotContainYaku(Yaku.TWICE_PURE_DOUBLE_SEQUENCE));
        assertTrue(taData.bothContainYaku(Yaku.PURE_DOUBLE_SEQUENCE));
    }

}
