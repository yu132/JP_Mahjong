package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import static yu.proj.ref.tile.TileType.*;
import static yu.proj.ref.utils.IntValueMapUtil.*;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.hutool.core.util.ArrayUtil;
import lombok.Builder;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair1Wait2Side1;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair1WaitMiddle1;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld3Pair2;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Pair1Tenpaiable;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Meld;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.concealedTile.Pair;
import yu.proj.ref.tilePatternElement.concealedTile.Wait2Side;
import yu.proj.ref.tilePatternElement.concealedTile.WaitMiddle;
import yu.proj.ref.tilePatternElement.exposedTile.AddKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.ConcealedKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.ExposedKanQuad;

/**  
 * @ClassName: TilesCounterUtil  
 *
 * @Description: 牌型分析的工具类
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TilesCounterUtilForPatternAnalyze {

    final static int ALL_TILES_IN_HAND_NUM = 14;

    final static int MELD_TILE_NUM = 3;
    final static int PAIR_TILE_NUM = 2;

    final static TileType[] TERMINALS = {MAN_1, MAN_9, PIN_1, PIN_9, SOU_1, SOU_9};
    final static TileType[] WINDS = {EAST, SOUTH, WEST, NORTH};
    final static TileType[] DRAGONS = {WHITE, GREEN, RED};
    final static TileType[] GREENS = {SOU_2, SOU_3, SOU_4, SOU_6, SOU_8, GREEN};

    final static TileType[] MANS = {MAN_1, MAN_2, MAN_3, MAN_4, MAN_5, MAN_6, MAN_7, MAN_8, MAN_9};
    final static TileType[] PINS = {PIN_1, PIN_2, PIN_3, PIN_4, PIN_5, PIN_6, PIN_7, PIN_8, PIN_9};
    final static TileType[] SOUS = {SOU_1, SOU_2, SOU_3, SOU_4, SOU_5, SOU_6, SOU_7, SOU_8, SOU_9};

    final static TileType[] TERMINAL_SEQUENCES = {MAN_1, MAN_7, PIN_1, PIN_7, SOU_1, SOU_7};

    private TilesCounterUtilForPatternAnalyzeData data = new TilesCounterUtilForPatternAnalyzeData();

    private TilesCounterUtilForPatternAnalyzeData unmodifyCopy = null;

    @Builder(toBuilder = true)
    public static class TilesCounterUtilForPatternAnalyzeData {
        public PlayerTileManager playerTileManager;
        public Tenpaiable tenpaiable;
        public TileType tileToWin;
        public Map<TileType, Integer> tripletCount;
        public int tripletTotalNum;
        public Map<TileType, Integer> sequenceCount;
        public int sequenceTotalNum;
        public TileType pairType;

        TilesCounterUtilForPatternAnalyzeData() {
            super();
        }

        TilesCounterUtilForPatternAnalyzeData(PlayerTileManager playerTileManager, Tenpaiable tenpaiable,
            TileType tileToWin, Map<TileType, Integer> tripletCount, int tripletTotalNum,
            Map<TileType, Integer> sequenceCount, int sequenceTotalNum, TileType pairType) {
            super();
            this.playerTileManager = playerTileManager;
            this.tenpaiable = tenpaiable;
            this.tileToWin = tileToWin;
            this.tripletCount = tripletCount;
            this.tripletTotalNum = tripletTotalNum;
            this.sequenceCount = sequenceCount;
            this.sequenceTotalNum = sequenceTotalNum;
            this.pairType = pairType;
        }
    }

    public TilesCounterUtilForPatternAnalyzeData getUnmodifyCopy() {
        if (unmodifyCopy == null) {
            unmodifyCopy = data.toBuilder().tripletCount(Collections.unmodifiableMap(data.tripletCount))
                .sequenceCount(Collections.unmodifiableMap(data.sequenceCount)).build();
        }
        return unmodifyCopy;
    }

    public TilesCounterUtilForPatternAnalyze(PlayerTileManager playerTileManager) {

        assert playerTileManager != null;

        data.playerTileManager = playerTileManager;
        data.tileToWin = NONE;
    }

    public TilesCounterUtilForPatternAnalyze(PlayerTileManager playerTileManager, Tenpaiable tenpaiable,
        TileType tileToWin) {
        assert playerTileManager != null && tenpaiable != null && tileToWin != null;

        data.playerTileManager = playerTileManager;
        data.tileToWin = tileToWin;
        data.tenpaiable = tenpaiable;

        initTripletAndSequenceCount();

        analyzePairType();
    }

    private void analyzePairType() {
        if (data.tenpaiable instanceof Meld4Pair1Tenpaiable) {
            data.pairType = ((Meld4Pair1Tenpaiable)data.tenpaiable).pairType(data.tileToWin);
        }
    }

    private void initTripletAndSequenceCount() {
        data.tripletCount = new EnumMap<>(TileType.class);
        data.sequenceCount = new EnumMap<>(TileType.class);

        if (data.tenpaiable instanceof Meld4Pair1Tenpaiable) {// 只有四面子一雀头这种形式才需要分析顺子和刻子

            countExposedTriplet();

            countConcealedTriplet();

            countExposedSequence();

            countConcealedSequence();

            fillPairAndWaitMiddleAndWait2Side();

            countQuadsAsTriplet();
        }
    }

    private void countConcealedSequence() {
        for (Sequence sequence : ((Meld4Pair1Tenpaiable)data.tenpaiable).getConcealedSequence()) {
            accSequenceCount(sequence.tileType());
        }
    }

    private void countExposedSequence() {
        for (Sequence sequence : data.playerTileManager.getPlayerExposedTilesManager().sequences) {
            accSequenceCount(sequence.tileType());
        }
    }

    private void countConcealedTriplet() {
        for (Triplet triplet : ((Meld4Pair1Tenpaiable)data.tenpaiable).getConcealedTriplet()) {
            accTripletCount(triplet.tileType());
        }
    }

    private void countExposedTriplet() {
        for (Triplet triplet : data.playerTileManager.getPlayerExposedTilesManager().triplets) {
            accTripletCount(triplet.tileType());
        }
    }

    private void fillPairAndWaitMiddleAndWait2Side() {
        fillPairToTriplet();
        fillWaitMiddleToSequence();
        fillWait2SideToSequence();
    }

    private void fillPairToTriplet() {
        if (data.tenpaiable instanceof Meld3Pair2) {
            for (Pair pair : ((Meld3Pair2)data.tenpaiable).getPairs()) {
                if (pair.type() == data.tileToWin) {
                    accTripletCount(data.tileToWin);
                }
            }
        }
    }

    private void fillWaitMiddleToSequence() {
        if (data.tenpaiable instanceof Meld3Pair1WaitMiddle1) {
            WaitMiddle middle = ((Meld3Pair1WaitMiddle1)data.tenpaiable).getWaitMiddle();
            accSequenceCount(middle.getLower().getTileType());
        }
    }

    private void fillWait2SideToSequence() {
        if (data.tenpaiable instanceof Meld3Pair1Wait2Side1) {
            Wait2Side wait2Side = ((Meld3Pair1Wait2Side1)data.tenpaiable).getWait2Side();
            if (wait2Side.getLower().getTileType().nextOf(data.tileToWin)) {// 当听两面且听的是前面那张
                accSequenceCount(data.tileToWin);// 记作听的牌的类型
            } else {
                accSequenceCount(wait2Side.getLower().getTileType());// 否则记作lower类型
            }
        }
    }

    // 由于杠子在牌型分析中可同时视为刻子和杠子，因此计数时需要考虑这一点
    private void countQuadsAsTriplet() {
        for (AddKanQuad quad : data.playerTileManager.getPlayerExposedTilesManager().addKanQuads) {
            accTripletCount(quad.tileType());
        }
        for (ConcealedKanQuad quad : data.playerTileManager.getPlayerExposedTilesManager().concealedKanQuads) {
            accTripletCount(quad.tileType());
        }
        for (ExposedKanQuad quad : data.playerTileManager.getPlayerExposedTilesManager().exposedKanQuads) {
            accTripletCount(quad.tileType());
        }
    }

    private void accTripletCount(TileType type) {
        acc(data.tripletCount, type);
        ++data.tripletTotalNum;
    }

    private void accSequenceCount(TileType type) {
        acc(data.sequenceCount, type);
        ++data.sequenceTotalNum;
    }

    // 返回指定牌型的牌的总数，是所有牌型的牌的数量之和，但是杠子只算作3张，牌的总数应为14张
    public int count(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            count += tileNum(tileType);
        }
        return count;
    }

    private int tileNum(TileType tileType) {
        return data.playerTileManager.countKanAs3TileAndRedAsNormal(tileType) + tenpaiPlus1(tileType);
    }

    // 如果是当前牌型听的牌，那么牌数是要加一的
    private int tenpaiPlus1(TileType tileType) {
        return tileType == data.tileToWin ? 1 : 0;
    }

    // 返回指定牌型中存在的个数，每种牌型无论存在几张，都记作1种类型
    public int has(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            boolean hasTile = tileNum(tileType) > 0;
            count += hasTile ? 1 : 0;
        }
        return count;
    }

    public int tripletTotalNum() {
        assert data.tenpaiable instanceof Meld4Pair1Tenpaiable;
        return data.tripletTotalNum;
    }

    public int exposedTripletNum() {
        return data.playerTileManager.getPlayerExposedTilesManager().triplets.size()
            + data.playerTileManager.getPlayerExposedTilesManager().addKanQuads.size()
            + data.playerTileManager.getPlayerExposedTilesManager().exposedKanQuads.size();
    }

    public int concealedTripletNum() {
        return tripletTotalNum() - exposedTripletNum();
    }

    public int quadTotalNum() {
        return data.playerTileManager.getPlayerExposedTilesManager().addKanQuads.size()
            + data.playerTileManager.getPlayerExposedTilesManager().concealedKanQuads.size()
            + data.playerTileManager.getPlayerExposedTilesManager().exposedKanQuads.size();
    }

    public Set<Entry<TileType, Integer>> sequencesNum() {
        return data.sequenceCount.entrySet();
    }

    public int sequenceTotalNum() {
        assert data.tenpaiable instanceof Meld4Pair1Tenpaiable;
        return data.sequenceTotalNum;
    }

    public boolean hasTriplet(TileType tileType) {
        return countTriplet(tileType) != 0;
    }

    public int countTriplet(TileType tileType) {
        return data.tripletCount.getOrDefault(tileType, 0);
    }

    public int countTriplets(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            count += data.tripletCount.getOrDefault(tileType, 0);
        }
        return count;
    }

    public boolean hasSequence(TileType tileType) {
        return countSequence(tileType) != 0;
    }

    public int countSequence(TileType tileType) {
        return data.sequenceCount.getOrDefault(tileType, 0);
    }

    public int countSequences(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            count += data.sequenceCount.getOrDefault(tileType, 0);
        }
        return count;
    }

    public int countTerminals() {
        return count(TERMINALS);
    }

    public boolean allTerminals() {
        return countTerminals() == ALL_TILES_IN_HAND_NUM;
    }

    public int countHonors() {
        return countWinds() + countDragons();
    }

    public boolean allHonors() {
        return countHonors() == ALL_TILES_IN_HAND_NUM;
    }

    private boolean all(int num) {
        return num == ALL_TILES_IN_HAND_NUM;
    }

    public int countTreminalsAndHonors() {
        return countTerminals() + countHonors();
    }

    public boolean allTerminalsAndHonors() {
        return all(countTreminalsAndHonors());
    }

    public int count2to8() {
        return ALL_TILES_IN_HAND_NUM - countTreminalsAndHonors();
    }

    public boolean all2to8() {
        return all(count2to8());
    }

    public int countDragons() {
        return count(DRAGONS);
    }

    public int countWinds() {
        return count(WINDS);
    }

    public int countGreens() {
        return count(GREENS);
    }

    public boolean allGreens() {
        return all(countGreens());
    }

    public int countOutSideHand() {
        int count = 0;
        count += countSequences(TERMINAL_SEQUENCES) * MELD_TILE_NUM;// 计算包含1、9的顺子的牌数
        count += countTriplets(TERMINALS) * MELD_TILE_NUM;// 计算包含1、9的刻子的牌数
        if (ArrayUtil.contains(TERMINALS, data.pairType)) {// 计算包含1、9的雀头的牌数
            count += PAIR_TILE_NUM;
        }
        return count;
    }

    public boolean allOutSideHand() {
        return all(countOutSideHand());
    }

    public int countOutSideHandAndHonors() {
        return countOutSideHand() + countHonors();
    }

    public boolean allOutSideHandAndHonors() {
        return all(countOutSideHandAndHonors());
    }

    public int countMans() {
        return count(MANS);
    }

    public boolean allMans() {
        return all(countMans());
    }

    public boolean allMansAndHonors() {
        return all(countMans() + countHonors());
    }

    public int countPins() {
        return count(PINS);
    }

    public boolean allPins() {
        return all(countPins());
    }

    public boolean allPinsAndHonors() {
        return all(countPins() + countHonors());
    }

    public int countSous() {
        return count(SOUS);
    }

    public boolean allSous() {
        return all(countSous());
    }

    public boolean allSousAndHonors() {
        return all(countSous() + countHonors());
    }

    public int differentTerminals() {
        return has(TERMINALS);
    }

    public int differentHonors() {
        return has(WINDS) + has(DRAGONS);
    }

    public int differentMans() {
        return has(MANS);
    }

    public int differentPins() {
        return has(PINS);
    }

    public int differentSous() {
        return has(SOUS);
    }

    public int differentTerminalsAndHonors() {
        return differentTerminals() + differentHonors();
    }

    // 是否门清是不判断暗杠的
    public boolean isMenzenchin() {
        return data.playerTileManager.getPlayerExposedTilesManager().sizeOfMakeCallExceptConcealedKan() == 0;
    }

    // 这个是否鸣牌中包括暗杠
    public boolean hasNotMakeCall() {
        return data.playerTileManager.getPlayerExposedTilesManager().sizeOfMakeCall() == 0;
    }

    public List<Meld> tripletsAndQuadsOrder() {
        return Collections.unmodifiableList(data.playerTileManager.getPlayerExposedTilesManager().tripletAndQuadOrder);
    }

    public List<Meld> tripletAndQuadOrderIgnoreAddKan() {
        return Collections
            .unmodifiableList(data.playerTileManager.getPlayerExposedTilesManager().tripletAndQuadOrderIgnoreAddKan);
    }

}
