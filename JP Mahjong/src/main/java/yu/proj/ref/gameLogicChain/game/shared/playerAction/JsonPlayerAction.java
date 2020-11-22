package yu.proj.ref.gameLogicChain.game.shared.playerAction;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**  
 * @ClassName: JsonPlayerAction  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */

@Getter
@AllArgsConstructor
public class JsonPlayerAction implements PlayerAction {

    private String jsonData;

}
