package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allHonors;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeAllTerminals  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeAllHonors {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = new AnalyzeAllHonors();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(EAST, EAST, EAST);

        taData.draw(SOUTH, SOUTH, SOUTH);

        taData.draw(WEST, WEST, WEST);

        taData.draw(NORTH, NORTH);

        taData.draw(RED, RED);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(RED), RED);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.ALL_HONORS));
    }

}
