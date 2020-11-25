package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai.HelperClass.TryOrder.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileInHandGetter;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
import yu.proj.ref.rule.GameRule;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.MeldSource;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.Singleton;
import yu.proj.ref.tilePatternElement.concealedTile.Wait2Side;
import yu.proj.ref.tilePatternElement.concealedTile.WaitMiddle;

/**  
 * @ClassName: AnalyzeMeld4Pair1  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月24日  
 *  
 */

@AllArgsConstructor
public class AnalyzeMeld4Pair1Tenpai {

    private GameRule rule;

    public List<Meld4Pair1Tenpaiable> analyze(PlayerTileManager playerTileManager) {

        int exposedMeldNum = playerTileManager.getPlayerExposedTilesManager().sizeOfMakeCall();

        PlayerTileInHandGetter getter = playerTileManager.playerTileInHandGetter();

        List<Meld4Pair1Tenpaiable> ans = new ArrayList<>();

        new HelperClass(MAN_1, TRY_NONE,
            new NotChangedData(exposedMeldNum, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), ans, playerTileManager, getter,
                new EnumMap<>(TileType.class))).help();

        return null;
    }

    @AllArgsConstructor
    @Builder(toBuilder = true)
    static class NotChangedData {
        int exposedMeldNum;
        List<Sequence> concealedSequences;
        List<Triplet> concealedTriplets;
        List<Pair> pairs;
        List<Singleton> singletons;
        List<Wait2Side> wait2Sides;
        List<WaitMiddle> waitMiddles;
        List<Meld4Pair1Tenpaiable> ans;
        PlayerTileManager playerTileManager;
        PlayerTileInHandGetter getter;
        EnumMap<TileType, Integer> used;
    }

    @AllArgsConstructor
    static class HelperClass {

        private final static int TRIPLET_TILE_NUM = 3;

        private final static int PAIR_TILE_NUM = 2;

        private final static int SINGLETON_TILE_NUM = 1;

        private final static int SEQUENCE_TILE_EACH_NUM = 1;// 顺子中每个牌只取一张，坎张和两面听也是顺子的一部分，同理

        /**
         * checkOrder 是为了牌型检测不重复而加入的判断
         * 首先给每种形状编号，刻子-1，对子-2，单骑-3，两面听-4，坎张听-5，顺子-6
         * 然后要求必须按照序号进行遍历，例如遍历完刻子可以遍历顺子，但是遍历完顺子之后不能再去遍历刻子
         * 这样保证了顺序的唯一性，因此就不会产生重复
         */
        @AllArgsConstructor
        static enum TryOrder {
            TRY_NONE(0), TRY_TRIPLET(1), TRY_PAIR(2), TRY_SINGLETON(3), TRY_WAIT_2_SIDE(4), TRY_WAIT_MIDDLE(5),
            TRY_SEQUENCE(6);

            private int order;

            boolean rightVisitOrder(TryOrder other) {
                return this == TRY_SEQUENCE ? true : order < other.order;
            }
        }

        TileType type;

        TryOrder visitOrder;

        NotChangedData data;

        void help() {

            if (isEnd()) {
                addAnsIfIsRightPattern();
                return;
            }

            if (countTile() == 0) {
                nextLevel(TRY_NONE);
                return;
            }

            if (checkOrder(TRY_TRIPLET)) {
                tryTriplet();
            }

            if (checkOrder(TRY_PAIR)) {
                tryPair();
            }

            if (checkOrder(TRY_SINGLETON)) {
                trySingleton();
            }

            if (checkOrder(TRY_WAIT_2_SIDE)) {
                tryWait2Side();
            }

            if (checkOrder(TRY_WAIT_MIDDLE)) {
                tryWaitMiddle();
            }

            if (checkOrder(TRY_SEQUENCE)) {
                trySequence();
            }
        }

        private boolean checkOrder(TryOrder order) {
            return visitOrder.rightVisitOrder(order);
        }

        void trySequence() {
            if (canHasSequence()) {
                addSequence();
                use(SEQUENCE_TILE_EACH_NUM);
                use(nextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                use(nextNextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                thisLevel(TRY_SEQUENCE);
                reuse(nextNextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                reuse(nextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                reuse(SEQUENCE_TILE_EACH_NUM);
                removeSequence();
            }
        }

        private boolean canHasSequence() {

            // 要有下下一张牌才能构成坎张听
            boolean hasNextNextTile = (countTile(nextNextSequenceTile()) > 0);

            // 要有下一张牌才能构成两面听
            boolean hasNextTile = (countTile(nextSequenceTile()) > 0);

            return hasNextTile && hasNextNextTile;
        }

        private void removeSequence() {
            data.concealedSequences.remove(data.concealedSequences.size() - 1);
            reclaimTile();
            reclaimTile(nextSequenceTile());
            reclaimTile(nextNextSequenceTile());
        }

        private void addSequence() {
            data.concealedSequences.add(sequence());
        }

        private Sequence sequence() {
            Tile[] tiles = {claimTile(), claimTile(nextSequenceTile()), claimTile(nextNextSequenceTile())};
            return Sequence.of(tiles, MeldSource.SELF, null);
        }

        void tryWaitMiddle() {
            if (canHasWaitMiddle()) {
                addWaitMiddle();
                use(SEQUENCE_TILE_EACH_NUM);
                use(nextNextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                thisLevel(TRY_WAIT_MIDDLE);
                reuse(nextNextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                reuse(SEQUENCE_TILE_EACH_NUM);
                removeWaitMiddle();
            }
        }

        private void removeWaitMiddle() {
            data.waitMiddles.remove(data.waitMiddles.size() - 1);
            reclaimTile();
            reclaimTile(nextNextSequenceTile());
        }

        private void addWaitMiddle() {
            data.waitMiddles.add(waitMiddle());
        }

        private WaitMiddle waitMiddle() {
            return WaitMiddle.of(claimTile(), claimTile(nextNextSequenceTile()));
        }

        private boolean canHasWaitMiddle() {
            // 要有下下一张牌才能构成坎张听
            boolean hasNextNextTile = (countTile(nextNextSequenceTile()) > 0);

            // 坎张听时，就不能听单骑
            boolean noSingleton = (singletonNum() == 0);

            // 坎张听的时候，至多且必须有一个对子
            boolean atMost1Pair = (pairNum() <= 1);

            // 听两面的时候不能还有坎张听
            boolean noWait2sides = (wait2SideNum() == 0);

            // 不能同时听两个坎张听
            boolean noWaitMiddles = (waitMiddleNum() == 0);

            return hasNextNextTile && noSingleton && atMost1Pair && noWait2sides && noWaitMiddles;
        }

        void tryWait2Side() {
            if (canHasWait2Side()) {
                addWait2Side();
                use(SEQUENCE_TILE_EACH_NUM);
                use(nextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                thisLevel(TRY_WAIT_2_SIDE);
                reuse(nextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                reuse(SEQUENCE_TILE_EACH_NUM);
                removeWait2Side();
            }
        }

        private void removeWait2Side() {
            data.wait2Sides.remove(data.wait2Sides.size() - 1);
            reclaimTile();
            reclaimTile(nextSequenceTile());
        }

        private void addWait2Side() {
            data.wait2Sides.add(wait2Side());
        }

        private Wait2Side wait2Side() {
            return Wait2Side.of(claimTile(), claimTile(nextSequenceTile()));
        }

        private TileType nextSequenceTile() {
            return type.nextNormalTile();
        }

        private TileType nextNextSequenceTile() {
            return type.nextNormalTile().nextNormalTile();
        }

        private boolean canHasWait2Side() {

            // 要有下一张牌才能构成两面听
            boolean hasNextTile = (countTile(nextSequenceTile()) > 0);

            // 两面听时，就不能听单骑
            boolean noSingleton = (singletonNum() == 0);

            // 听两面的时候，至多且必须有一个对子
            boolean atMost1Pair = (pairNum() <= 1);

            // 不能同时听两个两面听
            boolean noWait2sides = (wait2SideNum() == 0);

            // 听坎张的时候不能还有两面听
            boolean noWaitMiddles = (waitMiddleNum() == 0);

            return hasNextTile && noSingleton && atMost1Pair && noWait2sides && noWaitMiddles;
        }

        void trySingleton() {
            if (canHasSingleton()) {
                addSingleton();
                use(SINGLETON_TILE_NUM);
                thisLevel(TRY_SINGLETON);
                reuse(SINGLETON_TILE_NUM);
                removeSingleton();
            }
        }

        private void addSingleton() {
            data.singletons.add(singleton());
        }

        private void removeSingleton() {
            data.singletons.remove(singletonNum() - 1);
            reclaimTiles(SINGLETON_TILE_NUM);
        }

        private Singleton singleton() {
            return Singleton.of(claimTiles(SINGLETON_TILE_NUM)[0]);
        }

        private boolean canHasSingleton() {

            // 单骑存在，就不能再有别的单骑
            boolean noSingleton = (singletonNum() == 0);

            // 对子存在也不能再由单骑
            boolean noPair = (pairNum() == 0);

            // 听双面听也不能再听单骑
            boolean noWait2sides = (wait2SideNum() == 0);

            // 听坎张听也不能再听单骑
            boolean noWaitMiddles = (waitMiddleNum() == 0);

            return noSingleton && noPair && noWait2sides && noWaitMiddles;
        }

        private int waitMiddleNum() {
            return data.waitMiddles.size();
        }

        private int wait2SideNum() {
            return data.wait2Sides.size();
        }

        private int pairNum() {
            return data.pairs.size();
        }

        private int singletonNum() {
            return data.singletons.size();
        }

        void tryPair() {
            if (canHasPair()) {
                addPair();
                use(PAIR_TILE_NUM);
                thisLevel(TRY_PAIR);
                reuse(PAIR_TILE_NUM);
                removePair();
            }
        }

        private void addPair() {
            data.pairs.add(pair());
        }

        private void removePair() {
            data.pairs.remove(pairNum() - 1);
            reclaimTiles(PAIR_TILE_NUM);
        }

        private Pair pair() {
            return Pair.of(claimTiles(PAIR_TILE_NUM));
        }

        private boolean canHasPair() {

            boolean hasEnoughTile = countTile() >= PAIR_TILE_NUM;

            // 存在对子的牌型中是不能有单骑的
            boolean hasNoSingleton = singletonNum() == 0;

            // 没有对子，那么即使有两面听或者坎张听都是可以的
            boolean pairNumIs0 = pairNum() == 0;

            // 如果有一个对子，那么就不能有两面听或者坎张听了
            boolean pairNumIs1AndNoWait2SideNorWaitMiddle =
                (pairNum() == 1 && (wait2SideNum() == 0 && waitMiddleNum() == 0));

            return hasEnoughTile && hasNoSingleton && (pairNumIs0 || pairNumIs1AndNoWait2SideNorWaitMiddle);
        }

        void tryTriplet() {
            if (countTile() >= TRIPLET_TILE_NUM) {
                addTriplet();
                use(TRIPLET_TILE_NUM);
                thisLevel(TRY_TRIPLET);
                reuse(TRIPLET_TILE_NUM);
                removeTriplet();
            }
        }

        private void addTriplet() {
            data.concealedTriplets.add(triplet());
        }

        private void removeTriplet() {
            data.concealedTriplets.remove(data.concealedTriplets.size() - 1);
            reclaimTiles(TRIPLET_TILE_NUM);
        }

        private Triplet triplet() {
            return Triplet.of(claimTiles(TRIPLET_TILE_NUM), MeldSource.SELF, null);
        }

        private void use(TileType type, int usedTileNum) {
            data.used.put(type, data.used.getOrDefault(type, 0) + usedTileNum);
        }

        private void use(int usedTileNum) {
            use(type, usedTileNum);
        }

        private void reuse(TileType type, int usedTileNum) {
            data.used.put(type, data.used.getOrDefault(type, 0) - usedTileNum);
        }

        private void reuse(int usedTileNum) {
            reuse(type, usedTileNum);
        }

        private Tile claimTile(TileType type) {
            return data.getter.claim(type);
        }

        private Tile claimTile() {
            return claimTile(type);
        }

        private Tile[] claimTiles(int num) {
            Tile[] tiles = new Tile[num];

            for (int i = 0; i < num; ++i) {
                tiles[i] = claimTile();
            }
            return tiles;
        }

        private void reclaimTile() {
            reclaimTile(type);
        }

        private void reclaimTile(TileType type) {
            data.getter.reclaim(type);
        }

        private void reclaimTiles(int num) {
            for (int i = 0; i < num; ++i) {
                reclaimTile();
            }
        }

        private int countTile() {
            return countTile(type);
        }

        private int countTile(TileType type) {
            return data.playerTileManager.countNormalAndRedInHand(type) - data.used.getOrDefault(type, 0);
        }

        private void nextLevel(TryOrder order) {
            getNewHelperClass(next(type), order, data).help();
        }

        private void thisLevel(TryOrder order) {
            getNewHelperClass(type, order, data).help();
        }

        // 以便测试可重写提供Moke对象
        HelperClass getNewHelperClass(TileType type, TryOrder checkOrder, NotChangedData data) {
            return new HelperClass(type, checkOrder, data);
        }

        private boolean isEnd() {
            return type == NONE;
        }

        private void addAnsIfIsRightPattern() {
            if (isMeld4Singleton1()) {
                addMeld4Singleton1();
            } else if (isMeld3Pair2()) {
                addMeld3Pair2();
            } else if (isMeld3Pair1Wait2side1()) {
                addMeld3Pair1Wait2side1();
            } else if (isMeld3Pair1WaitMiddle1()) {
                addMeld3Pair1WaitMiddle1();
            }
        }

        private void addMeld3Pair1WaitMiddle1() {
            data.ans.add(Meld3Pair1WaitMiddle1.of(data.concealedSequences, data.concealedTriplets, data.pairs.get(0),
                data.waitMiddles.get(0)));
        }

        private boolean isMeld3Pair1WaitMiddle1() {
            return meldNum() == 3 && pairNum() == 1 && waitMiddleNum() == 1;
        }

        private void addMeld3Pair1Wait2side1() {
            data.ans.add(Meld3Pair1Wait2side1.of(data.concealedSequences, data.concealedTriplets, data.pairs.get(0),
                data.wait2Sides.get(0)));
        }

        private boolean isMeld3Pair1Wait2side1() {
            return meldNum() == 3 && pairNum() == 1 && wait2SideNum() == 1;
        }

        private void addMeld3Pair2() {
            data.ans.add(Meld3Pair2.of(data.concealedSequences, data.concealedTriplets, data.pairs));
        }

        private boolean isMeld3Pair2() {
            return meldNum() == 3 && pairNum() == 2;
        }

        private boolean addMeld4Singleton1() {
            return data.ans
                .add(Meld4Singleton1.of(data.concealedSequences, data.concealedTriplets, data.singletons.get(0)));
        }

        private boolean isMeld4Singleton1() {
            return meldNum() == 4 && singletonNum() == 1;
        }

        private int meldNum() {
            return data.concealedSequences.size() + data.concealedSequences.size() + data.exposedMeldNum;
        }

    }

}
