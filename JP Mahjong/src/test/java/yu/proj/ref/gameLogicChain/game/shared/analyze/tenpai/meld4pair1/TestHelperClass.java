package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static java.util.Collections.*;
import static org.junit.Assert.*;
import static yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai.HelperClass.TryOrder.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.junit.Test;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai.HelperClass;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai.NotChangedData;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai.NotChangedData.NotChangedDataBuilder;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.Singleton;
import yu.proj.ref.tilePatternElement.concealedTile.Wait2Side;
import yu.proj.ref.tilePatternElement.concealedTile.WaitMiddle;

/**  
 * @ClassName: TestHelperClass  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */
public class TestHelperClass {

    TestAnalyzeData data = new TestAnalyzeData();

    List<Meld4Pair1Tenpaiable> ans = new ArrayList<>();

    public final NotChangedData defalutSetting =
        new NotChangedData(0, emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), emptyList(), ans,
            data.playerTileManager, data.playerTileManager.playerTileInHandGetter(), new EnumMap<>(TileType.class));

    @Test
    public void testMeld4Singleton1() {

        NotChangedDataBuilder builder = builder();

        builder.exposedMeldNum(4).singletons(singletonList(Singleton.of(Tile.of(MAN_1, 0))));

        HelperClass HelperClass = new HelperClass(NONE, TRY_NONE, builder.build());

        HelperClass.help();

        assertEquals(1, ans.size());
        assertTrue(ans.get(0) instanceof Meld4Singleton1);
    }

    @Test
    public void testMeld3Pair2() {
        Pair pair = Pair.of(new Tile[] {Tile.of(MAN_1, 0), Tile.of(MAN_1, 2)});

        NotChangedDataBuilder builder = builder();

        builder.exposedMeldNum(3).pairs(Arrays.asList(pair, pair));

        HelperClass HelperClass = new HelperClass(NONE, TRY_NONE, builder.build());

        HelperClass.help();

        assertEquals(1, ans.size());
        assertTrue(ans.get(0) instanceof Meld3Pair2);
    }

    @Test
    public void testMeld3Pair1Wait2side1() {
        Pair pair = Pair.of(new Tile[] {Tile.of(MAN_1, 0), Tile.of(MAN_1, 2)});

        NotChangedDataBuilder builder = builder();

        builder.exposedMeldNum(3).pairs(singletonList(pair))
            .wait2Sides(singletonList(Wait2Side.of(Tile.of(MAN_2, 0), Tile.of(MAN_3, 1))));

        new HelperClass(NONE, TRY_NONE, builder.build()).help();

        assertEquals(1, ans.size());
        assertTrue(ans.get(0) instanceof Meld3Pair1Wait2Side1);
    }

    @Test
    public void testMeld3Pair1WaitMiddle1() {
        Pair pair = Pair.of(new Tile[] {Tile.of(MAN_1, 0), Tile.of(MAN_1, 2)});

        NotChangedDataBuilder builder = builder();

        builder.exposedMeldNum(3).pairs(singletonList(pair))
            .waitMiddles(singletonList(WaitMiddle.of(Tile.of(MAN_2, 0), Tile.of(MAN_4, 1))));

        new HelperClass(NONE, TRY_NONE, builder.build()).help();

        assertEquals(1, ans.size());
        assertTrue(ans.get(0) instanceof Meld3Pair1WaitMiddle1);
    }

    @Test
    public void testNoTile() {

        new TestableHelperClass(MAN_1, TRY_NONE, defalutSetting, (helper) -> {

            assertEquals(MAN_2, helper.type);

        }).help();;
    }

    @Test
    public void testTryTriplet() {

        data.draw(MAN_1, MAN_1, MAN_1);

        NotChangedData data = builder().concealedTriplets(new ArrayList<>()).build();

        new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {

            assertEquals(MAN_1, helper.type);

            assertEquals(1, helper.data.concealedTriplets.size());

            assertEquals(MAN_1, helper.data.concealedTriplets.get(0).getTiles()[0].getTileType());

        }).tryTriplet();

        assertEquals(0, data.concealedTriplets.size());

        data.getter.claim(MAN_1);
    }


    @Test
    public void testTryPair() {
        data.draw(MAN_1, MAN_1);

        NotChangedData data = builder().pairs(new ArrayList<>()).build();

        new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {

            assertEquals(MAN_1, helper.type);

            assertEquals(1, helper.data.pairs.size());

            assertEquals(MAN_1, helper.data.pairs.get(0).getTiles()[0].getTileType());

        }).tryPair();

        assertEquals(0, data.pairs.size());

        data.getter.claim(MAN_1);
    }

    @Test
    public void testTryPairIfSingletonExist() {
        data.draw(MAN_1, MAN_1);

        NotChangedData data = oneSingletonBuilder().pairs(new ArrayList<>()).build();

        assertNoRecursion(data).tryPair();

        assertEquals(0, data.pairs.size());
    }

    private List<Singleton> getSingleton1() {
        return singletonList(Singleton.of(Tile.of(RED, 0)));
    }

    @Test
    public void testTryPairIfPair1Wait2Side1() {
        data.draw(MAN_1, MAN_1);

        NotChangedData data = oneWait2SideBuilder().pairs(new ArrayList<>(getPair1())).build();

        assertNoRecursion(data).tryPair();

        assertEquals(1, data.pairs.size());
    }

    private NotChangedDataBuilder oneWait2SideBuilder() {
        return builder().wait2Sides(getWait2Side1());
    }

    private List<Wait2Side> getWait2Side1() {
        return singletonList(Wait2Side.of(Tile.of(SOU_1, 0), Tile.of(SOU_2, 1)));
    }

    @Test
    public void testTryPairIfPair1WaitMiddle1() {
        data.draw(MAN_1, MAN_1);

        NotChangedData data = oneWaitMiddleBuilder().pairs(new ArrayList<>(getPair1())).build();

        assertNoRecursion(data).tryPair();

        assertEquals(1, data.pairs.size());
    }

    private NotChangedDataBuilder oneWaitMiddleBuilder() {
        return builder().waitMiddles(getWaitMiddle1());
    }

    private List<Pair> getPair1() {
        return Arrays.asList(Pair.of(new Tile[] {Tile.of(RED, 0), Tile.of(RED, 1)}));
    }

    private List<Pair> getPair2() {
        Pair pair = Pair.of(new Tile[] {Tile.of(RED, 0), Tile.of(RED, 1)});
        return Arrays.asList(pair, pair);
    }

    private List<WaitMiddle> getWaitMiddle1() {
        return singletonList(WaitMiddle.of(Tile.of(SOU_1, 0), Tile.of(SOU_3, 1)));
    }

    @Test
    public void testTrySingleton() {
        data.draw(MAN_1);

        NotChangedData data = builder().singletons(new ArrayList<>()).build();

        new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {

            assertEquals(MAN_1, helper.type);

            assertEquals(1, helper.data.singletons.size());

            assertEquals(MAN_1, helper.data.singletons.get(0).getTile().getTileType());

        }).trySingleton();

        assertEquals(0, data.singletons.size());

        data.getter.claim(MAN_1);
    }

    @Test
    public void testTrySingletonWithSingleton1() {
        data.draw(MAN_1);

        NotChangedData data = oneSingletonBuilder().build();

        assertNoRecursion(data).trySingleton();

        assertEquals(1, data.singletons.size());
    }

    private NotChangedDataBuilder oneSingletonBuilder() {
        return builder().singletons(new ArrayList<>(getSingleton1()));
    }

    @Test
    public void testTrySingletonWithPair1() {
        data.draw(MAN_1);

        NotChangedData data = builder().pairs(getPair1()).build();

        assertNoRecursion(data).trySingleton();

        assertEquals(0, data.singletons.size());
    }

    @Test
    public void testTrySingletonWithWait2Side1() {
        data.draw(MAN_1);

        NotChangedData data = oneWait2SideBuilder().build();

        assertNoRecursion(data).trySingleton();

        assertEquals(0, data.singletons.size());
    }

    @Test
    public void testTryWait2Side() {
        data.draw(MAN_1, MAN_2);

        NotChangedData data = builder().pairs(getPair1()).wait2Sides(new ArrayList<>()).build();

        new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {

            assertEquals(MAN_1, helper.type);

            assertEquals(1, helper.data.wait2Sides.size());

            assertEquals(MAN_1, helper.data.wait2Sides.get(0).getLower().getTileType());

        }).tryWait2Side();

        assertEquals(0, data.wait2Sides.size());

        data.getter.claim(MAN_1);
        data.getter.claim(MAN_2);
    }

    @Test
    public void testTryWait2SideWithoutNextTile() {
        data.draw(MAN_1);

        NotChangedData data = builder().wait2Sides(new ArrayList<>()).build();

        assertNoRecursion(data).tryWait2Side();

        assertEquals(0, data.wait2Sides.size());
    }

    @Test
    public void testTryWait2SideWithSingleton1() {
        data.draw(MAN_1, MAN_2);

        NotChangedData data = oneSingletonBuilder().wait2Sides(new ArrayList<>()).build();

        assertNoRecursion(data).tryWait2Side();

        assertEquals(0, data.wait2Sides.size());
    }

    @Test
    public void testTryWait2SideWith2Pair() {
        data.draw(MAN_1, MAN_2);

        NotChangedData data = builder().pairs(getPair2()).wait2Sides(new ArrayList<>()).build();

        assertNoRecursion(data).tryWait2Side();

        assertEquals(0, data.wait2Sides.size());
    }

    @Test
    public void testTryWait2SideWithWait2Side1() {
        data.draw(MAN_1, MAN_2);

        NotChangedData data = builder().wait2Sides(new ArrayList<>(getWait2Side1())).build();

        assertNoRecursion(data).tryWait2Side();

        assertEquals(1, data.wait2Sides.size());
    }

    @Test
    public void testTryWait2SideWithWaitMiddle1() {
        data.draw(MAN_1, MAN_2);

        NotChangedData data = oneWaitMiddleBuilder().wait2Sides(new ArrayList<>()).build();

        assertNoRecursion(data).tryWait2Side();

        assertEquals(0, data.wait2Sides.size());
    }

    @Test
    public void testTryWaitMiddle() {
        data.draw(MAN_1, MAN_3);

        NotChangedData data = builder().waitMiddles(new ArrayList<>()).build();

        new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {

            assertEquals(MAN_1, helper.type);

            assertEquals(1, helper.data.waitMiddles.size());

            assertEquals(MAN_1, helper.data.waitMiddles.get(0).getLower().getTileType());

        }).tryWaitMiddle();

        assertEquals(0, data.wait2Sides.size());

        data.getter.claim(MAN_1);
        data.getter.claim(MAN_3);
    }

    @Test
    public void testTryWaitMiddleWithoutNextNextTile() {
        data.draw(MAN_1);

        NotChangedData data = builder().waitMiddles(new ArrayList<>()).build();

        assertNoRecursion(data).tryWaitMiddle();

        assertEquals(0, data.waitMiddles.size());
    }

    @Test
    public void testTryWaitMiddleWithSingleton1() {
        data.draw(MAN_1, MAN_3);

        NotChangedData data = oneSingletonBuilder().waitMiddles(new ArrayList<>()).build();

        assertNoRecursion(data).tryWaitMiddle();

        assertEquals(0, data.waitMiddles.size());
    }

    @Test
    public void testTryWaitMiddleWith2Pair() {
        data.draw(MAN_1, MAN_3);

        NotChangedData data = builder().pairs(getPair2()).waitMiddles(new ArrayList<>()).build();

        assertNoRecursion(data).tryWaitMiddle();

        assertEquals(0, data.waitMiddles.size());
    }

    @Test
    public void testTryWaitMiddleWithWaitMiddle1() {
        data.draw(MAN_1, MAN_3);

        NotChangedData data = builder().waitMiddles(new ArrayList<>(getWaitMiddle1())).build();

        assertNoRecursion(data).tryWaitMiddle();

        assertEquals(1, data.waitMiddles.size());
    }

    @Test
    public void testTryWaitMiddleWithWait2Side1() {
        data.draw(MAN_1, MAN_3);

        NotChangedData data = oneWait2SideBuilder().waitMiddles(new ArrayList<>()).build();

        assertNoRecursion(data).tryWaitMiddle();

        assertEquals(0, data.waitMiddles.size());
    }

    @Test
    public void testTrySequence() {
        data.draw(MAN_1, MAN_2, MAN_3);

        NotChangedData data = builder().concealedSequences(new ArrayList<>()).build();

        AtomicInteger count = new AtomicInteger(0);

        new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {

            count.incrementAndGet();

            assertEquals(MAN_1, helper.type);

            assertEquals(1, helper.data.concealedSequences.size());

            assertEquals(MAN_1, helper.data.concealedSequences.get(0).getTiles()[0].getTileType());

        }).trySequence();

        assertEquals(1, count.intValue());

        assertEquals(0, data.concealedSequences.size());

        data.getter.claim(MAN_1);
        data.getter.claim(MAN_2);
        data.getter.claim(MAN_3);
    }

    @Test
    public void testTrySequenceWithoutNextTile() {
        data.draw(MAN_1, MAN_3);

        NotChangedData data = builder().concealedSequences(new ArrayList<>()).build();

        assertNoRecursion(data).trySequence();

        assertEquals(0, data.concealedSequences.size());
    }

    @Test
    public void testTrySequenceWithoutNextNextTile() {
        data.draw(MAN_1, MAN_2);

        NotChangedData data = builder().concealedSequences(new ArrayList<>()).build();

        assertNoRecursion(data).trySequence();

        assertEquals(0, data.concealedSequences.size());
    }

    private NotChangedDataBuilder builder() {
        return defalutSetting.toBuilder();
    }

    private TestableHelperClass assertNoRecursion(NotChangedData data) {
        return new TestableHelperClass(MAN_1, TRY_NONE, data, (helper) -> {
            fail();
        });
    }

    static class TestableHelperClass extends HelperClass {

        private Consumer<HelperClass> consumer;

        public TestableHelperClass(TileType type, TryOrder checkOrder, NotChangedData data,
            Consumer<HelperClass> consumer) {
            super(type, checkOrder, data);
            this.consumer = consumer;
        }

        @Override
        void help() {
            super.help();
        }

        @Override
        HelperClass getNewHelperClass(TileType type, TryOrder checkOrder, NotChangedData data) {
            return new MokeHelperClass(type, checkOrder, data, consumer);
        }

    }

    static class MokeHelperClass extends HelperClass {

        private Consumer<HelperClass> consumer;

        public MokeHelperClass(TileType type, TryOrder checkOrder, NotChangedData data,
            Consumer<HelperClass> consumer) {
            super(type, checkOrder, data);
            this.consumer = consumer;
        }

        @Override
        void help() {
            consumer.accept(this);
        }

    }

}
