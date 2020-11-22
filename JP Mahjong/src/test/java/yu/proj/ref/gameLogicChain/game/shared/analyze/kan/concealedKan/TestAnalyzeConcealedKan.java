package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.concealedKan;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestAnalyzeExposedKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class TestAnalyzeConcealedKan {

    private TestAnalyzeData data = new TestAnalyzeData();

    private AnalyzeConcealedKan analyzer = new AnalyzeConcealedKan();

    @Test
    public void test() {
        TileType[] testResult = {MAN_7, MAN_4, SOU_4, PIN_1, NORTH};

        Arrays.sort(testResult, (x, y) -> x.getOrder() - y.getOrder());

        for (int i = 0; i < 4; ++i) {
            data.draw(testResult);
        }

        List<ConcealedKanable> kanables = analyzer.analyze(data.playerTileManager);

        assertEquals(testResult.length, kanables.size());

        for (int i = 0; i < kanables.size(); ++i) {
            assertEquals(testResult[i], kanables.get(i).getToConcealedKan());
        }
    }

    @Test
    public void testRed() {

        data.draw(MAN_5, MAN_5, MAN_5, MAN_5_RED);

        List<ConcealedKanable> kanables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, kanables.size());

        for (int i = 0; i < kanables.size(); ++i) {
            assertEquals(MAN_5, kanables.get(i).getToConcealedKan());
        }
    }

}
