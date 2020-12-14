package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.outsideHand;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeFullOutsideHand  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeFullOutsideHand {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = AnalyzeFullOutsideHand.getInstance();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_7, MAN_8, MAN_9);

        taData.draw(SOU_7, SOU_8, SOU_9);

        taData.draw(PIN_1);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_1), PIN_1);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.FULL_OUTSIDE_HAND));
    }

}
