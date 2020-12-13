package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.quads;

import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.Meld;
import yu.proj.ref.tilePatternElement.exposedTile.ExposedKanQuad;

/**  
 * @ClassName: FourQuads  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeFourQuads implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        if (countUtil.quadTotalNum() == 4) {
            yakuManager.both(Yaku.FOUR_QUADS);

            analyzeResponsibility(data, yakuManager);
        }
    }

    private void analyzeResponsibility(YakuAnalyzeData data, YakuManager yakuManager) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        List<Meld> quadsOrder = countUtil.tripletsAndQuadsOrder();

        // 最后一个杠子是明杠的时候，就需要包牌
        if (lastQuad(quadsOrder) instanceof ExposedKanQuad) {
            ExposedKanQuad quad = (ExposedKanQuad)lastQuad(quadsOrder);
            yakuManager.setFourQuadsResponsibility(quad.getSrc());
        }
    }

    private Meld lastQuad(List<Meld> quadsOrder) {
        return quadsOrder.get(3);// 由于是四杠子，保证杠子一定有4个，因此可以用get(3)而不会越界
    }

}
