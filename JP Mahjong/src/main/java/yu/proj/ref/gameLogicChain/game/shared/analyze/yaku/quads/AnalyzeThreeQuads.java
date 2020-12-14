package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.quads;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.SimpleConditionYakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
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
public class AnalyzeThreeQuads extends SimpleConditionYakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzeThreeQuads();

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    private AnalyzeThreeQuads() {
        super((util) -> util.quadTotalNum() == 3, Yaku.THREE_QUADS);
    }

}

