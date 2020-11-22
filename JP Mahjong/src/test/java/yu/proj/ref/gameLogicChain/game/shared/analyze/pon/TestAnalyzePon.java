package yu.proj.ref.gameLogicChain.game.shared.analyze.pon;

import static yu.proj.ref.gameLogicChain.game.shared.analyze.pon.Ponable.NumOfRedInPon.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import lombok.AllArgsConstructor;
import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.pon.Ponable.NumOfRedInPon;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestAnalyzePon  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class TestAnalyzePon {

    private TestAnalyzeData data = new TestAnalyzeData();

    @Test
    public void test() {
        TestData[] testData = {new TestData(EAST, USE_0)};
        data.draw(EAST, EAST);

        testPon(testData);
    }

    @Test
    public void test2() {
        TestData[] testData = {new TestData(WEST, USE_0)};
        data.draw(WEST, WEST);

        testPon(testData);
    }

    @Test
    public void test3() {
        TestData[] testData = {new TestData(MAN_3, USE_0)};
        data.draw(MAN_3, MAN_3, MAN_3);

        testPon(testData);
    }

    @Test
    public void test4() {
        TestData[] testData = {new TestData(SOU_7, USE_0)};
        data.draw(SOU_7, SOU_7, SOU_7);

        testPon(testData);
    }

    @Test
    public void testAllInHand() {
        TestData[] testData = {};
        data.draw(SOU_7, SOU_7, SOU_7, SOU_7);

        testPon(testData);
    }

    @Test
    public void testRed() {
        TestData[] testData = {new TestData(MAN_5, USE_0)};
        data.draw(MAN_5, MAN_5, MAN_5);

        testPon(testData);
    }

    @Test
    public void testRed2() {
        TestData[] testData = {new TestData(MAN_5, USE_0), new TestData(MAN_5, USE_1)};
        data.draw(MAN_5, MAN_5, MAN_5_RED);

        testPon(testData);
    }

    @Test
    public void testRed3() {
        TestData[] testData = {new TestData(MAN_5, USE_1), new TestData(MAN_5, USE_2)};
        data.draw(MAN_5, MAN_5_RED, MAN_5_RED);

        testPon(testData);
    }

    @Test
    public void testRed4() {
        TestData[] testData = {new TestData(MAN_5, USE_2)};
        data.draw(MAN_5_RED, MAN_5_RED, MAN_5_RED);

        testPon(testData);
    }

    private void testPon(TestData[] testData) {
        AnalyzePon    analyzePon = new AnalyzePon();

        List<Ponable> ponables   = analyzePon.analyze(data.playerTileManager);

        Assert.assertEquals(testData.length, ponables.size());

        for (int i = 0; i < testData.length; ++i) {
            Assert.assertEquals(testData[i].toPon, ponables.get(i).getToPon());
            Assert.assertEquals(testData[i].numOfRedInPon, ponables.get(i).getNumOfRedInPon());
        }
    }

    @AllArgsConstructor
    private static class TestData {
        TileType toPon;
        NumOfRedInPon numOfRedInPon;
    }

}
