package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair2;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
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

    private final static YakuAnalyzer SINGLETON = new AnalyzeFourConcealedTriplets();

    private AnalyzeFourConcealedTriplets() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        if (data.getTilesCountUtil().concealedTripletNum() == 4 && data.getTenpaiable() instanceof Meld3Pair2) {
            // 荣和的刻子不是暗刻，因此记为三暗刻，因此判断逻辑在三暗刻分析器中
            yakuManager.tsumo(Yaku.FOUR_CONCEALED_TRIPLETS);
        }
    }

}
