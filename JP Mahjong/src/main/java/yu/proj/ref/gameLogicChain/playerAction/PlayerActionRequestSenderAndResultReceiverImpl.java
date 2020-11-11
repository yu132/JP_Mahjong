package yu.proj.ref.gameLogicChain.playerAction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.AllArgsConstructor;
import yu.proj.ref.gameLogicChain.playerAction.timeLimit.BaseAndAddtionalTimeLimiter;

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

    private BlockingQueue<PlayerAction> actionQueue;

    private ExecutorService threadPool;

    private GetPalyerAction<D> getPlayerAction;

    private BaseAndAddtionalTimeLimiter timeLimiter;

    @Override
    public void requestPlayerAction(final D data) {
        Runnable task = () -> {
            Future<PlayerAction> future = threadPool.submit(() -> {
                return getPlayerAction.getPalyerAction(data);
            });
            try {
                long         timeNow   = System.currentTimeMillis();
                PlayerAction f         = future.get(timeLimiter.getTime(), TimeUnit.SECONDS);
                long         timeAfter = System.currentTimeMillis();
                timeLimiter.costTime(timeNow, timeAfter);
                actionQueue.add(f);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                timeLimiter.costAllAdditionalTime();
                actionQueue.add(PlayerAction.TIMEOUT_ACTION);
            }
        };
        threadPool.execute(task);
    }

}
