package yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.AllArgsConstructor;
import yu.proj.ref.gameLogicChain.game.shared.playerAction.PlayerAction;
import yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive.timeLimit.BaseAndAddtionalTimeLimiter;
import yu.proj.ref.player.Player;
import yu.proj.ref.utils.KeyValuePair;

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

@AllArgsConstructor
public class PlayerActionRequestSenderAndResultReceiverImpl<D>
    implements PlayerActionRequestSenderAndResultReceiver<D> {

    private BlockingQueue<KeyValuePair<Player, PlayerAction>> actionQueue;

    private ExecutorService threadPool;

    private GetPalyerAction<D> getPlayerAction;

    private Player player;

    private BaseAndAddtionalTimeLimiter timeLimiter;

    @Override
    public void requestPlayerAction(final D data) {
        threadPool.execute(generateSendAndReceiveTask(data));
    }

    private Runnable generateSendAndReceiveTask(final D data) {
        return () -> {
            Future<PlayerAction> future = sendDataAndReceivePlayerAction(data);
            try {
                waitSendingLogicResultUntilTimeout(future);
            } catch (TimeoutException e) {
                handleTimeout(future);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e);
                throw new RuntimeException("并发线程池执行错误");
            }
        };
    }

    private void handleTimeout(Future<PlayerAction> future) {
        future.cancel(true);
        timeLimiter.costAllAdditionalTime();
        actionQueue.add(new KeyValuePair<>(player, PlayerAction.TIMEOUT_ACTION));
    }

    private void waitSendingLogicResultUntilTimeout(Future<PlayerAction> future)
        throws InterruptedException, ExecutionException, TimeoutException {
        long         timeNow      = System.currentTimeMillis();
        PlayerAction playerAction = future.get(timeLimiter.getTime(), TimeUnit.SECONDS);
        long         timeAfter    = System.currentTimeMillis();
        timeLimiter.costTime(timeNow, timeAfter);
        actionQueue.add(new KeyValuePair<>(player, playerAction));
    }

    private Future<PlayerAction> sendDataAndReceivePlayerAction(final D data) {
        return threadPool.submit(() -> {
            return getPlayerAction.getPalyerAction(data);
        });
    }

}
