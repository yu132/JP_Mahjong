package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.m4p1;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;
import static yu.proj.ref.tile.Yaku.*;

import org.junit.Test;

import com.google.common.collect.Sets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Pair1Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;

/**  
 * @ClassName: TestMeld4Pair1TenpaiableYakuAnalyzer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestMeld4Pair1TenpaiableYakuAnalyzer {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private Meld4Pair1TenpaiableYakuAnalyzer analyzer =
        new TwoLevelMeld4Pair1TenpaiableYakuAnalyzer(taData.gameRule, EAST, EAST, taData.playerTileManager);

    @Test
    public void test() {
        taData.concealedKan(MAN_2, MAN_2, MAN_2, MAN_2);

        taData.concealedKan(PIN_2, PIN_2, PIN_2, PIN_2);

        taData.concealedKan(SOU_2, SOU_2, SOU_2, SOU_2);

        taData.draw(SOU_6, SOU_6);

        taData.draw(SOU_8, SOU_8);

        PatternAndYaku patternAndYaku = analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(SOU_8), SOU_8);

        assertEquals(Sets.newHashSet(DANYAO, TRIPLE_TRIPLETS, THREE_CONCEALED_TRIPLETS, THREE_QUADS, ALL_TRIPLETS),
            patternAndYaku.getRonYakus());
    }

    @Test
    public void test2() {
        taData.pon(EAST, EAST, EAST);

        taData.pon(WHITE, WHITE, WHITE);

        taData.pon(GREEN, GREEN, GREEN);

        taData.draw(RED, RED);

        taData.draw(SOU_6, SOU_7);

        PatternAndYaku patternAndYaku = analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(SOU_5), SOU_5);

        assertEquals(
            Sets.newHashSet(PREVALENT_WIND_E, SEAT_WIND_E, DRAGON_G, DRAGON_WH, LITTLE_THREE_DRAGONS, HALF_FLUSH),
            patternAndYaku.getRonYakus());
    }

    @Test
    public void test3() {
        taData.draw(RED, RED, RED);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_7, MAN_8, MAN_9);

        taData.draw(SOU_9, SOU_9);

        taData.draw(MAN_9, MAN_9);

        PatternAndYaku patternAndYaku = analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_9), MAN_9);

        assertEquals(Sets.newHashSet(DRAGON_R, HALF_OUTSIDE_HAND), patternAndYaku.getRonYakus());
    }

    @Test
    public void test4() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.chii(MAN_2, MAN_3, MAN_4);

        taData.draw(MAN_4, MAN_5, MAN_6);

        taData.chii(MAN_7, MAN_8, MAN_9);

        taData.draw(MAN_9);

        PatternAndYaku patternAndYaku = analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_9), MAN_9);

        assertEquals(Sets.newHashSet(PURE_STRAIGHT, FULL_FLUSH), patternAndYaku.getRonYakus());
    }

    @Test
    public void test5() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_7, MAN_8, MAN_9);

        taData.draw(MAN_7, MAN_8);

        taData.draw(SOU_1, SOU_1);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_9, Meld4Pair1Tenpaiable.class), MAN_9);

        assertEquals(Sets.newHashSet(PINFU, TWICE_PURE_DOUBLE_SEQUENCE, FULL_OUTSIDE_HAND),
            patternAndYaku.getRonYakus());
    }

    @Test
    public void test6() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_3, MAN_4, MAN_5);

        taData.draw(PIN_1, PIN_2, PIN_3);

        taData.draw(SOU_1, SOU_2);

        taData.draw(SOUTH, SOUTH);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(SOU_3, Meld4Pair1Tenpaiable.class), SOU_3);

        assertEquals(Sets.newHashSet(MIXED_TRIPLE_SEQUENCE), patternAndYaku.getRonYakus());
    }

    @Test
    public void test7() {
        taData.draw(MAN_1, MAN_1, MAN_1);

        taData.pon(MAN_9, MAN_9, MAN_9);

        taData.pon(PIN_1, PIN_1, PIN_1);

        taData.draw(SOU_1, SOU_1);

        taData.draw(SOUTH, SOUTH);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(SOU_1, Meld4Pair1Tenpaiable.class), SOU_1);

        assertEquals(Sets.newHashSet(ALL_TERMINALS_AND_HONORS), patternAndYaku.getRonYakus());
    }

    @Test
    public void test8() {
        taData.draw(GREEN, GREEN, GREEN);

        taData.draw(WHITE, WHITE, WHITE);

        taData.draw(RED, RED, RED);

        taData.draw(WEST, WEST);

        taData.draw(SOUTH, SOUTH);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(WEST, Meld4Pair1Tenpaiable.class), WEST);

        assertEquals(Sets.newHashSet(BIG_THREE_DRAGONS, ALL_HONORS), patternAndYaku.getRonYakus());

        assertEquals(Sets.newHashSet(BIG_THREE_DRAGONS, FOUR_CONCEALED_TRIPLETS, ALL_HONORS),
            patternAndYaku.getTsumoYakus());
    }

    @Test
    public void test9() {
        taData.draw(SOU_2, SOU_2, SOU_2);

        taData.pon(SOU_3, SOU_3, SOU_3);

        taData.draw(SOU_4, SOU_4, SOU_4);

        taData.draw(GREEN, GREEN);

        taData.draw(SOU_8, SOU_8);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(SOU_8, Meld4Pair1Tenpaiable.class), SOU_8);

        assertEquals(Sets.newHashSet(ALL_GREENS), patternAndYaku.getRonYakus());
    }


    @Test
    public void test10() {
        taData.concealedKan(MAN_1, MAN_1, MAN_1, MAN_1);

        taData.concealedKan(SOU_1, SOU_1, SOU_1, SOU_1);

        taData.concealedKan(PIN_1, PIN_1, PIN_1, PIN_1);

        taData.concealedKan(SOU_9, SOU_9, SOU_9, SOU_9);

        taData.draw(MAN_9);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_9, Meld4Pair1Tenpaiable.class), MAN_9);

        assertEquals(Sets.newHashSet(ALL_TERMINALS, SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS, FOUR_QUADS),
            patternAndYaku.getRonYakus());
    }

    @Test
    public void test11() {
        taData.draw(EAST, EAST, EAST);

        taData.pon(SOUTH, SOUTH, SOUTH);

        taData.draw(WEST, WEST, WEST);

        taData.draw(NORTH, NORTH);

        taData.draw(MAN_2, MAN_2);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_2, Meld4Pair1Tenpaiable.class), MAN_2);

        assertEquals(Sets.newHashSet(FOUR_LITTLE_WINDS), patternAndYaku.getRonYakus());
    }


    @Test
    public void test12() {
        taData.draw(EAST, EAST, EAST);

        taData.pon(SOUTH, SOUTH, SOUTH);

        taData.draw(WEST, WEST, WEST);

        taData.draw(NORTH, NORTH, NORTH);

        taData.draw(MAN_2);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_2, Meld4Pair1Tenpaiable.class), MAN_2);

        assertEquals(Sets.newHashSet(FOUR_BIG_WINDS), patternAndYaku.getRonYakus());
    }

    @Test
    public void test13() {
        taData.draw(PIN_1, PIN_1, PIN_1, PIN_1);

        taData.draw(PIN_2, PIN_3, PIN_4);

        taData.draw(PIN_5, PIN_6, PIN_7);

        taData.draw(PIN_8);

        taData.draw(PIN_9, PIN_9);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(PIN_9, Meld4Pair1Tenpaiable.class), PIN_9);

        assertEquals(Sets.newHashSet(NINE_GATES), patternAndYaku.getRonYakus());
    }

    @Test
    public void test14() {
        taData.draw(PIN_1, PIN_1, PIN_1);

        taData.draw(PIN_2, PIN_3, PIN_4);

        taData.draw(PIN_5, PIN_6, PIN_7);

        taData.draw(PIN_8);

        taData.draw(PIN_9, PIN_9, PIN_9);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(PIN_9, Meld4Pair1Tenpaiable.class), PIN_9);

        assertEquals(Sets.newHashSet(TRUE_NINE_GATES), patternAndYaku.getRonYakus());
    }

    @Test
    public void test15() {
        taData.draw(MAN_1, MAN_1, MAN_1);

        taData.draw(MAN_3, MAN_3, MAN_3);

        taData.draw(MAN_5, MAN_5, MAN_5);

        taData.draw(MAN_7, MAN_7);

        taData.draw(MAN_9, MAN_9);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((Meld4Pair1Tenpaiable)taData.getFirstTenpai(MAN_9, Meld4Pair1Tenpaiable.class), MAN_9);

        assertEquals(Sets.newHashSet(THREE_CONCEALED_TRIPLETS, FULL_FLUSH, ALL_TRIPLETS), patternAndYaku.getRonYakus());

        assertEquals(Sets.newHashSet(FOUR_CONCEALED_TRIPLETS), patternAndYaku.getTsumoYakus());
    }

}
