package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.pureDoubleSequence;

import java.util.Map.Entry;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzer;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.rule.yaku.pureFourSequence.PureFourSequenceRule;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: AnalyzePureDoubleSequence  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class AnalyzePureDoubleSequence implements YakuAnalyzer {

    private final static YakuAnalyzer SINGLETON = new AnalyzePureDoubleSequence();

    private AnalyzePureDoubleSequence() {}

    static public YakuAnalyzer getInstance() {
        return SINGLETON;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, YakuManager yakuManager) {

        int count = pureDoubleSequenceCount(data);

        if (count == 1) {
            yakuManager.both(Yaku.PURE_DOUBLE_SEQUENCE);
        } else if (count == 2) {
            yakuManager.both(Yaku.TWICE_PURE_DOUBLE_SEQUENCE);
        }
    }

    private int pureDoubleSequenceCount(YakuAnalyzeData data) {
        TilesCounterUtilForPatternAnalyze countUtil = data.getTilesCountUtil();

        int count = 0;

        for (Entry<TileType, Integer> seqTypeAndNum : countUtil.sequencesNum()) {

            int num = seqTypeAndNum.getValue();

            if (pureFourSequenceAsTwiceDoubleSequence(data)) {
                // 一色四通顺如果可以记作二杯口，那么整除2即可获取累加结果
                // 对于1个和3个同顺，都会舍去多余的那个通顺，只计2的倍数个同顺的数量
                count += num / 2;
            } else {
                // 如果不能计，那么无论有几个，当大于两个的时候，只记作一个2同顺
                // 保证一色四通顺只被记作一杯口
                if (num >= 2) {
                    ++count;
                }
            }
        }

        return count;
    }

    private boolean pureFourSequenceAsTwiceDoubleSequence(YakuAnalyzeData data) {
        return data.getRule().pureFourSequenceRule == PureFourSequenceRule.AS_TWICE_PURE_DOUBLE_SEQUENCE;
    }
}
