package yu.proj.ref;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.hutool.core.util.ArrayUtil;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.Kita;
import yu.proj.ref.meld.AddKanQuad;
import yu.proj.ref.meld.ConcealedKanQuad;
import yu.proj.ref.meld.ExposedKanQuad;
import yu.proj.ref.meld.Meld;
import yu.proj.ref.meld.MeldSource;
import yu.proj.ref.meld.Sequence;
import yu.proj.ref.meld.Triplet;
import yu.proj.ref.ops.AbstractGainAndExposedTileOperation;
import yu.proj.ref.ops.AddKanOperation;
import yu.proj.ref.ops.ConcealedKanOperation;
import yu.proj.ref.ops.DiscardOperation;
import yu.proj.ref.ops.DrawOperation;
import yu.proj.ref.ops.ExposedKanOperation;
import yu.proj.ref.ops.PonOperation;

/**  
 * @ClassName: TileCounter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */
public class TileCounterImpl implements TileCounter {

    private EnumMap<TileType, Set<Tile>> tilesInHand = new EnumMap<>(TileType.class);

    private EnumMap<TileType, Integer> countNumberAll = new EnumMap<>(TileType.class);

    private EnumSet<TileType> kanNumber = EnumSet.noneOf(TileType.class);

    private List<Meld> melds = new ArrayList<>();

    @Override
    public int countInHand(TileType tileType) {
        return tilesInHand.getOrDefault(tileType, Collections.emptySet()).size();
    }

    @Override
    public int countKanAs3TileAndRedAsNormal(TileType tileType) {
        return allTileNumberOfTileTypeWhenRedAsNormal(tileType) - kanNumberOfTileType(tileType);
    }

    // 计算当红宝牌当作正常牌的时候，某种牌的数量
    private int allTileNumberOfTileTypeWhenRedAsNormal(TileType type) {
        if (isFive(type)) {
            return allTileNumberOfTileType(type) + allTileNumberOfTileType(type.getRed());
        } else {
            return allTileNumberOfTileType(type);
        }
    }

    // 计算某种牌的数量，红宝牌需要单独计算
    private int allTileNumberOfTileType(TileType type) {
        return countNumberAll.getOrDefault(type, 0);
    }

    // 是5牌吗
    private boolean isFive(TileType type) {
        return type == TileType.MAN_5 || type == TileType.PIN_5 || type == TileType.SOU_5;
    }

    // 计算某种牌的杠的数量
    private int kanNumberOfTileType(TileType type) {
        return kanNumber.contains(type) ? 1 : 0;
    }

    @Override
    public List<Meld> getMeld() {
        return new ArrayList<>(this.melds);// 为安全性进行保护性复制
    }

    @Override
    public void chi(AbstractGainAndExposedTileOperation chi) {
        checkExistAndManageOldAndNewTile(chi);
        addChiAsSequenceInMelds(chi);
    }

    private void checkExistAndManageOldAndNewTile(AbstractGainAndExposedTileOperation op) {
        Tile   gainTile    = op.getGainExposedTile();
        Tile[] exposedTile = op.getExposedTiles();

        assert containTilesInHand(exposedTile);

        removeTilesFromHand(exposedTile);// 两张旧的牌需要移除手牌

        tileCountPlus1(gainTile);// 新的吃的牌要计入总的牌数
    }

    private void removeTilesFromHand(Tile[] tiles) {
        for (Tile tile : tiles) {
            removeTileFromHand(tile);
        }
    }

    // 将吃的牌转化为Sequence数据结构存入Meld中
    private void addChiAsSequenceInMelds(AbstractGainAndExposedTileOperation chi) {

        Tile[]   tiles = copyAndSortTiles(chi.getGainExposedTile(), chi.getExposedTiles());

        Sequence seq   = Sequence.of(tiles, MeldSource.LAST_PLAYER, chi.getGainExposedTile());

        melds.add(seq);
    }

    @Override
    public void pon(PonOperation pon) {
        assert pon.getSrc() != MeldSource.SELF;

        checkExistAndManageOldAndNewTile(pon);
        addPonAsTripletInMelds(pon);
    }

