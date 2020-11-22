package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import static yu.proj.ref.TestUtils.*;
import static yu.proj.ref.tile.TileType.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yu.proj.ref.ops.tilesRelated.AbstractGainAndExposedAllTileOperation;
import yu.proj.ref.ops.tilesRelated.AddKanOperation;
import yu.proj.ref.ops.tilesRelated.ChiOperation;
import yu.proj.ref.ops.tilesRelated.ConcealedKanOperation;
import yu.proj.ref.ops.tilesRelated.DiscardOperation;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.ops.tilesRelated.ExposedKanOperation;
import yu.proj.ref.ops.tilesRelated.KitaOperation;
import yu.proj.ref.ops.tilesRelated.PonOperation;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.exposedTile.Kita;


/**  
 * @ClassName: TestTileCounter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */
public class TestTileCounter {

    private PlayerTileManager tileCounter;

    @Before
    public void beforeTest() {
        tileCounter = new PlayerTileManagerImpl();
    }

    @Test
    public void countInHand() {
        draw(Tile.of(MAN_1, 0));

        assertTileNumberInHand(MAN_1, 1);
    }

    @Test
    public void countInHandAndRed() {
        draw(Tile.of(MAN_5, 0));
        draw(Tile.of(MAN_5_RED, 0));

        assertTileNumberInHand(MAN_5, 1);
        assertTileNumberInHand(MAN_5_RED, 1);

        Assert.assertEquals(2, tileCounter.countKanAs3TileAndRedAsNormal(MAN_5));
    }

    @Test
    public void drawTileAndGetTileNumber() {

        draw(Tile.of(MAN_1, 0));

        assertTileNumberInHand(MAN_1, 1);
    }

    @Test
    public void discardTileAndGetTileNumber() {

        Tile tile = Tile.of(MAN_1, 0);

        draw(tile);

        discard(tile);

        assertNotInHand(MAN_1);
    }

    @Test
    public void discardTileNotInHand() {

        Tile tile = Tile.of(MAN_1, 0);

        expectExceptionOrError(() -> {
            discard(tile);
        });

    }

    @Test
    public void chi() {

        Tile m2 = Tile.of(MAN_2, 1);

        draw(m2);

        Tile m4 = Tile.of(MAN_4, 1);

        draw(m4);

        Tile                                   m3  = Tile.of(MAN_3, 1);

        AbstractGainAndExposedAllTileOperation chi = new ChiOperation(new Tile[] {m2, m4}, m3);

        tileCounter.chi(chi);

        assertTotalTileNumber(MAN_3, 1);

        Tile firstTileOfSequence = tileCounter.getPlayerExposedTilesManager().sequences.get(0).getTiles()[0];

        Assert.assertEquals(MAN_2, firstTileOfSequence.getTileType());

        assertNotInHand(MAN_2);
        assertNotInHand(MAN_4);
    }

    @Test
    public void chiButTileNotEnoughTile() {
        Tile m2 = Tile.of(MAN_2, 1);

        Tile m4 = Tile.of(MAN_4, 1);

        draw(m4);

        Tile                                   m3  = Tile.of(MAN_3, 1);

        AbstractGainAndExposedAllTileOperation chi = new ChiOperation(new Tile[] {m2, m4}, m3);

        expectExceptionOrError(() -> {
            tileCounter.chi(chi);
        });
    }

    @Test
    public void chiButNotSequenceArgument() {
        Tile m2 = Tile.of(MAN_2, 1);

        Tile m5 = Tile.of(MAN_5, 1);

        draw(m5);

        Tile m3 = Tile.of(MAN_3, 1);

        expectExceptionOrError(() -> {
            new ChiOperation(new Tile[] {m2, m5}, m3);
        });
    }

