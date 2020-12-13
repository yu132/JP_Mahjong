package yu.proj.ref.gameLogicChain.game.shared.analyze;

import static yu.proj.ref.tile.TileType.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.AnalyzeTenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.TenpaiManager;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeData.YakuAnalyzeDataBuilder;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuAnalyzeDataForTest;
import yu.proj.ref.gameLogicChain.game.shared.analyze.yaku.YakuManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManagerImpl;
import yu.proj.ref.ops.tilesRelated.ChiiOperation;
import yu.proj.ref.ops.tilesRelated.ConcealedKanOperation;
import yu.proj.ref.ops.tilesRelated.DiscardOperation;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.ops.tilesRelated.ExposedKanOperation;
import yu.proj.ref.ops.tilesRelated.PonOperation;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;
import yu.proj.ref.tilePatternElement.MeldSource;

/**
 * @ClassName : TestAnalyzeChiiData  
 * @Description : TODO(这里用一句话描述这个类的作用)  
 * @author  余定邦  
 * @date  2020年11月21日  
 */
public class TestAnalyzeData {
    public PlayerTileManager playerTileManager;
    public GameRule gameRule;
    public EnumMap<TileType, Integer> count;
    public YakuManager yakuManager;

    public TestAnalyzeData() {
        this.gameRule = MahjongSoulRule.mahjongSoulDefaultFourPalyerRule;
        this.count = new EnumMap<>(TileType.class);
        this.playerTileManager = new PlayerTileManagerImpl();
        this.yakuManager = new YakuManager();
    }

    public void draw(TileType... tileTypesToDraw) {
        for (TileType tileTypeToDraw : tileTypesToDraw) {
            Tile tile = getTile(tileTypeToDraw);
            DrawOperation op = new DrawOperation(tile);
            playerTileManager.draw(op);
        }
    }

    public Tile getTile(TileType tileTypeToDraw) {
        int index = count.getOrDefault(tileTypeToDraw, 0);
        count.put(tileTypeToDraw, index + 1);
        Tile tile = Tile.of(tileTypeToDraw, index);
        return tile;
    }

    public Tile[] getTiles(TileType... tileTypeToDraw) {
        Tile[] tiles = new Tile[tileTypeToDraw.length];
        for (int i = 0; i < tiles.length; ++i) {
            tiles[i] = getTile(tileTypeToDraw[i]);
        }
        return tiles;
    }

    public Tile[] getTiles(List<TileType> tileTypeToDraw) {
        return getTiles(tileTypeToDraw.toArray(new TileType[0]));
    }

    public void draw(Tile... tiles) {
        for (Tile tile : tiles) {
            DrawOperation op = new DrawOperation(tile);
            playerTileManager.draw(op);
        }
    }

    public void draw(List<TileType> tiles) {
        draw(tiles.toArray(new TileType[0]));
    }

    public void chii(TileType... tiles) {
        assert tiles.length == 3 && tiles[0].previousOf(tiles[1]) && tiles[1].previousOf(tiles[2]);

        Tile[] t = getTiles(tiles);

        draw(t[0], t[1]);

        ChiiOperation op = new ChiiOperation(new Tile[] {t[0], t[1]}, t[2]);

        playerTileManager.chii(op);
    }

    public void pon(TileType... tiles) {
        assert tiles.length == 3 && tiles[0].sameNormalType(tiles[1]) && tiles[1].sameNormalType(tiles[2]);

        Tile[] t = getTiles(tiles);

        draw(t[0], t[1]);

        PonOperation op = new PonOperation(new Tile[] {t[0], t[1]}, t[2], MeldSource.NEXT_PLAYER);

        playerTileManager.pon(op);
    }

    public void concealedKan(TileType... tiles) {
        assert tiles.length == 4 && tiles[0].sameNormalType(tiles[1]) && tiles[1].sameNormalType(tiles[2])
            && tiles[2].sameNormalType(tiles[3]);

        Tile[] t = getTiles(tiles);

        Tile temp = Tile.of(NORTH, 1);

        draw(t[0], t[1], t[2], t[3]);

        ConcealedKanOperation op = new ConcealedKanOperation(t, temp);

        playerTileManager.concealedKan(op);

        DiscardOperation opDis = new DiscardOperation(temp);

        playerTileManager.discard(opDis);
    }

    public void exposedKan(MeldSource source, TileType... tiles) {
        assert tiles.length == 4 && tiles[0].sameNormalType(tiles[1]) && tiles[1].sameNormalType(tiles[2])
            && tiles[2].sameNormalType(tiles[3]);

        Tile[] t = getTiles(tiles);

        Tile temp = Tile.of(NORTH, 1);

        draw(t[0], t[1], t[2]);

        ExposedKanOperation op = new ExposedKanOperation(Arrays.copyOfRange(t, 0, 3), t[3], temp, source);

        playerTileManager.exposedKan(op);

        DiscardOperation opDis = new DiscardOperation(temp);

        playerTileManager.discard(opDis);
    }

    public boolean bothContainYaku(Yaku yaku) {
        return this.yakuManager.getRonYakus().contains(yaku) && this.yakuManager.getTsumoYakus().contains(yaku);
    }

    public boolean bothNotContainYaku(Yaku yaku) {
        return !this.yakuManager.getRonYakus().contains(yaku) && !this.yakuManager.getTsumoYakus().contains(yaku);
    }

    public Tenpaiable getFirstTenpai(TileType tileType) {
        Tenpaiable tenpaiable = analyzeTenpai().getTenpaiable(tileType).get(0);
        return tenpaiable;
    }

    public Tenpaiable getFirstTenpai(TileType tileType, Class<?> clazz) {
        return getFirstTenpai(tileType, (tenpaiable) -> clazz.isInstance(tenpaiable));
    }

    public Tenpaiable getFirstTenpai(TileType tileType, Function<Tenpaiable, Boolean> filter) {
        for (Tenpaiable tenpaiable : analyzeTenpai().getTenpaiable(tileType)) {
            if (filter.apply(tenpaiable)) {
                return tenpaiable;
            }
        }
        throw new NoSuchElementException();
    }

    public TenpaiManager analyzeTenpai() {
        TenpaiManager tenpaiManager = new AnalyzeTenpaiable(this.gameRule).analyze(this.playerTileManager);
        return tenpaiManager;
    }

    public YakuAnalyzeDataBuilder yaBuilder(Tenpaiable tenpaiable, TileType tileToWin) {
        return YakuAnalyzeDataForTest.builder(this, tenpaiable, tileToWin).rule(gameRule);
    }

    public YakuAnalyzeData yaData(Tenpaiable tenpaiable, TileType tileToWin) {
        return yaBuilder(tenpaiable, tileToWin).build();
    }
}