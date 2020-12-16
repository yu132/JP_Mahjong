package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.thirteenOrphans;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;
import static yu.proj.ref.tile.Yaku.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Sets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans.ThirteenOrphansTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestThirteenOrphansTenpaiableYakuAnalyzer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月16日  
 *  
 */
public class TestThirteenOrphansTenpaiableYakuAnalyzer {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private ThirteenOrphansTenpaiableYakuAnalyzer analyzer = new ThirteenOrphansTenpaiableYakuAnalyzer();

    List<TileType> terminalsAndHonors = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

    @Test
    public void test() {
        taData.draw(terminalsAndHonors);

        PatternAndYaku patternAndYaku = analyzer.analyze((ThirteenOrphansTenpaiable)taData.getFirstTenpai(RED), RED);

        assertEquals(Sets.newHashSet(THIRTEEN_WAIT_THIRTEEN_ORPHANS), patternAndYaku.getRonYakus());
    }

    @Test
    public void test2() {

        terminalsAndHonors.remove(MAN_1);
        terminalsAndHonors.add(MAN_9);

        taData.draw(terminalsAndHonors);

        PatternAndYaku patternAndYaku =
            analyzer.analyze((ThirteenOrphansTenpaiable)taData.getFirstTenpai(MAN_1), MAN_1);

        assertEquals(Sets.newHashSet(THIRTEEN_ORPHANS), patternAndYaku.getRonYakus());
    }
}
