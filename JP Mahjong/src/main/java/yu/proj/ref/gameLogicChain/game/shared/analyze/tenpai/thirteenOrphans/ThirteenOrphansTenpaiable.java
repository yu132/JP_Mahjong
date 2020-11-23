package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: ThirteenOrphansTenpaiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThirteenOrphansTenpaiable {

    private List<Tile> terminalsAndHonors;

    private List<TileType> wait;

    public ThirteenOrphansTenpaiable of(List<Tile> terminalsAndHonors) {
        return null;
    }

}
