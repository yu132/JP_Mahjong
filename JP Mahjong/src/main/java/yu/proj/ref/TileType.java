package yu.proj.ref;

import lombok.Getter;

/**  
 * @ClassName: TypeType  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
public enum TileType {

    NONE(null, 0), // 空花色，null对象
    MAN_9(list(NONE), 9), MAN_8(list(MAN_9), 8), MAN_7(list(MAN_8), 7), MAN_6(list(MAN_7), 6), MAN_5(list(MAN_6), 5) {
        @Override
        public TileType getRed() {
            return MAN_5_RED;
        }
    },
    MAN_5_RED(list(MAN_6), 5), MAN_4(list(MAN_5, MAN_5_RED), 4), MAN_3(list(MAN_4), 3), MAN_2(list(MAN_3), 2),
    MAN_1(list(MAN_2), 1), // 万子
    PIN_9(list(NONE), 18), PIN_8(list(PIN_9), 17), PIN_7(list(PIN_8), 16), PIN_6(list(PIN_7), 15),
    PIN_5(list(PIN_6), 14) {
        @Override
        public TileType getRed() {
            return PIN_5_RED;
        }
    },
    PIN_5_RED(list(PIN_6), 14), PIN_4(list(PIN_5, PIN_5_RED), 13), PIN_3(list(PIN_4), 12), PIN_2(list(PIN_3), 11),
    PIN_1(list(PIN_2), 10), // 饼子
    SOU_9(list(NONE), 27), SOU_8(list(SOU_9), 26), SOU_7(list(SOU_8), 25), SOU_6(list(SOU_7), 24),
    SOU_5(list(SOU_6), 23) {
        @Override
        public TileType getRed() {
            return SOU_5_RED;
        }
    },
    SOU_5_RED(list(SOU_6), 23), SOU_4(list(SOU_5, SOU_5_RED), 22), SOU_3(list(SOU_4), 21), SOU_2(list(SOU_3), 20),
    SOU_1(list(SOU_2), 19), // 索子
    NORTH(list(NONE), 31), WEST(list(NORTH), 30), SOUTH(list(WEST), 29), EAST(list(SOUTH), 28), // 风牌
    RED(list(NONE), 34), GREEN(list(RED), 33), WHITE(list(GREEN), 32); // 三元牌

    private final TileType[] nextTile;// 本张牌的下一张牌，如果是本种花色的末尾牌，那么就没有下一张牌

    private TileType[] nextDora;// 如果本张牌是指示牌，那么指定的宝牌为nextDora

    private final int order;

    private static TileType[] list(TileType... list) {
        return list;
    }

    static {
        initNextDoraForTile9();
    }

    // 9牌的dora处理，因为9牌的dora不是下一张牌，而是本种花色的1
    private static void initNextDoraForTile9() {
        MAN_9.nextDora = list(MAN_1);
        PIN_9.nextDora = list(PIN_1);
        SOU_9.nextDora = list(SOU_1);
    }

    private TileType(TileType[] nextTile, int order) {
        this.nextTile = nextTile;
        this.nextDora = nextTile;// 一般的情况下，dora就是下一张牌
        this.order    = order;
    }

    public TileType getRed() {
        throw new UnsupportedOperationException("只有5牌才能调用这个方法");
    }

}
