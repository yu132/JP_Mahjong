package yu.proj.ref.gameLogicChain.game.shared.analyze.draw.nineDifferntTerminalsandHonorsDraw;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**  
 * @ClassName: NineDifferntTerminalsandHonorsDrawable  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NineDifferntTerminalsandHonorsDrawable {

    private final static NineDifferntTerminalsandHonorsDrawable SINGLETON =
        new NineDifferntTerminalsandHonorsDrawable();

    public static NineDifferntTerminalsandHonorsDrawable of() {
        return SINGLETON;
    }

}
