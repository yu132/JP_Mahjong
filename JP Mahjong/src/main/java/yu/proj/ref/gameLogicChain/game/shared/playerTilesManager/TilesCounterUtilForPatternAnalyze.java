package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import static yu.proj.ref.tile.TileType.*;

import java.util.EnumMap;

import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.Tenpaiable;
import yu.proj.ref.gameLogicChain.game.shared.analyze.tenpai.meld4pair1.Meld4Pair1Tenpaiable;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.utils.IntValueMapUtil;

/**  
 * @ClassName: TilesCounterUtil  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class TilesCounterUtilForPatternAnalyze {

    private final static int ALL_TILES_IN_HAND_NUM = 14;

    private final static TileType[] TERMINALS = {MAN_1, MAN_9, PIN_1, PIN_9, SOU_1, SOU_9};
    private final static TileType[] HONORS = {EAST, SOUTH, WEST, NORTH, WHITE, GREEN, RED};

    private PlayerTileManager playerTileManager;

    private TileType tileToWin;

    private EnumMap<TileType, Integer> tripletCount;

    private EnumMap<TileType, Integer> sequenceCount;

    private Tenpaiable tenpaiable;

    public TilesCounterUtilForPatternAnalyze(PlayerTileManager playerTileManager, Tenpaiable tenpaiable,
        TileType tileToWin) {
        assert playerTileManager != null && tenpaiable != null && tileToWin != null;

        this.playerTileManager = playerTileManager;
        this.tileToWin = tileToWin;
        this.tenpaiable = tenpaiable;

        initTripletAndSequenceCount(playerTileManager);
    }

    private void initTripletAndSequenceCount(PlayerTileManager playerTileManager) {
        tripletCount = new EnumMap<>(TileType.class);
        sequenceCount = new EnumMap<>(TileType.class);

        if (tenpaiable instanceof Meld4Pair1Tenpaiable) {// 只有四面子一雀头这种形式才需要分析顺子和刻子

            Meld4Pair1Tenpaiable meld4Pair1Tenpaiable = (Meld4Pair1Tenpaiable)tenpaiable;

            for (Triplet triplet : playerTileManager.getPlayerExposedTilesManager().triplets) {
                IntValueMapUtil.acc(tripletCount, triplet.tileType());
            }

            for (Triplet triplet : meld4Pair1Tenpaiable.getConcealedTriplet()) {
                IntValueMapUtil.acc(tripletCount, triplet.tileType());
            }

            for (Sequence sequence : playerTileManager.getPlayerExposedTilesManager().sequences) {
                IntValueMapUtil.acc(sequenceCount, sequence.tileType());
            }

            for (Sequence sequence : meld4Pair1Tenpaiable.getConcealedSequence()) {
                IntValueMapUtil.acc(sequenceCount, sequence.tileType());
            }
        }
    }

    public TilesCounterUtilForPatternAnalyze(PlayerTileManager playerTileManager) {

        assert playerTileManager != null;

        this.playerTileManager = playerTileManager;
        this.tileToWin = NONE;
    }

    // 返回指定牌型的牌的总数，是所有牌型的牌的数量之和
    public int count(TileType... tileTypes) {
        int count = 0;
        for (TileType tileType : tileTypes) {
            count += tileNum(tileType);
        }
        return count;
    }

    private int tileNum(TileType tileType) {
        return playerTileManager.countKanAs3TileAndRedAsNormal(tileType) + tenpaiPlus1(tileType);
    }

    // 如果是当前牌型听的牌，那么牌数是要加一的
    private int tenpaiPlus1(TileType tileType) {
        return tileType == tileToWin ? 1 : 0;
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
        assert tenpaiable instanceof Meld4Pair1Tenpaiable;
        return playerTileManager.getPlayerExposedTilesManager().triplets.size()
            + ((Meld4Pair1Tenpaiable)tenpaiable).getConcealedTriplet().size();
    }

    public int sequenceTotalNum() {
        assert tenpaiable instanceof Meld4Pair1Tenpaiable;
        return playerTileManager.getPlayerExposedTilesManager().sequences.size()
            + ((Meld4Pair1Tenpaiable)tenpaiable).getConcealedSequence().size();
    }

    public boolean hasTriplet(TileType tileType) {
        return countTriplet(tileType) != 0;
    }

    public int countTriplet(TileType tileType) {
        return tripletCount.getOrDefault(tileType, 0);
    }

    public boolean hasSequence(TileType tileType) {
        return countSequence(tileType) != 0;
    }

    public int countSequence(TileType tileType) {
        return sequenceCount.getOrDefault(tileType, 0);
    }

    public int countTerminals() {
        return count(TERMINALS);
    }

    public int countHonors() {
        return count(HONORS);
    }

    public int countTreminalsAndHonors() {
        return countTerminals() + countHonors();
    }

    public int count2to8() {
        return ALL_TILES_IN_HAND_NUM - countTreminalsAndHonors();
    }

    public boolean all2to8() {
        return count2to8() == ALL_TILES_IN_HAND_NUM;
    }

    public int differentTerminals() {
        return has(TERMINALS);
    }

    public int differentHonors() {
        return has(HONORS);
    }

    public int differentTerminalsAndHonors() {
        return differentTerminals() + differentHonors();
    }

    public boolean isMenzenchin() {
        return playerTileManager.getPlayerExposedTilesManager().sizeOfMakeCall() == 0;
    }

}
