package yu.proj.ref.gameLogicChain.game.shared.analyze.utils;

import static yu.proj.ref.tile.TileType.*;

import java.util.Arrays;
import java.util.List;

import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TileTypeGroup  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */
public class TileTypeGroup {

    public final static List<TileType> TERMINALS_AND_HONORS =
        Arrays.asList(MAN_1, MAN_9, PIN_1, PIN_9, SOU_1, SOU_9, EAST, SOUTH, WEST, NORTH, WHITE, GREEN, RED);

}
