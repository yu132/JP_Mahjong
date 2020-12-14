package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.nineGate;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeNineGate  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class TestAnalyzeNineGate {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = new AnalyzeNineGate();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(MAN_1, MAN_1, MAN_1);

        taData.draw(MAN_2, MAN_3, MAN_4);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(MAN_8);

        taData.draw(MAN_9, MAN_9, MAN_9);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(MAN_9), MAN_9);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.TRUE_NINE_GATES));
    }

    @Test
    public void test2() {
        taData.draw(MAN_1, MAN_1, MAN_1);

        taData.draw(MAN_2, MAN_3, MAN_4);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(MAN_8, MAN_8);

        taData.draw(MAN_9, MAN_9);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(MAN_9), MAN_9);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.NINE_GATES));
    }

}
