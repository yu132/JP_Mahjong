package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeFourLittleWinds  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeFourLittleWinds extends SimpleConditionYakuAnalyzer {

    private final static int NUM_OF_FOUR_LITTLE_WINDS = 3 * 3 + 2;// 包括风牌的三个刻子和一个对子

    private final static YakuAnalyzer SINGLETON = new AnalyzeFourLittleWinds();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeFourLittleWinds() {
        super((util) -> util.countWinds() == NUM_OF_FOUR_LITTLE_WINDS, Yaku.FOUR_LITTLE_WINDS);
    }

}