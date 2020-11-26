package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair.AnalyzeSevenPairTenpai;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans.AnalyzeThirteenOrphansTenpai;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.rule.GameRule;

/**  
 * @ClassName: AnalyzeTenpaiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
public class AnalyzeTenpaiable {

    private GameRule rule;

    private AnalyzeThirteenOrphansTenpai thirteenOrphansAnalyzer = new AnalyzeThirteenOrphansTenpai();

    private AnalyzeSevenPairTenpai sevenPairAnalyzer = new AnalyzeSevenPairTenpai();

    private AnalyzeMeld4Pair1Tenpai meld4Pair1Analyzer = new AnalyzeMeld4Pair1Tenpai();

    public AnalyzeTenpaiable(GameRule rule) {
        super();
        this.rule = rule;
    }

    public TenpaiManager analyze(PlayerTileManager playerTileManager) {
        List<Tenpaiable> ans = new ArrayList<>();

        analyzeThirteenOrphans(playerTileManager, ans);

        if (ans.isEmpty()) {// 如果听国士无双，就不可能听七对子或者四面子一雀头
            analyzeSevenPair(playerTileManager, ans);
            analyzeMeld4Pair1(playerTileManager, ans);
        }

        return new TenpaiManager(ans, rule, playerTileManager);
    }

    private void analyzeMeld4Pair1(PlayerTileManager playerTileManager, List<Tenpaiable> ans) {
        ans.addAll(meld4Pair1Analyzer.analyze(playerTileManager));
    }

    private void analyzeSevenPair(PlayerTileManager playerTileManager, List<Tenpaiable> ans) {
        ans.addAll(sevenPairAnalyzer.analyze(playerTileManager));
    }

    private void analyzeThirteenOrphans(PlayerTileManager playerTileManager, List<Tenpaiable> ans) {
        ans.addAll(thirteenOrphansAnalyzer.analyze(playerTileManager));
    }

}
