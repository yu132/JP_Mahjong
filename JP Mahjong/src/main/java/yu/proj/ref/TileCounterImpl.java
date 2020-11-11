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
import yu.proj.ref.exposedTile.AddKanQuad;
import yu.proj.ref.exposedTile.ConcealedKanQuad;
import yu.proj.ref.exposedTile.ExposedKanQuad;
import yu.proj.ref.exposedTile.ExposedTile;
import yu.proj.ref.exposedTile.Kita;
import yu.proj.ref.exposedTile.MeldSource;
import yu.proj.ref.exposedTile.Sequence;
import yu.proj.ref.exposedTile.Triplet;
import yu.proj.ref.ops.AbstractGainAndExposedAllTileOperation;
import yu.proj.ref.ops.AddKanOperation;
import yu.proj.ref.ops.ConcealedKanOperation;
import yu.proj.ref.ops.DiscardOperation;
import yu.proj.ref.ops.DiscardTileOperation;
import yu.proj.ref.ops.DrawOperation;
import yu.proj.ref.ops.ExposedKanOperation;
import yu.proj.ref.ops.ExposedTileOperation;
import yu.proj.ref.ops.GainExposedTileOperation;
import yu.proj.ref.ops.GainTileOperation;
import yu.proj.ref.ops.KitaOperation;
import yu.proj.ref.ops.Operation;
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
public final class TileCounterImpl implements TileCounter {

    private EnumMap<TileType, Set<Tile>> tilesInHand = new EnumMap<>(TileType.class);

    private EnumMap<TileType, Integer> countNumberAll = new EnumMap<>(TileType.class);

    private EnumSet<TileType> kanNumber = EnumSet.noneOf(TileType.class);

    private List<ExposedTile> exposedTiles = new ArrayList<>();

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
    public List<ExposedTile> getExposedTile() {
        return new ArrayList<>(this.exposedTiles);// 为安全性进行保护性复制
    }

    @Override
    public void chi(AbstractGainAndExposedAllTileOperation chi) {
        manageTilesAndCountWithOperation(chi);
        insertChiAsSequence(chi);
    }

    // 将吃的牌转化为Sequence数据结构存入Meld中
    private void insertChiAsSequence(AbstractGainAndExposedAllTileOperation chi) {

        Tile[]   tiles = copyAndSortTiles(chi.getGainExposedTile(), chi.getExposedTiles());

        Sequence seq   = Sequence.of(tiles, MeldSource.LAST_PLAYER, chi.getGainExposedTile());

        exposedTiles.add(seq);
    }

    @Override
    public void pon(PonOperation pon) {
        assert pon.getSrc() != MeldSource.SELF;

        manageTilesAndCountWithOperation(pon);
        insertPonAsTriplet(pon);
    }

    // 将碰的牌转化为Triplet数据结构存入Meld中
    private void insertPonAsTriplet(PonOperation pon) {

        Tile[]  tiles   = copyAndSortTiles(pon.getGainExposedTile(), pon.getExposedTiles());

        Triplet triplet = Triplet.of(tiles, pon.getSrc(), pon.getGainExposedTile());

        exposedTiles.add(triplet);
    }

    @Override
    public void exposedKan(ExposedKanOperation kan) {
        assert kan.getSrc() != MeldSource.SELF;
        assert containTilesInHand(kan.getExposedTiles()) && !containTileInHand(kan.getGainExposedTile());

        manageTilesAndCountWithOperation(kan);
        insertExposedKanAsQuad(kan);
    }

    private void insertExposedKanAsQuad(ExposedKanOperation kan) {

        Tile[]         tiles = copyAndSortTiles(kan.getGainExposedTile(), kan.getExposedTiles());

        ExposedKanQuad quad  = ExposedKanQuad.of(tiles, kan.getSrc(), kan.getGainExposedTile());

        exposedTiles.add(quad);
    }

    @Override
    public void draw(DrawOperation draw) {
        manageTilesAndCountWithOperation(draw);
    }

    @Override
    public void concealedKan(ConcealedKanOperation kan) {

        manageTilesAndCountWithOperation(kan);

        insertConcealdedAsQuad(kan);
    }

