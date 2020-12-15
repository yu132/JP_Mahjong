package yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1;

import static yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.AnalyzeMeld4Pair1Tenpai.BacktrackingAlgorithmForTenpaiAnalyze.TryOrder.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import lombok.Builder;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileInHandGetter;
import yu.proj.ref.gameLogicChain.game.shared.playerTilesManager.PlayerTileManager;
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

public class AnalyzeMeld4Pair1Tenpai {

    public List<Meld4Pair1Tenpaiable> analyze(PlayerTileManager playerTileManager) {

        int exposedMeldNum = playerTileManager.getPlayerExposedTilesManager().sizeOfMakeCall();

        List<Meld4Pair1Tenpaiable> tenpaiResult = new ArrayList<>();

        new BacktrackingAlgorithmForTenpaiAnalyze(MAN_1, TRY_NONE,
            NotChangedData.of(exposedMeldNum, tenpaiResult, playerTileManager)).execute();

        return tenpaiResult;
    }

    // 递归中不变的数据
    @Builder(toBuilder = true)
    static class NotChangedData {
        int exposedMeldNum;
        List<Sequence> concealedSequences;
        List<Triplet> concealedTriplets;
        List<Pair> pairs;
        List<Singleton> singletons;
        List<Wait2Side> wait2Sides;
        List<WaitMiddle> waitMiddles;
        List<Meld4Pair1Tenpaiable> tenpaiResult;
        PlayerTileManager playerTileManager;
        PlayerTileInHandGetter getter;
        EnumMap<TileType, Integer> used;

        static NotChangedData of(int exposedMeldNum, List<Meld4Pair1Tenpaiable> tenpaiResult,
            PlayerTileManager playerTileManager) {
            return new NotChangedData(exposedMeldNum, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), tenpaiResult, playerTileManager,
                playerTileManager.playerTileInHandGetter(), new EnumMap<>(TileType.class));
        }

