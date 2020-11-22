package yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive;

import yu.proj.ref.gameLogicChain.game.shared.playerAction.PlayerAction;

/**  
 * @ClassName: GetPalyerAction  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */
public interface GetPalyerAction<D> {

    PlayerAction getPalyerAction(D data);

}
