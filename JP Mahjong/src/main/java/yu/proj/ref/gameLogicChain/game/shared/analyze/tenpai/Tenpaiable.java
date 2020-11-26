package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai;

import java.util.List;

import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: Tenpaiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
public interface Tenpaiable {

    List<TileType> getTilesToWin();

}
