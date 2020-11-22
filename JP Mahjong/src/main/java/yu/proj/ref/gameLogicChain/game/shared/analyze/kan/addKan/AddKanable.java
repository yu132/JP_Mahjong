package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.addKan;

import java.util.Arrays;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: AddKanable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddKanable {

    private TileType toAddKan;

    public static List<AddKanable> of(TileType toAddKan) {
        assert toAddKan != null;

        return Arrays.asList(new AddKanable(toAddKan));
    }

}
