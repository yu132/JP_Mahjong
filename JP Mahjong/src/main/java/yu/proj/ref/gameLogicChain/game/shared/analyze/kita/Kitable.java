package yu.proj.ref.gameLogicChain.game.shared.analyze.kita;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**  
 * @ClassName: Kitable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Kitable {

    private final static Kitable SINGLETON = new Kitable();

    public static Kitable of() {
        return SINGLETON;
    }

}
