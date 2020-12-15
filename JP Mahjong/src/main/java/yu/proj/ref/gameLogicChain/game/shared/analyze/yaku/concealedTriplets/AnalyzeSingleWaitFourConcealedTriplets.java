package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.concealedTriplets;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Singleton1;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeSingleWaitFourConcealedTriplets  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeSingleWaitFourConcealedTriplets implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeSingleWaitFourConcealedTriplets();

    private AnalyzeSingleWaitFourConcealedTriplets() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        if (data.getTilesCountUtil().concealedTripletNum() == 4 && data.getTenpaiable() instanceof Meld4Singleton1) {
            yakuManager.both(Yaku.SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS);
        }
    }

}
