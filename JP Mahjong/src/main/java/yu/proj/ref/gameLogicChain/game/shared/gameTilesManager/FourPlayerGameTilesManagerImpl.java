package yu.proj.ref.gameLogicChain.game.shared.gameTilesManager;

import java.util.NoSuchElementException;

import cn.hutool.core.util.ArrayUtil;
import yu.proj.jpmahjong.define.Numbers;
import yu.proj.ref.tile.Tile;

/**  
 * @ClassName: FourPlayerGameTilesManagerImpl  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */
public class FourPlayerGameTilesManagerImpl implements GameTilesManager {

    private final static int NUMBER_OF_TILES = 136;

    private final static int NUMBER_OF_LAST_TILES = 14;// 王牌，不摸的牌

    private final static int NUMBER_OF_TILES_FOR_KAN = 4;// 岭上牌

    private Tile[] tiles;

    /**
     * 用于累计从牌山中取走的牌数，包括杠后从岭上摸走的牌
     */
    private int indexOfDraw = 0;

    private int indexOfKan = 0;

    public FourPlayerGameTilesManagerImpl(Tile[] tiles) {
        super();

        assert tiles.length == NUMBER_OF_TILES && ArrayUtil.isAllNotNull(tiles);

        this.tiles = tiles;
    }

    @Override
    public boolean isDraw() {
        // 如果剩下牌的数量和王牌的数量相等，如果还需要摸牌的情况下，那么就流局
        return NUMBER_OF_TILES - indexOfDraw - indexOfKan == NUMBER_OF_LAST_TILES;
    }

    @Override
    public Tile draw() {
        if (isDraw()) {
            throw new NoSuchElementException("流局了，无牌可摸");
        }
        return tiles[indexOfDraw++];
    }

    @Override
    public Tile kan() {
        if (isDraw()) {
            throw new NoSuchElementException("海底不可杠");
        } else if (indexOfKan == 4) {// 第五次开杠时，无牌可返回，流局
            return null;
        }
        return tiles[NUMBER_OF_TILES - 1 - (indexOfKan++)];
    }

    private Tile[] doraIndicator, uraDoraIndicator;

    @Override
    public Tile[] doraIndicator() {
        if (doraIndicator == null || doraIndicator.length != indexOfKan + 1) {
            doraIndicator = new Tile[indexOfKan + 1];
            for (int i = 0; i < doraIndicator.length; ++i) {
                doraIndicator[i] = tiles[NUMBER_OF_TILES - NUMBER_OF_TILES_FOR_KAN - 1 - i * 2];
            }
        }
        return doraIndicator;
    }

    @Override
    public Tile[] uraDoraIndicator() {
        if (uraDoraIndicator == null || uraDoraIndicator.length != indexOfKan + 1) {
            uraDoraIndicator = new Tile[indexOfKan + 1];
            for (int i = 0; i < uraDoraIndicator.length; ++i) {
                uraDoraIndicator[i] = tiles[NUMBER_OF_TILES - NUMBER_OF_TILES_FOR_KAN - 2 - i * 2];
            }
        }
        return uraDoraIndicator;
    }

    @Override
    public boolean isStarted() {
        return indexOfDraw != 0;
    }

    @Override
    public int tileToDrawNum() {
        // 将王牌，摸的牌和杠的牌都去掉，就是剩下的牌的数量
        return NUMBER_OF_TILES - indexOfDraw - indexOfKan - Numbers.NUMBER_OF_LAST_TILES;
    }

}
