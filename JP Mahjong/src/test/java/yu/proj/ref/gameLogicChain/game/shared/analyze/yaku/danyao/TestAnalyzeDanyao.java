package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.danyao;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.rule.danyao.DanyaoRule;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestAnalyzeDanyao  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
public class TestAnalyzeDanyao {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private AnalyzeDanyao analyzer = new AnalyzeDanyao();

    @Test
    public void test() {
        taData.draw(MAN_2, MAN_2, MAN_2);

        taData.draw(MAN_4, MAN_4, MAN_4);

        taData.draw(MAN_6, MAN_6, MAN_6);

        taData.draw(MAN_8, MAN_8, MAN_8);

        taData.draw(PIN_2);

        YakuAnalyzeData yaData = taData.yaData(taData.getFirstTenpai(PIN_2), PIN_2);

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothContainYaku(Yaku.DANYAO));
    }

    @Test
    public void testNotAllowMakeCall() {
        taData.pon(MAN_2, MAN_2, MAN_2);// 碰了，非门清

        taData.draw(MAN_4, MAN_4, MAN_4);

        taData.draw(MAN_6, MAN_6, MAN_6);

        taData.draw(MAN_8, MAN_8, MAN_8);

        taData.draw(PIN_2);

        GameRule rule = // 设置不允许食断的规则
            MahjongSoulRule.mahjongSoulDefaultFourPalyerRule.toBuilder().danyaoRule(DanyaoRule.ONLY_MENZENCHIN).build();

        YakuAnalyzeData yaData = taData.yaBuilder(taData.getFirstTenpai(PIN_2), PIN_2).rule(rule).build();

        analyzer.analyzeYaku(yaData, taData.yakuManager);

        assertTrue(taData.bothNotContainYaku(Yaku.DANYAO));
    }


}
