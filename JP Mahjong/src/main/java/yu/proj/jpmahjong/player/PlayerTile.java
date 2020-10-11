package yu.proj.jpmahjong.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.AddKan;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.AnalyzeAddKan;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.AnalyzeConcealedKan;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.AnalyzeNineDifferntTerminalsandHonorsDraw;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.ConcealedKan;
import yu.proj.jpmahjong.gamelogic.analyze.chi.AnalyzeChi;
import yu.proj.jpmahjong.gamelogic.analyze.chi.Chi;
import yu.proj.jpmahjong.gamelogic.analyze.discardTile.AnalyzeDiscardTile;
import yu.proj.jpmahjong.gamelogic.analyze.discardTile.AnalyzeRiichi;
import yu.proj.jpmahjong.gamelogic.analyze.manganAtDraw.AnalyzeManganAtDraw;
import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.AnalyzePonAndExposedKan;
import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.ExposedKan;
import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.Pon;
import yu.proj.jpmahjong.gamelogic.analyze.robbingKan.AnalyzeRobbingKan;
import yu.proj.jpmahjong.gamelogic.analyze.win.AnalyzeTsumo;
import yu.proj.jpmahjong.gamelogic.analyze.win.AnalyzeTsumo.Tsumo;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.FinalTenpaiAns;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.DrawOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.ExposedKanOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperationChoice;
import yu.proj.jpmahjong.player.operation.getTileOperation.PonOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.AddKanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.ConcealedKanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.DiscardTileOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperationChoice;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.RiichiOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperationChoice;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.tiles.meld.Meld;
import yu.proj.jpmahjong.tiles.meld.MeldType;

/**  
 * @ClassName: PlayerTile  
 *
 * @Description: 维护、分析玩家的手牌
 *
 * @author 余定邦  
 *
 * @date 2020年9月21日  
 *  
 */
public class PlayerTile {

    private Rule rule;

    // 存储场上信息
    private boolean isDealer;
    private TileType prevalentWind;
    private TileType seatWind;

    private CountNum concealedHand;
    private CountNum fullHand;

    private Tile drawTile;

    private List<Meld> makeCall = new ArrayList<>();
    private List<Meld> concealedKan = new ArrayList<>();

    private boolean[] discardTiles = new boolean[34];

    // 数据缓存 不update就能一直复用的
    // 只有是摸牌，结束时是弃牌，切弃牌和摸牌完全一致的时候，才能够不update
    private Map<Integer, List<Chi>> chi;
    private Map<Integer, Pon> pon;
    private Map<Integer, ExposedKan> kan;

    private FinalTenpaiAns tenpaiAns;

    // 分析类
    // 进张分析
    private AnalyzeChi analyzeChi;
    private AnalyzePonAndExposedKan analyzePonAndKan;
    private GetFinalTenpaiTileAndPoint analyzeTenpaiAndPoint;

    // 进张后的杠分析
    private AnalyzeAddKan analyzeAddKan;
    private AnalyzeConcealedKan analyzeConcealedKan;

    // 自摸分析
    private AnalyzeTsumo analyzeTsumo;

    // 立直和弃牌分析
    private AnalyzeRiichi analyzeRiichi;
    private AnalyzeDiscardTile analyzeDiscardTile;

    // 抢杠分析
    private AnalyzeRobbingKan analyzeRobbingKan;

    // 九种九牌分析
    private AnalyzeNineDifferntTerminalsandHonorsDraw analyzeNineDifferntTerminalsandHonorsDraw;

    // 流局满贯分析
    private AnalyzeManganAtDraw analyzeManganAtDraw;

    public PlayerTile(Tile[] initTiles, boolean isDealer, TileType prevalentWind, TileType seatWind, Rule rule) {
        super();
        this.concealedHand         = new CountNum(initTiles);
        this.fullHand              = new CountNum(initTiles);

        this.rule                  = rule;

        this.analyzeChi            = new AnalyzeChi();
        this.analyzePonAndKan      = new AnalyzePonAndExposedKan();
        this.analyzeTenpaiAndPoint = new GetFinalTenpaiTileAndPoint(rule);

        this.analyzeAddKan         = new AnalyzeAddKan();
        this.analyzeConcealedKan   = new AnalyzeConcealedKan();
        this.analyzeTsumo          = new AnalyzeTsumo();
        this.analyzeRiichi         = new AnalyzeRiichi();
        this.analyzeDiscardTile    = new AnalyzeDiscardTile();

        this.analyzeRobbingKan     = new AnalyzeRobbingKan();

        this.isDealer              = isDealer;
        this.prevalentWind         = prevalentWind;
        this.seatWind              = seatWind;

        // 初始化过后就进行分析
        analyzeGetTileOperation();
    }

