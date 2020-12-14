package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.wind;

import static yu.proj.ref.tile.TileType.*;

import java.util.Arrays;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.rule.responsibility.ResponsibilityRule.FourBigWind;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.Meld;
import yu.proj.ref.tilePatternElement.MeldSource;

/**  
 * @ClassName: AnalyzeFourBigWinds  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class AnalyzeFourBigWinds implements YakuAnalyzer {

    private final static List<TileType> WINDS = Arrays.asList(EAST, SOUTH, WEST, NORTH);

    private final static YakuAnalyzer SINGLETON = new AnalyzeFourBigWinds();

    private AnalyzeFourBigWinds() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {
        if (hasAllWindTriplets(data)) {
            yakuManager.both(Yaku.FOUR_BIG_WINDS);
            if (enableResponsibility(data)) {
                analyzeResponsibility(data, yakuManager);
            }
        }
    }

    private void analyzeResponsibility(YakuAnalyzeData data, YakuManager yakuManager) {
        int count = 0;
        MeldSource src = null;

        for (Meld meld : data.getTilesCountUtil().tripletAndQuadOrderIgnoreAddKan()) {
            if (WINDS.contains(meld.tileType())) {
                ++count;
                src = meld.getSrc();
            }
        }

        if (count == 4 && src != MeldSource.SELF) {
            yakuManager.setFourBigWindResponsibility(src);
        }
    }

    private boolean enableResponsibility(YakuAnalyzeData data) {
        return data.getRule().responsibilityForFourBigWind == FourBigWind.ENABLE;
    }

    private boolean hasAllWindTriplets(YakuAnalyzeData data) {
        return data.getTilesCountUtil().countWinds() == 3 * 4;// 四种风各有一个刻子
    }

}
