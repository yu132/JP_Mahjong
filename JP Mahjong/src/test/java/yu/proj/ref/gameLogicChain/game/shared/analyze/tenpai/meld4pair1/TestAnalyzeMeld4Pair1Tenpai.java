package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;

/**  
 * @ClassName: AnalyzeMeld4Pair1Tenpai  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */
public class TestAnalyzeMeld4Pair1Tenpai {

    private AnalyzeMeld4Pair1Tenpai analyzer = new AnalyzeMeld4Pair1Tenpai();

    private TestAnalyzeData data = new TestAnalyzeData();

    @Test
    public void testMeld4Singleton1() {

        data.chii(MAN_1, MAN_2, MAN_3);

        data.pon(SOU_8, SOU_8, SOU_8);

        data.draw(SOU_1, SOU_1, SOU_1);

        data.draw(PIN_1, PIN_1, PIN_1);

        data.draw(EAST);

        List<Meld4Pair1Tenpaiable> meld4Pair1Tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, meld4Pair1Tenpaiables.size());

        assertTrue(meld4Pair1Tenpaiables.get(0) instanceof Meld4Singleton1);
    }

    @Test
    public void testMeld3Pair2() {

        data.chii(MAN_1, MAN_2, MAN_3);

        data.pon(SOU_8, SOU_8, SOU_8);

        data.draw(SOU_1, SOU_1, SOU_1);

        data.draw(PIN_1, PIN_1);

        data.draw(EAST, EAST);

        List<Meld4Pair1Tenpaiable> meld4Pair1Tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, meld4Pair1Tenpaiables.size());

        assertTrue(meld4Pair1Tenpaiables.get(0) instanceof Meld3Pair2);
    }

    @Test
    public void testMeld3Pair1Wait2Side1() {

        data.chii(MAN_1, MAN_2, MAN_3);

        data.pon(SOU_8, SOU_8, SOU_8);

        data.draw(SOU_1, SOU_1, SOU_1);

        data.draw(PIN_1, PIN_2);

        data.draw(EAST, EAST);

        List<Meld4Pair1Tenpaiable> meld4Pair1Tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, meld4Pair1Tenpaiables.size());

        assertTrue(meld4Pair1Tenpaiables.get(0) instanceof Meld3Pair1Wait2Side1);
    }

    @Test
    public void testMeld3Pair1WaitMiddle1() {

        data.chii(MAN_1, MAN_2, MAN_3);

        data.pon(SOU_8, SOU_8, SOU_8);

        data.draw(SOU_1, SOU_1, SOU_1);

        data.draw(PIN_1, PIN_3);

        data.draw(EAST, EAST);

        List<Meld4Pair1Tenpaiable> meld4Pair1Tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, meld4Pair1Tenpaiables.size());

        assertTrue(meld4Pair1Tenpaiables.get(0) instanceof Meld3Pair1WaitMiddle1);
    }

    // 最复杂的听牌分析情况 —— 纯正九莲宝灯
    // 111 23 456 789 99 两面听 1 4
    // 11 123 456 78 999 两面听 6 9

    // 11 123 456 789 99 双碰听 1 9

    // 111 2 345 678 999 单骑听 2
    // 111 234 5 678 999 单骑听 5
    // 111 234 567 8 999 单骑听 8

    // 11 12 345 678 999 边张听 3
    // 111 234 567 89 99 边张听 7

    // 111 234 56 789 99 两面听 4 7
    // 11 123 45 678 999 两面听 3 6
    @Test
    public void testNineGate() {
        data.draw(SOU_1, SOU_1, SOU_1);
        data.draw(SOU_2, SOU_3, SOU_4, SOU_5, SOU_6, SOU_7, SOU_8);
        data.draw(SOU_9, SOU_9, SOU_9);

        List<Meld4Pair1Tenpaiable> meld4Pair1Tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(10, meld4Pair1Tenpaiables.size());

        Map<Class<?>, Integer> count = new HashMap<>();

        for (Meld4Pair1Tenpaiable meld4Pair1Tenpaiable : meld4Pair1Tenpaiables) {
            count.put(meld4Pair1Tenpaiable.getClass(), count.getOrDefault(meld4Pair1Tenpaiable.getClass(), 0) + 1);
        }

        assertEquals(Integer.valueOf(3), count.getOrDefault(Meld4Singleton1.class, 0));
        assertEquals(Integer.valueOf(1), count.getOrDefault(Meld3Pair2.class, 0));
        assertEquals(Integer.valueOf(6), count.getOrDefault(Meld3Pair1Wait2Side1.class, 0));
        assertEquals(Integer.valueOf(0), count.getOrDefault(Meld3Pair1WaitMiddle1.class, 0));
    }


    public static void main(String[] args) {
        new TestAnalyzeMeld4Pair1Tenpai().testNineGate();
    }
}