    /**
     * 
     * @Title: isTenpai  
     *
     * @Description: 检查是否听牌，用于荒牌流局时的罚符
     *
     */
    public boolean isTenpai() {
        // 哪怕空听，或者听没有役的牌，只听一种那都也是听牌
        return !tenpaiAns.getFullTenpaiAns().isEmpty();
    }

    public boolean isManganAtDraw() {
        return analyzeManganAtDraw.analyzeManganAtDraw(discardTiles);
    }

    /**
     * 
     * @Title: analyzeGetTileOperation  
     *
     * @Description: 分析得牌操作，包括吃、碰、杠和和牌（主要是为了判断荣和）的信息  
     *               为了下一步用户在选择中判断和最终的竞价（即大家都选择得牌操作）
     *               还需要判断谁的操作更优先（荣>杠=碰>吃）
     *
     */
    private void analyzeGetTileOperation() {
        chi       = analyzeChi.analyzeChi(concealedHand);
        pon       = analyzePonAndKan.analyzePon(concealedHand);
        kan       = analyzePonAndKan.analyzeKan(concealedHand);
        tenpaiAns = analyzeTenpaiAndPoint.analyzeTenpaiAns(concealedHand, fullHand, makeCall, concealedKan,
            prevalentWind, seatWind, isDealer);
    }

    public GetTileOperationChoice getGetTileOperationChoice(Tile tile, boolean riichi, boolean furiten,
        TileSource playerLoc, boolean isLastTile, boolean kanable) {
        int           tileIndex = CountNum.getTileIndex(tile);

        List<Chi>     chis      = null;
        Pon           pon       = null;
        ExposedKan    kan       = null;

        TenpaiAnsNode ron       = null;

        if (!riichi) {// 立直的时候不能吃碰和杠

            if (!isLastTile) {// 河底牌也不能鸣牌

                if (playerLoc == TileSource.NEXT_PLAYER) {// 下家才能吃
                    chis = chi.get(tileIndex);
                }
                pon = this.pon.get(tileIndex);

                if (kanable) {// 如果已经开了四杠子，并且规则不允许开第五个杠，那么也不能杠
                    kan = this.kan.get(tileIndex);
                }
            }
        }

        if (!furiten) {// 振听的时候不能荣别人家的牌

            // 由于外面判断不了舍张振听，因此在这里判断

            // 如果听的牌是打过的牌，且开启舍张振听规则的话，就不能荣
            if (rule.enableDiscardFuriten) {
                for (int tileToRon : tenpaiAns.getRon().keySet()) {
                    if (discardTiles[tileToRon]) {
                        furiten = true;
                        break;
                    }
                }
            }
        }

        ron = tenpaiAns.getRon().get(tileIndex);

        if (!furiten) {
            return new GetTileOperationChoice(tile, chis, pon, kan, ron, false, ron != null,
                playerLoc == TileSource.NEXT_PLAYER && !isLastTile);
        } else {
            return new GetTileOperationChoice(tile, chis, pon, kan, null, true, ron != null,
                playerLoc == TileSource.NEXT_PLAYER && !isLastTile);// 河底也不允许摸牌
        }
    }

