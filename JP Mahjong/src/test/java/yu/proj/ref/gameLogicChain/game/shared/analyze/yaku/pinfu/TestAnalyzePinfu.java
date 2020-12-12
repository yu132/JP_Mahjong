package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pinfu;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzePinfu  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class TestAnalyzePinfu {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer = new AnalyzePinfu();

    @Test
    public void test() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_5, SOU_6);

        taData.draw(PIN_2, PIN_2);

        isPinfu();
    }

    private void isPinfu() {
        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(SOU_4), SOU_4);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.PINFU));
    }

    @Test
    public void testNoSeq() {
        taData.draw(MAN_1, MAN_1, MAN_1);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_5, SOU_6);

        taData.draw(PIN_2, PIN_2);

        noPinfu(SOU_4);
    }

    @Test
    public void testNoWait2Side() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_5, SOU_5);

        taData.draw(PIN_2, PIN_2);

        noPinfu(SOU_5);
    }

    @Test
    public void testWait2SideButTerminal() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_1, SOU_2);

        taData.draw(PIN_2, PIN_2);

        noPinfu(SOU_3);
    }

    @Test
    public void testPairIsDragon() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.draw(MAN_5, MAN_6, MAN_7);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_2, SOU_3);

        taData.draw(RED, RED);

        noPinfu(SOU_1);
    }

    @Test
    public void testMakeCall() {
        taData.draw(MAN_1, MAN_2, MAN_3);

        taData.chii(MAN_5, MAN_6, MAN_7);

        taData.draw(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_2, SOU_3);

        taData.draw(PIN_2, PIN_2);

        noPinfu(SOU_1);
    }

    private void noPinfu(TileType tileToWin) {
        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(tileToWin), tileToWin);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothNotContainYaku(Yaku.PINFU));
    }

}
