package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.rule.responsibility.ResponsibilityRule.BigThreeDragon;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.MeldSource;

/**  
 * @ClassName: TestAnalyzeBigThreeDragons  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class TestAnalyzeBigThreeDragons {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = new AnalyzeBigThreeDragons();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(RED, RED, RED);

        taData.draw(PIN_2, PIN_2, PIN_2);

        taData.concealedKan(WHITE, WHITE, WHITE, WHITE);

        taData.draw(GREEN, GREEN);

        taData.draw(PIN_5, PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(GREEN), GREEN);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.BIG_THREE_DRAGONS));
    }

    @Test
    public void testResponsibility() {
        taData.pon(RED, RED, RED);

        taData.draw(PIN_2, PIN_2, PIN_2);

        taData.concealedKan(WHITE, WHITE, WHITE, WHITE);

        taData.exposedKan(MeldSource.OPPOSED_PLAYER, GREEN, GREEN, GREEN, GREEN);

        taData.draw(PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_5), PIN_5);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.BIG_THREE_DRAGONS));
        assertEquals(MeldSource.OPPOSED_PLAYER, taData.yakuManager.getBigThreeDragonResponsibility());
    }

    @Test
    public void testDisableResponsibility() {

        taData.gameRule = taData.gameRule.toBuilder().responsibilityForBigThreeDragon(BigThreeDragon.DISABLE).build();

        taData.pon(RED, RED, RED);

        taData.draw(PIN_2, PIN_2, PIN_2);

        taData.concealedKan(WHITE, WHITE, WHITE, WHITE);

        taData.exposedKan(MeldSource.OPPOSED_PLAYER, GREEN, GREEN, GREEN, GREEN);

        taData.draw(PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_5), PIN_5);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.BIG_THREE_DRAGONS));
        assertEquals(null, taData.yakuManager.getBigThreeDragonResponsibility());
    }
}
