package yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;

/**  
 * @ClassName: AnalyzeNineDifferntTerminalsandHonorsDraw  
 *
 * @Description: 检查是不是有九种以上的九牌
 *
 * @author 余定邦  
 *
 * @date 2020年10月9日  
 *  
 */
public class AnalyzeNineDifferntTerminalsandHonorsDraw {

    private final static int[] TO_CHECK = {M1, M9, P1, P9, S1, S9, E, S, W, N, WH, G, R};

    public boolean analyzeNineDifferntTerminalsandHonorsDraw(CountNum fullHand) {

        int sum = 0;

        for (int toCheck : TO_CHECK) {// 对于每种幺九牌都去检查一下
            if (fullHand.getCount(toCheck) > 0) {
                ++sum;
            }
        }

        return sum >= 9;// 如果确实有九种以上的九牌，那么就可以九种九牌流局
    }

}
