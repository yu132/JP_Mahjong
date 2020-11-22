package yu.proj.ref.gameLogicChain.game.shared.gameTilesShuffler;

import java.util.concurrent.ThreadLocalRandom;

import cn.hutool.core.util.ArrayUtil;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: GameTilesShuffler  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */
public class GameTilesShufflerImpl implements GameTilesShuffler {

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public Tile[] shuffle(Tile[] tiles) {
        knuthDurstenfeldShuffle(tiles);
        return tiles;
    }

    private void knuthDurstenfeldShuffle(Tile[] tiles) {
        for (int i = tiles.length; i > 0; --i) {
            int chosenIndex = random.nextInt(i);
            ArrayUtil.swap(tiles, chosenIndex, i - 1);
        }
    }

}
