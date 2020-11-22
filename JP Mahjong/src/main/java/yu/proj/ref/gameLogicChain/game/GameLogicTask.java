package yu.proj.ref.gameLogicChain.game;

/**  
 * @ClassName: GameLogic  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */
public interface GameLogicTask {

    GameLogicTask[] nextLogicTasks();

    void executeLogicTask(GameLogicData data);

}
