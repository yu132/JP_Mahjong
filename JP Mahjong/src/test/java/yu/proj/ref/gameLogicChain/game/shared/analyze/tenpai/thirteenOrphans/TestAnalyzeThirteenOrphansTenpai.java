package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestThirteenOrphansTenpaiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */
public class TestAnalyzeThirteenOrphansTenpai {

    private TestAnalyzeData data = new TestAnalyzeData();

    private AnalyzeThirteenOrphansTenpai analyzer = new AnalyzeThirteenOrphansTenpai();

    @Test
    public void test13wait() {
        data.draw(TileTypeGroup.TERMINALS_AND_HONORS);

        List<ThirteenOrphansTenpaiable> thirteenOrphansTenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, thirteenOrphansTenpaiables.size());

        assertEquals(13, thirteenOrphansTenpaiables.get(0).getWait().size());
    }


    @Test
    public void test1wait() {
        List<TileType> terminalsAndHonors = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

        terminalsAndHonors.remove(MAN_1);

        terminalsAndHonors.add(MAN_9);

        data.draw(terminalsAndHonors);

        List<ThirteenOrphansTenpaiable> thirteenOrphansTenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, thirteenOrphansTenpaiables.size());

        assertEquals(1, thirteenOrphansTenpaiables.get(0).getWait().size());

        assertEquals(MAN_1, thirteenOrphansTenpaiables.get(0).getWait().get(0));
    }

    @Test
    public void testIsNot13Orphans() {
        List<TileType> terminalsAndHonors = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

        terminalsAndHonors.remove(MAN_1);

        terminalsAndHonors.remove(SOU_1);

        terminalsAndHonors.add(MAN_9);

        data.draw(terminalsAndHonors);

        List<ThirteenOrphansTenpaiable> thirteenOrphansTenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(0, thirteenOrphansTenpaiables.size());
    }
}
