package yu.proj.ref.gameLogicChain.game.shared.gameTilesManager;

import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: GameTilesManager  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */
public interface GameTilesManager {

    boolean isStarted();

    boolean isDraw();

    int tileToDrawNum();

    Tile draw();

    Tile kan();

    Tile[] doraIndicator();

    Tile[] uraDoraIndicator();

}
