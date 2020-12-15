package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade.m4p1;

import lombok.AllArgsConstructor;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Pair1Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Meld4Pair2TenpaiableYakuAnalyzer  
 *
 * @Description: 作为役分析中，4面子1雀头的分析门面
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
@AllArgsConstructor
public abstract class Meld4Pair1TenpaiableYakuAnalyzer {

    protected GameRule rule;
    protected TileType prevalentWind;
    protected TileType seatWind;
    protected PlayerTileManager playerTileManager;

    public abstract PatternAndYaku analyze(Meld4Pair1Tenpaiable tenpaiable, TileType tileToWin);

}
