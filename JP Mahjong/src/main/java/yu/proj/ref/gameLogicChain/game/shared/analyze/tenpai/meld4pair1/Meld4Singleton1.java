package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import java.util.List;

import lombok.Getter;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.concealedTile.Singleton;

/**  
 * @ClassName: Meld4Singleton1  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */

@Getter
public class Meld4Singleton1 extends Meld4Pair1Tenpaiable {

    private Singleton singleton;

    private Meld4Singleton1(List<Sequence> concealedSequence, List<Triplet> concealedTriplet, Singleton singleton) {
        super(concealedSequence, concealedTriplet);

        assert singleton != null;

        this.singleton = singleton;
    }

    public static Meld4Singleton1 of(List<Sequence> concealedSequence, List<Triplet> concealedTriplet,
        Singleton singleton) {
        return new Meld4Singleton1(concealedSequence, concealedTriplet, singleton);
    }

    @Override
    public List<TileType> getTilesToWin() {
        return singleton.getTilesToWin();
    }
}
