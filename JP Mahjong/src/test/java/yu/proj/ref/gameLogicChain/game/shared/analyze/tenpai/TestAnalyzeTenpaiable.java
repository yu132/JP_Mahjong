package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.rule.tenpai.NotRealTenpaiRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestAnalyzeTenpaiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
public class TestAnalyzeTenpaiable {

    private TestAnalyzeData data = new TestAnalyzeData();

    private GameRule rule = MahjongSoulRule.mahjongSoulDefaultFourPalyerRule;

    private AnalyzeTenpaiable analyzer = new AnalyzeTenpaiable(rule);

    @Test
    public void test13waitThirteenOrphans() {

        data.draw(TileTypeGroup.TERMINALS_AND_HONORS);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(13, manager.getTilesToWin().size());
    }

    @Test
    public void testNormalThirteenOrphans() {

        List<TileType> types = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

        types.remove(MAN_1);
        types.add(MAN_9);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(1, manager.getTilesToWin().size());
    }

    @Test
    public void testSevenPair() {

        List<TileType> types =
            Arrays.asList(MAN_1, MAN_1, MAN_2, MAN_2, MAN_4, MAN_4, MAN_5, MAN_5, MAN_7, MAN_7, MAN_8, MAN_8, RED);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(1, manager.getTilesToWin().size());
    }

    @Test
    public void testSevenPairAndMeld4Pair1() {

        List<TileType> types =
            Arrays.asList(MAN_1, MAN_1, MAN_2, MAN_2, MAN_3, MAN_3, MAN_6, MAN_6, MAN_7, MAN_7, MAN_8, MAN_8, RED);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(1, manager.getTilesToWin().size());

        assertEquals(2, manager.getTenpaiable(RED).size());
    }

    @Test
    public void testTrueNineGate() {

        List<TileType> types =
            Arrays.asList(MAN_1, MAN_1, MAN_1, MAN_2, MAN_3, MAN_4, MAN_5, MAN_6, MAN_7, MAN_8, MAN_9, MAN_9, MAN_9);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(9, manager.getTilesToWin().size());

        assertEquals(2, manager.getTenpaiable(MAN_1).size());
        assertEquals(1, manager.getTenpaiable(MAN_2).size());
        assertEquals(2, manager.getTenpaiable(MAN_3).size());
        assertEquals(2, manager.getTenpaiable(MAN_4).size());
        assertEquals(1, manager.getTenpaiable(MAN_5).size());
        assertEquals(2, manager.getTenpaiable(MAN_6).size());
        assertEquals(2, manager.getTenpaiable(MAN_7).size());
        assertEquals(1, manager.getTenpaiable(MAN_8).size());
        assertEquals(2, manager.getTenpaiable(MAN_9).size());
    }

    @Test
    public void testNotRealTenpai() {

        List<TileType> types =
            Arrays.asList(MAN_1, MAN_1, MAN_1, MAN_1, MAN_4, MAN_4, MAN_4, MAN_6, MAN_7, MAN_8, MAN_9, MAN_9, MAN_9);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(0, manager.getTilesToWin().size());
    }

    @Test
    public void testNotRealTenpai2() {

        List<TileType> types =
            Arrays.asList(MAN_1, MAN_1, MAN_1, MAN_1, MAN_2, MAN_3, MAN_2, MAN_3, MAN_7, MAN_8, MAN_9, SOU_9, SOU_9);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(2, manager.getTilesToWin().size());

        assertTrue(manager.getTenpaiable(MAN_1).isEmpty());
    }

    @Test
    public void testAllowNotRealTenpai() {

        GameRule rule = this.rule.toBuilder().notRealTenpaiRule(NotRealTenpaiRule.ENABLE).build();

        AnalyzeTenpaiable analyzer = new AnalyzeTenpaiable(rule);

        List<TileType> types =
            Arrays.asList(MAN_1, MAN_1, MAN_1, MAN_1, MAN_4, MAN_4, MAN_4, MAN_6, MAN_7, MAN_8, MAN_9, MAN_9, MAN_9);

        data.draw(types);

        TenpaiManager manager = analyzer.analyze(data.playerTileManager);

        assertEquals(1, manager.getTilesToWin().size());
    }

    public static void main(String[] args) {
        new TestAnalyzeTenpaiable().testNotRealTenpai2();
    }

}
