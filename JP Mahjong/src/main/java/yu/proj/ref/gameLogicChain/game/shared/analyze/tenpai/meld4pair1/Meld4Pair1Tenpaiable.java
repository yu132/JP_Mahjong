package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static yu.proj.ref.utils.MyListUtils.*;

import java.util.List;

import lombok.Getter;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;

/**  
 * @ClassName: Meld4Pair1Tenpaiable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */
@Getter
public abstract class Meld4Pair1Tenpaiable implements Tenpaiable {

    private List<Sequence> concealedSequence;
    private List<Triplet> concealedTriplet;

    protected Meld4Pair1Tenpaiable(List<Sequence> concealedSequence, List<Triplet> concealedTriplet) {
        super();
        this.concealedSequence = unmodifiableCopy(concealedSequence);
        this.concealedTriplet = unmodifiableCopy(concealedTriplet);
    }

    public abstract TileType pairType(TileType tileToWin);

}
