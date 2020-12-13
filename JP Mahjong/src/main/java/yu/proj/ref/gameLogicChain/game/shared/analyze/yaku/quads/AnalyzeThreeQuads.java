package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.quads;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeThreeQuads  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeThreeQuads implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        if (isThreeQuads(data)) {
            yakuManager.both(Yaku.THREE_QUADS);
        }
    }

    private boolean isThreeQuads(YakuAnalyzeData data) {
        return data.getTilesCountUtil().quadTotalNum() == 3;
    }
}
