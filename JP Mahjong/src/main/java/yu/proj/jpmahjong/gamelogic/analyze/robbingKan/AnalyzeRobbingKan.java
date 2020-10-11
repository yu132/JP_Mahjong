package yu.proj.jpmahjong.gamelogic.analyze.robbingKan;

import java.util.Set;

import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.FinalTenpaiAns;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.ConcealedKanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperationChoice;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.yaku.Yaku;

/**  
 * @ClassName: RobbingKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月30日  
 *  
 */
public class AnalyzeRobbingKan {

    /**
     * 
     * @Title: analyzeRobbingKan  
     *
     * @Description: 判断是否能够抢杠
     *               其实抢杠和荣和没有任何区别，甚至抢杠就是一种荣和
     *               区别就是一般的荣和是有玩家打出一张牌的时候判断，而抢杠是在有玩家杠的时候判断
     *               判断的时间节点不一样罢了，本质是完全一样的
     *
     * @param kanTile
     * @param tenpaiAns
     * @return
     *
     * @throws  
     *
     */
    public RobbingKanOperationChoice analyzeRobbingKan(int kanTile, FinalTenpaiAns tenpaiAns, KanOperation kanOp,
        Rule rule) {
        if (!(kanOp instanceof ConcealedKanOperation)) {// 不是暗杠的话，那么就是加杠和拔北，那么直接判断就可以了
            TenpaiAnsNode ron = tenpaiAns.getRon().get(kanTile);
            if (ron == null) {
                return new RobbingKanOperationChoice(null, false);
            }
            return new RobbingKanOperationChoice(new RobbingKan(ron), true);

        } else {// 是暗杠

            if (rule.enableThirteenOrphansRobbingConcealedKan) {// 如果规则允许国士无双抢暗杠

                TenpaiAnsNode ron = tenpaiAns.getRon().get(kanTile);
                if (ron != null) {
                    Set<Yaku> yaku = ron.getYakuAns().getYaku();

                    // 和的牌是国士无双或者国士无双十三面，才允许抢暗杠
                    if (yaku.contains(Yaku.THIRTEEN_ORPHANS) || yaku.contains(Yaku.THIRTEEN_WAIT_THIRTEEN_ORPHANS)) {
                        return new RobbingKanOperationChoice(new RobbingKan(ron), true);
                    }
                }

            }

            return new RobbingKanOperationChoice(null, false);
        }
    }

    public static class RobbingKan {
        private TenpaiAnsNode tenpaiAnsNode;

        public RobbingKan(TenpaiAnsNode tenpaiAnsNode) {
            super();
            this.tenpaiAnsNode = tenpaiAnsNode;
        }

        public TenpaiAnsNode getTenpaiAnsNode() {
            return tenpaiAnsNode;
        }
    }

}
