package yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita;

import java.util.ArrayList;
import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.FinalTenpaiAns;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: AnalyzeConcealedKan  
 *
 * @Description: 是否可以暗杠 
 *
 * @author 余定邦  
 *
 * @date 2020年9月24日  
 *  
 */
public class AnalyzeConcealedKan {

    public List<ConcealedKan> analyzeConcealedKan(Tile drawTile, CountNum concealedHand, boolean riichi,
        FinalTenpaiAns finalTenpaiAns, Rule rule) {

        if (!riichi) {
            List<ConcealedKan> list = new ArrayList<>();

            for (int i = CountNum.M1; i <= CountNum.R; ++i) {
                if (concealedHand.getCount(i) == 4) {
                    list.add(new ConcealedKan(i));
                }
            }

            return list.size() != 0 ? list : null;
        }

        // TODO
        if (!rule.enableStrictModeOfConcealedKanWhenRiichi) {// 非严格模式下，只需要检查最大打点的牌是否符合规则即可
            switch (rule.concealedKanWhenRiichi) {
                // 这种情况必定改变听牌形式，因此只要有一种能够保持听牌，那么就可以暗杠
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_EVEN_NOT_DRAW: {
                    // 先检查杠后能不能听牌
                    for (int i = CountNum.M1; i <= CountNum.R; ++i) {
                        if (concealedHand.getCount(i) == 4) {

                        }
                    }
                    break;
                }
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_NUMBER_NOT_CHANGE: {
                    // 检查杠后的听牌数量有没有改变

                    break;
                }
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_PATTERN_NOT_CHANGE: {
                    // 检查杠的那张牌和听牌的面子、顺子有没有关系

                    break;
                }
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TILE_PATTERN_NOT_CHANGE: {
                    // 检查杠的那张牌是不是在所有的听牌方式当中都以刻子的形式存在

                    break;
                }
                case NOT_ALLOW_CONCEALED_KAN_WHEN_RIICHI: {
                    // 立直后不允许暗杠
                    return null;
                }
            }
        } else {
            switch (rule.concealedKanWhenRiichi) {
                // 这种情况必定改变听牌形式，因此只要有一种能够保持听牌，那么就可以暗杠
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_EVEN_NOT_DRAW: {
                    // 先检查杠后能不能听牌
                    for (int i = CountNum.M1; i <= CountNum.R; ++i) {
                        if (concealedHand.getCount(i) == 4) {

                        }
                    }
                    break;
                }
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_NUMBER_NOT_CHANGE: {
                    // 检查杠后的听牌数量有没有改变

                    break;
                }
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_PATTERN_NOT_CHANGE: {
                    // 检查杠的那张牌和听牌的面子、顺子有没有关系

                    break;
                }
                case ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TILE_PATTERN_NOT_CHANGE: {
                    // 检查杠的那张牌是不是在所有的听牌方式当中都以刻子的形式存在

                    break;
                }
                case NOT_ALLOW_CONCEALED_KAN_WHEN_RIICHI: {
                    // 立直后不允许暗杠
                    return null;
                }
            }
        }

        return null;
    }

}
