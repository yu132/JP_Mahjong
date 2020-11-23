package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import yu.proj.ref.TestUtils;
import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileInHandGetter;
import yu.proj.ref.tile.Tile;
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
public class TestThirteenOrphansTenpaiable {

    @Test
    public void test13wait() {
        List<TileType>            terminalsAndHonors        = TileTypeGroup.TERMINALS_AND_HONORS;

        List<Tile>                tiles                     = of(terminalsAndHonors);

        ThirteenOrphansTenpaiable thirteenOrphansTenpaiable = ThirteenOrphansTenpaiable.of(tiles);

        assertEquals(13, thirteenOrphansTenpaiable.getWait().size());
    }


    @Test
    public void test1wait() {
        List<TileType> terminalsAndHonors = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

        terminalsAndHonors.remove(MAN_1);

        terminalsAndHonors.add(MAN_9);

        List<Tile>                tiles                     = of(terminalsAndHonors);

        ThirteenOrphansTenpaiable thirteenOrphansTenpaiable = ThirteenOrphansTenpaiable.of(tiles);

        assertEquals(1, thirteenOrphansTenpaiable.getWait().size());

        assertEquals(MAN_1, thirteenOrphansTenpaiable.getWait().get(0));
    }

    @Test
    public void testException() {
        List<TileType> terminalsAndHonors = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

        terminalsAndHonors.remove(MAN_1);

        terminalsAndHonors.remove(SOU_1);

        terminalsAndHonors.add(MAN_9);

        terminalsAndHonors.add(SOU_9);

        List<Tile> tiles = of(terminalsAndHonors);

        TestUtils.expectExceptionOrError(() -> {
            ThirteenOrphansTenpaiable.of(tiles);
        });
    }

    private List<Tile> of(List<TileType> list) {
        TestAnalyzeData data = new TestAnalyzeData();
        data.draw(list.toArray(new TileType[0]));
        List<Tile>             ans    = new ArrayList<>();
        PlayerTileInHandGetter getter = data.playerTileManager.playerTileInHandGetter();
        for (TileType type : list) {
            ans.add(getter.claim(type));
        }
        return ans;
    }
}
