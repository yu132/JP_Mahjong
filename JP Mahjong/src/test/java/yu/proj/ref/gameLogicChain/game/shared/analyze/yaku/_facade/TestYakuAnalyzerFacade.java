package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;
import static yu.proj.ref.tile.Yaku.*;

import org.junit.Test;

import com.google.common.collect.Sets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;

/**  
 * @ClassName: TestYakuAnalyzerFacade  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月16日  
 *  
 */
public class TestYakuAnalyzerFacade {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzerFacade analyzer = new YakuAnalyzerFacade(taData.gameRule, EAST, EAST, taData.playerTileManager);

    @Test
    public void test() {
        taData.concealedKan(MAN_2, MAN_2, MAN_2, MAN_2);

        taData.concealedKan(PIN_2, PIN_2, PIN_2, PIN_2);

        taData.concealedKan(SOU_2, SOU_2, SOU_2, SOU_2);

        taData.draw(SOU_6, SOU_6);

        taData.draw(SOU_8, SOU_8);

        YakuManager yakuManager = analyzer.analyze(taData.analyzeTenpai());

        assertEquals(Sets.newHashSet(SOU_6, SOU_8), yakuManager.getTilesToWin());

        assertEquals(Sets.newHashSet(DANYAO, TRIPLE_TRIPLETS, THREE_CONCEALED_TRIPLETS, THREE_QUADS, ALL_TRIPLETS),
            yakuManager.getPatternsAndYakus(SOU_6).get(0).getRonYakus());
    }

    @Test
    public void test2() {
        taData.draw(EAST, EAST);

        taData.draw(SOUTH, SOUTH);

        taData.draw(WEST, WEST);

        taData.draw(NORTH, NORTH);

        taData.draw(WHITE, WHITE);

        taData.draw(GREEN, GREEN);

        taData.draw(RED);

        YakuManager yakuManager = analyzer.analyze(taData.analyzeTenpai());

        assertEquals(Sets.newHashSet(RED), yakuManager.getTilesToWin());

        assertEquals(Sets.newHashSet(ALL_HONORS), yakuManager.getPatternsAndYakus(RED).get(0).getRonYakus());
    }

    @Test
    public void test3() {
        taData.draw(TileTypeGroup.TERMINALS_AND_HONORS);

        YakuManager yakuManager = analyzer.analyze(taData.analyzeTenpai());

        assertEquals(Sets.newHashSet(TileTypeGroup.TERMINALS_AND_HONORS), yakuManager.getTilesToWin());

        assertEquals(Sets.newHashSet(THIRTEEN_WAIT_THIRTEEN_ORPHANS),
            yakuManager.getPatternsAndYakus(RED).get(0).getRonYakus());
    }

}
