package yu.proj.jpmahjong.gamelogic.analyze.discardTile;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.win.AnalyzeTenpai;
import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.meld.Meld;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;

/**  
 * @ClassName: AnalyzeRiichi  
 *
 * @Description: 分析哪些牌打完之后是可以立直（即听牌）的
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class AnalyzeRiichi {

    public List<Tile> analyzeRiichi(CountNum concealedHand, List<Meld> makeCall, List<Meld> concealedKan, Rule rule,
        ChiOperation chi) {

        if (makeCall.size() != 0) {// 非门清的情况下不能立直
            return null;
        }

        List<Tile> tileAns = new ArrayList<>();

        for (int i = M1; i <= R; ++i) {// 对于每种牌去检测能不能打出这张牌并立直

            if (concealedHand.getCount(i) < 1) {// 这种牌一张都没有
                continue;
            }

            AnalyzeTenpai                       analyzeTenpai =
                new AnalyzeTenpai(rule, concealedHand, makeCall, concealedKan, i);

            Map<Integer, List<TilePatternNode>> tenpaiAns     = analyzeTenpai.isTenpai();

            if (!tenpaiAns.isEmpty()) {// 只要能听牌，就可以立直
                for (int j = concealedHand.getCount(i) - 1; j >= 0; --j) {
                    tileAns.add(concealedHand.getTile(i, j));
                }
            }
        }

        TileReplace.tileReplace(tileAns, chi, rule);// 食替

        Collections.sort(tileAns);

        return tileAns.isEmpty() ? null : tileAns;
    }

}