    private void insertConcealdedAsQuad(ConcealedKanOperation kan) {
        Arrays.sort(kan.getExposedTiles());

        ConcealedKanQuad quad = ConcealedKanQuad.of(kan.getExposedTiles(), MeldSource.SELF);

        exposedTiles.add(quad);
    }

    @Override
    public void discard(DiscardOperation discard) {
        manageTilesAndCountWithOperation(discard);
    }

    @Override
    public void kita(KitaOperation kitaOp) {

        manageTilesAndCountWithOperation(kitaOp);

        Kita kita = Kita.of(kitaOp.getExposedTiles()[0]);

        exposedTiles.add(kita);
    }

    @Override
    public void addKan(AddKanOperation kan) {

        assert exposedTiles.contains(kan.getTriplet());

        manageTilesAndCountWithOperation(kan);

        removeOldTripletAndInsertAddKanAsQuad(kan);
    }

    private void removeOldTripletAndInsertAddKanAsQuad(AddKanOperation kan) {
        Triplet triplet        = kan.getTriplet();

        Tile[]  tiles          = copyAndSortTiles(kan.getExposedTiles()[0], triplet.getTiles());

        int     numOfMakeCalls = exposedTiles.size();

        exposedTiles.remove(triplet);

        AddKanQuad quad =
            AddKanQuad.of(tiles, triplet.getSrc(), triplet.getSpecialTile(), kan.getExposedTiles()[0], numOfMakeCalls);

        exposedTiles.add(quad);
    }

    private void manageTilesAndCountWithOperation(Operation op) {
        if (op instanceof DiscardTileOperation) {
            manageTilesWithDiscardTileOperation((DiscardTileOperation)op);
        }
        if (op instanceof ExposedTileOperation) {
            manageTilesWithExposedTileOperation((ExposedTileOperation)op);
        }
        if (op instanceof GainExposedTileOperation) {
            manageTilesWithGainExposedTileOperation((GainExposedTileOperation)op);
        }
        if (op instanceof GainTileOperation) {
            manageTilesWithGainTileOperation((GainTileOperation)op);
        }
    }

    private void manageTilesWithGainTileOperation(GainTileOperation op) {
        assert !containTileInHand(op.getGainTile());

        insertTileToHand(op.getGainTile());
        tileCountPlus1(op.getGainTile());
    }

    private void manageTilesWithGainExposedTileOperation(GainExposedTileOperation op) {
        assert !containTileInHand(op.getGainExposedTile());

        tileCountPlus1(op.getGainExposedTile());
    }

    private void manageTilesWithExposedTileOperation(ExposedTileOperation exposedTileOp) {
        assert containTilesInHand(exposedTileOp.getExposedTiles());

        removeTilesFromHand(exposedTileOp.getExposedTiles());
    }

    private void manageTilesWithDiscardTileOperation(DiscardTileOperation discardOp) {
        assert containTileInHand(discardOp.getDiscardTile());

        removeTileFromHand(discardOp.getDiscardTile());
        tileCountMinus1(discardOp.getDiscardTile());
    }

    private void removeTileFromHand(Tile tile) {
        getSetInTilesInHand(tile.getTileType()).remove(tile);
    }

    private void removeTilesFromHand(Tile[] tiles) {
        for (Tile tile : tiles) {
            removeTileFromHand(tile);
        }
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

    private void insertTileToHand(Tile tile) {
        getSetInTilesInHand(tile.getTileType()).add(tile);
    }

    private void tileCountPlus1(Tile tile) {
        countNumberAll.put(tile.getTileType(), countNumberAll.getOrDefault(tile.getTileType(), 0) + 1);
    }

    private void tileCountMinus1(Tile tile) {
        countNumberAll.put(tile.getTileType(), countNumberAll.getOrDefault(tile.getTileType(), 0) - 1);
    }

    private Tile[] copyAndSortTiles(Tile tile, Tile[] tilesArray) {

        final int TILE_NUM = 1;

        Tile[]    tiles    = new Tile[TILE_NUM + tilesArray.length];
        ArrayUtil.copy(tilesArray, tiles, tilesArray.length);
        tiles[tiles.length - 1] = tile;
        Arrays.sort(tiles);

        return tiles;
    }
}
