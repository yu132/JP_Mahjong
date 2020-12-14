package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.rule.responsibility.ResponsibilityRule.FourBigWind;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.MeldSource;

/**  
 * @ClassName: TestAnalyzeFourBigWinds  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeFourBigWinds {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = new AnalyzeFourBigWinds();

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

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(WEST), WEST);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.FOUR_BIG_WINDS));
    }

    @Test
    public void testResponsibility() {
        taData.pon(EAST, EAST, EAST);

        taData.pon(SOUTH, SOUTH, SOUTH);

        taData.concealedKan(NORTH, NORTH, NORTH, NORTH);

        taData.exposedKan(MeldSource.OPPOSED_PLAYER, WEST, WEST, WEST, WEST);

        taData.draw(PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_5), PIN_5);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.FOUR_BIG_WINDS));
        assertEquals(MeldSource.OPPOSED_PLAYER, taData.yakuManager.getFourBigWindResponsibility());
    }

    @Test
    public void testDisableResponsibility() {

        taData.gameRule = taData.gameRule.toBuilder().responsibilityForFourBigWind(FourBigWind.DISABLE).build();

        taData.pon(EAST, EAST, EAST);

        taData.pon(SOUTH, SOUTH, SOUTH);

        taData.concealedKan(NORTH, NORTH, NORTH, NORTH);

        taData.exposedKan(MeldSource.OPPOSED_PLAYER, WEST, WEST, WEST, WEST);

        taData.draw(PIN_5);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_5), PIN_5);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.FOUR_BIG_WINDS));
        assertEquals(null, taData.yakuManager.getFourBigWindResponsibility());
    }

}