    // 将碰的牌转化为Triplet数据结构存入Meld中
    private void addPonAsTripletInMelds(PonOperation pon) {

        Tile[]  tiles   = copyAndSortTiles(pon.getGainExposedTile(), pon.getExposedTiles());

        Triplet triplet = Triplet.of(tiles, pon.getSrc(), pon.getGainExposedTile());

        melds.add(triplet);
    }

    private Tile[] copyAndSortTiles(Tile tile, Tile[] tilesArray) {

        final int TILE_NUM = 1;

        Tile[]    tiles    = new Tile[TILE_NUM + tilesArray.length];
        ArrayUtil.copy(tilesArray, tiles, tilesArray.length);
        tiles[tiles.length - 1] = tile;
        Arrays.sort(tiles);

        return tiles;
    }

    @Override
    public void exposedKan(ExposedKanOperation kan) {
        assert kan.getSrc() != MeldSource.SELF;

        checkExistAndManageOldAndNewTile(kan);
        addExposedKanAsQuadInMelds(kan);
    }

    private void addExposedKanAsQuadInMelds(ExposedKanOperation kan) {
        Tile[]         tiles = copyAndSortTiles(kan.getGainExposedTile(), kan.getExposedTiles());

        ExposedKanQuad quad  = ExposedKanQuad.of(tiles, kan.getSrc(), kan.getGainExposedTile());

        melds.add(quad);
    }

    @Override
    public void draw(DrawOperation draw) {
        Tile drawTile = draw.getGainTile();

        assert !containTileInHand(drawTile);

        addTileToHand(drawTile);
        tileCountPlus1(drawTile);
    }

    private boolean containTilesInHand(Tile[] tiles) {
        for (Tile tile : tiles) {
            if (!containTileInHand(tile)) {
                return false;
            }
        }
        return true;
    }

    private boolean containTileInHand(Tile tile) {
        return getSetInTilesInHand(tile.getTileType()).contains(tile);
    }

    private Set<Tile> getSetInTilesInHand(TileType tileType) {
        return tilesInHand.computeIfAbsent(tileType, (x) -> new HashSet<>());
    }

    private void addTileToHand(Tile tile) {
        getSetInTilesInHand(tile.getTileType()).add(tile);
    }

    private void tileCountPlus1(Tile tile) {
        countNumberAll.put(tile.getTileType(), countNumberAll.getOrDefault(tile.getTileType(), 0) + 1);
    }

    @Override
    public void concealedKan(ConcealedKanOperation kan) {
        assert containTilesInHand(kan.getExposedTiles());

        removeTilesFromHand(kan.getExposedTiles());

        Arrays.sort(kan.getExposedTiles());

        ConcealedKanQuad quad = ConcealedKanQuad.of(kan.getExposedTiles(), MeldSource.SELF);

        melds.add(quad);
    }

    @Override
    public void discard(DiscardOperation discard) {
        assert containTileInHand(discard.getDiscardTile());

        removeTileFromHand(discard.getDiscardTile());
        tileCountMinus1(discard.getDiscardTile());
    }

    private void removeTileFromHand(Tile tile) {
        getSetInTilesInHand(tile.getTileType()).remove(tile);
    }

    private void tileCountMinus1(Tile tile) {
        countNumberAll.put(tile.getTileType(), countNumberAll.getOrDefault(tile.getTileType(), 0) - 1);
    }

    @Override
    public void kita(Kita kita) {
        // TODO Auto-generated method stub
    }

    @Override
    public void addKan(AddKanOperation kan) {

        assert melds.contains(kan.getTriplet()) && containTilesInHand(kan.getExposedTiles());

        Triplet triplet        = kan.getTriplet();

        Tile[]  tiles          = copyAndSortTiles(kan.getExposedTiles()[0], triplet.getTiles());

        int     numOfMakeCalls = melds.size();

        melds.remove(triplet);
        removeTilesFromHand(kan.getExposedTiles());

        AddKanQuad quad =
            AddKanQuad.of(tiles, triplet.getSrc(), triplet.getSpecialTile(), kan.getExposedTiles()[0], numOfMakeCalls);

        melds.add(quad);
    }
}
