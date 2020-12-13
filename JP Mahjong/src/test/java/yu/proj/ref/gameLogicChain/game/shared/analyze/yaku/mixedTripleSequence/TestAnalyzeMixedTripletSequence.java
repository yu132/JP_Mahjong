package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.mixedTripleSequence;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.NoYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzePureDoubleSequence  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class TestAnalyzeMixedTripletSequence {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = new AnalyzeMixedTripletSequence();

    @Test
    public void noYaku() {
        new NoYaku().test(analyzer);
    }

    @Test
    public void test() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(PIN_1, PIN_2, PIN_3);

        taData.draw(SOU_1, SOU_2);

        taData.draw(SOU_5, SOU_6, SOU_7);

        taData.draw(PIN_2, PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(SOU_3), SOU_3);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.MIXED_TRIPLE_SEQUENCE));
    }

}
