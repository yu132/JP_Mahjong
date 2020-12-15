package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pinfu;

import static yu.proj.ref.tile.TileType.*;

import java.util.Arrays;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair1Wait2Side1;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzePinfu  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class AnalyzePinfu implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzePinfu();

    private AnalyzePinfu() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        if (isPinfu(data)) {
            yakuManager.both(Yaku.PINFU);
        }
    }

    public boolean isPinfu(YakuAnalyzeData data) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        boolean isMenzenchin = countUtil.isMenzenchin();

        boolean _4Sequence = countUtil.sequenceTotalNum() == 4;

        boolean wait2side = // 必须为不是边张听的真正的两面听
            data.getTenpaiable() instanceof Meld3Pair1Wait2Side1 && data.getTenpaiable().getTilesToWin().size() == 2;

        boolean isNotDragonNorPrevalentWindNorSeatWind = wait2side && // 此处使用短路与是因为后续判断必须为两面听才合法
            isNotDragonNorPrevalentWindNorSeatWind(data);

        return isMenzenchin && _4Sequence && wait2side && isNotDragonNorPrevalentWindNorSeatWind;
    }

    private boolean isNotDragonNorPrevalentWindNorSeatWind(YakuAnalyzeData data) {
        Meld3Pair1Wait2Side1 meld3Pair1Wait2Side1 = (Meld3Pair1Wait2Side1)data.getTenpaiable();

        List<TileType> banPair = Arrays.asList(WHITE, GREEN, RED, data.getPrevalentWind(), data.getSeatWind());

        return !banPair.contains(meld3Pair1Wait2Side1.getPair().type());
    }

}
