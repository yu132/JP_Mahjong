package yu.proj.jpmahjong.gamelogic.analyze.win;

import static yu.proj.jpmahjong.yaku.Yaku.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternFu.FuAns;
import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternPoint.PointWhenDealerWin;
import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternPoint.PointWhenNotDealerWin;
import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternYaku.YakuAns;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.tiles.meld.Meld;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;
import yu.proj.jpmahjong.yaku.Yaku;

/***

@ClassName: GetFinalTenpaiTileAndPoint
 *
 * @Description: 获取每张听的牌，以及这张牌对应自摸和荣和对应的符数和役和番数，并算出最后的打点
 *
 * @author 余定邦
 *
 * @date 2020年9月22日
 *
 */
public class GetFinalTenpaiTileAndPoint {

    private Rule rule;

    public GetFinalTenpaiTileAndPoint(Rule rule) {
        super();
        this.rule = rule;
    }

    public FinalTenpaiAns analyzeTenpaiAns(CountNum concealedHand, CountNum fullHand, List<Meld> makeCall,
        List<Meld> concealedKan, TileType prevalentWind, TileType seatWind, boolean isDealer) {

        AnalyzeTenpai analyzeTenpai = new AnalyzeTenpai(rule, concealedHand, fullHand, makeCall, concealedKan);

        Map<Integer, List<TilePatternNode>> tenpaiAns = analyzeTenpai.isTenpai();

        Map<Integer, TenpaiAnsNode> ron = new HashMap<>();
        Map<Integer, TenpaiAnsNode> tsumo = new HashMap<>();

        for (Entry<Integer, List<TilePatternNode>> entry : tenpaiAns.entrySet()) {

            int tenpaiTileIndex = entry.getKey();

            /*
            * 对于每种听的牌去计算听这张牌的情况下最大的一种牌型
            * 由于非牌型的番对于每种牌型都是适用的，因此不存在哪种牌型在某种情况下会有额外的番
            * 因此大家的加的番同样的情况下，之前分数高的解释情况依然适用，而不会说受到加番的影响
            * 而导致某种解释情况在加番之前得分低，而之后得分高的情况
            * 因此我们可以直接先把得分高的解释情况先筛选出来
            */
            TenpaiAnsNode ronMaxPoint = null;
            TenpaiAnsNode tsumoMaxPoint = null;

            for (TilePatternNode tpn : entry.getValue()) {
                CountPatternYaku countYaku =
                    new CountPatternYaku(rule, prevalentWind, seatWind, tpn, tenpaiTileIndex, fullHand);

                YakuAns yakuAns = countYaku.countYaku();

                Set<Yaku> yakuSet = yakuAns.getYaku();

                CountPatternFu countFu = new CountPatternFu(rule, prevalentWind, seatWind, tpn, tenpaiTileIndex,
                    yakuAns.getYaku().contains(Yaku.PINFU), yakuAns.isMenzenchin());

                FuAns fuAns = countFu.countFu();

                int hanWhenRon = 0;
                int hanWhenTsumo = 0;

                int yakumanWhenRon = 0;
                int yakumanWhenTsumo = 0;

                // // 统计役对应的番数
                // for (Yaku yaku : yakuAns.getYaku()) {
                // int order = yaku.ordinal();
                //
                // if (order < END_OF_1_HAN.ordinal()) {
                // hanWhenRon += 1;
                // hanWhenTsumo += 1;
                // } else if (yaku == THREE_CONCEALED_TRIPLETS_WHEN_TSUMO) {
                // hanWhenTsumo += 2;
                // } else if (order < END_OF_2_HAN.ordinal()) {
                // hanWhenRon += 2;
                // hanWhenTsumo += 2;
                // } else if (order < END_OF_3_HAN.ordinal()) {
                // hanWhenRon += 3;
                // hanWhenTsumo += 3;
                // } else if (order < END_OF_6_HAN.ordinal()) {
                // hanWhenRon += 6;
                // hanWhenTsumo += 6;
                // } else if (yaku == FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO) {
                // yakumanWhenTsumo += 1;
                // } else if (order < END_OF_YAKUMAN.ordinal()) {
                // yakumanWhenRon += 1;
                // yakumanWhenTsumo += 1;
                // } else if (order < END_OF_DOUBLE_YAKUMAN.ordinal()) {
                // yakumanWhenRon += 2;
                // yakumanWhenTsumo += 2;
                // }
                // }

                // 减去食下役的减番
                if (!yakuAns.isMenzenchin()) {
                    if (yakuSet.contains(HALF_OUTSIDE_HAND)) {
                        hanWhenRon -= 1;
                        hanWhenTsumo -= 1;
                    }
                    if (yakuSet.contains(PURE_STRAIGHT)) {
                        hanWhenRon -= 1;
                        hanWhenTsumo -= 1;
                    }
                    if (yakuSet.contains(MIXED_TRIPLE_SEQUENCE)) {
                        hanWhenRon -= 1;
                        hanWhenTsumo -= 1;
                    }
                    if (yakuSet.contains(FULL_OUTSIDE_HAND)) {
                        hanWhenRon -= 1;
                        hanWhenTsumo -= 1;
                    }
                }

                // 在这里还判断不了番缚的情况，因为除了CountPatternYaku里能够检查的番，还有其他的番的可能
                TenpaiAnsNode ronAns = new TenpaiAnsNode(tenpaiTileIndex, tpn, yakumanWhenRon, hanWhenRon,
                    fuAns.getFuWhenRon(), isDealer, false, yakuAns, rule);

                TenpaiAnsNode tsumoAns = new TenpaiAnsNode(tenpaiTileIndex, tpn, yakumanWhenTsumo, hanWhenTsumo,
                    fuAns.getFuWhenTsumo(), isDealer, true, yakuAns, rule);

                if (ronMaxPoint == null || ronAns.totalPoint > ronMaxPoint.totalPoint) {
                    ronMaxPoint = ronAns;
                }

                if (tsumoMaxPoint == null || tsumoAns.totalPoint > tsumoMaxPoint.totalPoint) {
                    tsumoMaxPoint = tsumoAns;
                }
            }

            if (ronMaxPoint != null) {
                ron.put(tenpaiTileIndex, ronMaxPoint);
            }
            if (tsumoMaxPoint != null) {
                ron.put(tenpaiTileIndex, tsumoMaxPoint);
            }
        }

        return new FinalTenpaiAns(ron, tsumo, tenpaiAns);
    }

