package yu.proj.jpmahjong.yaku;

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
public enum Yaku {

    // [1番]
    RIICHI, // 立直
    DANYAO, //
    FULLY_CONCEALED_HAND, //
    PREVALENT_WIND_E, PREVALENT_WIND_S, PREVALENT_WIND_W, PREVALENT_WIND_N, //
    SEAT_WIND_E, SEAT_WIND_S, SEAT_WIND_W, SEAT_WIND_N, //
    DRAGON_WH, DRAGON_G, DRAGON_R, //
    PINFU, //
    PURE_DOUBLE_SEQUENCE, //
    ROBBING_A_KAN, //
    AFTER_A_KAN, //
    UNDER_THE_SEA, //
    UNDER_THE_RIVER, //
    LPPATSU, //
    DORA, //
    RED_FIVE, //
    KITA, //
    URA_DORA, //

    END_OF_1_HAN,

    // [2番]
    DOUBLE_RIICHI, //
    TRIPLE_TRIPLETS, //
    THREE_QUADS, //
    ALL_TRIPLETS, //
    THREE_CONCEALED_TRIPLETS, //
    THREE_CONCEALED_TRIPLETS_WHEN_TSUMO, LITTLE_THREE_DRAGONS, //
    ALL_TERMINALS_AND_HONORS, //
    SEVEN_PAIRS, //
    HALF_OUTSIDE_HAND, //
    PURE_STRAIGHT, //
    MIXED_TRIPLE_SEQUENCE, //

    END_OF_2_HAN,

    // [3番]
    TWICE_PURE_DOUBLE_SEQUENCE, //
    FULL_OUTSIDE_HAND, //
    HALF_FLUSH, //

    END_OF_3_HAN,

    // [6番]
    FULL_FLUSH, //

    END_OF_6_HAN,

    // [满贯]
    MANGAN_AT_DRAW, //

    END_OF_MANGAN,

    // [役满]
    BLESSING_OF_HEAVEN, //
    BLESSING_OF_EARTH, //
    BIG_THREE_DRAGONS, //
    FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO, //
    ALL_HONORS, //
    ALL_GREENS, //
    ALL_TERMINALS, //
    THIRTEEN_ORPHANS, //
    FOUR_LITTLE_WINDS, //
    FOUR_QUADS, //
    NINE_GATES, //

    END_OF_YAKUMAN,

    // [双倍役满]
    SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS, //
    THIRTEEN_WAIT_THIRTEEN_ORPHANS, //
    TRUE_NINE_GATES, //
    FOUR_BIG_WINDS, //


    // [古役和地方役]
    TRUE_ALL_GREENS, //

    END_OF_DOUBLE_YAKUMAN,
}
