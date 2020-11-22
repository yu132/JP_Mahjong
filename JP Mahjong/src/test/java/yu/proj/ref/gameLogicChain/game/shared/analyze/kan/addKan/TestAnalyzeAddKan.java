package yu.proj.ref.gameLogicChain.game.shared.analyze.kan.addKan;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.List;

import org.junit.Test;

import yu.proj.ref.exposedTile.MeldSource;
import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.ops.tilesRelated.PonOperation;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TestAnalyzeAddKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TestAnalyzeAddKan {

    private TestAnalyzeData data = new TestAnalyzeData();

    private AnalyzeAddKan analyzer = new AnalyzeAddKan();

    @Test
    public void test() {

        TileType testTileType = WEST;

        testAddKan(testTileType);
    }

    @Test
    public void test1() {

        TileType testTileType = MAN_1;

        testAddKan(testTileType);
    }

    @Test
    public void test2() {

        TileType testTileType = SOU_8;

        testAddKan(testTileType);
    }

    private void testAddKan(TileType testTileType) {
        Tile tile       = Tile.of(testTileType, 0);
        Tile tile1      = Tile.of(testTileType, 1);
        Tile addKanTile = Tile.of(testTileType, 2);
        Tile ponTile    = Tile.of(testTileType, 3);

        testHelper(testTileType, tile, tile1, addKanTile, ponTile);
    }

    private void testHelper(TileType testTileType, Tile tile, Tile tile1, Tile addKanTile, Tile ponTile) {
        data.draw(tile, tile1, addKanTile);

        data.playerTileManager.pon(new PonOperation(new Tile[] {tile, tile1}, ponTile, MeldSource.NEXT_PLAYER));

        List<AddKanable> addKanables = analyzer.analyze(data.playerTileManager);

        assertEquals(1, addKanables.size());
        assertEquals(testTileType, addKanables.get(0).getToAddKan());
    }

    @Test
    public void testRed() {
        Tile tile       = Tile.of(MAN_5, 0);
        Tile tile1      = Tile.of(MAN_5, 1);
        Tile addKanTile = Tile.of(MAN_5_RED, 2);
        Tile ponTile    = Tile.of(MAN_5, 3);

        testHelper(MAN_5, tile, tile1, addKanTile, ponTile);
    }

    @Test
    public void testRed2() {
        Tile tile       = Tile.of(MAN_5, 0);
        Tile tile1      = Tile.of(MAN_5, 1);
        Tile addKanTile = Tile.of(MAN_5, 3);
        Tile ponTile    = Tile.of(MAN_5_RED, 2);

        testHelper(MAN_5, tile, tile1, addKanTile, ponTile);
    }

    @Test
    public void testRed3() {
        Tile tile       = Tile.of(MAN_5_RED, 0);
        Tile tile1      = Tile.of(MAN_5, 1);
        Tile addKanTile = Tile.of(MAN_5, 3);
        Tile ponTile    = Tile.of(MAN_5, 2);

        testHelper(MAN_5, tile, tile1, addKanTile, ponTile);
    }

    @Test
    public void testLackTile() {

        Tile tile       = Tile.of(WEST, 0);
        Tile tile1      = Tile.of(WEST, 1);
        Tile addKanTile = Tile.of(WEST, 2);

        data.draw(tile, tile1);

        data.playerTileManager.pon(new PonOperation(new Tile[] {tile, tile1}, addKanTile, MeldSource.NEXT_PLAYER));

        List<AddKanable> addKanables = analyzer.analyze(data.playerTileManager);

        assertEquals(0, addKanables.size());
    }

    @Test
    public void testNoPon() {

        Tile tile       = Tile.of(WEST, 0);
        Tile tile1      = Tile.of(WEST, 1);
        Tile addKanTile = Tile.of(WEST, 2);
        Tile ponTile    = Tile.of(WEST, 3);

        data.draw(tile, tile1, addKanTile, ponTile);

        List<AddKanable> addKanables = analyzer.analyze(data.playerTileManager);

        assertEquals(0, addKanables.size());
    }

}
