package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import java.util.List;

import lombok.Getter;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.Wait2Side;

/**  
 * @ClassName: Meld3Pair1Wait2side1  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */

@Getter
public class Meld3Pair1Wait2Side1 extends Meld4Pair1Tenpaiable {

    private Pair pair;
    private Wait2Side wait2Side;

    private Meld3Pair1Wait2Side1(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, Pair pair,
        Wait2Side wait2Side) {

        super(concealedSequence, concealedTriplet);

        assert pair != null && wait2Side != null;
        this.pair = pair;
        this.wait2Side = wait2Side;
    }

    public static Meld3Pair1Wait2Side1 of(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, Pair pair,
        Wait2Side wait2Side) {
        return new Meld3Pair1Wait2Side1(concealedSequence, concealedTriplet, pair, wait2Side);
    }

    @Override
    public List<TileType> getTilesToWin() {
        return wait2Side.getTilesToWin();
    }

    @Override
    public TileType pairType(TileType tileToWin) {
        return getPair().type();
    }

}
