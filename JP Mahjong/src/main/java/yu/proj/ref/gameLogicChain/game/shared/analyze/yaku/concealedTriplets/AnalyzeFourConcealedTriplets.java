package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair2;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeFourConcealedTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeFourConcealedTriplets implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        if (data.getTilesCountUtil().concealedTripletNum() == 4 && data.getTenpaiable() instanceof Meld3Pair2) {
            yakuManager.tsumo(Yaku.FOUR_CONCEALED_TRIPLETS);
            yakuManager.ron(Yaku.THREE_CONCEALED_TRIPLETS);// 荣和的刻子不是暗刻，因此记为三暗刻
        }
    }

}
