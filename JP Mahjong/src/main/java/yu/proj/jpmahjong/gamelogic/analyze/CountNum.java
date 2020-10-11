package yu.proj.jpmahjong.gamelogic.analyze;

import java.util.ArrayList;
import java.util.List;

import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.tiles.meld.Meld;

/**  
 * @ClassName: CountNum  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 * 
 */
public class CountNum {

    public final static int M1 = 0;
    public final static int M2 = 1;
    public final static int M3 = 2;
    public final static int M4 = 3;
    public final static int M5 = 4;
    public final static int M6 = 5;
    public final static int M7 = 6;
    public final static int M8 = 7;
    public final static int M9 = 8;

    public final static int P1 = 9;
    public final static int P2 = 10;
    public final static int P3 = 11;
    public final static int P4 = 12;
    public final static int P5 = 13;
    public final static int P6 = 14;
    public final static int P7 = 15;
    public final static int P8 = 16;
    public final static int P9 = 17;

    public final static int S1 = 18;
    public final static int S2 = 19;
    public final static int S3 = 20;
    public final static int S4 = 21;
    public final static int S5 = 22;
    public final static int S6 = 23;
    public final static int S7 = 24;
    public final static int S8 = 25;
    public final static int S9 = 26;

    public final static int E = 27;
    public final static int S = 28;
    public final static int W = 29;
    public final static int N = 30;

    public final static int WH = 31;
    public final static int G = 32;
    public final static int R = 33;

    // public final static String[] indexMap =
    // new String[] {"1m", "2m", "3m", "4m", "5m", "6m", "7m", "8m", "9m", "1p", "2p", "3p", "4p", "5p", "6p", "7p",
    // "8p", "9p", "1s", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "东", "南", "西", "北", "白", "发", "中"};

    private List<Tile>[] count;

    private int[] rCount = new int[3];

    /**
     * 
     * 创建一个新的实例 CountNum.  
     *
     * @param concealedHand
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public CountNum(List<Tile> concealedHand) {
        super();

        count = new List[3 * 9 + 4 + 3];

        for (int i = 0; i < count.length; ++i) {
            count[i] = new ArrayList<>();
        }

        for (Tile tile : concealedHand) {
            count(tile);
        }
    }

    public CountNum(Tile[] initTiles) {
        for (Tile tile : initTiles) {
            count(tile);
        }
    }

    @Deprecated
    public void add(List<Meld> makeCall, List<Meld> concealedKan) {
        for (Meld ncm : makeCall) {
            for (Tile tile : ncm.getTiles()) {
                count(tile);
            }
        }

        for (Meld ncm : concealedKan) {
            for (Tile tile : ncm.getTiles()) {
                count(tile);
            }
        }
    }

    public void add(Tile tile) {
        count(tile);
    }

    public Tile remove(int tileIndex, boolean isRed) {
        return removeTile(tileIndex, isRed);
    }

    public int getRCount(TileType type) {
        return rCount[type.ordinal()];
    }

    public int getCount(int index) {
        return count[index].size();
    }

    public int getCountByNumberTile(TileType type, int intId) {
        return count[type.ordinal() * 9 + intId - 1].size();
    }

    public Tile getTile(int index, int indexOfList) {
        return count[index].get(indexOfList);
    }

    public Tile getTileByNumberTile(TileType type, int intId, int indexOfList) {
        return count[type.ordinal() * 9 + intId - 1].get(indexOfList);
    }

    private void count(Tile tile) {
        if (tile.getIntId() != 0) {
            if (tile.isRed()) {
                // 将红宝牌优先插入到数组前面去，在移除的时候，就可以从后面移除非红宝牌的牌
                count[numberTileIndex(tile)].add(0, tile);
                ++rCount[tile.getType().ordinal()];
            } else {
                count[numberTileIndex(tile)].add(tile);
            }
        } else {
            count[honorTileIndex(tile.getType())].add(tile);
        }
    }

    /**
     * 
     * @Title: removeTile  
     *
     * @Description: 移除一张牌，如果没有这张牌，那么会返回null
     * 
     * @param tileIndex
     * @param isRed 要求移除的一定必须是红色的牌
     * @return 移除的那张牌
     *
     */
    private Tile removeTile(int tileIndex, boolean isRed) {
        List<Tile> list = count[tileIndex];
        if (!isRed || (tileIndex != M5 && tileIndex != P5 && tileIndex != S5)) {
            if (list.isEmpty()) {
                return null;
            }
            Tile tile = list.remove(list.size() - 1);// 由于总是从最后面开始移除牌的，因此可以保证优先移除的不是红宝牌
            if (tile.isRed()) {// 这张牌也可能是红宝牌
                --rCount[tile.getType().ordinal()];
            }
            return tile;
        } else {
            // 由于红宝牌总是在最前面插入的，因此第一张不是红宝牌，那么后面的更不可能是
            Tile tile = list.get(0);
            if (tile.isRed()) {
                list.remove(0);
                --rCount[tile.getType().ordinal()];
                return tile;
            }
            return null;
        }
    }

    public void removeTile(Tile tile) {
        int index = getTileIndex(tile);
        count[index].remove(tile);
    }

    public static int getTileIndex(Tile tile) {
        if (tile.getIntId() == 0) {
            return honorTileIndex(tile.getType());
        } else {
            return numberTileIndex(tile);
        }
    }

    public static int numberTileIndex(Tile tile) {
        return tile.getType().ordinal() * 9 + tile.getIntId() - 1;
    }

    public static int honorTileIndex(TileType type) {
        return type.ordinal() - 3 + 3 * 9;
    }

    public static boolean isTileThisType(int tileIntIndex, Tile tile) {
        if (tile.getIntId() == 0) {
            return honorTileIndex(tile.getType()) == tileIntIndex;
        } else {
            return numberTileIndex(tile) == tileIntIndex;
        }
    }

}
