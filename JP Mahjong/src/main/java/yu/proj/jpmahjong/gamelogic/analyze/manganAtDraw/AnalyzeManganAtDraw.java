package yu.proj.jpmahjong.gamelogic.analyze.manganAtDraw;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

/**  
 * @ClassName: AnalyzeManganAtDraw  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年10月10日  
 *  
 */
public class AnalyzeManganAtDraw {

    public boolean analyzeManganAtDraw(boolean[] discardTiles) {
        for (int i = M2; i <= M8; ++i) {
            if (discardTiles[i]) {
                return false;
            }
        }
        for (int i = P2; i <= P8; ++i) {
            if (discardTiles[i]) {
                return false;
            }
        }
        for (int i = S2; i <= S8; ++i) {
            if (discardTiles[i]) {
                return false;
            }
        }
        return true;
    }

}
