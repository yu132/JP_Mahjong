package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.dragon;

import static yu.proj.ref.tile.TileType.*;

import java.util.Arrays;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.rule.responsibility.ResponsibilityRule.BigThreeDragon;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.Meld;
import yu.proj.ref.tilePatternElement.MeldSource;

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
public class AnalyzeBigThreeDragons implements YakuAnalyzer {

    private final static List<TileType> DRAGONS = Arrays.asList(WHITE, GREEN, RED);

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        if (hasAllDragonTriplets(data)) {
            yakuManager.both(Yaku.BIG_THREE_DRAGONS);
            if (enableResponsibility(data)) {
                analyzeResponsibility(data, yakuManager);
            }
        }
    }

    private void analyzeResponsibility(YakuAnalyzeData data, YakuManager yakuManager) {
        int count = 0;
        MeldSource src = null;

        for (Meld meld : data.getTilesCountUtil().tripletAndQuadOrderIgnoreAddKan()) {
            if (DRAGONS.contains(meld.tileType())) {
                ++count;
                src = meld.getSrc();
            }
        }

        if (count == 3 && src != MeldSource.SELF) {
            yakuManager.setBigThreeDragonResponsibility(src);
        }
    }

    private boolean enableResponsibility(YakuAnalyzeData data) {
        return data.getRule().responsibilityForBigThreeDragon == BigThreeDragon.ENABLE;
    }

    private boolean hasAllDragonTriplets(YakuAnalyzeData data) {
        return data.getTilesCountUtil().countDragons() == 3 * 3;// 白中发各有3个
    }

}