    /**
     * 
     * @Title: executeGetTileOperation  
     *
     * @Description: 处理摸牌，吃牌，碰牌，杠牌的逻辑  
     *
     * @param tile 进张
     * @param kanDraw 如果杠了牌，那么还需要补一张岭上牌
     * @param tileSource 进张的来源
     * @param ans 玩家的进张选择
     *
     */
    public void executeGetTileOperation(Tile tile, TileSource tileSource, GetTileOperation op) {

        // 进张一定要加入手牌
        concealedHand.add(tile);
        fullHand.add(tile);

        drawTile = null;

        if (op instanceof DrawOperation) {
            drawTile = tile;

        } else if (op instanceof ChiOperation) {// 吃

            ChiOperation chiOperation = (ChiOperation)op;

            Chi          chi          = chiOperation.getChi();
            int[]        tilesIndex   = new int[3];
            tilesIndex[0] = chi.getTileToChi();
            tilesIndex[1] = chi.getIndexOfTileInHand()[0];
            tilesIndex[2] = chi.getIndexOfTileInHand()[1];
            Arrays.sort(tilesIndex);

            Tile[] tiles = new Tile[3];

            for (int i = 0; i < 3; ++i) {
                tiles[i] = concealedHand.remove(tilesIndex[i], chiOperation.isUseRedTile());
            }

            Meld meld = new Meld(MeldType.SEQUENCE, tiles, tile, tileSource, false, 0);

            makeCall.add(meld);

        } else if (op instanceof PonOperation) {// 碰

            PonOperation ponOperation = (PonOperation)op;

            Pon          pon          = ponOperation.getPon();

            int          tileIndex    = pon.getTileToPon();

            Tile[]       tiles        = new Tile[3];

            int          redFiveNum   = ponOperation.getUseRedTileNumber();

            for (int i = 0; i < redFiveNum; ++i) {
                tiles[i] = concealedHand.remove(tileIndex, true);
            }

            for (int i = redFiveNum; i < 2; ++i) {
                tiles[i] = concealedHand.remove(tileIndex, false);
            }

            Meld meld = new Meld(MeldType.TRIPLET, tiles, tile, tileSource, false, 0);

            makeCall.add(meld);

        } else if (op instanceof ExposedKanOperation) {// 杠

            ExposedKanOperation exposedKanOperation = (ExposedKanOperation)op;

            ExposedKan          kan                 = exposedKanOperation.getKan();

            int                 tileIndex           = kan.getTileToKan();

            Tile[]              tiles               = new Tile[4];

            for (int i = 0; i < 4; ++i) {
                // 这里不在意有没有红宝牌，因为在能杠的时候，手上一定有3张牌，且会杠进来一张牌，因此最终手上
                // 一定会有全部的牌，因此有没有红宝牌无所谓，因为只可能有一种情况，不需要讨论
                tiles[i] = concealedHand.remove(tileIndex, false);
            }

            Meld meld = new Meld(MeldType.EXPOSED_QUAD, tiles, tile, tileSource, false, 0);

            makeCall.add(meld);

            // 由于杠后牌型有很大的改变，因此必须重新进行牌型分析
            // 必须在杠牌加入手牌前进行，因为分析是基于13张分析的，如果加入就变成14张了
            analyzeGetTileOperation();

            concealedHand.add(exposedKanOperation.getKanDraw());
            fullHand.add(exposedKanOperation.getKanDraw());

            drawTile = exposedKanOperation.getKanDraw();
        }
    }

    /**
     * 
     * @Title: getRobbingKanOperationChoice  
     *
     * @Description: 根据当前牌型判断是否能够抢杠
     *
     * @param kanTile 别的玩家杠的牌
     * 
     * @return 是否能够抢杠的选择
     *
     * @throws  
     *
     */
    public RobbingKanOperationChoice getRobbingKanOperationChoice(int kanTile, KanOperation kanOp) {
        return analyzeRobbingKan.analyzeRobbingKan(kanTile, tenpaiAns, kanOp, rule);
    }

    /**
     * 
     * @Title: getMiddleOperation  
     *
     * @Description: 获取进张之后的操作可能，由于每次进张之后都需要重新分析，因此没必要进行缓存  
     *
     * @return 可能的操作  
     *
     */
    public KanAndDiscardTileOperationChoice getKanAndDiscardTileOperationChoice(boolean riichi, boolean canRiichi,
        boolean isLastTile, boolean kanable, boolean blessingWin, ChiOperation chi) {
        List<AddKan>       addKan       = null;
        List<ConcealedKan> concealedKan = null;

        // 如果海底了，就不能杠了
        // 如果已经开了四杠子，并且规则不允许开第五个杠，那么也不能杠
        if (!isLastTile && kanable) {
            analyzeAddKan.analyzeAddKan(concealedHand, makeCall, riichi);
            analyzeConcealedKan.analyzeConcealedKan(drawTile, concealedHand, riichi, tenpaiAns, rule);
        }

        Tsumo      tsumo               = analyzeTsumo.analyzeTsumo(tenpaiAns, drawTile);

        List<Tile> disCardTileToRiichi = null;

        if (canRiichi) {
            disCardTileToRiichi = analyzeRiichi.analyzeRiichi(concealedHand, makeCall, this.concealedKan, rule, chi);
        }

        List<Tile> discardTile = analyzeDiscardTile.analyzeDiscardTile(concealedHand, chi, rule);

        boolean    nineDifferntTerminalsandHonors;

        if (!blessingWin) {
            nineDifferntTerminalsandHonors = false;
        } else {
            nineDifferntTerminalsandHonors =
                analyzeNineDifferntTerminalsandHonorsDraw.analyzeNineDifferntTerminalsandHonorsDraw(fullHand);
        }

        return new KanAndDiscardTileOperationChoice(addKan, concealedKan, null, tsumo, disCardTileToRiichi, discardTile,
            nineDifferntTerminalsandHonors);
    }

