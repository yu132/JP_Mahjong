package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.sevenPairs;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;
import static yu.proj.ref.tile.Yaku.*;

import org.junit.Test;

import com.google.common.collect.Sets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair.SevenPairTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;

/**  
 * @ClassName: TestSevenPairTenpaiableAnalyzer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月15日  
 *  
 */
public class TestSevenPairTenpaiableAnalyzer {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private SevenPairTenpaiableAnalyzer analyzer =
        new SevenPairTenpaiableAnalyzer(taData.gameRule, taData.playerTileManager);

    @Test
    public void test() {
        taData.draw(EAST, EAST);

        taData.draw(SOUTH, SOUTH);

        taData.draw(WEST, WEST);

        taData.draw(NORTH, NORTH);

        taData.draw(WHITE, WHITE);

        taData.draw(GREEN, GREEN);

        taData.draw(RED);

        PatternAndYaku patternAndYaku = analyzer.analyze((SevenPairTenpaiable)taData.getFirstTenpai(RED));

        assertEquals(Sets.newHashSet(ALL_HONORS), patternAndYaku.getRonYakus());
    }

    @Test
    public void test2() {
        taData.draw(EAST, EAST);

        taData.draw(SOUTH, SOUTH);

        taData.draw(WEST, WEST);

        taData.draw(NORTH, NORTH);

        taData.draw(WHITE, WHITE);

        taData.draw(GREEN, GREEN);

        taData.draw(MAN_1);

        PatternAndYaku patternAndYaku = analyzer.analyze((SevenPairTenpaiable)taData.getFirstTenpai(MAN_1));

        assertEquals(Sets.newHashSet(ALL_TERMINALS_AND_HONORS, SEVEN_PAIRS, HALF_FLUSH), patternAndYaku.getRonYakus());
    }

    @Test
    public void test3() {
        taData.draw(MAN_2, MAN_2);

        taData.draw(SOU_2, SOU_2);

        taData.draw(MAN_3, MAN_3);

        taData.draw(SOU_3, SOU_3);

        taData.draw(PIN_5, PIN_5);

        taData.draw(SOU_8, SOU_8);

        taData.draw(MAN_6);

        PatternAndYaku patternAndYaku = analyzer.analyze((SevenPairTenpaiable)taData.getFirstTenpai(MAN_6));

        assertEquals(Sets.newHashSet(DANYAO, SEVEN_PAIRS), patternAndYaku.getRonYakus());
    }

    @Test
    public void test4() {
        taData.draw(MAN_1, MAN_1);

        taData.draw(MAN_2, MAN_2);

        taData.draw(MAN_4, MAN_4);

        taData.draw(MAN_5, MAN_5);

        taData.draw(MAN_7, MAN_7);

        taData.draw(MAN_8, MAN_8);

        taData.draw(MAN_9);

        PatternAndYaku patternAndYaku = analyzer.analyze((SevenPairTenpaiable)taData.getFirstTenpai(MAN_9));

        assertEquals(Sets.newHashSet(FULL_FLUSH, SEVEN_PAIRS), patternAndYaku.getRonYakus());
    }

}
