package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.quads;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.rule.responsibility.ResponsibilityRule.FourQuads;
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

        if (isFourQuads(data)) {
            yakuManager.both(Yaku.FOUR_QUADS);

            if (enableResponsibility(data)) {
                analyzeResponsibility(data, yakuManager);
            }

        }
    }

    private boolean enableResponsibility(YakuAnalyzeData data) {
        return data.getRule().responsibilityForFourQuads == FourQuads.ENABLE;
    }

    private boolean isFourQuads(YakuAnalyzeData data) {
        return data.getTilesCountUtil().quadTotalNum() == 4;
    }

    private void analyzeResponsibility(YakuAnalyzeData data, YakuManager yakuManager) {
        // 最后一个杠子是明杠的时候，就需要包牌
        if (lastQuad(data) instanceof ExposedKanQuad) {
            yakuManager.setFourQuadsResponsibility(((ExposedKanQuad)lastQuad(data)).getSrc());
        }
    }

    private Meld lastQuad(YakuAnalyzeData data) {
        return data.getTilesCountUtil().tripletsAndQuadsOrder().get(3);// 由于是四杠子，保证杠子一定有4个，因此可以用get(3)而不会越界
    }

}
