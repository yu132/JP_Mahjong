package yu.proj.jpmahjong.gamelogic.analyze.chi;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.TileType;

/**  
 * @ClassName: AnalyzeChi  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月11日  
 *  
 */
public class AnalyzeChi {

    public Map<Integer, List<Chi>> analyzeChi(CountNum counter) {

        List<Chi> ans = new ArrayList<>();

        helper(M1, TileType.MAN, counter, ans);
        helper(P1, TileType.PIN, counter, ans);
        helper(S1, TileType.SOU, counter, ans);

        Map<Integer, List<Chi>> map = new HashMap<>();

        for (Chi chi : ans) {
            map.computeIfAbsent(chi.getTileToChi(), (x) -> new ArrayList<>()).add(chi);
        }

        return map;
    }

    private void helper(int base, TileType type, CountNum counter, List<Chi> ans) {

        /*
         * 先计算每种数字牌的下标，index[n]对应序数牌n的下标，index[0]无效
         */
        int[] index = new int[10];

        for (int i = 1; i <= 9; ++i) {
            index[i] = base + i - 1;
        }

        for (int i = index[1]; i <= index[8]; ++i) {// 对于连续牌的吃，例如对于存在3s和4s，那么可以吃2s和5s

            // 如果这张牌有,并且这张牌的下一张也有，那么就可以吃前后的牌
            if (counter.getCount(i) >= 1 && counter.getCount(i + 1) >= 1) {

                if (i == index[1]) {

                    Chi chi = new Chi(index[3], new int[] {index[1], index[2]}, false, false, -1);

                    ans.add(chi);

                } else if (i == index[8]) {

                    Chi chi = new Chi(index[7], new int[] {index[8], index[9]}, false, false, -1);

                    ans.add(chi);

                } else if (i == index[4] || i == index[5]) {

                    int N5   = counter.getCount(index[5]);
                    int r5   = counter.getRCount(type);

                    Chi chi1 = new Chi(i - 1, new int[] {i, i + 1}, N5 - r5 != 0, r5 != 0, i + 2);
                    Chi chi2 = new Chi(i + 2, new int[] {i, i + 1}, N5 - r5 != 0, r5 != 0, i - 1);

                    ans.add(chi1);
                    ans.add(chi2);

                } else {
                    Chi chi1 = new Chi(i - 1, new int[] {i, i + 1}, false, false, i + 2);
                    Chi chi2 = new Chi(i + 2, new int[] {i, i + 1}, false, false, i - 1);

                    ans.add(chi1);
                    ans.add(chi2);
                }
            }
        }

        for (int i = index[1]; i <= index[7]; ++i) {// 对于坎张吃的分析，如存在3s和5s，能吃4s的情况

            // 如果这张牌有,并且这张牌的下下张也有，那么就可以吃中间的牌
            if (counter.getCount(i) >= 1 && counter.getCount(i + 2) >= 1) {
                if (i == index[3] || i == index[5]) {
                    int N5  = counter.getCount(index[5]);
                    int r5  = counter.getRCount(type);

                    Chi chi = new Chi(i + 1, new int[] {i, i + 2}, N5 - r5 != 0, r5 != 0, -1);
                    ans.add(chi);
                } else {
                    Chi chi = new Chi(i + 1, new int[] {i, i + 2}, false, false, -1);
                    ans.add(chi);
                }
            }

        }
    }

}
