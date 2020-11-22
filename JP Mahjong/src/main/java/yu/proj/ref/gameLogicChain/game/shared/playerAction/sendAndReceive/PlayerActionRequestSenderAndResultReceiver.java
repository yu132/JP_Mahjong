package yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive;

/**  
 * @ClassName: PlayerActionRequestSenderAndResultReceiver  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */
public interface PlayerActionRequestSenderAndResultReceiver<D> {

    void requestPlayerAction(D data);

}
