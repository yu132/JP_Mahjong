package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.thirteenOrphans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.gameLogicChain.game.shared.analyze.utils.TileTypeGroup;
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

    public static ThirteenOrphansTenpaiable of(List<Tile> terminalsAndHonors) {

        assert checkTileType(terminalsAndHonors);

        if (has13DifferentTerminalsAndHonors(terminalsAndHonors)) {
            return new ThirteenOrphansTenpaiable(terminalsAndHonors, TileTypeGroup.TERMINALS_AND_HONORS);
        }

        return getNormalThirteenOrphans(terminalsAndHonors);
    }

    private static ThirteenOrphansTenpaiable getNormalThirteenOrphans(List<Tile> terminalsAndHonors) {
        List<TileType> allTerminalsAndHonors = new ArrayList<>(TileTypeGroup.TERMINALS_AND_HONORS);

        allTerminalsAndHonors.removeAll(getTypeSet(terminalsAndHonors));

        TileType missingType = allTerminalsAndHonors.get(0);

        return new ThirteenOrphansTenpaiable(terminalsAndHonors, Collections.singletonList(missingType));
    }

    private static boolean has13DifferentTerminalsAndHonors(List<Tile> terminalsAndHonors) {
        return getTypeSet(terminalsAndHonors).size() == 13;
    }

    private static boolean checkTileType(List<Tile> terminalsAndHonors) {
        Set<TileType> set = getTypeSet(terminalsAndHonors);
        return sizeIsRight(set) && typeIsRight(set);
    }

    private static boolean typeIsRight(Set<TileType> set) {
        return TileTypeGroup.TERMINALS_AND_HONORS.containsAll(set);
    }

    private static boolean sizeIsRight(Set<TileType> set) {
        return set.size() == 12 || set.size() == 13;
    }

    private static Set<TileType> getTypeSet(List<Tile> terminalsAndHonors) {
        Set<TileType> set = new HashSet<>();

        for (Tile tile : terminalsAndHonors) {
            set.add(tile.getTileType());
        }

        return set;
    }

}