    public void executeKanAndDiscardTileOperation(KanAndDiscardTileOperation op) {

        if (op instanceof AddKanOperation) {// 是加杠，那么把之前作为明刻的结构改为明杠

            AddKan addKan = ((AddKanOperation)op).getAddKan();

            Meld   meld   = addKan.getTriplet();

            meld.setType(MeldType.EXPOSED_QUAD);

            Tile[] tiles = new Tile[4];
            Tile[] old   = meld.getTiles();
            System.arraycopy(old, 0, tiles, 0, 3);

            tiles[4] = concealedHand.remove(addKan.getTileToKan(), false);

            meld.setTiles(tiles);
            meld.setAddKan(true);
            meld.setMakeCallWhenAddKan(makeCall.size());

            // 由于杠后牌型有很大的改变，因此必须重新进行牌型分析
            // 必须在杠牌加入手牌前进行，因为分析是基于13张分析的，如果加入就变成14张了
            analyzeGetTileOperation();

            Tile kanDraw = ((AddKanOperation)op).getKanDraw();
            drawTile = kanDraw;

            // 杠进来的牌加入手牌
            concealedHand.add(kanDraw);
            fullHand.add(kanDraw);

        } else if (op instanceof ConcealedKanOperation) {// 是暗杠

            ConcealedKan concealedKan = ((ConcealedKanOperation)op).getConcealedKan();

            Tile[]       tiles        = new Tile[4];

            for (int i = 0; i < 4; ++i) {
                tiles[i] = concealedHand.remove(concealedKan.getTileIndex(), false);
            }

            Meld meld = new Meld(MeldType.CONCEALED_QUAD, tiles, null, TileSource.SELF, false, 0);

            this.concealedKan.add(meld);

            // 由于杠后牌型有很大的改变，因此必须重新进行牌型分析
            // 必须在杠牌加入手牌前进行，因为分析是基于13张分析的，如果加入就变成14张了
            analyzeGetTileOperation();

            Tile kanDraw = ((AddKanOperation)op).getKanDraw();
            drawTile = kanDraw;

            // 杠进来的牌加入手牌
            concealedHand.add(kanDraw);
            fullHand.add(kanDraw);

        } else if (op instanceof RiichiOperation || op instanceof DiscardTileOperation) {// 是立直或者弃牌

            Tile tileToDiscard;

            if (op instanceof RiichiOperation) {
                tileToDiscard = ((RiichiOperation)op).getTileToDiscard();
            } else {
                tileToDiscard = ((DiscardTileOperation)op).getTileToDiscard();
            }

            concealedHand.removeTile(tileToDiscard);
            fullHand.removeTile(tileToDiscard);

            discardTiles[CountNum.getTileIndex(tileToDiscard)] = true;

            // 摸得牌和切的牌不一样，就意味着之前的13张牌改变了，那么就必须重新计算分数
            // 杠完之后也同理，因为杠后就需要对牌型进行重新分析，那么分析过后如果没有改变，那么也不需要重新分析
            // 吃和碰由于drawTile==null，因此一定会进行重新分析的操作
            if (tileToDiscard != drawTile) {
                analyzeGetTileOperation();
            }
        }
    }

    public void updateDora(Tile[] doraIndicator, Tile[] uraDoraIndicator, TenpaiAnsNode node) {

        int   doraNumber = 0, uraDoraNumber = 0;

        int[] dora       = getDora(doraIndicator), uraDora = getDora(uraDoraIndicator);

        for (int i = 0; i < dora.length; ++i) {
            doraNumber += fullHand.getCount(dora[i]);
            doraNumber += fullHand.getCount(uraDora[i]);
        }

        node.updatePointWithNotPatternYakuAndDora(doraNumber, uraDoraNumber);
    }

    private int[] getDora(Tile[] indicator) {
        int[] dora = new int[indicator.length];

        for (int i = 0; i < indicator.length; ++i) {
            int index = CountNum.getTileIndex(indicator[i]);
            if (index == CountNum.M9 || index == CountNum.P9 || index == CountNum.S9) {
                dora[i] = index - 8;
            } else if (index == CountNum.N) {
                dora[i] = index - 3;
            } else if (index == CountNum.R) {
                dora[i] = index - 2;
            } else {
                dora[i] = index + 1;
            }
        }

        return dora;
    }


}
