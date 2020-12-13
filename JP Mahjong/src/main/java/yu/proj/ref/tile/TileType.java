package yu.proj.ref.tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import lombok.Getter;
import yu.proj.ref.utils.TriConsumer;

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

    NONE(list(), 0), // 空花色，null对象

    MAN_9(list(), 9), MAN_8(list(MAN_9), 8), MAN_7(list(MAN_8), 7), MAN_6(list(MAN_7), 6), MAN_5(list(MAN_6), 5) {
        @Override
        public TileType getRed() {
            return MAN_5_RED;
        }
    },
    MAN_5_RED(list(MAN_6), 5) {
        @Override
        public TileType getNormalType() {
            return MAN_5;
        }
    },
    MAN_4(list(MAN_5, MAN_5_RED), 4), MAN_3(list(MAN_4), 3), MAN_2(list(MAN_3), 2), MAN_1(list(MAN_2), 1), // 万子

    PIN_9(list(), 18), PIN_8(list(PIN_9), 17), PIN_7(list(PIN_8), 16), PIN_6(list(PIN_7), 15), PIN_5(list(PIN_6), 14) {
        @Override
        public TileType getRed() {
            return PIN_5_RED;
        }
    },
    PIN_5_RED(list(PIN_6), 14) {
        @Override
        public TileType getNormalType() {
            return PIN_5;
        }
    },
    PIN_4(list(PIN_5, PIN_5_RED), 13), PIN_3(list(PIN_4), 12), PIN_2(list(PIN_3), 11), PIN_1(list(PIN_2), 10), // 饼子

    SOU_9(list(), 27), SOU_8(list(SOU_9), 26), SOU_7(list(SOU_8), 25), SOU_6(list(SOU_7), 24), SOU_5(list(SOU_6), 23) {
        @Override
        public TileType getRed() {
            return SOU_5_RED;
        }
    },
    SOU_5_RED(list(SOU_6), 23) {
        @Override
        public TileType getNormalType() {
            return SOU_5;
        }
    },
    SOU_4(list(SOU_5, SOU_5_RED), 22), SOU_3(list(SOU_4), 21), SOU_2(list(SOU_3), 20), SOU_1(list(SOU_2), 19), // 索子

    NORTH(list(), 31), WEST(list(NORTH), 30), SOUTH(list(WEST), 29), EAST(list(SOUTH), 28), // 风牌

    RED(list(), 34), GREEN(list(RED), 33), WHITE(list(GREEN), 32); // 三元牌

    /**
     * nextTiles和lastTiles是用作吃牌分析和听牌分析中顺子类型的判断
     */
    private List<TileType> nextTiles;// 本张牌的下一张牌，如果是本种花色的末尾牌，那么就没有下一张牌
    private List<TileType> lastTiles;// 本张牌的上一张牌，如果是本种花色的第一张牌，那么就没有上一张牌

    private List<TileType> nextDora;// 如果本张牌是指示牌，那么指定的宝牌为nextDora

    private final int order;

    private static List<TileType> list(TileType... list) {
        return Arrays.asList(list);
    }

    static {
        initNextDoraForTile9();
        initLastTile();
    }

    // 9牌的dora处理，因为9牌的dora不是下一张牌，而是本种花色的1
    private static void initNextDoraForTile9() {
        MAN_9.nextDora = list(MAN_1);
        PIN_9.nextDora = list(PIN_1);
        SOU_9.nextDora = list(SOU_1);
    }

    private static void initLastTile() {
        for (TileType tileType : TileType.values()) {
            for (TileType next : tileType.nextTiles) {
                next.lastTiles.add(tileType);
            }
        }
    }

    private TileType(List<TileType> nextTile, int order) {
        this.nextTiles = nextTile;
        this.nextDora = nextTile; // 一般的情况下，dora就是下一张牌
        this.order = order;
        this.lastTiles = new ArrayList<>();
    }

    /**
     * @Title: nextNormalTile  
     * 
     * @Description: 和getNextTile不同，这个方法返回下一张普通牌(非红宝牌)，对于9牌和北和红中和NONE，返回的是None
     */
    public TileType nextNormalTile() {
        if (isNineOrNorthOrRedOrNone()) {
            return NONE;
        }
        return nextTiles.get(0);
    }

    public boolean isNormalFive() {
        return this == MAN_5 || this == PIN_5 || this == SOU_5;
    }

    private boolean isNineOrNorthOrRedOrNone() {
        return this == MAN_9 || this == PIN_9 || this == SOU_9 || this == NORTH || this == RED || this == NONE;
    }

    /**
     * @Title: getRed  
     * @Description: 返回普通5牌的红宝牌表示，其他牌调用该方法会返回NONE
     */
    public TileType getRed() {
        return NONE;
    }

    public boolean isLast() {
        return nextTiles.isEmpty();
    }

    public boolean previousOf(TileType type) {
        return nextTiles.contains(type);
    }

    public boolean isFirst() {
        return lastTiles.isEmpty();
    }

    public boolean nextOf(TileType type) {
        return lastTiles.contains(type);
    }

    // 有的时候不需要区分是不是红宝牌，需要获取一般牌型时，使用该方法获取
    public TileType getNormalType() {
        return this;
    }

    // 判断是不是同一种花色，不区分红宝牌和普通牌，例如MAN_5和MAN_5_RED比较时返回true
    public boolean sameNormalType(TileType tileType) {
        return this == tileType // 两者都是普通牌或者都为红宝牌时，这个成立
            || this.getRed() == tileType // this为普通牌，tileType为红宝牌是成立
            || this == tileType.getRed();// this为红宝牌，tileType为普通牌时成立
    }

    // 提供迭代全部普通牌型的方法
    // 迭代顺序是从某种牌型的开始迭代到这个牌型的结束，但是不区分普通牌和红宝牌
    // 以万子为例，则遍历顺序为1 2 3 4 5 6 7 8 9，但不包括5r
    public static void forEachNormalTileType(Consumer<TileType> consumer) {
        for (TileType now = MAN_1; now != NONE; now = next(now)) {
            consumer.accept(now);
        }
    }

    /**
     * 此处的next是牌型迭代时的下一张牌，此时不同种类的牌型就有前后顺序了
     * 顺序是万子->饼子->索子->风牌->三元牌
     */
    public static TileType next(TileType type) {
        switch (type) {
            case MAN_9:
                return PIN_1;
            case PIN_9:
                return SOU_1;
            case SOU_9:
                return EAST;
            case NORTH:
                return WHITE;
            case RED:
                return NONE;
            default:
                return type.nextNormalTile();
        }
    }

    public static void forEachManPinSou(TriConsumer<TileType, TileType, TileType> consumer) {
        consumer.accept(MAN_1, PIN_1, SOU_1);
        consumer.accept(MAN_2, PIN_2, SOU_2);
        consumer.accept(MAN_3, PIN_3, SOU_3);
        consumer.accept(MAN_4, PIN_4, SOU_4);
        consumer.accept(MAN_5, PIN_5, SOU_5);
        consumer.accept(MAN_6, PIN_6, SOU_6);
        consumer.accept(MAN_7, PIN_7, SOU_7);
        consumer.accept(MAN_8, PIN_8, SOU_8);
        consumer.accept(MAN_9, PIN_9, SOU_9);
    }
}
