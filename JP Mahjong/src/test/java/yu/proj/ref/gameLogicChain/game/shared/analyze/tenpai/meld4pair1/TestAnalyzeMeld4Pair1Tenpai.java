package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.MahjongSoulRule;

/**  
 * @ClassName: AnalyzeMeld4Pair1Tenpai  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */
public class TestAnalyzeMeld4Pair1Tenpai {

    private GameRule rule = MahjongSoulRule.mahjongSoulDefaultFourPalyerRule;

    private AnalyzeMeld4Pair1Tenpai analyzer = new AnalyzeMeld4Pair1Tenpai(rule);

    private TestAnalyzeData data = new TestAnalyzeData();

    @Test
    public void testManageChiiSequence() {

        data.chii(MAN_1, MAN_2, MAN_3);

        data.pon(SOU_8, SOU_8, SOU_8);

        data.draw(SOU_1, SOU_1, SOU_1);

        data.draw(PIN_1, PIN_1, PIN_1);

        data.draw(EAST);

        List<Meld4Pair1Tenpaiable> meld4Pair1Tenpaiables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, meld4Pair1Tenpaiables);
    }

}
