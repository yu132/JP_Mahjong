package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.thirteenOrphans;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans.ThirteenOrphansTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: ThirteenOrphansTenpaiableYakuAnalyzer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月16日  
 *  
 */
public class ThirteenOrphansTenpaiableYakuAnalyzer {

    public PatternAndYaku analyze(ThirteenOrphansTenpaiable tenpaiable, TileType tileToWin) {
        PatternAndYaku patternAndYaku = new PatternAndYaku(tenpaiable, tileToWin);

        if (tenpaiable.getWait().size() == 1) {
            patternAndYaku.both(Yaku.THIRTEEN_ORPHANS);
        } else {
            patternAndYaku.both(Yaku.THIRTEEN_WAIT_THIRTEEN_ORPHANS);
        }

        return patternAndYaku;
    }
}
