package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.MeldSource;

/**  
 * @ClassName: YakuManager  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月26日  
 *  
 */
@Getter
@Setter
public class PatternAndYaku {

    private Tenpaiable tenpaiable;
    private TileType tileToWin;

    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    private Set<Yaku> yakusWhenRon = new HashSet<>();
    @Setter(value = AccessLevel.NONE)
    @Getter(value = AccessLevel.NONE)
    private Set<Yaku> yakusWhenTsumo = new HashSet<>();

    private MeldSource bigThreeDragonResponsibility = null;
    private MeldSource fourBigWindResponsibility = null;
    private MeldSource fourQuadsResponsibility = null;

    public PatternAndYaku() {
        super();
    }

    public PatternAndYaku(Tenpaiable tenpaiable, TileType tileToWin) {
        super();
        this.tenpaiable = tenpaiable;
        this.tileToWin = tileToWin;
    }

    public void ron(Yaku yaku) {
        yakusWhenRon.add(yaku);
    }

    public void tsumo(Yaku yaku) {
        yakusWhenTsumo.add(yaku);
    }

    public void both(Yaku yaku) {
        ron(yaku);
        tsumo(yaku);
    }

    public Set<Yaku> getRonYakus() {
        return Collections.unmodifiableSet(yakusWhenRon);
    }

    public Set<Yaku> getTsumoYakus() {
        return Collections.unmodifiableSet(yakusWhenTsumo);
    }

}
