package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair;

import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileInHandGetter;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.Singleton;

/**  
 * @ClassName: AnalyzeSevenPairTenpai  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */

public class AnalyzeSevenPairTenpai {

    public List<SevenPairTenpaiable> analyze(PlayerTileManager playerTileManager) {

        if (isNotMenzenchin(playerTileManager)) {
            return Collections.emptyList();
        }

        List<Pair> pairs = new ArrayList<>();
        List<Singleton> singletons = new ArrayList<>();

        analyzePairsAndSingleton(playerTileManager, pairs, singletons);

        if (isNotSevenPairPattern(pairs, singletons)) {
            return Collections.emptyList();
        }

        return ofSevenPair(pairs, singletons);
    }

    private List<SevenPairTenpaiable> ofSevenPair(List<Pair> pairs, List<Singleton> singletons) {
        return Collections.singletonList(SevenPairTenpaiable.of(pairs, singletons.get(0)));
    }

    private boolean isNotSevenPairPattern(List<Pair> pairs, List<Singleton> singletons) {
        return pairs.size() != 6 || singletons.size() != 1;
    }

    private boolean isNotMenzenchin(PlayerTileManager playerTileManager) {
        return !(new TilesCounterUtilForPatternAnalyze(playerTileManager).isMenzenchin());
    }

    private void analyzePairsAndSingleton(PlayerTileManager playerTileManager, List<Pair> pairs,
        List<Singleton> singletons) {

        PlayerTileInHandGetter inHandTileGetter = playerTileManager.playerTileInHandGetter();

        forEachNormalTileType((tileType) -> {

            int tileNum = playerTileManager.countInHand(tileType);

            switch (tileNum) {
                case 2: {// 对子
                    pairs.add(Pair.of(new Tile[] {inHandTileGetter.claim(tileType), inHandTileGetter.claim(tileType)}));
                    break;
                }
                case 1: {// 单骑
                    singletons.add(Singleton.of(inHandTileGetter.claim(tileType)));
                    if (singletons.size() == 2) {
                        return;
                    }
                    break;
                }
                default:// 由于七对子中只能有对子和单骑，其他数量的牌是肯定不符合七对子的
                    return; // 甚至连四张同样的牌也不行，因为日麻中要求七对子是必须是不同的七个对子，相同的是不行的
            }
        });
    }



}
