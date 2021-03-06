package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

import cn.hutool.core.util.ArrayUtil;
import lombok.AccessLevel;
import lombok.Getter;
import yu.proj.ref.ops.tilesRelated.AbstractGainAndExposedAllTileOperation;
import yu.proj.ref.ops.tilesRelated.AddKanOperation;
import yu.proj.ref.ops.tilesRelated.ChiiOperation;
import yu.proj.ref.ops.tilesRelated.ConcealedKanOperation;
import yu.proj.ref.ops.tilesRelated.DiscardOperation;
import yu.proj.ref.ops.tilesRelated.DiscardTileOperation;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.ops.tilesRelated.ExposedKanOperation;
import yu.proj.ref.ops.tilesRelated.ExposedTileOperation;
import yu.proj.ref.ops.tilesRelated.GainExposedTileOperation;
import yu.proj.ref.ops.tilesRelated.GainTileOperation;
import yu.proj.ref.ops.tilesRelated.KitaOperation;
import yu.proj.ref.ops.tilesRelated.Operation;
import yu.proj.ref.ops.tilesRelated.PonOperation;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.exposedTile.AddKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.ConcealedKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.ExposedKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.Kita;

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

@Getter(value = AccessLevel.PACKAGE)
public final class PlayerTileManagerImpl implements PlayerTileManager {

    private EnumMap<TileType, List<Tile>> tilesInHand = new EnumMap<>(TileType.class);

    private EnumMap<TileType, Integer> countNumberAll = new EnumMap<>(TileType.class);

    private EnumSet<TileType> kanNumber = EnumSet.noneOf(TileType.class);

    private PlayerExposedTilesManager playerExposedTilesManager = new PlayerExposedTilesManager();

    @Override
    public int countInHand(TileType tileType) {
        return tilesInHand.getOrDefault(tileType, Collections.emptyList()).size();
    }

    @Override
    public int countNormalAndRedInHand(TileType tileType) {
        return countInHand(tileType) + countInHand(tileType.getRed());
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
    public void chii(ChiiOperation chi) {
        manageTilesAndCountWithOperation(chi);
        insertChiAsSequence(chi);
    }

    // 将吃的牌转化为Sequence数据结构存入Meld中
    private void insertChiAsSequence(AbstractGainAndExposedAllTileOperation chi) {

        Tile[] tiles = copyAndSortTiles(chi.getGainExposedTile(), chi.getExposedTiles());

        Sequence seq = Sequence.of(tiles, MeldSource.LAST_PLAYER, chi.getGainExposedTile());

        playerExposedTilesManager.addSequence(seq);
    }

    @Override
    public void pon(PonOperation pon) {
        assert pon.getSrc() != MeldSource.SELF;

        manageTilesAndCountWithOperation(pon);
        insertPonAsTriplet(pon);
    }

    // 将碰的牌转化为Triplet数据结构存入Meld中
    private void insertPonAsTriplet(PonOperation pon) {

        Tile[] tiles = copyAndSortTiles(pon.getGainExposedTile(), pon.getExposedTiles());

        Triplet triplet = Triplet.of(tiles, pon.getSrc(), pon.getGainExposedTile());

        playerExposedTilesManager.addTriplet(triplet);
    }

    @Override
    public void exposedKan(ExposedKanOperation kan) {
        assert kan.getSrc() != MeldSource.SELF;
        assert containTilesInHand(kan.getExposedTiles()) && !containTileInHand(kan.getGainExposedTile());

        manageTilesAndCountWithOperation(kan);
        insertExposedKanAsQuad(kan);
    }

    private void insertExposedKanAsQuad(ExposedKanOperation kan) {

        Tile[] tiles = copyAndSortTiles(kan.getGainExposedTile(), kan.getExposedTiles());

        ExposedKanQuad quad = ExposedKanQuad.of(tiles, kan.getSrc(), kan.getGainExposedTile());

        playerExposedTilesManager.addExposedKanQuad(quad);
        kanNumber.add(quad.tileType());
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

        playerExposedTilesManager.addConcealedKanQuad(quad);
        kanNumber.add(quad.tileType());
    }

    @Override
    public void discard(DiscardOperation discard) {
        manageTilesAndCountWithOperation(discard);
    }

    @Override
    public void kita(KitaOperation kitaOp) {

        manageTilesAndCountWithOperation(kitaOp);

        Kita kita = Kita.of(kitaOp.getExposedTiles()[0]);

        playerExposedTilesManager.addKita(kita);
    }

    @Override
    public void addKan(AddKanOperation kan) {

        assert playerExposedTilesManager.containTriplet(kan.getTriplet());

        manageTilesAndCountWithOperation(kan);

        removeOldTripletAndInsertAddKanAsQuad(kan);
    }

    private void removeOldTripletAndInsertAddKanAsQuad(AddKanOperation kan) {
        Triplet triplet = kan.getTriplet();

        Tile[] tiles = copyAndSortTiles(kan.getExposedTiles()[0], triplet.getTiles());

        playerExposedTilesManager.removeTriplet(triplet);

        AddKanQuad quad = AddKanQuad.of(tiles, triplet.getSrc(), triplet.getSpecialTile(), kan.getExposedTiles()[0]);

        playerExposedTilesManager.addAddKanQuad(quad);
        kanNumber.add(quad.tileType());
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

    private List<Tile> getSetInTilesInHand(TileType tileType) {
        return tilesInHand.computeIfAbsent(tileType, (x) -> new ArrayList<>());
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

        Tile[] tiles = new Tile[TILE_NUM + tilesArray.length];
        ArrayUtil.copy(tilesArray, tiles, tilesArray.length);
        tiles[tiles.length - 1] = tile;
        Arrays.sort(tiles);

        return tiles;
    }

    @Override
    public PlayerExposedTilesManager getPlayerExposedTilesManager() {
        return playerExposedTilesManager;
    }

    @Override
    public PlayerTileInHandGetter playerTileInHandGetter() {
        return new PlayerTileInHandGetterImpl(this);
    }
}
