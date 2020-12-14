package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import java.util.List;

import lombok.Getter;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.WaitMiddle;

/**  
 * @ClassName: Meld3Pair1WaitMiddle1  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */

@Getter
public class Meld3Pair1WaitMiddle1 extends Meld4Pair1Tenpaiable {

    private Pair pair;
    private WaitMiddle waitMiddle;

    private Meld3Pair1WaitMiddle1(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, Pair pair,
        WaitMiddle waitMiddle) {
        super(concealedSequence, concealedTriplet);

        assert pair != null && waitMiddle != null;

        this.pair = pair;
        this.waitMiddle = waitMiddle;
    }

    public static Meld3Pair1WaitMiddle1 of(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, Pair pair,
        WaitMiddle waitMiddle) {
        return new Meld3Pair1WaitMiddle1(concealedSequence, concealedTriplet, pair, waitMiddle);
    }

    @Override
    public List<TileType> getTilesToWin() {
        return waitMiddle.getTilesToWin();
    }

    @Override
    public TileType pairType(TileType tileToWin) {
        return getPair().type();
    }

}
