package yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.TileType;

/**  
 * @ClassName: AnalyzePonAndKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月11日  
 *  
 */
public class AnalyzePonAndExposedKan {

    public Map<Integer, Pon> analyzePon(CountNum counter) {

        List<Pon> ans = new ArrayList<>();

        for (int i = M1; i <= R; ++i) {
            if (counter.getCount(i) >= 2) {
                Pon pon;
                if (i == M5) {
                    int N5 = counter.getCount(M5);
                    int r5 = counter.getRCount(TileType.MAN);
                    pon = new Pon(i, N5 - r5, r5);
                } else if (i == P5) {
                    int N5 = counter.getCount(P5);
                    int r5 = counter.getRCount(TileType.PIN);
                    pon = new Pon(i, N5 - r5, r5);
                } else if (i == S5) {
                    int N5 = counter.getCount(S5);
                    int r5 = counter.getRCount(TileType.SOU);
                    pon = new Pon(i, N5 - r5, r5);
                } else {
                    pon = new Pon(i, counter.getCount(i), 0);
                }
                ans.add(pon);
            }
        }

        Map<Integer, Pon> map = new HashMap<>();

        for (Pon pon : ans) {
            map.put(pon.getTileToPon(), pon);
        }

        return map;
    }

    public Map<Integer, ExposedKan> analyzeKan(CountNum counter) {
        List<ExposedKan> ans = new ArrayList<>();

        for (int i = M1; i <= R; ++i) {
            if (counter.getCount(i) == 3) {
                ExposedKan kan = new ExposedKan(i);
                ans.add(kan);
            }
        }

        Map<Integer, ExposedKan> map = new HashMap<>();

        for (ExposedKan kan : ans) {
            map.put(kan.getTileToKan(), kan);
        }

        return map;
    }
}
