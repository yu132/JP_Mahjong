package yu.proj.ref;

import static yu.proj.ref.TestUtils.*;
import static yu.proj.ref.TileType.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import yu.proj.ref.meld.AddKanQuad;
import yu.proj.ref.meld.ConcealedKanQuad;
import yu.proj.ref.meld.ExposedKanQuad;
import yu.proj.ref.meld.MeldSource;
import yu.proj.ref.meld.Sequence;
import yu.proj.ref.meld.Triplet;
import yu.proj.ref.ops.AbstractGainAndExposedTileOperation;
import yu.proj.ref.ops.AddKanOperation;
import yu.proj.ref.ops.ChiOperation;
import yu.proj.ref.ops.ConcealedKanOperation;
import yu.proj.ref.ops.DiscardOperation;
import yu.proj.ref.ops.DrawOperation;
import yu.proj.ref.ops.ExposedKanOperation;
import yu.proj.ref.ops.PonOperation;


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

    private TileCounter tileCounter;

    @Before
    public void beforeTest() {
        tileCounter = new TileCounterImpl();
    }

    @Test
    public void drawTileAndGetTileNumber() {

        draw(new Tile(MAN_1, 0));

        assertTileNumberInHand(MAN_1, 1);
    }

    @Test
    public void discardTileAndGetTileNumber() {

        Tile tile = new Tile(MAN_1, 0);

        draw(tile);

        discard(tile);

        assertNotInHand(MAN_1);
    }

    @Test
    public void discardTileNotInHand() {

        Tile tile = new Tile(MAN_1, 0);

        expectExceptionOrError(() -> {
            discard(tile);
        });

    }

    @Test
    public void chi() {

        Tile m2 = new Tile(MAN_2, 1);

        draw(m2);

        Tile m4 = new Tile(MAN_4, 1);

        draw(m4);

        Tile                                m3  = new Tile(MAN_3, 1);

        AbstractGainAndExposedTileOperation chi = new ChiOperation(new Tile[] {m2, m4}, m3);

        tileCounter.chi(chi);

        assertTotalTileNumber(MAN_3, 1);

        Tile firstTileOfSequence = ((Sequence)tileCounter.getMeld().get(0)).getTiles()[0];

        Assert.assertEquals(MAN_2, firstTileOfSequence.getTileType());

        assertNotInHand(MAN_2);
        assertNotInHand(MAN_4);
    }

    @Test
    public void chiButTileNotEnoughTile() {
        Tile m2 = new Tile(MAN_2, 1);

        Tile m4 = new Tile(MAN_4, 1);

        draw(m4);

        Tile                                m3  = new Tile(MAN_3, 1);

        AbstractGainAndExposedTileOperation chi = new ChiOperation(new Tile[] {m2, m4}, m3);

        expectExceptionOrError(() -> {
            tileCounter.chi(chi);
        });
    }

    @Test
    public void chiButNotSequenceArgument() {
        Tile m2 = new Tile(MAN_2, 1);

        Tile m5 = new Tile(MAN_5, 1);

        draw(m5);

        Tile m3 = new Tile(MAN_3, 1);

        expectExceptionOrError(() -> {
            new ChiOperation(new Tile[] {m2, m5}, m3);
        });
    }

    @Test
    public void pon() {
        Tile m2_1 = new Tile(MAN_2, 1);

        draw(m2_1);

        Tile m2_3 = new Tile(MAN_2, 3);

        draw(m2_3);

        assertTileNumberInHand(MAN_2, 2);

        Tile         m2_2 = new Tile(MAN_2, 2);

        PonOperation pon  = new PonOperation(new Tile[] {m2_1, m2_3}, m2_2, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        assertTotalTileNumber(MAN_2, 3);

        Tile firstTileOfSequence = ((Triplet)tileCounter.getMeld().get(0)).getTiles()[0];

        assertTypeOfTile(MAN_2, firstTileOfSequence);

        assertNotInHand(MAN_2);
    }

    @Test
    public void ponButNotEnoughTile() {
        Tile m2_1 = new Tile(MAN_2, 1);

        Tile m2_3 = new Tile(MAN_2, 3);

        draw(m2_1);

        Tile         m2_2 = new Tile(MAN_2, 2);

        PonOperation pon  = new PonOperation(new Tile[] {m2_1, m2_3}, m2_2, MeldSource.LAST_PLAYER);

        expectExceptionOrError(() -> {
            tileCounter.pon(pon);
        });
    }

    @Test
    public void ponButTilesISNotSameType() {
        Tile m2_1 = new Tile(MAN_2, 1);

        Tile m2_3 = new Tile(MAN_2, 3);

        Tile m3   = new Tile(MAN_3, 2);

        expectExceptionOrError(() -> {
            new PonOperation(new Tile[] {m2_1, m2_3}, m3, MeldSource.LAST_PLAYER);
        });
    }

    @Test
    public void exposedKan() {
        Tile west_1 = new Tile(WEST, 0);
        Tile west_2 = new Tile(WEST, 1);
        Tile west_3 = new Tile(WEST, 2);
        Tile west_4 = new Tile(WEST, 3);

        draw(west_1, west_2, west_3);

        ExposedKanOperation kan =
            new ExposedKanOperation(new Tile[] {west_1, west_2, west_3}, west_4, MeldSource.NEXT_PLAYER);

        tileCounter.exposedKan(kan);

        assertTotalTileNumber(WEST, 4);

        Tile firstTileOfSequence = ((ExposedKanQuad)tileCounter.getMeld().get(0)).getTiles()[0];

        assertTypeOfTile(WEST, firstTileOfSequence);

        assertNotInHand(WEST);
    }

    @Test
    public void exposedKanButNotEnoughTile() {
        Tile west_1 = new Tile(WEST, 0);
        Tile west_2 = new Tile(WEST, 1);
        Tile west_3 = new Tile(WEST, 2);
        Tile west_4 = new Tile(WEST, 3);

        draw(west_1, west_2);

        ExposedKanOperation kan =
            new ExposedKanOperation(new Tile[] {west_1, west_2, west_3}, west_4, MeldSource.NEXT_PLAYER);

        expectExceptionOrError(() -> {
            tileCounter.exposedKan(kan);
        });
    }

    @Test
    public void exposedkanButTilesISNotSameType() {
        Tile west_1 = new Tile(WEST, 0);
        Tile west_2 = new Tile(WEST, 1);
        Tile north  = new Tile(NORTH, 2);
        Tile west_4 = new Tile(WEST, 3);

        expectExceptionOrError(() -> {
            new ExposedKanOperation(new Tile[] {west_1, west_2, north}, west_4, MeldSource.NEXT_PLAYER);
        });
    }

    @Test
    public void concealedKan() {
        Tile west_1 = new Tile(WEST, 0);
        Tile west_2 = new Tile(WEST, 1);
        Tile west_3 = new Tile(WEST, 2);
        Tile west_4 = new Tile(WEST, 3);

        draw(west_1, west_2, west_3, west_4);

        ConcealedKanOperation kan = new ConcealedKanOperation(new Tile[] {west_1, west_2, west_3, west_4});

        tileCounter.concealedKan(kan);

        Tile firstTileOfSequence = ((ConcealedKanQuad)tileCounter.getMeld().get(0)).getTiles()[0];

        assertTypeOfTile(WEST, firstTileOfSequence);

        assertNotInHand(WEST);
    }

    @Test
    public void concealedKanButNotEnoughTile() {
        Tile west_1 = new Tile(WEST, 0);
        Tile west_2 = new Tile(WEST, 1);
        Tile west_3 = new Tile(WEST, 2);
        Tile west_4 = new Tile(WEST, 3);

        draw(west_1, west_2, west_3);

        ConcealedKanOperation kan = new ConcealedKanOperation(new Tile[] {west_1, west_2, west_3, west_4});

        expectExceptionOrError(() -> {
            tileCounter.concealedKan(kan);
        });
    }

    @Test
    public void concealedkanButTilesISNotSameType() {
        Tile west_1 = new Tile(WEST, 0);
        Tile west_2 = new Tile(WEST, 1);
        Tile north  = new Tile(NORTH, 2);
        Tile west_4 = new Tile(WEST, 3);

        expectExceptionOrError(() -> {
            new ConcealedKanOperation(new Tile[] {west_1, west_2, north, west_4});
        });
    }

    @Test
    public void addKan() {
        Tile red_1 = new Tile(RED, 0);
        Tile red_2 = new Tile(RED, 1);
        Tile red_3 = new Tile(RED, 2);
        Tile red_4 = new Tile(RED, 3);

        draw(red_1, red_2, red_4);

        PonOperation pon = new PonOperation(new Tile[] {red_1, red_2}, red_3, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        Triplet         triplet = (Triplet)tileCounter.getMeld().get(0);

        AddKanOperation kan     = new AddKanOperation(new Tile[] {red_4}, triplet);

        tileCounter.addKan(kan);

        Assert.assertTrue(!tileCounter.getMeld().contains(triplet));

        Tile firstTileOfSequence = ((AddKanQuad)tileCounter.getMeld().get(0)).getTiles()[0];

        assertTypeOfTile(RED, firstTileOfSequence);

        assertNotInHand(RED);
    }

    @Test
    public void addKanButTileNotExist() {
        Tile red_1 = new Tile(RED, 0);
        Tile red_2 = new Tile(RED, 1);
        Tile red_3 = new Tile(RED, 2);
        Tile red_4 = new Tile(RED, 3);

        draw(red_1, red_2);// 删除了red_4

        PonOperation pon = new PonOperation(new Tile[] {red_1, red_2}, red_3, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        Triplet         triplet = (Triplet)tileCounter.getMeld().get(0);

        AddKanOperation kan     = new AddKanOperation(new Tile[] {red_4}, triplet);

        expectExceptionOrError(() -> {
            tileCounter.addKan(kan);
        });
    }

    @Test
    public void addKanButTripletNotExist() {
        Tile red_1 = new Tile(RED, 0);
        Tile red_2 = new Tile(RED, 1);
        Tile red_3 = new Tile(RED, 2);
        Tile red_4 = new Tile(RED, 3);

        draw(red_1, red_2, red_4);

        Triplet         triplet = Triplet.of(new Tile[] {red_1, red_2, red_3}, MeldSource.LAST_PLAYER, red_3);

        AddKanOperation kan     = new AddKanOperation(new Tile[] {red_4}, triplet);

        expectExceptionOrError(() -> {
            tileCounter.addKan(kan);
        });
    }

    @Test
    public void addKanButTilesISNotSameType() {
        Tile red_1 = new Tile(RED, 0);
        Tile red_2 = new Tile(RED, 1);
        Tile red_3 = new Tile(RED, 2);
        Tile east  = new Tile(EAST, 3);

        draw(red_1, red_2, east);

        PonOperation pon = new PonOperation(new Tile[] {red_1, red_2}, red_3, MeldSource.LAST_PLAYER);

        tileCounter.pon(pon);

        Triplet triplet = (Triplet)tileCounter.getMeld().get(0);
        expectExceptionOrError(() -> {
            new AddKanOperation(new Tile[] {east}, triplet);
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


}
