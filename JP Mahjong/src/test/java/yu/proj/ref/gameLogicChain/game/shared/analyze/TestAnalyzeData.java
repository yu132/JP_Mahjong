package yu.proj.ref.gameLogicChain.game.shared.analyze;

import java.util.EnumMap;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManagerImpl;
import yu.proj.ref.ops.tilesRelated.ChiiOperation;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.ops.tilesRelated.PonOperation;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;

/**
 * @ClassName : TestAnalyzeChiiData  
 * @Description : TODO(这里用一句话描述这个类的作用)  
 * @author  余定邦  
 * @date  2020年11月21日  
 */
public class TestAnalyzeData {
    /**  
     * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
     */
    public PlayerTileManager playerTileManager;
    /**  
     * @Fields field:field:{todo}(用一句话描述这个变量表示什么)  
     */
    public GameRule gameRule;

    public EnumMap<TileType, Integer> count;

    /**  
     * 创建一个新的实例 yu.proj.ref.gameLogicChain.game.shared.analyze.chii.TestAnalyzeChiiData.  
     *  
     */
    public TestAnalyzeData() {
        this.gameRule = MahjongSoulRule.mahjongSoulDefaultFourPalyerRule;
        this.count = new EnumMap<>(TileType.class);
        this.playerTileManager = new PlayerTileManagerImpl();
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
}