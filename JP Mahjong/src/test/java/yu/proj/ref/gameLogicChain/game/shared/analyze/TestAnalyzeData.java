package yu.proj.ref.gameLogicChain.game.shared.analyze;

import java.util.EnumMap;

import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManagerImpl;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.rule.MahjongSoulRule;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

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
        this.gameRule          = MahjongSoulRule.mahjongSoulDefaultFourPalyerRule;
        this.count             = new EnumMap<>(TileType.class);
        this.playerTileManager = new PlayerTileManagerImpl();
    }

    public void draw(TileType... tileTypesToDraw) {
        for (TileType tileTypeToDraw : tileTypesToDraw) {
            int index = count.getOrDefault(tileTypeToDraw, 0);
            count.put(tileTypeToDraw, index + 1);
            Tile          tile = Tile.of(tileTypeToDraw, index);
            DrawOperation op   = new DrawOperation(tile);
            playerTileManager.draw(op);
        }
    }

    public void draw(Tile... tiles) {
        for (Tile tile : tiles) {
            DrawOperation op = new DrawOperation(tile);
            playerTileManager.draw(op);
        }
    }
}