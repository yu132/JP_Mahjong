package yu.proj.jpmahjong.player;

import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
import yu.proj.jpmahjong.player.opInterface.UserOperationInterface;
import yu.proj.jpmahjong.player.operation.Operation;
import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.DrawOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperation;
import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperationChoice;
import yu.proj.jpmahjong.player.operation.getTileOperation.RonOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperationChoice;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanOperation;
import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.TsumoOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.ChooseRobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.NotRobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperation;
import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperationChoice;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;

/**  
 * @ClassName: Player  
 *
 * @Description: 负责控制用户信息和用户交互的类，基本上就向下交互信息
 *
 * @author 余定邦  
 *
 * @date 2020年10月5日  
 *  
 */
public class Player {

    private PlayerInformation info;
    private UserOperationInterface ui;

    public Player(PlayerInformation info, UserOperationInterface ui) {
        super();
        this.info = info;
        this.ui   = ui;
    }

    public void reset(Tile[] initTiles, TileType prevalentWind, TileType seatWind, boolean isDealer) {
        info.reset(initTiles, prevalentWind, seatWind, isDealer);
    }

    public boolean isDealer() {
        return info.isDealer();
    }

    public boolean isRiichi() {
        return info.isRiichi();
    }

    public boolean isTenpai() {
        return info.isTenpai();
    }

    public int getPoint() {
        return info.getPoint();
    }

    public void updatePoint(int point) {
        info.updatePoint(point);
    }

    public void updateDora(Tile[] doraIndicator, Tile[] uraDoraIndicator, TenpaiAnsNode node) {
        info.updateDora(doraIndicator, uraDoraIndicator, node);
    }

    public void setDiscardTileBeenMadeCall(boolean discardTileBeenMadeCall) {
        info.setDiscardTileBeenMadeCall(discardTileBeenMadeCall);
    }

    public boolean isManganAtDraw() {
        return info.isManganAtDraw();
    }

    public GetTileOperation getUserGetTileOperationChoice(Tile tile, boolean isLastTile, TileSource playerLoc,
        boolean kanable) {

        GetTileOperationChoice choice = info.getGetTileOperationChoice(tile, isLastTile, playerLoc, kanable);

        if (choice.getChi() == null && choice.getPon() == null && choice.getKan() == null && choice.getRon() == null) {

            if (choice.canDraw()) {// 不能吃碰杠荣，而且还是打牌者的下家，那么就抓牌吧
                return new DrawOperation();
            } else {// 而且也不是下家的话，就啥都干不了
                return null;
            }
        }

        GetTileOperation op = ui.getUserGetTileOperationChoice(choice);

        // 振听检查
        info.furitenCheck(op, choice);

        return op;
    }

    public KanAndDiscardTileOperation getUserKanAndDiscardTileOperationChoice(boolean isLastTile, boolean isAfterKan,
        boolean kanable, boolean moreThanFourTileToDraw, ChiOperation chi) {

        KanAndDiscardTileOperationChoice choice =
            info.getKanAndDiscardTileOperationChoice(isLastTile, isAfterKan, kanable, moreThanFourTileToDraw, chi);

        KanAndDiscardTileOperation       op     = ui.getKanAndDiscardTileOperationChoice(choice);

        return op;
    }

    public RobbingKanOperation getUserRobbingKanOperationChoice(int kanTile, KanOperation kanOp) {
        RobbingKanOperationChoice choice = info.getRobbingKanOperationChoice(kanTile, kanOp);

        if (choice.getRobbingKan() == null) {// 如果不能抢杠，那么就直接返回就好了
            return new NotRobbingKanOperation();
        }

        RobbingKanOperation op = ui.getRobbingKanOperationChoice(choice);

        // 抢杠也要振听检查
        info.furitenCheck(op, choice);

        return op;
    }

    public void announceOperation(TileSource player, Operation op) {
        ui.announceOperation(player, op);
    }

    public void executeGetTileOperation(Tile tile, TileSource tileSource, GetTileOperation op) {
        info.executeGetTileOperation(tile, tileSource, op);
    }

    public void executeKanAndDiscardTileOperation(KanAndDiscardTileOperation op) {
        info.executeKanAndDiscardTileOperation(op);
    }

    public void otherPlayerMakeCallOrConcealedKan() {
        info.otherPlayerMakeCallOrConcealedKan();
    }

    public void ronAddPoint(RonOperation ronOp, int riichiStick, int dealerStick) {
        info.ronAddPoint(ronOp, riichiStick, dealerStick);
    }

    public void ronMinusPoint(RonOperation ronOp, int riichiStick, int dealerStick) {
        info.ronMinusPoint(ronOp, riichiStick, dealerStick);
    }

    public void robbingKanAddPoint(ChooseRobbingKanOperation robbingKanOp, int riichiStick, int dealerStick) {
        info.robbingKanAddPoint(robbingKanOp, riichiStick, dealerStick);
    }

    public void robbingKanMinusPoint(ChooseRobbingKanOperation robbingKanOp, int riichiStick, int dealerStick) {
        info.robbingKanMinusPoint(robbingKanOp, riichiStick, dealerStick);
    }

    public void tsumoAddPoint(TsumoOperation tsumoOp, int riichiStick, int dealerStick) {
        info.tsumoAddPoint(tsumoOp, riichiStick, dealerStick);
    }

    public void tsumoMinusPoint(TsumoOperation tsumoOp, int riichiStick, int dealerStick) {
        info.tsumoMinusPoint(tsumoOp, riichiStick, dealerStick);
    }
}
