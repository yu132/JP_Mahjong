package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair2;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeThreeConcealedTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeThreeConcealedTriplets implements YakuAnalyzer {

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        if (data.getTilesCountUtil().concealedTripletNum() == 3) {
            if (data.getTenpaiable() instanceof Meld3Pair2) {// 当双碰听的时候，由于最后一个已经默认计入暗刻
                // 但是荣和的时候，荣和的那个刻子是不算暗刻的，因此只有自摸计入番数，荣和只有两个暗刻，不计三暗刻
                yakuManager.tsumo(Yaku.THREE_CONCEALED_TRIPLETS);
            } else {
                yakuManager.both(Yaku.THREE_CONCEALED_TRIPLETS);
            }
        }
    }

}