    @Test
    public void pon() {
        Tile m2_1 = Tile.of(MAN_2, 1);

        draw(m2_1);

        Tile m2_3 = Tile.of(MAN_2, 3);

        draw(m2_3);

        assertTileNumberInHand(MAN_2, 2);

        Tile         m2_2 = Tile.of(MAN_2, 2);

        PonOperation pon  = new PonOperation(new Tile[] {m2_1, m2_3}, m2_2, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        assertTotalTileNumber(MAN_2, 3);

        Tile firstTileOfSequence = tileCounter.getPlayerExposedTilesManager().triplets.get(0).getTiles()[0];

        assertTypeOfTile(MAN_2, firstTileOfSequence);

        assertNotInHand(MAN_2);
    }

    @Test
    public void ponButNotEnoughTile() {
        Tile m2_1 = Tile.of(MAN_2, 1);

        Tile m2_3 = Tile.of(MAN_2, 3);

        draw(m2_1);

        Tile         m2_2 = Tile.of(MAN_2, 2);

        PonOperation pon  = new PonOperation(new Tile[] {m2_1, m2_3}, m2_2, MeldSource.LAST_PLAYER);

        expectExceptionOrError(() -> {
            tileCounter.pon(pon);
        });
    }

    @Test
    public void ponButTilesISNotSameType() {
        Tile m2_1 = Tile.of(MAN_2, 1);

        Tile m2_3 = Tile.of(MAN_2, 3);

        Tile m3   = Tile.of(MAN_3, 2);

        expectExceptionOrError(() -> {
            new PonOperation(new Tile[] {m2_1, m2_3}, m3, MeldSource.LAST_PLAYER);
        });
    }

    @Test
    public void exposedKan() {
        Tile west_1 = Tile.of(WEST, 0);
        Tile west_2 = Tile.of(WEST, 1);
        Tile west_3 = Tile.of(WEST, 2);
        Tile west_4 = Tile.of(WEST, 3);

        Tile s9     = Tile.of(SOU_9, 3);

        draw(west_1, west_2, west_3);

        ExposedKanOperation kan =
            new ExposedKanOperation(new Tile[] {west_1, west_2, west_3}, west_4, s9, MeldSource.NEXT_PLAYER);

        tileCounter.exposedKan(kan);

        assertTotalTileNumber(WEST, 4);

        Tile firstTileOfSequence = tileCounter.getPlayerExposedTilesManager().exposedKanQuads.get(0).getTiles()[0];

        assertTypeOfTile(WEST, firstTileOfSequence);

        assertNotInHand(WEST);

        assertTileNumberInHand(SOU_9, 1);
    }

    @Test
    public void exposedKanButNotEnoughTile() {
        Tile west_1 = Tile.of(WEST, 0);
        Tile west_2 = Tile.of(WEST, 1);
        Tile west_3 = Tile.of(WEST, 2);
        Tile west_4 = Tile.of(WEST, 3);

        Tile s9     = Tile.of(SOU_9, 3);

        draw(west_1, west_2);

        ExposedKanOperation kan =
            new ExposedKanOperation(new Tile[] {west_1, west_2, west_3}, west_4, s9, MeldSource.NEXT_PLAYER);

        expectExceptionOrError(() -> {
            tileCounter.exposedKan(kan);
        });
    }

    @Test
    public void exposedkanButTilesISNotSameType() {
        Tile west_1 = Tile.of(WEST, 0);
        Tile west_2 = Tile.of(WEST, 1);
        Tile north  = Tile.of(NORTH, 2);
        Tile west_4 = Tile.of(WEST, 3);

        Tile s9     = Tile.of(SOU_9, 3);

        expectExceptionOrError(() -> {
            new ExposedKanOperation(new Tile[] {west_1, west_2, north}, west_4, s9, MeldSource.NEXT_PLAYER);
        });
    }

    @Test
    public void concealedKan() {
        Tile west_1 = Tile.of(WEST, 0);
        Tile west_2 = Tile.of(WEST, 1);
        Tile west_3 = Tile.of(WEST, 2);
        Tile west_4 = Tile.of(WEST, 3);

        Tile s9     = Tile.of(SOU_9, 3);

        draw(west_1, west_2, west_3, west_4);

        ConcealedKanOperation kan = new ConcealedKanOperation(new Tile[] {west_1, west_2, west_3, west_4}, s9);

        tileCounter.concealedKan(kan);

        Tile firstTileOfSequence = tileCounter.getPlayerExposedTilesManager().concealedKanQuads.get(0).getTiles()[0];

        assertTypeOfTile(WEST, firstTileOfSequence);

        assertNotInHand(WEST);

        assertTileNumberInHand(SOU_9, 1);
    }

    @Test
    public void concealedKanButNotEnoughTile() {
        Tile west_1 = Tile.of(WEST, 0);
        Tile west_2 = Tile.of(WEST, 1);
        Tile west_3 = Tile.of(WEST, 2);
        Tile west_4 = Tile.of(WEST, 3);

        Tile s9     = Tile.of(SOU_9, 3);

        draw(west_1, west_2, west_3);

        ConcealedKanOperation kan = new ConcealedKanOperation(new Tile[] {west_1, west_2, west_3, west_4}, s9);

        expectExceptionOrError(() -> {
            tileCounter.concealedKan(kan);
        });
    }

    @Test
    public void concealedkanButTilesISNotSameType() {
        Tile west_1 = Tile.of(WEST, 0);
        Tile west_2 = Tile.of(WEST, 1);
        Tile north  = Tile.of(NORTH, 2);
        Tile west_4 = Tile.of(WEST, 3);

        Tile s9     = Tile.of(SOU_9, 3);

        expectExceptionOrError(() -> {
            new ConcealedKanOperation(new Tile[] {west_1, west_2, north, west_4}, s9);
        });
    }

    @Test
    public void addKan() {
        Tile red_1 = Tile.of(RED, 0);
        Tile red_2 = Tile.of(RED, 1);
        Tile red_3 = Tile.of(RED, 2);
        Tile red_4 = Tile.of(RED, 3);

        Tile s9    = Tile.of(SOU_9, 3);

        draw(red_1, red_2, red_4);

        PonOperation pon = new PonOperation(new Tile[] {red_1, red_2}, red_3, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        Triplet         triplet = tileCounter.getPlayerExposedTilesManager().triplets.get(0);

        AddKanOperation kan     = new AddKanOperation(new Tile[] {red_4}, triplet, s9);

        tileCounter.addKan(kan);

        Assert.assertTrue(!tileCounter.getPlayerExposedTilesManager().containTriplet(triplet));

        Tile firstTileOfSequence = tileCounter.getPlayerExposedTilesManager().addKanQuads.get(0).getTiles()[0];

        assertTypeOfTile(RED, firstTileOfSequence);

        assertNotInHand(RED);

        assertTileNumberInHand(SOU_9, 1);
    }

    @Test
    public void addKanButTileNotExist() {
        Tile red_1 = Tile.of(RED, 0);
        Tile red_2 = Tile.of(RED, 1);
        Tile red_3 = Tile.of(RED, 2);
        Tile red_4 = Tile.of(RED, 3);

        Tile s9    = Tile.of(SOU_9, 3);

        draw(red_1, red_2);// 删除了red_4

        PonOperation pon = new PonOperation(new Tile[] {red_1, red_2}, red_3, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        Triplet         triplet = tileCounter.getPlayerExposedTilesManager().triplets.get(0);

        AddKanOperation kan     = new AddKanOperation(new Tile[] {red_4}, triplet, s9);

        expectExceptionOrError(() -> {
            tileCounter.addKan(kan);
        });
    }

    @Test
    public void addKanButTripletNotExist() {
        Tile red_1 = Tile.of(RED, 0);
        Tile red_2 = Tile.of(RED, 1);
        Tile red_3 = Tile.of(RED, 2);
        Tile red_4 = Tile.of(RED, 3);

        Tile s9    = Tile.of(SOU_9, 3);

        draw(red_1, red_2, red_4);

        Triplet         triplet = Triplet.of(new Tile[] {red_1, red_2, red_3}, MeldSource.LAST_PLAYER, red_3);

        AddKanOperation kan     = new AddKanOperation(new Tile[] {red_4}, triplet, s9);

        expectExceptionOrError(() -> {
            tileCounter.addKan(kan);
        });
    }

    @Test
    public void addKanButTilesISNotSameType() {
        Tile red_1 = Tile.of(RED, 0);
        Tile red_2 = Tile.of(RED, 1);
        Tile red_3 = Tile.of(RED, 2);
        Tile east  = Tile.of(EAST, 3);

        Tile s9    = Tile.of(SOU_9, 3);

        draw(red_1, red_2, east);

        PonOperation pon = new PonOperation(new Tile[] {red_1, red_2}, red_3, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        Triplet triplet = tileCounter.getPlayerExposedTilesManager().triplets.get(0);
        expectExceptionOrError(() -> {
            new AddKanOperation(new Tile[] {east}, triplet, s9);
        });
    }

    @Test
    public void kita() {
        Tile north = Tile.of(NORTH, 0);

        Tile s9    = Tile.of(SOU_9, 3);

        draw(north);

        KitaOperation kitaOp = new KitaOperation(new Tile[] {north}, s9);

        tileCounter.kita(kitaOp);

        @SuppressWarnings("unused")
        Kita kita = tileCounter.getPlayerExposedTilesManager().kitas.get(0);

        assertNotInHand(NORTH);

        assertTileNumberInHand(SOU_9, 1);
    }

    @Test
    public void kitaButNorthNotExist() {
        Tile          north  = Tile.of(NORTH, 0);

        Tile          s9     = Tile.of(SOU_9, 3);

        KitaOperation kitaOp = new KitaOperation(new Tile[] {north}, s9);

        expectExceptionOrError(() -> {
            tileCounter.kita(kitaOp);
        });
    }

    @Test
    public void kitaButTypeNotNorth() {
        Tile s8 = Tile.of(SOU_8, 3);
        Tile s9 = Tile.of(SOU_9, 3);

        expectExceptionOrError(() -> {
            new KitaOperation(new Tile[] {s8}, s9);
        });
    }

    private void assertTotalTileNumber(TileType type, int num) {
        Assert.assertEquals(num, tileCounter.countKanAs3TileAndRedAsNormal(type));
    }

    private void assertTypeOfTile(TileType type, Tile tile) {
        Assert.assertEquals(type, tile.getTileType());
    }

    private void assertTileNumberInHand(TileType type, int num) {
        Assert.assertEquals(num, tileCounter.countInHand(type));
    }

    private void assertNotInHand(TileType type) {
        assertTileNumberInHand(type, 0);
    }

    private void discard(Tile tile) {
        DiscardOperation discardOp = new DiscardOperation(tile);
        this.tileCounter.discard(discardOp);
    }

    private void draw(Tile... tiles) {
        for (Tile tile : tiles) {
            draw(tile);
        }
    }

    private void draw(Tile tile) {
        DrawOperation drawOp = new DrawOperation(tile);
        this.tileCounter.draw(drawOp);
    }

    public static void main(String[] args) {
        TestTileCounter testTileCounter = new TestTileCounter();
        testTileCounter.beforeTest();
        testTileCounter.chi();
    }

}
