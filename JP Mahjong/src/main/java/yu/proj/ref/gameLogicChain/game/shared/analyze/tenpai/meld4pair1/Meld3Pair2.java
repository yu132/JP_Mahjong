package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static yu.proj.ref.utils.MyListUtils.*;

import java.util.List;

import lombok.Getter;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;

/**  
 * @ClassName: Meld3Pair2  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */
@Getter
public class Meld3Pair2 extends Meld4Pair1Tenpaiable {

    private List<Pair> pair;

    private Meld3Pair2(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, List<Pair> pair) {
        super(concealedSequence, concealedTriplet);

        assert pair.size() == 2;

        this.pair = unmodifiableCopy(pair);
    }

    public static Meld3Pair2 of(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, List<Pair> pair) {
        return new Meld3Pair2(concealedSequence, concealedTriplet, pair);
    }
}
