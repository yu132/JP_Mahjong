package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku._facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.PatternAndYaku;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: YakuManager  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class YakuManager {

    private EnumMap<TileType, List<PatternAndYaku>> tileToWinAndPatternsAndYakusMap = new EnumMap<>(TileType.class);

    public void addPatternAndYaku(TileType tileToWin, PatternAndYaku patternAndYaku) {
        tileToWinAndPatternsAndYakusMap.computeIfAbsent(tileToWin, (x) -> new ArrayList<>()).add(patternAndYaku);
    }

    public List<PatternAndYaku> getPatternsAndYakus(TileType tileToWin) {
        return tileToWinAndPatternsAndYakusMap.getOrDefault(tileToWin, Collections.emptyList());
    }

    public Set<TileType> getTilesToWin() {
        return tileToWinAndPatternsAndYakusMap.keySet();
    }

}
