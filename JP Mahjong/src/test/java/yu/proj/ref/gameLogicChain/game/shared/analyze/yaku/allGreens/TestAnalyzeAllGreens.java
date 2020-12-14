package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allGreens;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.rule.yaku.allGreen.GreenDragonInAllGreenRule;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeAllTerminals  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeAllGreens {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = AnalyzeAllGreens.getInstance();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(SOU_2, SOU_2, SOU_2);

        taData.draw(SOU_3, SOU_3, SOU_3);

        taData.draw(SOU_4, SOU_4, SOU_4);

        taData.draw(SOU_6, SOU_6);

        taData.draw(GREEN, GREEN);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(GREEN), GREEN);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.ALL_GREENS));
    }

    @Test
    public void testRuleNoGreen() {

        taData.gameRule = taData.gameRule.toBuilder()
            .allGreenRule(GreenDragonInAllGreenRule.NEED_GREEN_DRAGON_TO_BE_ALL_GREEN).build();

        taData.draw(SOU_2, SOU_2, SOU_2);

        taData.draw(SOU_3, SOU_3, SOU_3);

        taData.draw(SOU_4, SOU_4, SOU_4);

        taData.draw(SOU_6, SOU_6);

        taData.draw(SOU_8, SOU_8);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(SOU_8), SOU_8);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothNotContainYaku(Yaku.ALL_GREENS));
    }

    @Test
    public void testRuleNoGreenToTrueGreen() {

        taData.gameRule = taData.gameRule.toBuilder()
            .allGreenRule(GreenDragonInAllGreenRule.WITHOUT_GREEN_DRAGON_AS_TRUE_ALL_GREEN).build();

        taData.draw(SOU_2, SOU_2, SOU_2);

        taData.draw(SOU_3, SOU_3, SOU_3);

        taData.draw(SOU_4, SOU_4, SOU_4);

        taData.draw(SOU_6, SOU_6);

        taData.draw(SOU_8, SOU_8);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(SOU_8), SOU_8);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.TRUE_ALL_GREENS));
    }

    @Test
    public void testRuleHasGreenNotToTrueGreen() {

        taData.gameRule = taData.gameRule.toBuilder()
            .allGreenRule(GreenDragonInAllGreenRule.WITHOUT_GREEN_DRAGON_AS_TRUE_ALL_GREEN).build();

        taData.draw(SOU_2, SOU_2, SOU_2);

        taData.draw(SOU_3, SOU_3, SOU_3);

        taData.draw(SOU_4, SOU_4, SOU_4);

        taData.draw(SOU_6, SOU_6);

        taData.draw(GREEN, GREEN);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(GREEN), GREEN);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.ALL_GREENS));
    }

}
