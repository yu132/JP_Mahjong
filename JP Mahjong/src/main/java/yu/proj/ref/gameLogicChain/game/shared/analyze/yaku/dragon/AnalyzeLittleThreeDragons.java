package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeBigThreeDragons  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public class AnalyzeLittleThreeDragons extends SimpleConditionYakuAnalyzer {

    private final static int NUM_OF_LITTLE_THREE_DRAGON = 3 * 2 + 2;// 包括三元牌的两个刻子和一个对子

    private final static YakuAnalyzer SINGLETON = new AnalyzeLittleThreeDragons();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeLittleThreeDragons() {
        super((util) -> util.countDragons() == NUM_OF_LITTLE_THREE_DRAGON, Yaku.LITTLE_THREE_DRAGONS);
    }

}
