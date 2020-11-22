package yu.proj.ref.gameLogicChain.game.shared.playerAction.sendAndReceive.timeLimit;

/**  
 * @ClassName: TimeLimiter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */
public class BaseAndAddtionalTimeLimiter {

    private int additionalTime;

    private final int baseTime;

    public BaseAndAddtionalTimeLimiter(int otherTime, int baseTime) {
        super();
        this.additionalTime = otherTime;
        this.baseTime       = baseTime;
    }

    public int getTime() {
        return baseTime + additionalTime;
    }

    public void costTime(long beforeAction, long afterAction) {
        int second                 = (int)((afterAction - beforeAction) / 1000);

        // 除了基准时间之外，消费的时间，即使基准时间没消耗完也不可能为负数
        int timeCostExceptBaseTime = Math.max(second - baseTime, 0);

        // 当基准时间被消耗完，就消耗额外时间，额外时间不可能为负数
        additionalTime = Math.max(additionalTime - timeCostExceptBaseTime, 0);
    }

    public void costAllAdditionalTime() {
        additionalTime = 0;
    }

}
