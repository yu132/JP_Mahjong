package yu.proj.ref.gameLogicChain.game.shared.analyze.chii;

import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import lombok.AllArgsConstructor;
import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.rule.chii.ChiiRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestAnalyzeChii  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class TestAnalyzeChii {

    private AnalyzeChii chiiAnalyzer;

    private TestAnalyzeData data = new TestAnalyzeData();

    @Before
    public void setUp() {
        chiiAnalyzer = new AnalyzeChii(data.gameRule);
    }

    @Test
    public void testNormalChii2Side() {
        TestData[] testData = {new TestData(SOU_5, SOU_6, SOU_4, SOU_7), new TestData(SOU_5, SOU_6, SOU_7, SOU_4)};
        data.draw(SOU_5, SOU_6);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChii2Side2() {
        TestData[] testData = {new TestData(PIN_5, PIN_6, PIN_4, PIN_7), new TestData(PIN_5, PIN_6, PIN_7, PIN_4)};
        data.draw(PIN_5, PIN_6);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChii2Side3() {
        TestData[] testData = {new TestData(PIN_1, PIN_2, PIN_3, null)};
        data.draw(PIN_1, PIN_2);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChii2Side4() {
        TestData[] testData = {new TestData(PIN_8, PIN_9, PIN_7, null)};
        data.draw(PIN_8, PIN_9);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChii2Side() {
        TestData[] testData = {new TestData(PIN_3, PIN_4, PIN_2, PIN_5), new TestData(PIN_3, PIN_4, PIN_5, PIN_2),
            new TestData(PIN_3, PIN_4, PIN_5_RED, PIN_2)};
        data.draw(PIN_3, PIN_4);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChii2Side2() {
        TestData[] testData = {new TestData(PIN_6, PIN_7, PIN_5, PIN_8), new TestData(PIN_6, PIN_7, PIN_5_RED, PIN_8),
            new TestData(PIN_6, PIN_7, PIN_8, PIN_5)};
        data.draw(PIN_6, PIN_7);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChii2Side3() {
        TestData[] testData = {new TestData(SOU_5, SOU_6, SOU_4, SOU_7), new TestData(SOU_5, SOU_6, SOU_7, SOU_4),
            new TestData(SOU_5_RED, SOU_6, SOU_4, SOU_7), new TestData(SOU_5_RED, SOU_6, SOU_7, SOU_4)};
        data.draw(SOU_5, SOU_5_RED, SOU_6);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChii2Side4() {
        TestData[] testData = {new TestData(SOU_4, SOU_5, SOU_3, SOU_6), new TestData(SOU_4, SOU_5, SOU_6, SOU_3),
            new TestData(SOU_4, SOU_5_RED, SOU_3, SOU_6), new TestData(SOU_4, SOU_5_RED, SOU_6, SOU_3)};
        data.draw(SOU_4, SOU_5, SOU_5_RED);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChiiMiddle() {
        TestData[] testData = {new TestData(SOU_5, SOU_7, SOU_6, null)};
        data.draw(SOU_5, SOU_7);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChiiMiddle2() {
        TestData[] testData = {new TestData(MAN_5, MAN_7, MAN_6, null)};
        data.draw(MAN_5, MAN_7);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChiiMiddle3() {
        TestData[] testData = {new TestData(MAN_1, MAN_3, MAN_2, null)};
        data.draw(MAN_1, MAN_3);

        testAnalyzeChii(testData);
    }

    @Test
    public void testNormalChiiMiddle4() {
        TestData[] testData = {new TestData(MAN_7, MAN_9, MAN_8, null)};
        data.draw(MAN_7, MAN_9);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChiiMiddle() {
        TestData[] testData = {new TestData(MAN_4, MAN_6, MAN_5, null), new TestData(MAN_4, MAN_6, MAN_5_RED, null)};
        data.draw(MAN_4, MAN_6);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChiiMiddle2() {
        TestData[] testData = {new TestData(MAN_3, MAN_5, MAN_4, null), new TestData(MAN_3, MAN_5_RED, MAN_4, null)};
        data.draw(MAN_3, MAN_5, MAN_5_RED);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRedChiiMiddle3() {
        TestData[] testData = {new TestData(MAN_5, MAN_7, MAN_6, null), new TestData(MAN_5_RED, MAN_7, MAN_6, null)};
        data.draw(MAN_5, MAN_5_RED, MAN_7);

        testAnalyzeChii(testData);
    }

    @Test
    public void testRuleDisableChii() {

        data.gameRule =
            MahjongSoulRule.mahjongSoulDefaultFourPalyerRule.toBuilder().chiiRule(ChiiRule.DISABLE_CHII).build();

        chiiAnalyzer  = new AnalyzeChii(data.gameRule);

        TestData[] testData = {};

        data.draw(MAN_5, MAN_5_RED, MAN_7);

        testAnalyzeChii(testData);
    }

    private void testAnalyzeChii(TestData[] testData) {
        List<Chiiable> chiiables = chiiAnalyzer.analyze(data.playerTileManager);

        Assert.assertEquals(testData.length, chiiables.size());

        for (int i = 0; i < testData.length; ++i) {
            Assert.assertEquals(testData[i].lower, chiiables.get(i).getLower());
            Assert.assertEquals(testData[i].upper, chiiables.get(i).getUpper());
            Assert.assertEquals(testData[i].toChii, chiiables.get(i).getToChii());
            if (testData[i].tender != null) {
                Assert.assertEquals(testData[i].tender, chiiables.get(i).getTender().get());
            } else {
                Assert.assertTrue(!chiiables.get(i).getTender().isPresent());
            }
        }
    }

    @AllArgsConstructor
    private static class TestData {
        TileType lower, upper, toChii, tender;
    }

}
