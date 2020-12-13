package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.allTriplets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeAllTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeAllTriplets implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        if (data.getTilesCountUtil().tripletTotalNum() == 4) {
            yakuManager.both(Yaku.ALL_TRIPLETS);
        }
    }

}
