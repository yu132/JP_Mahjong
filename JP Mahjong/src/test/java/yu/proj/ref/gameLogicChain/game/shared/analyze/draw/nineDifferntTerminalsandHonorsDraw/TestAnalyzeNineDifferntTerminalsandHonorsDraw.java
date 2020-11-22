package yu.proj.ref.gameLogicChain.game.shared.analyze.draw.nineDifferntTerminalsandHonorsDraw;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;

/**  
 * @ClassName: TestAnalyzeNineDifferntTerminalsandHonorsDraw  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */

public class TestAnalyzeNineDifferntTerminalsandHonorsDraw {

    private TestAnalyzeData data = new TestAnalyzeData();

    private AnalyzeNineDifferntTerminalsandHonorsDraw analyzer = new AnalyzeNineDifferntTerminalsandHonorsDraw();

    @Test
    public void test() {
        data.draw(MAN_1, MAN_9, SOU_1, SOU_9, PIN_1, PIN_9, EAST, SOUTH, WEST);// 9
        List<NineDifferntTerminalsandHonorsDrawable> result = analyzer.analyze(data.playerTileManager);
        assertEquals(1, result.size());
    }

    @Test
    public void test2() {
        data.draw(MAN_1, MAN_9, SOU_1, SOU_9, PIN_1, PIN_9, EAST, SOUTH);// 8
        List<NineDifferntTerminalsandHonorsDrawable> result = analyzer.analyze(data.playerTileManager);
        assertEquals(0, result.size());
    }

    @Test
    public void test3() {
        data.draw(MAN_1, MAN_9, SOU_1, SOU_9, PIN_1, PIN_9, EAST, SOUTH, SOUTH);// 8
        List<NineDifferntTerminalsandHonorsDrawable> result = analyzer.analyze(data.playerTileManager);
        assertEquals(0, result.size());
    }

}
