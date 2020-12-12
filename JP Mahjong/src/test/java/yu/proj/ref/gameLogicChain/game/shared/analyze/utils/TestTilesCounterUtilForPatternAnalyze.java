package yu.proj.ref.gameLogicChain.game.shared.analyze.utils;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.TilesCounterUtilForPatternAnalyze;

/**  
 * @ClassName: TestTilesCounterUtil  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TestTilesCounterUtilForPatternAnalyze {

    private TestAnalyzeData data = new TestAnalyzeData();

    private TilesCounterUtilForPatternAnalyze tilesCounterUtil =
        new TilesCounterUtilForPatternAnalyze(data.playerTileManager);


    private void initTiles() {
        forEachNormalTileType((tileType) -> {
            data.draw(tileType);
        });
    }

    @Test
    public void test() {
        initTiles();

        assertEquals(1, tilesCounterUtil.count(MAN_1));

        assertEquals(1, tilesCounterUtil.count(SOU_1));

        assertEquals(1, tilesCounterUtil.count(PIN_9));

        assertEquals(1, tilesCounterUtil.count(SOUTH));
    }

    @Test
    public void test2() {
        initTiles();

        assertEquals(7, tilesCounterUtil.count(MAN_1, SOU_1, PIN_1, MAN_5, PIN_6, SOUTH, RED));
    }

    @Test
    public void testHas() {
        initTiles();

        assertEquals(1, tilesCounterUtil.has(MAN_1));

        assertEquals(1, tilesCounterUtil.has(SOU_1));

        assertEquals(1, tilesCounterUtil.has(PIN_9));

        assertEquals(1, tilesCounterUtil.has(SOUTH));
    }

    @Test
    public void testHas2() {
        initTiles();

        assertEquals(7, tilesCounterUtil.has(MAN_1, SOU_1, PIN_1, MAN_5, PIN_6, SOUTH, RED));
    }

    @Test
    public void testCountDifferentTerminals2() {
        data.draw(MAN_1, MAN_9);
        assertEquals(2, tilesCounterUtil.differentTerminals());

        data.draw(SOU_9, PIN_1);
        assertEquals(4, tilesCounterUtil.differentTerminals());
    }

    @Test
    public void testCountDifferentTerminals() {
        initTiles();
        initTiles();// 每个牌都有2个了

        assertEquals(6, tilesCounterUtil.differentTerminals());
    }

    @Test
    public void testCountDifferentHonors() {
        data.draw(MAN_1, MAN_9);
        assertEquals(0, tilesCounterUtil.differentHonors());

        data.draw(SOUTH, WHITE);
        assertEquals(2, tilesCounterUtil.differentHonors());
    }

    @Test
    public void testCountDifferentHonors2() {
        initTiles();
        initTiles();// 每个牌都有2个了

        assertEquals(7, tilesCounterUtil.differentHonors());
    }

    @Test
    public void testCountDifferentTerminalsAndHonors() {
        initTiles();
        initTiles();// 每个牌都有2个了

        assertEquals(13, tilesCounterUtil.differentTerminalsAndHonors());
    }

    @Test
    public void testSequencetTotalNum() {
        data.draw(MAN_1, MAN_2, MAN_3);
        data.chii(MAN_1, MAN_2, MAN_3);
        data.draw(SOU_1, SOU_2, SOU_3);
        data.draw(PIN_1, PIN_2, PIN_3);

        data.draw(SOUTH);

        Tenpaiable tenpaiable = data.getFirstTenpai(SOUTH);

        tilesCounterUtil = new TilesCounterUtilForPatternAnalyze(data.playerTileManager, tenpaiable, SOUTH);

        assertEquals(4, tilesCounterUtil.sequenceTotalNum());
    }

    @Test
    public void testTripletTotalNum() {
        data.draw(MAN_1, MAN_1, MAN_1);
        data.pon(MAN_2, MAN_2, MAN_2);
        data.draw(SOU_1, SOU_1, SOU_1);
        data.draw(PIN_1, PIN_1, PIN_1);

        data.draw(SOUTH);

        Tenpaiable tenpaiable = data.getFirstTenpai(SOUTH);

        tilesCounterUtil = new TilesCounterUtilForPatternAnalyze(data.playerTileManager, tenpaiable, SOUTH);

        assertEquals(4, tilesCounterUtil.tripletTotalNum());
    }
}
