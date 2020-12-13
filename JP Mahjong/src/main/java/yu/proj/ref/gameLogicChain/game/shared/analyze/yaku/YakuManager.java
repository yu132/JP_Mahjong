package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
public class YakuManager {

    private Set<Yaku> yakusWhenRon = new HashSet<>();
    private Set<Yaku> yakusWhenTsumo = new HashSet<>();

    private MeldSource bigThreeDragonResponsibility = null;
    private MeldSource fourBigWindResponsibility = null;
    private MeldSource fourQuadsResponsibility = null;

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

    public MeldSource getBigThreeDragonResponsibility() {
        return bigThreeDragonResponsibility;
    }

    public void setBigThreeDragonResponsibility(MeldSource bigThreeDragonResponsibility) {
        this.bigThreeDragonResponsibility = bigThreeDragonResponsibility;
    }

    public MeldSource getFourBigWindResponsibility() {
        return fourBigWindResponsibility;
    }

    public void setFourBigWindResponsibility(MeldSource fourBigWindResponsibility) {
        this.fourBigWindResponsibility = fourBigWindResponsibility;
    }

    public MeldSource getFourQuadsResponsibility() {
        return fourQuadsResponsibility;
    }

    public void setFourQuadsResponsibility(MeldSource fourQuadsResponsibility) {
        this.fourQuadsResponsibility = fourQuadsResponsibility;
    }

}
