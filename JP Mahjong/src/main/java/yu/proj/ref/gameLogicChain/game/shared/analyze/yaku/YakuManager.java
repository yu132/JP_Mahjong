package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import yu.proj.ref.tile.Yaku;

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
