package yu.proj.ref.rule.win;

/**  
 * @ClassName: DealerContinueFirst  
 *
 * @Description: 是否开启连庄优先，如果在分数不够导致西入或者南入的时候，有一家达到最低完场分数
 *               但是庄家没有达到且没人击飞且继续连庄的情况下，那么庄家继续连庄而不是结束比赛 
 *
 * @author 余定邦  
 *
 * @date 2020年11月17日  
 *  
 */
public enum DealerContinueFirstRule {

    DEFAULT_YES, OPTIONAL, NOT_ALLOW;

}