    public static class FinalTenpaiAns {

        private Map<Integer, TenpaiAnsNode> ron;
        private Map<Integer, TenpaiAnsNode> tsumo;

        Map<Integer, List<TilePatternNode>> fullTenpaiAns;

        public FinalTenpaiAns(Map<Integer, TenpaiAnsNode> ron, Map<Integer, TenpaiAnsNode> tsumo,
            Map<Integer, List<TilePatternNode>> fullTenpaiAns) {
            super();
            this.ron = ron;
            this.tsumo = tsumo;
            this.fullTenpaiAns = fullTenpaiAns;
        }

        public Map<Integer, TenpaiAnsNode> getRon() {
            return ron;
        }

        public Map<Integer, TenpaiAnsNode> getTsumo() {
            return tsumo;
        }

        public Map<Integer, List<TilePatternNode>> getFullTenpaiAns() {
            return fullTenpaiAns;
        }
    }

    public static class TenpaiAnsNode {

        private CountPatternPoint countPoint;
        private Rule rule;

        private int tenpaiTileIndex;

        private TilePatternNode pattern;

        private int yakuman;
        private int han;

        private int fu;

        private int totalPoint;

        private boolean isDealer;
        private boolean isTsumo;

        private PointWhenDealerWin pointWhenDealerWin;
        private PointWhenNotDealerWin pointWhenNotDealerWin;

        /**
        * 用于保存牌的役和包牌信息
        */
        private YakuAns yakuAns;

        /**
        * 用于存储非牌型番的算点问题
        */
        private Set<Yaku> yakuSet;
        private Yaku removeYaku;

        private int otherHan = 0;
        private int otherYakuman = 0;

        public TenpaiAnsNode(int tenpaiTileIndex, TilePatternNode pattern, int yakuman, int han, int fu,
            boolean isDealer, boolean isTsumo, YakuAns yakuAns, Rule rule) {
            super();
            this.tenpaiTileIndex = tenpaiTileIndex;
            this.pattern = pattern;
            this.yakuman = yakuman;
            this.han = han;
            this.fu = fu;
            this.isDealer = isDealer;
            this.isTsumo = isTsumo;
            this.yakuAns = yakuAns;

            countPoint = new CountPatternPoint(rule);
            this.rule = rule;

            countPoint();
        }

