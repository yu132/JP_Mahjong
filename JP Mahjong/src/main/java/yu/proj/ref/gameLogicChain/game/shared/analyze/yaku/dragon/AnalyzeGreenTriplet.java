package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon;

import static yu.proj.ref.tile.TileType.*;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.AnalyzeTriplet;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: White  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class AnalyzeGreenTriplet extends AnalyzeTriplet {

    @Override
    protected TileType getTripletType(YakuAnalyzeData data) {
        return GREEN;
    }

    @Override
    protected Yaku getYaku(YakuAnalyzeData data) {
        return Yaku.DRAGON_G;
    }

}
