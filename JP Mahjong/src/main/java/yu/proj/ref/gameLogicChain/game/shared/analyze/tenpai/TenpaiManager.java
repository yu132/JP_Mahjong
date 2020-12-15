package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.tenpai.NotRealTenpaiRule;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TenpaiManager  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
public class TenpaiManager {

    private GameRule rule;

    private EnumMap<TileType, List<Tenpaiable>> tileToWinAndTenpaiablesMap = new EnumMap<>(TileType.class);

    private PlayerTileManager playerTileManager;

    public TenpaiManager(List<Tenpaiable> tenpaiables, GameRule rule, PlayerTileManager playerTileManager) {
        this(rule, playerTileManager);
        addTenpaiables(tenpaiables);
    }

    public TenpaiManager(GameRule rule, PlayerTileManager playerTileManager) {
        super();
        this.rule = rule;
        this.playerTileManager = playerTileManager;
    }

    public void addTenpaiables(List<Tenpaiable> tenpaiables) {
        for (Tenpaiable tenpaiable : tenpaiables) {
            addTenpaiable(tenpaiable);
        }
    }

    public void addTenpaiable(Tenpaiable tenpaiable) {
        for (TileType tileToWin : tenpaiable.getTilesToWin()) {
            if (allowNotRealTenpai() || isNotNotRealTenpai(tileToWin)) {// 虚听处理
                tileToWinAndTenpaiablesMap.computeIfAbsent(tileToWin, (x) -> new ArrayList<>()).add(tenpaiable);
            }
        }
    }

    private boolean isNotNotRealTenpai(TileType tileToWin) {
        return playerTileManager.countNormalAndRedInHand(tileToWin) < 4;
    }

    private boolean allowNotRealTenpai() {
        return rule.notRealTenpaiRule == NotRealTenpaiRule.ENABLE;
    }

    public List<Tenpaiable> getTenpaiable(TileType type) {
        return tileToWinAndTenpaiablesMap.getOrDefault(type, Collections.emptyList());
    }

    public Set<TileType> getTilesToWin() {
        return tileToWinAndTenpaiablesMap.keySet();
    }
}
