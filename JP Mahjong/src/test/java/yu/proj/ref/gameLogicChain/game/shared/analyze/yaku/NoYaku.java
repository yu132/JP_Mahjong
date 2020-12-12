package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;

/**  
 * @ClassName: NoYaku  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class NoYaku {

    private TestAnalyzeData taData = new TestAnalyzeData();

    public void test(YakuAnalyzer analyzer) {
        taData.chii(MAN_1, MAN_2, MAN_3);

        taData.chii(MAN_4, MAN_5, MAN_6);

        taData.chii(SOU_1, SOU_2, SOU_3);

        taData.draw(SOU_5, SOU_6);

        taData.draw(PIN_2, PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(SOU_4), SOU_4);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.yakuManager.getRonYakus().isEmpty());
        assertTrue(taData.yakuManager.getTsumoYakus().isEmpty());
    }

}