        /**
        *
        * @Title: updateNotPatternYakuAndDora
        *
        * @Description: 更新非牌型带来的役，包括：
        * 立直、一发、双立直、门前清自摸和 、抢杠、岭上开花、海底摸月、河底捞鱼、天和、地和
        *
        * @param yakuSet
        * @param removeYaku
        *
        * @throws
        *
        */
        public void updateNotPatternYaku(Set<Yaku> yakuSet, Yaku removeYaku) {
            // for (Yaku yaku : yakuSet) {
            // int order = yaku.ordinal();
            // if (order < END_OF_1_HAN.ordinal()) {// 立直、一发、门前清自摸和 、抢杠、岭上开花、海底摸月、河底捞鱼
            // otherHan += 1;
            // } else if (order < END_OF_2_HAN.ordinal()) {// 双立直
            // otherHan += 2;
            // } else if (order < END_OF_YAKUMAN.ordinal()) {// 天和、地和
            // otherYakuman += 1;
            // }
            // }

            // 天和时一倍满改位二倍满的情况
            // 天和最多复合一种役满，纯正九莲宝灯，国士无双十三面和四暗刻单骑是冲突的
            otherYakuman += removeYaku == null ? 0 : 1;
        }

        public boolean hasEnoughHanToWin() {
            int totalYakuman = yakuman + otherYakuman;
            if (totalYakuman > 0) {// 不会有番缚是一个役满以上的吧，真的不会吧
                return true;
            }
            int totalHan = han + otherHan;
            return totalHan >= rule.minHanToWin;
        }

        /**
        *
        * @Title: updatePointWithNotPatternYakuAndDora
        *
        * @Description: 更新最终的打点
        *
        * @param dora
        * @param uraDora
        *
        */
        public void updatePointWithNotPatternYakuAndDora(int dora, int uraDora) {

            han += otherHan;
            yakuman += otherYakuman;

            int totalDora = dora + yakuAns.getRedFive() + uraDora;
            if (rule.enableCountDoraWhenHasYaukuman && yakuman > 0) {// 在役满上累计宝牌
                yakuman += totalDora / 13;// 每13个宝牌算一个役满
            } else {
                han += totalDora;
            }

            if (!rule.enable13HanCeilingOfAddUpYakuman) {// 累计役满如果不是13番封顶
                if (han >= 26) {
                    yakuman += han / 13;
                }
            }

            for (Yaku yaku : yakuSet) {
                yakuAns.addYaku(yaku);
            }

            if (removeYaku != null) {
                yakuAns.removeYaku(removeYaku);
            }

            yakuAns.setDora(dora);
            yakuAns.setUraDora(uraDora);

            countPoint();// 重新计算分数
        }

        private void countPoint() {
            if (isDealer) {
                if (yakuman != 0) {
                    pointWhenDealerWin = countPoint.pointWhenYakumanAndDealerWin(yakuman);
                } else {
                    pointWhenDealerWin = countPoint.pointWhenDealerWin(fu, han);
                }
                totalPoint = pointWhenDealerWin.getPointWhenRon();
            } else {
                if (yakuman != 0) {
                    pointWhenNotDealerWin = countPoint.pointWhenYakumanAndNotDealerWin(yakuman);
                } else {
                    pointWhenNotDealerWin = countPoint.pointWhenNotDealerWin(fu, han);
                }
                totalPoint = pointWhenNotDealerWin.getPointWhenRon();
            }
        }

        public TilePatternNode getPattern() {
            return pattern;
        }

        public int getYakuman() {
            return yakuman;
        }

        public int getHan() {
            return han;
        }

        public int getFu() {
            return fu;
        }

        public int getTenpaiTileIndex() {
            return tenpaiTileIndex;
        }

        public YakuAns getYakuAns() {
            return yakuAns;
        }

        public boolean isDealer() {
            return isDealer;
        }

        public PointWhenDealerWin getPointWhenDealerWin() {
            return pointWhenDealerWin;
        }

        public PointWhenNotDealerWin getPointWhenNotDealerWin() {
            return pointWhenNotDealerWin;
        }

        public int getTotalPoint() {
            return totalPoint;
        }

        public boolean isTsumo() {
            return isTsumo;
        }
    }

}
