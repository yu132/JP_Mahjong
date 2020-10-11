package yu.proj.jpmahjong.gamelogic.analyze.discardTile;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: AnalyzeDiscardTile  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class AnalyzeDiscardTile {

    public List<Tile> analyzeDiscardTile(CountNum concealedHand, ChiOperation chi, Rule rule) {

        List<Tile> tileAns = new ArrayList<>();

        for (int i = M1; i <= R; ++i) {// 对于每种牌去检测能不能打出这张牌并立直

            if (concealedHand.getCount(i) >= 1) {// 只要有一张手牌，就可以打出
                for (int j = concealedHand.getCount(i) - 1; j >= 0; --j) {
                    tileAns.add(concealedHand.getTile(i, j));
                }
            }

        }

        TileReplace.tileReplace(tileAns, chi, rule);// 食替

        Collections.sort(tileAns);

        return tileAns;
    }

}
