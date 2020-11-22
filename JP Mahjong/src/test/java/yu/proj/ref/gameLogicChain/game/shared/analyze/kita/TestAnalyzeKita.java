package yu.proj.ref.gameLogicChain.game.shared.analyze.kita;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;

/**  
 * @ClassName: TestAnalyzeKita  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TestAnalyzeKita {

    private TestAnalyzeData data = new TestAnalyzeData();

    private AnalyzeKita analyzeKita = new AnalyzeKita();

    @Test
    public void test() {
        data.draw(NORTH);

        List<Kitable> kitables = analyzeKita.analyze(data.playerTileManager);

        assertEquals(1, kitables.size());
    }

    @Test
    public void test2() {
        data.draw(NORTH);
        data.draw(NORTH);

        List<Kitable> kitables = analyzeKita.analyze(data.playerTileManager);

        assertEquals(1, kitables.size());
    }

    @Test
    public void testNoNorth() {

        List<Kitable> kitables = analyzeKita.analyze(data.playerTileManager);

        assertEquals(0, kitables.size());
    }

}
