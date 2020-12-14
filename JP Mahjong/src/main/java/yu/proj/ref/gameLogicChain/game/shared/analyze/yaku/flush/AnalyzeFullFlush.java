package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.flush;

import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzeFullFlush  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeFullFlush extends AnalyzeFlush {

    protected AnalyzeFullFlush() {
        super((util) -> isFullFlush(util), Yaku.FULL_FLUSH);
    }

}
