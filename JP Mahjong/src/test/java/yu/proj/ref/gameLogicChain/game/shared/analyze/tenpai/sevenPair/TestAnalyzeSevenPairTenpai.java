package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair.AnalyzeSevenPairTenpai;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair.SevenPairTenpaiable;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestAnalyzeSevenPairTenpai  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TestAnalyzeSevenPairTenpai {

    private TestAnalyzeData data = new TestAnalyzeData();

    private AnalyzeSevenPairTenpai analyzer = new AnalyzeSevenPairTenpai();

    @Test
    public void test() {
        data.draw(MAN_1, MAN_3, MAN_5, MAN_6, MAN_8, SOU_6);
        data.draw(MAN_1, MAN_3, MAN_5, MAN_6, MAN_8, SOU_6);

        data.draw(SOU_7);

        List<SevenPairTenpaiable> tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, tenpaiables.size());

        assertEquals(SOU_7, tenpaiables.get(0).getSingleton().getTile().getTileType());

        assertEquals(MAN_1, pairTileType(tenpaiables, 0));
        assertEquals(MAN_3, pairTileType(tenpaiables, 1));
        assertEquals(MAN_5, pairTileType(tenpaiables, 2));
        assertEquals(MAN_6, pairTileType(tenpaiables, 3));
        assertEquals(MAN_8, pairTileType(tenpaiables, 4));
        assertEquals(SOU_6, pairTileType(tenpaiables, 5));
    }

    @Test
    public void test2() {
        data.draw(MAN_1, MAN_1, MAN_5, MAN_6, MAN_8, SOU_6);
        data.draw(MAN_1, MAN_1, MAN_5, MAN_6, MAN_8, SOU_6);// 有4个一样的MAN_1

        data.draw(SOU_7);

        List<SevenPairTenpaiable> tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(0, tenpaiables.size());
    }

    private TileType pairTileType(List<SevenPairTenpaiable> tenpaiables, int index) {
        return tenpaiables.get(0).getPairs().get(index).getTiles()[0].getTileType();
    }

}
