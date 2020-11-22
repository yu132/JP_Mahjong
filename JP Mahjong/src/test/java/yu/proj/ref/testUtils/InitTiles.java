package yu.proj.ref.testUtils;

import yu.proj.ref.gameLogicChain.game.shared.gameTilesInitializer.FourPlayerGameTilesInitializerImpl;
import yu.proj.ref.gameLogicChain.game.shared.gameTilesInitializer.GameTilesInitializer;
import yu.proj.ref.gameLogicChain.game.shared.gameTilesShuffler.GameTilesShuffler;
import yu.proj.ref.gameLogicChain.game.shared.gameTilesShuffler.GameTilesShufflerImpl;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: InitTiles  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月15日  
 *  
 */
public class InitTiles {

    public static Tile[] initTilesAndShuffle(int manRedNum, int pinRedNum, int souRedNum) {
        GameTilesInitializer gameTilesInitializer =
            new FourPlayerGameTilesInitializerImpl(manRedNum, pinRedNum, souRedNum);
        GameTilesShuffler    gameTilesShuffler    = new GameTilesShufflerImpl();

        Tile[]               tiles                = gameTilesInitializer.initializeTiles();

        gameTilesShuffler.shuffle(tiles);

        return tiles;
    }

}
