package yu.proj.ref.rule.draw;

/**  
 * @ClassName: WinnerNumRule  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月16日  
 *  
 */
public enum MaxWinnerNumRule {

    ONLY_ONE_WIN_AND_CLOSEST_WIN, // 头跳，并且最近的下家获胜
    ONLY_ONE_WIN_AND_THIRTEEN_ORPHANS_WIN, // 头跳，并且国士无双能有更高的优先级，如果没有国士无双，才是按照最近下家获胜的规则
    TWO_PLAYER_WIN, // 两家赢，即三家和了流局开启
    THREE_PLAYER_WIN;// 三家赢

}
