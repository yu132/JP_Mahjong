package yu.proj.jpmahjong.player;

import static yu.proj.jpmahjong.define.Numbers.*;
import static yu.proj.jpmahjong.yaku.Yaku.*;

import java.util.HashSet;
import java.util.Set;

import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.ExposedKanOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperationChoice;
import yu.proj.jpmahjong.player.operation.getTileOperation.RonOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.AddKanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.ConcealedKanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.DiscardTileOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperationChoice;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KitaOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.RiichiOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.TsumoOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.ChooseRobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.NotRobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperationChoice;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.yaku.Yaku;

/**  
 * @ClassName: PlayerInformation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public class PlayerInformation {

    private Rule rule;

    // 分数
    private int point;

    // 正处于立直状态吗
    private boolean riichi;

    // 是双立直吗
    private boolean wRiichi;

    // 处于一发状态吗
    private boolean lppatsu;

    // 是否处于能天和或者地和的状态
    private boolean blessingWin;

    // 和手牌相关的逻辑
    private PlayerTile playerTile;

    // 振听相关规则
    // 舍张振听由 {@code PlayerTile} 来处理，而不在这里处理，因为舍张振听和弃牌相关

    // 同巡振听
    public boolean temporaryFuriten;

    // 立直振听
    public boolean riichiFuriten;

    public boolean isDealer;

    // 被别人鸣牌
    public boolean discardTileBeenMadeCall;

    public PlayerInformation(Rule rule) {
        super();
        this.rule = rule;
    }

    /**
     * 
     * @Title: reset  
     *
     * @Description: 每局游戏开始的时候需要进行的初始化操作
     *               只有得分不初始化，因为得分是每局累计下来的，按场次清零
     *               而不是每场重置分数，最后的分数是这场赢家的胜利标准 
     *
     * @param location      当前玩家的自风，即位置
     * @param initTiles     初始化的手牌
     */
    public void reset(Tile[] initTiles, TileType prevalentWind, TileType seatWind, boolean isDealer) {
        playerTile       = new PlayerTile(initTiles, isDealer, prevalentWind, seatWind, rule);

        riichi           = false;
        wRiichi          = false;
        lppatsu          = false;
        blessingWin      = true;
        temporaryFuriten = false;
        riichiFuriten    = false;
        this.isDealer    = isDealer;
    }

    public boolean isManganAtDraw() {
        return discardTileBeenMadeCall && playerTile.isManganAtDraw();// 流局满贯还要求自己没有被别人鸣牌
    }

    public void setDiscardTileBeenMadeCall(boolean discardTileBeenMadeCall) {
        this.discardTileBeenMadeCall = discardTileBeenMadeCall;
    }

    public boolean isDealer() {
        return isDealer;
    }

    public boolean isRiichi() {
        return riichi;
    }

    public boolean isTenpai() {
        return playerTile.isTenpai();
    }

    public int getPoint() {
        return point;
    }

    public void updateDora(Tile[] doraIndicator, Tile[] uraDoraIndicator, TenpaiAnsNode node) {
        playerTile.updateDora(doraIndicator, uraDoraIndicator, node);
    }

    public GetTileOperationChoice getGetTileOperationChoice(Tile tile, boolean isLastTile, TileSource playerLoc,
        boolean kanable) {

        boolean                furiten                =
            (rule.enableTemporaryFuriten && temporaryFuriten) || (rule.enableRiichiFuriten && riichiFuriten);

        GetTileOperationChoice getTileOperationChoice =
            playerTile.getGetTileOperationChoice(tile, riichi, furiten, playerLoc, isLastTile, kanable);

        if (getTileOperationChoice.getRon() != null) {// 这牌能荣

            // 处理番缚的问题，至少要高于番缚才能和

            // 需要考虑立直、一发、双立直、门前清自摸和 、抢杠、岭上开花、海底摸月、河底捞鱼、天和、地和这些非牌型的役
            // 由于是荣和，因此门前清自摸和 、抢杠、岭上开花、海底摸月、天和、地和是不可能的
            // 抢杠虽然算作荣和，但是这里的荣和指的是打出一张牌后的是否能够荣和
            // 抢杠是在杠后的，那个逻辑需要到 {@code RobbingKanOperationChoice} 里去判断
            // 只需要考虑立直、一发、双立直、河底捞鱼

            TenpaiAnsNode node  = getTileOperationChoice.getRon();

            Set<Yaku>     yakus = new HashSet<>();

            if (wRiichi) {
                yakus.add(DOUBLE_RIICHI);
            } else if (riichi) {// 立直和w立不重复计算，因此用else-if
                yakus.add(RIICHI);
            }

            if (lppatsu) {
                yakus.add(LPPATSU);
            }

            if (isLastTile) {// 是最后一张牌，那么就是河底捞鱼
                yakus.add(UNDER_THE_RIVER);
            }

            node.updateNotPatternYaku(yakus, null);

            // 不足起番，不能荣和
            // 通过canRon将ron从非null时指示可以荣的指示器上解放了出来
            // 因此ron在不足番的时候，可以直接设置为null
            if (!node.hasEnoughHanToWin()) {
                getTileOperationChoice.setRon(null);
            }
        }

        return getTileOperationChoice;
    }

    /**
     * 
     * @Title: furitenCheck  
     *
     * @Description: 检查是否产生振听，本来逻辑是放在executeGetTileOperation里的
     *               但是后来突然发现可以荣的没荣，可能接下来也不是这个人摸牌，那么
     *               就没法处理这个振听的问题，因此还必须额外处理振听，并且抢杠没抢也是
     *               要振听的，要注意这个问题
     *
     * @param op
     * @param choice
     *
     * @throws  
     *
     */
    public void furitenCheck(GetTileOperation op, GetTileOperationChoice choice) {

        // 见逃了，即能和的时候没有和
        // 在能荣和的时候见逃有两种情况
        // 未立直的时候导致同巡振听，立直的时候就是立直振听了
        // 也不一定是玩家选择不和，很有可能是振听导致的不能和
        // 也有可能是不足番缚而导致的不能和，但是都无所谓，当作见逃处理就行
        if (choice.canRon() && !(op instanceof RonOperation)) {
            furitenHelper(choice.getRon() == null);// 形式上能荣但是却不能真的荣，那肯定是番缚导致的
        }
    }

    public void furitenCheck(RobbingKanOperation op, RobbingKanOperationChoice choice) {
        if (choice.canRobbingKan() && op instanceof NotRobbingKanOperation) {
            furitenHelper(choice.getRobbingKan() == null);
        }
    }

    private void furitenHelper(boolean notEnoughHan) {
        if (riichi) {
            if (notEnoughHan) {// 不足番导致的
                if (rule.enableNotEnoughHanToRonCausedRiichiFuriten) {// 苛刻要求必须立直振听
                    riichiFuriten = true;
                } else {
                    temporaryFuriten = true;
                }
            } else {
                riichiFuriten = true;
            }
        } else {
            temporaryFuriten = true;
        }
    }

    public void executeGetTileOperation(Tile tile, TileSource tileSource, GetTileOperation op) {

        // 杠需要特殊处理，因为杠完之后不一定打牌，还有可能直接自摸，即岭上开花
        // 并且杠了，就不能一发和地和了，这里是为了防止出现岭上一发的情况
        if (op instanceof ExposedKanOperation) {
            lppatsu     = false;
            blessingWin = false;
        }

        playerTile.executeGetTileOperation(tile, tileSource, op);
    }

    public KanAndDiscardTileOperationChoice getKanAndDiscardTileOperationChoice(boolean isLastTile, boolean isAfterKan,
        boolean kanable, boolean moreThanFourTileToDraw, ChiOperation chi) {

        // 立直的最基本要求至少是现在没有立直且至少有1000分，还有牌山剩下的牌必须大于4张
        // 另外两个条件：听牌和门清在里面的逻辑判断
        boolean                          canRiichi                        =
            !riichi && point >= 1000 && moreThanFourTileToDraw;

        KanAndDiscardTileOperationChoice kanAndDiscardTileOperationChoice =
            playerTile.getKanAndDiscardTileOperationChoice(riichi, canRiichi, isLastTile, kanable, blessingWin, chi);

        if (kanAndDiscardTileOperationChoice.getTsumo() != null) {// 能够自摸的前提下

            // 处理番缚的问题，至少要高于番缚才能和
            // 需要考虑立直、一发、双立直、门前清自摸和 、抢杠、岭上开花、海底摸月、河底捞鱼、天和、地和这些非牌型的役
            // 由于是自摸，因此抢杠、河底捞鱼是不可能的
            // 只需要考虑立直、一发、双立直、门前清自摸和 、岭上开花、海底摸月、天和、地和

            TenpaiAnsNode node       = kanAndDiscardTileOperationChoice.getTsumo().getTenpaiAnsNode();

            Set<Yaku>     yakus      = new HashSet<>();

            Yaku          removeYaku = null;

            if (blessingWin) {

                if (isDealer) {// 是亲家

                    yakus.add(BLESSING_OF_HEAVEN);

                    // 由于对于亲家牌型的判断和一般情况下是一样的，当作先摸13张牌，然后再摸一张这样处理
                    // 最终就会导致天和时对于纯正九莲宝灯、国士无双十三面和四暗刻单骑有判断的区别
                    // 如果是认为必须前13张和最后一张有固定的顺序，那么就和现有的逻辑是一样的
                    // 如果没有这样的要求，那么这里就必须特殊处理
                    if (!rule.enableNeedExactPatternForTNGAndTWTOAndSWFCTWhenBOH) {

                        Set<Yaku> patternYaku = node.getYakuAns().getYaku();

                        if (patternYaku.contains(NINE_GATES)) {
                            removeYaku = NINE_GATES;
                            yakus.add(TRUE_NINE_GATES);
                        } else if (patternYaku.contains(THIRTEEN_ORPHANS)) {
                            removeYaku = THIRTEEN_ORPHANS;
                            yakus.add(THIRTEEN_WAIT_THIRTEEN_ORPHANS);
                        } else if (patternYaku.contains(FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO)) {
                            removeYaku = FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO;
                            yakus.add(SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS);
                        }

                    }
                } else {
                    yakus.add(BLESSING_OF_EARTH);
                }
            }

            // 上面是追加役满，如果追加役满了，那么下面的牌型就不用分析了
            if (!yakus.isEmpty()) {
                node.updateNotPatternYaku(yakus, removeYaku);
            } else {

                if (wRiichi) {
                    yakus.add(DOUBLE_RIICHI);
                } else if (riichi) {// 立直和w立不重复计算，因此用else-if
                    yakus.add(RIICHI);
                }

                if (lppatsu) {
                    yakus.add(LPPATSU);
                }

                if (isLastTile) {// 是最后一张牌，那么就是海底摸月
                    yakus.add(UNDER_THE_SEA);
                }

                if (isAfterKan) {// 杠后自摸，是岭上开花
                    yakus.add(AFTER_A_KAN);
                }

                if (node.getYakuAns().isMenzenchin()) {// 是门前清，那么自然是门清自摸
                    yakus.add(FULLY_CONCEALED_HAND);
                }

                node.updateNotPatternYaku(yakus, removeYaku);
            }

            if (!node.hasEnoughHanToWin()) {// 不足起番，不能自摸
                kanAndDiscardTileOperationChoice.setTsumo(null);
            }
        }

        return kanAndDiscardTileOperationChoice;
    }

    public void executeKanAndDiscardTileOperation(KanAndDiscardTileOperation op) {

        boolean riichiOp             = op instanceof RiichiOperation;
        boolean discardTileOperation = op instanceof DiscardTileOperation;

        if (riichiOp || discardTileOperation) {
            if (riichiOp) {// 立直的话，就会少一根棒子，并且能有一发的状态
                point   -= 1000;
                riichi   = true;
                lppatsu  = true;
                // 如果是打出第一张牌时就立直，那么就是w立直
                // 不过w立直和地和一样，如果之前有人鸣牌，那么就不算w立直而是普通的立直了
                if (blessingWin) {
                    wRiichi = true;
                }
            } else {// discardTileOperation
                lppatsu = false;// 打出牌后就破除一发
            }
            temporaryFuriten = false;// 如果有同巡振听，那么也解除
            blessingWin      = false;// 第一张牌不能和，那么就不是天和和地和啦
        } else if (op instanceof AddKanOperation || op instanceof ConcealedKanOperation) {
            // 加杠和暗杠也会破除自己的一发和天地和的能力
            lppatsu     = false;
            blessingWin = false;
        }
        playerTile.executeKanAndDiscardTileOperation(op);
    }

    public RobbingKanOperationChoice getRobbingKanOperationChoice(int kanTile, KanOperation kanOp) {

        RobbingKanOperationChoice robbingKanOperationChoice = playerTile.getRobbingKanOperationChoice(kanTile, kanOp);

        if (robbingKanOperationChoice.getRobbingKan() != null) {// 能抢杠

            TenpaiAnsNode node  = robbingKanOperationChoice.getRobbingKan().getTenpaiAnsNode();

            // 抢杠和荣和是一样的，但是由于是抢杠，那么没有可能海底捞鱼，因为海底是不能加杠的
            // 只需要考虑立直、一发、双立直，抢杠是一定有的役
            Set<Yaku>     yakus = new HashSet<>();

            if (wRiichi) {
                yakus.add(DOUBLE_RIICHI);
            } else if (riichi) {// 立直和w立不重复计算，因此用else-if
                yakus.add(RIICHI);
            }

            if (lppatsu) {
                yakus.add(LPPATSU);
            }

            if (!(kanOp instanceof KitaOperation)) {// 抢暗杠和加杠都是抢杠，但是抢拔北不算抢杠
                yakus.add(ROBBING_A_KAN);
            }

            node.updateNotPatternYaku(yakus, null);

            if (!node.hasEnoughHanToWin()) {// 不足起番，不能抢杠
                robbingKanOperationChoice.setRobbingKan(null);
            }
        }

        return robbingKanOperationChoice;
    }

    // 当其他玩家吃碰或者杠的时候，破除一发和地和的可能性
    public void otherPlayerMakeCallOrConcealedKan() {
        lppatsu     = false;
        blessingWin = false;
    }

    /**
     * 
     * @Title: updatePoint  
     *
     * @Description: 用于罚符和包牌时特殊处理分数
     *
     * @param point
     *
     * @throws  
     *
     */
    public void updatePoint(int point) {
        this.point += point;
    }

    /**
     * 
     * @Title: ron  
     *
     * @Description: 荣和得分
     *
     * @param ronOp 荣和的牌型
     * @param riichiStick 立直棒的数量
     * @param dealerStick 本场棒的数量
     */
    public void ronAddPoint(RonOperation ronOp, int riichiStick, int dealerStick) {
        point += ronPoint(ronOp, riichiStick, dealerStick);
    }

    public void ronMinusPoint(RonOperation ronOp, int riichiStick, int dealerStick) {
        point -= ronPoint(ronOp, riichiStick, dealerStick);
    }

    private int ronPoint(RonOperation ronOp, int riichiStick, int dealerStick) {
        // 和了的分数为牌的得分+立直棒的得分（一根1k）+本场棒的得分（放铳者全付 一根300）
        return ronOp.getTenpaiAnsNode().getTotalPoint() + riichiStick * RIICHI_STICK_POINT
            + dealerStick * DEALER_STICK_POINT * 3;
    }

    public void robbingKanAddPoint(ChooseRobbingKanOperation robbingKanOp, int riichiStick, int dealerStick) {
        point += robbingKanPoint(robbingKanOp, riichiStick, dealerStick);
    }

    public void robbingKanMinusPoint(ChooseRobbingKanOperation robbingKanOp, int riichiStick, int dealerStick) {
        point -= robbingKanPoint(robbingKanOp, riichiStick, dealerStick);
    }

    private int robbingKanPoint(ChooseRobbingKanOperation robbingKanOp, int riichiStick, int dealerStick) {
        // 抢杠的分数计算方法和荣和一模一样
        return robbingKanOp.getRobbingKan().getTenpaiAnsNode().getTotalPoint() + riichiStick * RIICHI_STICK_POINT
            + dealerStick * DEALER_STICK_POINT * 3;
    }

    public void tsumoAddPoint(TsumoOperation tsumoOp, int riichiStick, int dealerStick) {
        // 和荣和一样，和了的分数为牌的得分+立直棒的得分（一根1k）+本场棒的得分（放铳者全付 一根300）
        point += tsumoOp.getTsumo().getTenpaiAnsNode().getTotalPoint() + riichiStick * RIICHI_STICK_POINT
            + dealerStick * DEALER_STICK_POINT * 3;
    }

    public void tsumoMinusPoint(TsumoOperation tsumoOp, int riichiStick, int dealerStick) {

        if (isDealer) {// 是亲家

            // 由于亲家罚点，因此肯定是子家赢，不需要额外判断
            // 自摸每个人的本场棒就罚一根，不罚立直棒
            point -= tsumoOp.getTsumo().getTenpaiAnsNode().getPointWhenNotDealerWin().getPointWhenTsumoFromDealer()
                + dealerStick * DEALER_STICK_POINT;

        } else {// 子家

            int           tilePoint;

            TenpaiAnsNode node = tsumoOp.getTsumo().getTenpaiAnsNode();

            if (node.isDealer()) {// 亲家赢
                tilePoint = node.getPointWhenDealerWin().getPointWhenTsumoFromNotDealer();
            } else {// 子家赢
                tilePoint = node.getPointWhenNotDealerWin().getPointWhenTsumoFromNotDealer();
            }

            // 自摸每个人的本场棒就罚一根，不罚立直棒
            point -= tilePoint + dealerStick * DEALER_STICK_POINT;
        }
    }

}
