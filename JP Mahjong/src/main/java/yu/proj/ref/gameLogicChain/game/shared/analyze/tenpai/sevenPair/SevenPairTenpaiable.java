package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.sevenPair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.Singleton;
import yu.proj.ref.utils.MyListUtils;

/**  
 * @ClassName: SevenPairTenpaiable  
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
public class SevenPairTenpaiable {

    private List<Pair> pairs;
    private Singleton singleton;

    public static SevenPairTenpaiable of(List<Pair> pairs, Singleton singleton) {

        assert pairs.size() == 6 && singleton != null;
        assert MyListUtils.allNotNull(pairs);

        List<Pair> unmodifiableCopy = Collections.unmodifiableList(new ArrayList<>(pairs));

        return new SevenPairTenpaiable(unmodifiableCopy, singleton);
    }
}
