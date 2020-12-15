package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import java.util.function.Function;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: SimpleConditionYakuAnalyzer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月13日  
 *  
 */
public abstract class SimpleConditionYakuAnalyzer implements YakuAnalyzer {

    private Function<TilesCounterUtilForPatternAnalyze, Boolean> condition;

    private Yaku yaku;

    protected SimpleConditionYakuAnalyzer(Function<TilesCounterUtilForPatternAnalyze, Boolean> condition, Yaku yaku) {
        super();

        assert condition != null && yaku != null;

        this.condition = condition;
        this.yaku = yaku;
    }

    @Override
    public void analyzeYaku(YakuAnalyzeData data, PatternAndYaku yakuManager) {
        if (condition.apply(data.getTilesCountUtil())) {
            yakuManager.both(yaku);
        }
    }
}
