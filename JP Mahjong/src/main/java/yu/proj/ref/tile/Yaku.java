package yu.proj.ref.tile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import yu.proj.jpmahjong.rule.Rule;

/**  
 * @ClassName: Yaku  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月11日  
 *  
 */

@AllArgsConstructor
public enum Yaku {

    // [1番]
    RIICHI(1, 0, 0, 0), // 立直
    DANYAO(1, DEPENDING_TO_RULE(), 0, 0) {
        @Override
        protected int hanWhenMakeCallDependingToRule(Rule rule) {
            if (rule.enableDanyaoAsYakuWhenMakeCall) {
                return 1;
            } else {
                return 0;
            }
        }
    }, // 断幺九
    FULLY_CONCEALED_HAND(1, 0, 0, 0), // 门清自摸
    PREVALENT_WIND_E(ALL_1_HAN()), PREVALENT_WIND_S(ALL_1_HAN()), PREVALENT_WIND_W(ALL_1_HAN()),
    PREVALENT_WIND_N(ALL_1_HAN()), // 场风
    SEAT_WIND_E(ALL_1_HAN()), SEAT_WIND_S(ALL_1_HAN()), SEAT_WIND_W(ALL_1_HAN()), SEAT_WIND_N(ALL_1_HAN()), // 自风
    DRAGON_WH(ALL_1_HAN()), DRAGON_G(ALL_1_HAN()), DRAGON_R(ALL_1_HAN()), // 三元牌
    PINFU(1, 0, 0, 0), // 平和
    PURE_DOUBLE_SEQUENCE(1, 0, 0, 0), // 一杯口
    ROBBING_A_KAN(ALL_1_HAN()), // 抢杠
    AFTER_A_KAN(ALL_1_HAN()), // 岭上开花
    UNDER_THE_SEA(ALL_1_HAN()), // 海底摸月
    UNDER_THE_RIVER(ALL_1_HAN()), // 河底捞鱼
    LPPATSU(1, 0, 0, 0), // 一发
    DORA(ALL_1_HAN()), // 宝牌
    RED_FIVE(ALL_1_HAN()), // 赤宝牌
    KITA(ALL_1_HAN()), // 北宝牌
    URA_DORA(1, 0, 0, 0), // 里宝牌

    // [2番]
    DOUBLE_RIICHI(2, 0, 0, 0), // 两立直
    TRIPLE_TRIPLETS(ALL_2_HAN()), // 三色同刻
    THREE_QUADS(ALL_2_HAN()), // 三杠子
    ALL_TRIPLETS(ALL_2_HAN()), // 对对和
    THREE_CONCEALED_TRIPLETS(2, 0, 0, 0), THREE_CONCEALED_TRIPLETS_WHEN_TSUMO(2, 0, 0, 0), // 三暗刻
    LITTLE_THREE_DRAGONS(ALL_2_HAN()), // 小三元
    ALL_TERMINALS_AND_HONORS(ALL_2_HAN()), // 混老头
    SEVEN_PAIRS(2, 0, 0, 0), // 七对子
    HALF_OUTSIDE_HAND(2, 1, 0, 0), // 混全带幺九
    PURE_STRAIGHT(2, 1, 0, 0), // 一气通贯
    MIXED_TRIPLE_SEQUENCE(2, 1, 0, 0), // 三色同顺

    // [3番]
    TWICE_PURE_DOUBLE_SEQUENCE(3, 0, 0, 0), // 二杯口
    FULL_OUTSIDE_HAND(3, 2, 0, 0), // 纯全带幺九
    HALF_FLUSH(3, 2, 0, 0), // 混一色

    // [6番]
    FULL_FLUSH(6, 5, 0, 0), // 清一色

    // [满贯]
    MANGAN_AT_DRAW(5, 5, 0, 0), // 流局满贯

