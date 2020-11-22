package yu.proj.ref.ops.tilesRelated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: DrawTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@AllArgsConstructor
@ToString
public class DrawOperation implements GainTileOperation {

    private Tile gainTile;

}
