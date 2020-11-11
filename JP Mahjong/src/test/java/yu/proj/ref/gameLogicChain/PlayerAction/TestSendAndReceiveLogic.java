package yu.proj.ref.gameLogicChain.PlayerAction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.ref.gameLogicChain.playerAction.GetPalyerAction;
import yu.proj.ref.gameLogicChain.playerAction.PlayerAction;
import yu.proj.ref.gameLogicChain.playerAction.PlayerActionRequestSenderAndResultReceiver;
import yu.proj.ref.gameLogicChain.playerAction.PlayerActionRequestSenderAndResultReceiverImpl;
import yu.proj.ref.gameLogicChain.playerAction.timeLimit.BaseAndAddtionalTimeLimiter;

/**  
 * @ClassName: TestSendAndReceiveLogic  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */
public class TestSendAndReceiveLogic {

    private final static PlayerAction TEST_ACTION = new PlayerAction() {};

    private BlockingQueue<PlayerAction> actionQueue = new LinkedBlockingDeque<>();

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private BaseAndAddtionalTimeLimiter timeLimiter = new BaseAndAddtionalTimeLimiter(3, 1);

    private GetPalyerAction<Integer> sendMesgLogic = new GetPalyerAction<Integer>() {

        @Override
        public PlayerAction getPalyerAction(Integer data) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return TEST_ACTION;
        }
    };

    private PlayerActionRequestSenderAndResultReceiver<Integer> sendAndReceiveLogic =
        new PlayerActionRequestSenderAndResultReceiverImpl<Integer>(actionQueue, threadPool, sendMesgLogic,
            timeLimiter);

    @Test
    public void test() throws InterruptedException {
        sendAndReceiveLogic.requestPlayerAction(1);
        Assert.assertEquals(TEST_ACTION, actionQueue.poll(1, TimeUnit.DAYS));
        Assert.assertEquals(2, timeLimiter.getTime());
        sendAndReceiveLogic.requestPlayerAction(1);
        Assert.assertEquals(PlayerAction.TIMEOUT_ACTION, actionQueue.poll(1, TimeUnit.DAYS));
    }

}