    // [役满]
    BLESSING_OF_HEAVEN(ALL_1_YAKUMAN()), // 天和
    BLESSING_OF_EARTH(ALL_1_YAKUMAN()), // 地和
    BIG_THREE_DRAGONS(ALL_1_YAKUMAN()), // 大三元
    FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO(0, 0, 1, 0), // 四暗刻
    ALL_HONORS(ALL_1_YAKUMAN()), // 字一色
    ALL_GREENS(ALL_1_YAKUMAN()), // 绿一色
    ALL_TERMINALS(ALL_1_YAKUMAN()), // 清老头
    THIRTEEN_ORPHANS(ALL_1_YAKUMAN()), // 国士无双
    FOUR_LITTLE_WINDS(ALL_1_YAKUMAN()), // 小四喜
    FOUR_QUADS(ALL_1_YAKUMAN()), // 四杠子
    NINE_GATES(ALL_1_YAKUMAN()), // 九莲宝灯

    // [双倍役满]
    SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS(ALL_2_YAKUMAN()), // 四暗刻单骑
    THIRTEEN_WAIT_THIRTEEN_ORPHANS(ALL_2_YAKUMAN()), // 国士无双十三面
    TRUE_NINE_GATES(ALL_2_YAKUMAN()), // 纯正九莲宝灯
    FOUR_BIG_WINDS(ALL_2_YAKUMAN()), // 大四喜


    // [古役和地方役]
    TRUE_ALL_GREENS(0, 0, 3, 3); // 纯正绿一色

    public final static int DEPENDING_TO_RULE = -1;

    public final static int DEPENDING_TO_RULE() {
        return DEPENDING_TO_RULE;
    }

    public final static ValueDefine ALL_1_HAN = new ValueDefine(1, 1, 0, 0);

    public final static ValueDefine ALL_2_HAN = new ValueDefine(2, 2, 0, 0);

    public final static ValueDefine ALL_3_HAN = new ValueDefine(2, 2, 0, 0);

    public final static ValueDefine ALL_1_YAKUMAN = new ValueDefine(0, 0, 1, 1);

    public final static ValueDefine ALL_2_YAKUMAN = new ValueDefine(0, 0, 2, 2);

    public final static ValueDefine ALL_1_HAN() {
        return ALL_1_HAN;
    }

    public final static ValueDefine ALL_2_HAN() {
        return ALL_2_HAN;
    }

    public final static ValueDefine ALL_3_HAN() {
        return ALL_3_HAN;
    }

    public final static ValueDefine ALL_1_YAKUMAN() {
        return ALL_1_YAKUMAN;
    }

    public final static ValueDefine ALL_2_YAKUMAN() {
        return ALL_2_YAKUMAN;
    }

    @Getter
    @AllArgsConstructor
    private static class ValueDefine {
        private final int hanWhenMenzenchin;
        private final int hanWhenMakeCall;
        private final int yakumanWhenMenzenchin;
        private final int yakumanWhenMakeCall;
    }

    private final int hanWhenMenzenchin;
    private final int hanWhenMakeCall;
    private final int yakumanWhenMenzenchin;
    private final int yakumanWhenMakeCall;

    private Yaku(ValueDefine valueDefine) {
        this.hanWhenMenzenchin     = valueDefine.hanWhenMakeCall;
        this.hanWhenMakeCall       = valueDefine.hanWhenMakeCall;
        this.yakumanWhenMenzenchin = valueDefine.yakumanWhenMenzenchin;
        this.yakumanWhenMakeCall   = valueDefine.yakumanWhenMakeCall;
    }

    public int getHanWhenMenzenchin(Rule rule) {
        return hanWhenMenzenchin;
    }

    public int getHanWhenMakeCall(Rule rule) {
        if (hanWhenMakeCall == DEPENDING_TO_RULE) {
            return hanWhenMakeCallDependingToRule(rule);
        }
        return hanWhenMakeCall;
    }

    protected int hanWhenMakeCallDependingToRule(Rule rule) {
        throw new RuntimeException("Impossible to reach here");
    }

    public int getYakumanWhenMenzenchin(Rule rule) {
        return yakumanWhenMenzenchin;
    }

    public int getYakumanWhenMakeCall(Rule rule) {
        return yakumanWhenMakeCall;
    }

}
