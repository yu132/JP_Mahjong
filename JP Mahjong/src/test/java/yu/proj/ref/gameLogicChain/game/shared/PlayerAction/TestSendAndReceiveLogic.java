package yu.proj.ref.gameLogicChain.game.shared.PlayerAction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.playerAction.PlayerAction;
import yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive.GetPalyerAction;
import yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive.PlayerActionRequestSenderAndResultReceiver;
import yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive.PlayerActionRequestSenderAndResultReceiverImpl;
import yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive.timeLimit.BaseAndAddtionalTimeLimiter;
import yu.proj.ref.player.Player;
import yu.proj.ref.utils.KeyValuePair;

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

    private BlockingQueue<KeyValuePair<Player, PlayerAction>> actionQueue = new LinkedBlockingDeque<>();

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private BaseAndAddtionalTimeLimiter timeLimiter = new BaseAndAddtionalTimeLimiter(3, 1);

    private Player testPlayer = new Player();

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
        new PlayerActionRequestSenderAndResultReceiverImpl<Integer>(actionQueue, threadPool, sendMesgLogic, testPlayer,
            timeLimiter);

    @Test
    public void test() throws InterruptedException {
        has4SecondSoTimeIsEnough();
        has2SecondSoTimeout();
    }

    private void has2SecondSoTimeout() throws InterruptedException {
        Assert.assertEquals(2, timeLimiter.getTime());
        sendAndReceiveLogic.requestPlayerAction(1);
        Assert.assertEquals(PlayerAction.TIMEOUT_ACTION, actionQueue.poll(1, TimeUnit.DAYS).getValue());
    }

    private void has4SecondSoTimeIsEnough() throws InterruptedException {
        sendAndReceiveLogic.requestPlayerAction(1);
        KeyValuePair<Player, PlayerAction> ans1 = actionQueue.poll(1, TimeUnit.DAYS);
        Assert.assertEquals(TEST_ACTION, ans1.getValue());
        Assert.assertEquals(testPlayer, ans1.getKey());
    }

}