        NotChangedData(int exposedMeldNum, List<Sequence> concealedSequences, List<Triplet> concealedTriplets,
            List<Pair> pairs, List<Singleton> singletons, List<Wait2Side> wait2Sides, List<WaitMiddle> waitMiddles,
            List<Meld4Pair1Tenpaiable> tenpaiResult, PlayerTileManager playerTileManager, PlayerTileInHandGetter getter,
            EnumMap<TileType, Integer> used) {
            super();
            this.exposedMeldNum = exposedMeldNum;
            this.concealedSequences = concealedSequences;
            this.concealedTriplets = concealedTriplets;
            this.pairs = pairs;
            this.singletons = singletons;
            this.wait2Sides = wait2Sides;
            this.waitMiddles = waitMiddles;
            this.tenpaiResult = tenpaiResult;
            this.playerTileManager = playerTileManager;
            this.getter = getter;
            this.used = used;
        }
    }

    static class BacktrackingAlgorithmForTenpaiAnalyze {

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
        static enum TryOrder {
            TRY_NONE(0), TRY_TRIPLET(1), TRY_PAIR(2), TRY_SINGLETON(3), TRY_WAIT_2_SIDE(4), TRY_WAIT_MIDDLE(5),
            TRY_SEQUENCE(6);

            private int order;

            private TryOrder(int order) {
                this.order = order;
            }

            boolean rightVisitOrder(TryOrder other) {
                return other == TRY_SEQUENCE ? true : order < other.order;
            }
        }

        TileType type;// 递归中，本层的牌的类型

        TryOrder visitOrder;

        NotChangedData data;

        BacktrackingAlgorithmForTenpaiAnalyze(TileType type, TryOrder visitOrder, NotChangedData data) {
            super();
            this.type = type;
            this.visitOrder = visitOrder;
            this.data = data;
        }

        // 递归的核心方法
        void execute() {

            if (isEnd()) {
                addTenpaiResultIfIsRightPattern();
                return;
            }

            if (countTile() == 0) {
                nextLevel(TRY_NONE);
                return;
            }

            tryTriplet();
            tryPair();
            trySingleton();
            tryWait2Side();
            tryWaitMiddle();
            trySequence();
        }



        /* tryXxx —— 尝试添加某个元素*/

        void tryTriplet() {
            if (checkOrder(TRY_TRIPLET) && countTile() >= TRIPLET_TILE_NUM) {
                addTriplet();
                use(TRIPLET_TILE_NUM);
                thisLevel(TRY_TRIPLET);
                reuse(TRIPLET_TILE_NUM);
                removeTriplet();
            }
        }

        void tryPair() {
            if (checkOrder(TRY_PAIR) && canHasPair()) {
                addPair();
                use(PAIR_TILE_NUM);
                thisLevel(TRY_PAIR);
                reuse(PAIR_TILE_NUM);
                removePair();
            }
        }

        void trySingleton() {
            if (checkOrder(TRY_SINGLETON) && canHasSingleton()) {
                addSingleton();
                use(SINGLETON_TILE_NUM);
                thisLevel(TRY_SINGLETON);
                reuse(SINGLETON_TILE_NUM);
                removeSingleton();
            }
        }

        void tryWait2Side() {
            if (checkOrder(TRY_WAIT_2_SIDE) && canHasWait2Side()) {
                addWait2Side();
                use(SEQUENCE_TILE_EACH_NUM);
                use(nextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                thisLevel(TRY_WAIT_2_SIDE);
                reuse(nextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                reuse(SEQUENCE_TILE_EACH_NUM);
                removeWait2Side();
            }
        }

        void tryWaitMiddle() {
            if (checkOrder(TRY_WAIT_MIDDLE) && canHasWaitMiddle()) {
                addWaitMiddle();
                use(SEQUENCE_TILE_EACH_NUM);
                use(nextNextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                thisLevel(TRY_WAIT_MIDDLE);
                reuse(nextNextSequenceTile(), SEQUENCE_TILE_EACH_NUM);
                reuse(SEQUENCE_TILE_EACH_NUM);
                removeWaitMiddle();
            }
        }

        void trySequence() {
            if (checkOrder(TRY_SEQUENCE) && canHasSequence()) {
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



        /* checkOrder —— 检查尝试的顺序 */

        private boolean checkOrder(TryOrder order) {
            return visitOrder.rightVisitOrder(order);
        }



        /* canHasXxx —— 检查某个元素是否有加入的可能*/

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

        private boolean canHasSingleton() {
            final int pairLimit = 0;
            return canHasNotCompletedElement(pairLimit);
        }

        private boolean canHasWait2Side() {
            final int pairLimit = 1;
            return hasNextTile() // 要有下一张牌才能构成两面听
                && canHasNotCompletedElement(pairLimit);
        }

        private boolean canHasWaitMiddle() {
            final int pairLimit = 1;
            return hasNextNextTile() // 要有下下一张牌才能构成坎张听
                && canHasNotCompletedElement(pairLimit);
        }

        private boolean canHasSequence() {
            return hasNextTile()// 顺子要有下一张牌才能构成
                && hasNextNextTile();// 顺子要有下下一张牌才能构成
        }

        /*  未完成元素指的是两个对子、单骑、两面听和坎张听这样的元素
         * 只要有一种出现，那么其他的类型都不能出现
         * 这是由于麻将中听牌的话，最多只有一种未完成的元素
         * 否则就不叫听牌了，而是几向听了
         * 对于坎张听和两面听，可以允许有一个对子，但是单骑不允许有对子出现
         */
        private boolean canHasNotCompletedElement(int pairLimit) {
            boolean noSingleton = (singletonNum() == 0);
            boolean atMost1Pair = (pairNum() <= pairLimit);
            boolean noWait2sides = (wait2SideNum() == 0);
            boolean noWaitMiddles = (waitMiddleNum() == 0);
            return noSingleton && atMost1Pair && noWait2sides && noWaitMiddles;
        }



        /* addXxx —— 递归前新增元素*/

        private void addTriplet() {
            data.concealedTriplets.add(triplet());
        }

        private void addPair() {
            data.pairs.add(pair());
        }

        private void addSingleton() {
            data.singletons.add(singleton());
        }

        private void addWait2Side() {
            data.wait2Sides.add(wait2Side());
        }

        private void addWaitMiddle() {
            data.waitMiddles.add(waitMiddle());
        }

        private void addSequence() {
            data.concealedSequences.add(sequence());
        }



        /*  removeXxx —— 递归后移除元素*/

        private void removeTriplet() {
            data.concealedTriplets.remove(data.concealedTriplets.size() - 1);
            reclaimTiles(TRIPLET_TILE_NUM);
        }

        private void removePair() {
            data.pairs.remove(pairNum() - 1);
            reclaimTiles(PAIR_TILE_NUM);
        }

        private void removeSingleton() {
            data.singletons.remove(singletonNum() - 1);
            reclaimTile();
        }

        private void removeWait2Side() {
            data.wait2Sides.remove(data.wait2Sides.size() - 1);
            reclaimTile();
            reclaimTile(nextSequenceTile());
        }

        private void removeWaitMiddle() {
            data.waitMiddles.remove(data.waitMiddles.size() - 1);
            reclaimTile();
            reclaimTile(nextNextSequenceTile());
        }

        private void removeSequence() {
            data.concealedSequences.remove(data.concealedSequences.size() - 1);
            reclaimTile();
            reclaimTile(nextSequenceTile());
            reclaimTile(nextNextSequenceTile());
        }



        /* xxx —— 创建新的元素 */

        private Triplet triplet() {
            return Triplet.of(claimTiles(TRIPLET_TILE_NUM), MeldSource.SELF, Tile.NONE_TILE);
        }

        private Pair pair() {
            return Pair.of(claimTiles(PAIR_TILE_NUM));
        }

        private Singleton singleton() {
            return Singleton.of(claimTile());
        }

        private Wait2Side wait2Side() {
            return Wait2Side.of(claimTile(), claimTile(nextSequenceTile()));
        }

        private Sequence sequence() {
            Tile[] tiles = {claimTile(), claimTile(nextSequenceTile()), claimTile(nextNextSequenceTile())};
            return Sequence.of(tiles, MeldSource.SELF, Tile.NONE_TILE);
        }

        private WaitMiddle waitMiddle() {
            return WaitMiddle.of(claimTile(), claimTile(nextNextSequenceTile()));
        }



        /* xxxNum —— 元素数量计数*/

        private int pairNum() {
            return data.pairs.size();
        }

        private int singletonNum() {
            return data.singletons.size();
        }

        private int waitMiddleNum() {
            return data.waitMiddles.size();
        }

        private int wait2SideNum() {
            return data.wait2Sides.size();
        }



        /* nextTile 和 nextNextTile —— 获取下一张牌和下下张牌*/

        private TileType nextSequenceTile() {
            return type.nextNormalTile();
        }

        private TileType nextNextSequenceTile() {
            return type.nextNormalTile().nextNormalTile();
        }

        private boolean hasNextTile() {
            return countTile(nextSequenceTile()) > 0;
        }

        private boolean hasNextNextTile() {
            return countTile(nextNextSequenceTile()) > 0;
        }



        /* use 和 reuse —— 负责本地计数 */

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



        /* claim 和 reclaim —— 负责从getter取牌和还牌 */

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



        /* countTile —— 牌计数*/

        private int countTile() {
            return countTile(type);
        }

        private int countTile(TileType type) {
            return data.playerTileManager.countNormalAndRedInHand(type) - data.used.getOrDefault(type, 0);
        }



        /* level —— 递归层级控制*/

        /* 这里简单说明一下递归的作用和顺序
         * 递归的作用就是将每种牌都转化为牌型中的元素，即把某张牌当作为顺子、刻子、对子、两面听、坎张听和单骑的一部分
         * 如果可以转换，就重入本层，如果不能转换，就返回，如果本层没有牌，就进入下一层
         * 
         * 当遍历到NONE的时候，表示所有牌都遍历过了，那么就判断所有的元素是否能表现为一个四面子一雀头的听牌牌型
         * 如果可以，那么就得到一个结果，但是无论可以或者不行，都会进行回溯
         * 
         * 因为一个牌可以有多种元素的解释方式，即一张牌既可以作为顺子的一部分，也可以作为刻子的一部分，也可以组成其他元素
         * 因此需要遍历所有可能的元素的组成方式时，需要进行回溯
         * 
         * 递归的顺序是 MAN_1 -> MAN_9 -> PIN_1 -> PIN_9 -> SOU_1 -> SOU_9 -> EAST -> NORTH -> WHITE -> RED -> NONE
         */

        private void nextLevel(TryOrder order) {
            getNewHelperClass(next(type), order, data).execute();
        }

        private void thisLevel(TryOrder order) {
            getNewHelperClass(type, order, data).execute();
        }

        // 以便测试可重写提供Moke对象
        BacktrackingAlgorithmForTenpaiAnalyze getNewHelperClass(TileType type, TryOrder checkOrder,
            NotChangedData data) {
            return new BacktrackingAlgorithmForTenpaiAnalyze(type, checkOrder, data);
        }

        private boolean isEnd() {
            return type == NONE;
        }



        /* tenpaiResult 收集处理 */

        private void addTenpaiResultIfIsRightPattern() {
            if (isMeld4Singleton1()) {
                addMeld4Singleton1();
            } else if (isMeld3Pair2()) {
                addMeld3Pair2();
            } else if (isMeld3Pair1Wait2Side1()) {
                addMeld3Pair1Wait2side1();
            } else if (isMeld3Pair1WaitMiddle1()) {
                addMeld3Pair1WaitMiddle1();
            }
        }

        private boolean isMeld4Singleton1() {
            return meldNum() == 4 && singletonNum() == 1;
        }

        private boolean addMeld4Singleton1() {
            return data.tenpaiResult
                .add(Meld4Singleton1.of(data.concealedSequences, data.concealedTriplets, data.singletons.get(0)));
        }

        private boolean isMeld3Pair2() {
            return meldNum() == 3 && pairNum() == 2;
        }

        private void addMeld3Pair2() {
            data.tenpaiResult.add(Meld3Pair2.of(data.concealedSequences, data.concealedTriplets, data.pairs));
        }

        private boolean isMeld3Pair1Wait2Side1() {
            return meldNum() == 3 && pairNum() == 1 && wait2SideNum() == 1;
        }

        private void addMeld3Pair1Wait2side1() {
            data.tenpaiResult.add(Meld3Pair1Wait2Side1.of(data.concealedSequences, data.concealedTriplets,
                data.pairs.get(0), data.wait2Sides.get(0)));
        }

        private boolean isMeld3Pair1WaitMiddle1() {
            return meldNum() == 3 && pairNum() == 1 && waitMiddleNum() == 1;
        }

        private void addMeld3Pair1WaitMiddle1() {
            data.tenpaiResult.add(Meld3Pair1WaitMiddle1.of(data.concealedSequences, data.concealedTriplets,
                data.pairs.get(0), data.waitMiddles.get(0)));
        }

        private int meldNum() {
            return data.concealedSequences.size() + data.concealedTriplets.size() + data.exposedMeldNum;
        }
    }
}
