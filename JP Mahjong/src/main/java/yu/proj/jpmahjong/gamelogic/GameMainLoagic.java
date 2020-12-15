// package yu.proj.jpmahjong.gamelogic;
//
// import static yu.proj.jpmahjong.define.Numbers.*;
//
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
//
// import yu.proj.jpmahjong.define.Numbers;
// import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
// import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternYaku.YakuAns;
// import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
// import yu.proj.jpmahjong.player.Player;
// import yu.proj.jpmahjong.player.PlayerInformation;
// import yu.proj.jpmahjong.player.TileSource;
// import yu.proj.jpmahjong.player.opInterface.UserOperationInterface;
// import yu.proj.jpmahjong.player.operation.Operation;
// import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
// import yu.proj.jpmahjong.player.operation.getTileOperation.DrawOperation;
// import yu.proj.jpmahjong.player.operation.getTileOperation.ExposedKanOperation;
// import yu.proj.jpmahjong.player.operation.getTileOperation.GetTileOperation;
// import yu.proj.jpmahjong.player.operation.getTileOperation.RonOperation;
// import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.DiscardTileOperation;
// import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanAndDiscardTileOperation;
// import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.KanOperation;
// import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.NineDifferntTerminalsandHonorsDrawOperation;
// import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.RiichiOperation;
// import yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation.TsumoOperation;
// import yu.proj.jpmahjong.player.operation.robbingKanOperation.ChooseRobbingKanOperation;
// import yu.proj.jpmahjong.player.operation.robbingKanOperation.RobbingKanOperation;
// import yu.proj.jpmahjong.rule.Rule;
// import yu.proj.jpmahjong.tiles.Tile;
// import yu.proj.jpmahjong.tiles.TileType;
// import yu.proj.jpmahjong.tiles.dealer.BaseDealer;
// import yu.proj.jpmahjong.tiles.dealer.TilesDealer;
// import yu.proj.jpmahjong.tiles.initializer.TilesInitializer;
// import yu.proj.jpmahjong.tiles.manager.TilesManager;
// import yu.proj.jpmahjong.tiles.shuffler.TilesShuffler;
//
/// **
// * @ClassName: GameMainLoagic
// *
// * @Description: 整个游戏的核心逻辑，把所有组件搭成后才能使用
// *
// * @author 余定邦
// *
// * @date 2020年9月29日
// *
// */
// public class GameMainLoagic {
//
// /**
// * 玩家们
// */
// private Player[] players;
//
// /**
// * 发牌姬们，分别是牌初始化姬、洗牌姬、牌管理姬
// * 三者共同构成一个完整的发牌姬
// */
// private TilesInitializer tilesInitializer;
// private TilesShuffler tilesShuffler;
// private TilesManager tilesManager;
//
// /**
// * 真发牌姬，发最初的手牌
// */
// private TilesDealer tilesDealer;
//
// /**
// * 循环的轮数，即如东风战就只转1圈，半庄战就转2圈
// */
// private int numberOfCycle;
//
// /**
// * 玩家的数量
// */
// private int playerNumber;
//
// /**
// * 规则设置
// */
// private Rule rule;
//
// public GameMainLoagic(TilesInitializer tilesInitializer, TilesShuffler tilesShuffler, TilesManager tilesManager,
// int numberOfCycle, int playerNumber, Rule rule, UserOperationInterface[] uis) {
// super();
//
// if (uis.length != playerNumber) {
// throw new IllegalArgumentException("ui的数量和playerNumber不匹配");
// }
//
// this.tilesInitializer = tilesInitializer;
// this.tilesShuffler = tilesShuffler;
// this.tilesManager = tilesManager;
//
// this.numberOfCycle = numberOfCycle;
// this.playerNumber = playerNumber;
//
// this.tilesDealer = new BaseDealer(playerNumber);
// this.rule = rule;
//
// this.players = new Player[playerNumber];
//
// for (int i = 0; i < playerNumber; ++i) {
// PlayerInformation info = new PlayerInformation(rule);
// players[i] = new Player(info, uis[i]);
// }
// }
//
// // 内逻辑参数
//
// /**
// * 当前玩家，决定下一个摸牌的玩家是谁
// */
// private int playerNow;
//
// /**
// * 场风，是由当前的场次所决定的，如东风场的场风就是东风
// */
// private TileType prevalentWind;
//
// /**
// * 场上立直棒的数量
// */
// private int riichiStick;
//
// /**
// * 场上本场棒的数量，也表示了几本场，即亲家进行了几次连庄操作
// * 和立直棒不一样，这个本场棒不真的消耗亲家的点棒
// */
// private int dealerStick;
//
// private boolean isDraw; // 流局
//
// private boolean noTileDraw; // 是荒牌流局吗，不是的话那就是中途流局
//
// private boolean isAfterKan;
//
// private boolean dealerWin;
//
// private RiichiDeclear riichiDeclear = null; // 立直宣言
//
// /* 四杠散了流局 */
// private int numberOfKan; // 杠的数量
// private boolean[] kanSrc;// 杠的来源
//
// // 四杠宣言，如果有这个宣言的情况下，加杠不被抢杠，并且杠后打出的牌不被荣和，就算四杠散了
// private boolean fourKanDeclear;
//
// /* 四家立直 */
// /*
// * 可能有的人会想问为什么还需要一个riichiNum来计算立直的数量，直接通过riichiStick来计算不就行了吗
// * riichiStick就是场上的立直棒的数量啊，但是有没有想过如果上局是流局的情况下，立直棒是会留下来的
// * 因此就可能会有4根立直棒，但是却还没有四家立直，因此还需要额外的计数
// */
// private int riichiNum;
//
// /* 四风连打 */
// private int windTileType;// 本场开局打出来第一张牌是风牌时，风牌的类别
// private int windNum;// 计算风牌的数量
// private boolean makeCall;// 在打出4张连续的风时，有没有人鸣牌（基本上特指暗杠和拔北）
//
// // 食替
// private ChiOperation chi;
//
// private boolean overtime;// 是不是南入或者西入了
//
// // 这个类的三人兼容和算分方式的不兼容会产生一定的问题
// public void gameLogic() {
//
// riichiStick = 0;
//
// dealerStick = 0;
//
// overtime = false;
//
// // 比赛要进行几圈，由于可能存在南入、西入规则，因此可能会多一圈
// endFullGame:
// for (int cycle = 1; cycle <= numberOfCycle + 1; ++cycle) {/* 一圈 例如东x局 */
//
// prevalentWind = windIndexMap(cycle);
//
// for (int numberOfGame = 1; numberOfGame <= playerNumber; ++numberOfGame) {/* 一局 例如东二局 */
//
// /* 初始化一局内的参数 */
// isAfterKan = false;
//
// dealerWin = false;
//
// playerNow = numberOfGame - 1;
//
// isDraw = false;
// noTileDraw = false;
//
// numberOfKan = 0;
// kanSrc = new boolean[playerNumber];
// fourKanDeclear = false;
//
// riichiNum = 0;
//
// windTileType = -1;
// windNum = 0;
// makeCall = false;
//
// /* 发牌阶段 */
// /*
// * 这里亲家牌发14张的时候，先发13张，再发最后一张是有原因的
// * 这个项目分析和牌的方法是只分析13张，然后判断是否听牌，听牌的牌型
// * 以及听牌的役，然后再进第14张和牌，因此如果一次直接塞14张到手上
// * 就没办法使用这个分析器了，而重写一个14张牌是否和牌的分析器完全没必要
// * 因为除了天和四暗刻单骑、纯正九莲宝灯和国士无双十三面之外，两种分析方法并无区别
// * 而写一个分析器是很累的。。。你去看看gamelogic.analyze就知道了，
// * 写这个注释的时候已经有3400多行代码了，基本上占据项目一半的复杂度
// *
// * 对于上面的特殊情况，只需要特殊判断一下就可以了，同时也加上一个特殊的规则
// * 就是是不是严格要求天和这几种牌型的第14张牌
// */
// Tile[][] tiles = tilesDealer.dealTile();
//
// Tile d14 = tiles[0][13]; // 亲家的第14张牌要额外处理
//
// tiles[0] = Arrays.copyOf(tiles[0], 13);// 亲家的牌也只当作13张来处理
//
// for (int i = 0; i < playerNumber; ++i) {// 初始化手牌和自风
// players[(playerNow + i) % playerNumber].reset(tiles[i], prevalentWind, windIndexMap(i + 1), i == 0);
// }
//
// // 给亲家补上第14张牌
// players[playerNow].executeGetTileOperation(d14, TileSource.TILE_WALL, new DrawOperation());
//
//
// endOfGame:
// while (true) {// 每一个循环都是一个玩家切牌-下一个玩家得牌的过程
//
// Tile discardTile = null;// 每轮切出来的牌
//
// while (true) {/* 切牌阶段 */
//
// // 如果还没有开四杠或者规则允许开五杠，那么才能开杠
// boolean kanable = numberOfKan < 4 || rule.enableFiveKan;
//
// boolean moreThanFourTileToDraw = tilesManager.tileToDrawNum() >= 4;
//
// KanAndDiscardTileOperation op =
// players[playerNow].getUserKanAndDiscardTileOperationChoice(tilesManager.isDraw(),
// isAfterKan, kanable, moreThanFourTileToDraw, chi);
//
// // 真的切牌了，那么就退出
// if (op instanceof RiichiOperation || op instanceof DiscardTileOperation) {
// /*
// * 在这里是不能执行execKDOP(playerNow, op)和++riichiStick的
// * 因为这里还没有真正的立直成功，因此不能直接扣除玩家的分数和多一根立直棒
// * 因此要等下一个玩家选择不荣和的情况下，才能够立直完成
// */
// if (op instanceof RiichiOperation) {// 立直了的话，立直棒加一
// discardTile = ((RiichiOperation)op).getTileToDiscard();
// announceOperation(op);
//
// riichiDeclear = new RiichiDeclear(playerNow, (RiichiOperation)op);
// } else {
// discardTile = ((DiscardTileOperation)op).getTileToDiscard();
// execKDOP(playerNow, op);
// }
//
// int discardTileIndex = CountNum.getTileIndex(discardTile);
//
// /* 四风连打 */
// /*
// * 在这里检查四风连打，这是由于我们无需到判断荣和之后去检查，直接在打牌处就可以检查
// * 这是因为由于打出的是一样的风，前面打牌的玩家是不可能荣和后家的牌的，这是由于舍张振听规则的存在
// * 但是规则内也可以关闭舍张振听，因此也有可能出现打出四张同样的风之后不能和的情况
// * 不过这个概率太低了，忽略不计吧，而且这种骗牌的人可恶不可恶，不给和
// */
// if (windTileType == -1) {
// if (discardTileIndex == CountNum.E || discardTileIndex == CountNum.S
// || discardTileIndex == CountNum.W || discardTileIndex == CountNum.N) {// 第一张是风牌
// windTileType = discardTileIndex;
// ++windNum;
// } else {
// windTileType = -2;// 设置为失效，即四风连打已经不可能了
// }
// } else if (windTileType == discardTileIndex) {// 连续打出同样的风
// ++windNum;
// if (windNum == 4 && !makeCall) {// 在无人鸣牌的情况下四风连打，就四风连打流局，是中途流局
// isDraw = true;
// noTileDraw = false;
//
// break endOfGame;
// }
// } else {// windTileType != discardTileIndex 打出的牌不是那张风牌了
// windTileType = -2;
// }
//
// break;
// }
//
// // 杠了，那么还要继续进行下一个切牌阶段
// if (op instanceof KanOperation) {
//
// int kanTile = ((KanOperation)op).getKanTile();
//
// /* 抢杠 */
// List<OPNode<ChooseRobbingKanOperation>> robbingKans = new ArrayList<>();
//
// for (int i = 1; i < playerNumber; ++i) {// i从1开始表示打牌的那个人不允许抢自己的杠
//
// // 获取玩家的编号
// int n = (playerNow + i) % playerNumber;
//
// RobbingKanOperation rkop =
// players[n].getUserRobbingKanOperationChoice(kanTile, (KanOperation)op);
//
// if (rkop instanceof ChooseRobbingKanOperation) {// 选择抢杠，那么和荣和一样，本局游戏结束
// robbingKans.add(new OPNode<ChooseRobbingKanOperation>(n,
// (ChooseRobbingKanOperation)rkop, players[n].isDealer(), i));
//
// if (rule.enableFirstWinnerWin) {// 只允许一家抢杠
// break;
// }
// }
// }
//
// if (rule.enableThreeRonDraw && robbingKans.size() == 3) {// 三家荣和流局
// isDraw = true;
// noTileDraw = false;
//
// break endOfGame;
//
// } else if (!robbingKans.isEmpty()) {// 有人抢杠，结束对局
//
// for (OPNode<ChooseRobbingKanOperation> pair : robbingKans) {
// boolean first = true;
//
// if (hasResponsibility(pair.op.getRobbingKan().getTenpaiAnsNode())) {// 要包牌的话，要特殊处理
// takeResponsibilityForRonOrRobbingKan(pair.indexOfPlayer,
// pair.op.getRobbingKan().getTenpaiAnsNode(), first);
// } else {
// robbingKanCountPoint(pair.indexOfPlayer, pair.op, first);
// }
//
// dealerWin = dealerWin || pair.isDealer;// 赢者有人是亲家
// first = false;
// }
//
// if (checkEndFullGame()) {// 快速死亡规则，直接结束整场游戏
// break endFullGame;
// }
//
// break endOfGame;// 抢杠，一局游戏结束
// }
//
//
// // 检查四杠散了流局和五杠流局，是五杠流局就直接结束比赛
// if (checkFourKanDraw()) {
// break endOfGame;
// }
//
// Tile kanDraw = tilesManager.kan();
//
// // 设置杠后的摸牌
// ((KanOperation)op).setKanDraw(kanDraw);
//
// // 先进行抢杠，再执行操作，如果抢杠了，那么加杠、拔北、暗杠是不成立的
// execKDOP(playerNow, op);
//
// isAfterKan = true;
//
// // 这个操作也必须在抢杠之后，因为如果有人抢杠，那么是可以一发和的
// // 因为抢杠意味着这个杠不成立，因此不算鸣牌
// // 但是如果先执行这个操作，那么就破除了别人的一发，是不合理的
// makeCallOrConcealedKan(playerNow);
//
// continue;
// }
//
// // 自摸了
// if (op instanceof TsumoOperation) {
//
// if (hasResponsibility(((TsumoOperation)op).getTsumo().getTenpaiAnsNode())) {// 要包牌的话，要特殊处理
// takeResponsibilityForTsumo((TsumoOperation)op);
// } else {
// tsumoCountPoint((TsumoOperation)op);
// }
//
// dealerWin = players[playerNow].isDealer();// 赢者是亲家
//
// if (checkEndFullGame()) {// 快速死亡规则，直接结束整场游戏
// break endFullGame;
// }
//
// break endOfGame;// 自摸，一局游戏结束
// }
// }
//
// if (tilesManager.isDraw()) {// 无牌可摸流局
//
// List<OPNode<RonOperation>> rons = new ArrayList<>();
//
// // 判断是否有人能够荣和，进行河底摸鱼
// for (int i = 1; i < playerNumber; ++i) {// i从1开始表示打牌的那个人不允许和自己的牌
//
// // 获取玩家的编号
// int n = (playerNow + i) % playerNumber;
//
// GetTileOperation op =
// players[n].getUserGetTileOperationChoice(discardTile, true, indexLocMap(i), false);
//
// if (op instanceof RonOperation) {// 河底牌的操作中，只有荣和成立
// rons.add(new OPNode<RonOperation>(n, (RonOperation)op, players[n].isDealer(), i));
//
// if (rule.enableFirstWinnerWin) {// 只允许一家荣和
// break;
// }
// }
// }
//
// if (rule.enableThreeRonDraw && rons.size() == 3) {// 三家荣和流局
// isDraw = true;
// noTileDraw = false;
// } else {
// if (!rons.isEmpty()) {// 有人荣和的情况
// boolean first = true;
// for (OPNode<RonOperation> pair : rons) {
// if (hasResponsibility(pair.op.getTenpaiAnsNode())) {// 要包牌的话，要特殊处理
// takeResponsibilityForRonOrRobbingKan(pair.indexOfPlayer,
// pair.op.getTenpaiAnsNode(), first);
// } else {
// ronCountPoint(pair.indexOfPlayer, pair.op, first);
// }
// dealerWin = dealerWin || pair.isDealer;// 赢者有人是亲家
// announceOperation(pair.op);
// first = false;
// }
// } else {
//
// /*
// * 这里是有可能出现最后一杠杠完后，牌山为空的
// * 这种情况出现在牌山只有一张牌时，一个玩家杠了
// * 并且杠完之后打出的牌没有放铳，而且还是第四个杠子
// * 那么优先认为是四杠散了，而不是荒牌流局
// */
// if (fourKanDeclear) {// 四杠散了流局
// isDraw = true;
// noTileDraw = false;
//
// } else {// 荒牌流局
// isDraw = true;
// noTileDraw = true;
// }
// }
// }
//
// if (checkEndFullGame()) {// 快速死亡规则，直接结束整场游戏
// break endFullGame;
// }
//
// break endOfGame;// 不论有没有荣和，这局都结束了
// }
//
// /* 集中竞价阶段 */
//
// List<OPNode<GetTileOperation>> ops = new ArrayList<>();
//
// /*
// * 竞价的逻辑是这样的
// * 每个玩家各出一个GetTileOperation，而各个GetTileOperation之间是有大小差分的
// * RonOperation > ExposedKanOperation > PonOperation > ChiOperation > DrawOperation > null
// * 当出现一个优先级高的GetTileOperation时，就把优先级低的操作给移除掉
// * 而出现同级别的GetTileOperation时，就将其也加入到列表中
// * 出现低级别的GetTileOperation，就无需做任何操作
// *
// * 为什么要将同级别的GetTileOperation都加入到列表中呢
// * 这是因为可能出现多个RonOperation，即多家荣和的情况，但是出现多家吃碰杠是不可能的
// * 并且至少有一个DrawOperation，保证一定比null大，因此不可能出现列表中只有null的情况
// */
// for (int i = 1; i < playerNumber; ++i) {// i从1开始表示打牌的那个人不允许参与集中竞价
//
// // 获取玩家的编号
// int n = (playerNow + i) % playerNumber;
//
// // 如果还没有开四杠或者规则允许开五杠，那么才能开杠
// boolean kanable = numberOfKan < 4 || rule.enableFiveKan;
//
// GetTileOperation op =
// players[n].getUserGetTileOperationChoice(discardTile, false, indexLocMap(i), kanable);
//
// if (ops.isEmpty()) {
// ops.add(new OPNode<GetTileOperation>(n, op, players[n].isDealer(), i));
// } else {
// int comp = GetTileOperation.compare(ops.get(0).op, op);
// if (comp < 0) {
// ops.clear();
// ops.add(new OPNode<GetTileOperation>(n, op, players[n].isDealer(), i));
//
// } else if (comp == 0) {
// ops.add(new OPNode<GetTileOperation>(n, op, players[n].isDealer(), i));
// }
// }
// }
//
// // 这个是对于每个玩家来说的，因此切换到下一个玩家的时候，就要重置
// isAfterKan = false;
//
// GetTileOperation op = ops.get(0).op;// 获取一个操作，看看是什么操作
//
// if (op instanceof RonOperation) {// 如果是荣和
//
// if (rule.enableThreeRonDraw && ops.size() == 3 && !rule.enableFirstWinnerWin) {// 三家荣和流局
// isDraw = true;
// noTileDraw = false;
// } else {
// boolean first = true;
// for (OPNode<GetTileOperation> pair : ops) {
// if (hasResponsibility(((RonOperation)pair.op).getTenpaiAnsNode())) {// 要包牌的话，要特殊处理
// takeResponsibilityForRonOrRobbingKan(pair.indexOfPlayer,
// ((RonOperation)pair.op).getTenpaiAnsNode(), first);
// } else {
// ronCountPoint(pair.indexOfPlayer, (RonOperation)pair.op, first);
// }
// announceOperation(pair.op);
// dealerWin = dealerWin || pair.isDealer;// 赢者有人是亲家
// first = false;
// if (rule.enableFirstWinnerWin) {// 头跳
// break;
// }
// }
// }
//
// if (checkEndFullGame()) {// 快速死亡规则，直接结束整场游戏
// break endFullGame;
// }
//
// break endOfGame;// 荣和，一局游戏结束
// } else if (!(op instanceof NineDifferntTerminalsandHonorsDrawOperation)) {// 对于吃碰杠和摸牌，都是执行op这样处理就行了
//
// // 对于成功立直的玩家，就需要补充刚刚没有进行的立直操作
// if (riichiDeclear != null) {
// ++riichiStick;
// ++riichiNum;
// players[riichiDeclear.indexOfPlayer].executeKanAndDiscardTileOperation(riichiDeclear.op);
//
// riichiDeclear = null;// 处理完就清空掉，防止反复处理同一个立直宣言
//
// if (riichiNum == 4) {// 四家立直的情况，中途流局
// isDraw = true;
// noTileDraw = false;
//
// break endOfGame;
// }
// }
//
// if (fourKanDeclear) {// 没人选择荣和第四个杠所打出的牌的话，就四杠散了流局了，四杠散了流局也是中途流局
// isDraw = true;
// noTileDraw = false;
//
// break endOfGame;
// }
//
// OPNode<GetTileOperation> pair = ops.get(0);// 非荣和的操作一定只有一个
//
// // 对于不是摸牌也不是荣和的情况来说，那就一定是鸣牌，因为吃碰杠都是鸣牌
// // 而鸣牌就会破除一发
// if (!(pair.op instanceof DrawOperation)) {
// makeCallOrConcealedKan(pair.indexOfPlayer);
// players[playerNow].setDiscardTileBeenMadeCall(true);// 自己被别人鸣牌了
// }
//
// if (op instanceof ExposedKanOperation) {// 杠的话还要补一张牌
// isAfterKan = true;
//
// Tile kanDraw = tilesManager.kan();
// ((ExposedKanOperation)pair.op).setKanDraw(kanDraw);
//
// // 检查四杠散了流局和五杠流局，是五杠流局就直接结束比赛
// if (checkFourKanDraw()) {
// break endOfGame;
// }
// }
//
// if (op instanceof ChiOperation) {
// chi = (ChiOperation)op;
// } else {
// chi = null;
// }
//
// execGTOP(pair.indexOfPlayer, discardTile, indexSrcMap(pair.bias), op);
//
// // 竞价成功的人称为下一个切牌的玩家
// // 同时出现多个同样大小的竞价的情况只有荣和
// // 对于荣和来说playerNow就没有什么意义
// // 因为还没到下一个切牌阶段游戏就结束了
// playerNow = pair.indexOfPlayer;
//
// } else {// 九种九牌流局，中途流局
// isDraw = true;
// noTileDraw = false;
//
// break endOfGame;
// }
//
// riichiDeclear = null;
// }
//
// /* 终局阶段 */
//
// boolean dealerContinue;
//
// boolean clearDealerStick;
//
// boolean clearRiichiStick;
//
// if (isDraw) {
// if (noTileDraw) {// 荒牌流局，还需要额外的罚符阶段
//
// boolean manganAtDraw = false;
//
// /* 流局满贯 */
// for (int i = 0; i < playerNumber; ++i) {
// if (players[i].isManganAtDraw()) {
//
// manganAtDraw = true;
//
// if (players[i].isDealer()) {
// players[i].updatePoint(12000);
// for (int j = 0; j < playerNumber; ++j) {
// if (i != j) {
// players[j].updatePoint(-4000);
// }
// }
// } else {
// players[i].updatePoint(8000);
// for (int j = 0; j < playerNumber; ++j) {
// if (i != j) {
// if (players[j].isDealer()) {
// players[j].updatePoint(-4000);
// } else {
// players[j].updatePoint(-2000);
// }
// }
// }
// }
// }
// }
//
// if (!manganAtDraw) {// 流局满贯就不罚符了
//
// /* 罚符阶段 */
// List<Integer> tenpai = new ArrayList<>();
// List<Integer> noten = new ArrayList<>();
//
// for (int i = 0; i < playerNumber; ++i) {// 统计听牌和不听者的数量
// if (players[i].isTenpai()) {
// tenpai.add(i);
// } else {
// noten.add(i);
// }
// }
//
// if (!(tenpai.isEmpty() || noten.isEmpty())) {// 不是全部听牌或者全不听牌
// int totalPointToPunish = playerNumber == 3 ? 2000 : 3000;
//
// for (int n : tenpai) {// 听牌者赚取听牌料
// players[n].updatePoint(totalPointToPunish / tenpai.size());
// }
//
// for (int n : noten) {// 不听者被罚符
// players[n].updatePoint(-totalPointToPunish / noten.size());
// }
// }
// }
//
// if (checkEndFullGame()) {// 快速死亡规则，直接结束整场游戏
// break endFullGame;
// }
//
// if (players[playerNow].isTenpai()) {// 荒牌流局的情况下，亲家听牌
// dealerContinue = true;
// clearDealerStick = false;
// clearRiichiStick = false;
//
// } else {// 亲家没听的情况下，庄流给下一家，本场数继续累计
// dealerContinue = false;
// clearDealerStick = false;
// clearRiichiStick = false;
// }
// } else {// 中途流局
// dealerContinue = true;
// clearDealerStick = false;
// clearRiichiStick = false;
// }
// } else if (dealerWin) {// 没流局且亲家赢了
// dealerContinue = true;
// clearDealerStick = false;
// clearRiichiStick = true;
// } else {// 没流局且亲家没赢
// dealerContinue = false;
// clearDealerStick = true;
// clearRiichiStick = true;
// }
//
// // 如果亲家连庄，那么局数减一，由于循环内会加一，因此局数保持不变
// if (dealerContinue) {
// --numberOfGame;
// }
//
// if (clearDealerStick) {// 清空本场棒
// dealerStick = 0;
// } else {// 本场棒不清空的情况下，就是加一本场棒
// ++dealerStick;
// }
//
// if (clearRiichiStick) {// 一场有人赢的比赛结束之后就要清空立直棒
// riichiStick = 0;
// }
// }
//
// if (cycle == numberOfCycle) {// 考虑南入、西入规则
//
// if (rule.enableEnoughPointToFinishGame) {
//
// overtime = true;
//
// boolean flag = false;
//
// for (int i = 0; i < playerNumber; ++i) {
// if (players[i].getPoint() >= rule.minPointToFinishGame) {
// flag = true;
// break;
// }
// }
//
// if (!flag) {
// continue;
// }
// }
//
// // 不需要最低分数即可完场或者有一家大于最低完场分数
// break;
// }
// }
// }
//
// /**
// *
// * @ClassName: OPNode
// *
// * @Description: 存储各玩家给出的操作以及与其相关的信息
// *
// * @author 余定邦
// *
// * @date 2020年10月8日
// *
// * @param <E> 玩家操作的类型
// */
// private static class OPNode<E> {
// int indexOfPlayer;
// E op;
// boolean isDealer;
// int bias;
//
// public OPNode(int indexOfPlayer, E op, boolean isDealer, int bias) {
// super();
// this.indexOfPlayer = indexOfPlayer;
// this.op = op;
// this.isDealer = isDealer;
// this.bias = bias;
// }
// }
//
// /**
// *
// * @ClassName: RiichiDeclear
// *
// * @Description: 立直宣告
// *
// * @author 余定邦
// *
// * @date 2020年10月8日
// *
// */
// private static class RiichiDeclear {
// int indexOfPlayer;
// RiichiOperation op;
//
// public RiichiDeclear(int indexOfPlayer, RiichiOperation op) {
// super();
// this.indexOfPlayer = indexOfPlayer;
// this.op = op;
// }
// }
//
// /**
// *
// * @Title: hasResponsibility
// *
// * @Description: 是否存在包牌的责任
// *
// * @param node
// *
// * @return 是否存在包牌的责任
// *
// */
// private boolean hasResponsibility(TenpaiAnsNode node) {
// YakuAns yakuAns = node.getYakuAns();
// return yakuAns.getBigWindSource() != null || yakuAns.getFourQuadSource() != null
// || yakuAns.getThreeDragonSource() != null;
// }
//
// private void takeResponsibilityForTsumo(TsumoOperation op) {
// // 更新宝牌数量
// updateDora(playerNow, op.getTsumo().getTenpaiAnsNode());
//
// TenpaiAnsNode node = op.getTsumo().getTenpaiAnsNode();
//
// List<TileSource> responsibility = getTileSource(node);
//
// /*
// * 自摸包牌的规则大致是这样的，如果是只和一个役满，且这个役满需要包牌的情况下
// * 那么本场棒对应的本场费将**由包牌者全付**
// *
// * 但是如果出现了两个役满以上的情况下，那么本场费就会变成**大家均摊**的情况
// *
// * 只要出现了两个役满，哪怕这两个役满都需要包牌（例如出现了一个大四喜，一个四杠子）的情况
// * 那么就一定要大家均摊本场费，哪怕一家包一个役满，有一家是完全无辜的，但是他也要出本场费
// * 但是，这点本场费。。。对于要赔役满的另外两家来说，是不是有点小？
// *
// * 但是明确这些规则还是比较重要的，毕竟规则是死的，总得确定下来
// */
//
// // 赢家自己是不影响的
// players[playerNow].tsumoAddPoint(op, riichiStick, dealerStick);
//
// int yakumanNum = node.getYakuman();
//
// if (yakumanNum == 1) {// 包牌者全包
// int point = 0;
//
// if (players[playerNow].isDealer()) {// 一个役满的分数
// point += 48000;
// } else {
// point += 32000;
// }
//
// point += +riichiStick * Numbers.RIICHI_STICK_POINT + dealerStick * Numbers.DEALER_STICK_POINT * 3;
//
// int playerTakeResponsibility = playerTakeResponsibility(responsibility.get(0));
//
// players[playerTakeResponsibility].updatePoint(-point);
//
// } else {// 有两个役满以上的情况
//
// if (players[playerNow].isDealer()) {
//
// int otherYakuman = yakumanNum - responsibility.size();// 计算不用包牌的役满的数量
//
// for (int i = 0; i < playerNumber; ++i) {// 先赔不包牌的役满的分数和本场棒的分数
// if (i != playerNow) {
// players[i].updatePoint(-16000 * otherYakuman);
// players[i].updatePoint(-100 * dealerStick);
// }
// }
//
// for (int i = 0; i < responsibility.size(); ++i) {// 再赔包牌的分数
// int playerTakeResponsibility = playerTakeResponsibility(responsibility.get(i));
// players[playerTakeResponsibility].updatePoint(-48000);
// }
//
// } else {
//
// int otherYakuman = yakumanNum - responsibility.size();// 计算不用包牌的役满的数量
//
// for (int i = 0; i < playerNumber; ++i) {// 先赔不包牌的役满的分数和本场棒的分数
// if (i != playerNow) {
// if (players[i].isDealer()) {
// players[i].updatePoint(-16000 * otherYakuman);
// } else {
// players[i].updatePoint(-8000 * otherYakuman);
// }
// players[i].updatePoint(-100 * dealerStick);
// }
// }
//
// for (int i = 0; i < responsibility.size(); ++i) {// 再赔包牌的分数
// int playerTakeResponsibility = playerTakeResponsibility(responsibility.get(i));
// players[playerTakeResponsibility].updatePoint(-32000);
// }
// }
// }
// }
//
// public void takeResponsibilityForRonOrRobbingKan(int playerWin, TenpaiAnsNode node, boolean firstWinner) {
// // 更新宝牌数量
// updateDora(playerNow, node);
//
// List<TileSource> responsibility = getTileSource(node);
//
// /*
// * 荣的话就不是很好分摊本场棒了，可能会出现这样一种情况
// * 就是1本场的时候，赢家和了一个例如说大三元
// * 一家放铳，一家包牌，那么分数理论上应当由两家分摊
// * 但是规则中对本场棒的分数就比较模糊，没有明确到底应该谁付
// * 还是一人一半，但是这个时候，如果一人一半就会出现一个问题
// * 那就是一个人应该付150点，因为是3根棒子，一人一根半
// * 但是这样又每人至少罚200点了，因为最小是100点
// * 所以不太合理，不如就**只罚放铳那家了**
// * 不过说真的，放铳了一个役满，真的会在意那点本场棒吗。。。
// */
//
// int point = node.getTotalPoint();
//
// if (firstWinner) {// 头跳家才能吃到立直棒和本场棒的分数
// point += riichiStick * RIICHI_STICK_POINT + dealerStick * DEALER_STICK_POINT * 3;
// }
//
// // 先按一般算法扣分
// players[playerWin].updatePoint(point);
// players[playerNow].updatePoint(-point);
//
// for (int i = 0; i < responsibility.size(); ++i) {// 给放铳者补分，给包牌者扣半个役满的分数
// int playerTakeResponsibility = playerTakeResponsibility(responsibility.get(i));
// if (players[playerWin].isDealer()) {
// players[playerNow].updatePoint(24000);
// players[playerTakeResponsibility].updatePoint(-24000);
// } else {
// players[playerNow].updatePoint(16000);
// players[playerTakeResponsibility].updatePoint(-16000);
// }
// }
// }
//
// public List<TileSource> getTileSource(TenpaiAnsNode node) {
// List<TileSource> responsibility = new ArrayList<>();
//
// if (node.getYakuAns().getBigWindSource() != null) {
// responsibility.add(node.getYakuAns().getBigWindSource());
// }
// if (node.getYakuAns().getFourQuadSource() != null) {
// responsibility.add(node.getYakuAns().getFourQuadSource());
// }
// if (node.getYakuAns().getThreeDragonSource() != null) {
// responsibility.add(node.getYakuAns().getThreeDragonSource());
// }
//
// return responsibility;
// }
//
// private int playerTakeResponsibility(TileSource src) {
// int index = playerNow;
// switch (src) {
// case NEXT_PLAYER:
// index += 1;
// break;
// case OPPOSED_PLAYER:
// index += 2;
// break;
// case LAST_PLAYER:
// index += 3;
// break;
// default:
// break;
// }
// return index % playerNumber;
// }
//
// /**
// *
// * @Title: makeCallOrConcealedKan
// *
// * @Description: 当自己鸣牌的时候，破除别人的一发和地和
// * 为什么不破除自己的一发呢，那是因为自己一发的破除还有些区别
// * 比如我立直之后获得一发，但是我又打出了一张牌，这个时候如果又破除自己的一发
// * 就相当于用于没可能有一发了，这是不合理的，因此影响自己一发和地和的情况在内部逻辑额外处理
// * 而不在这里统一处理
// *
// * @param self
// *
// * @throws
// *
// */
// private void makeCallOrConcealedKan(int self) {
// makeCall = true;// 这个makecall是用于检测四风连打的
// for (int i = 0; i < playerNumber; ++i) {
// if (i != self) {
// players[i].otherPlayerMakeCallOrConcealedKan();
// }
// }
// }
//
// private void updateDora(int playerNow, TenpaiAnsNode node) {
// players[playerNow].updateDora(tilesManager.doraIndicator(),
// players[playerNow].isRiichi() ? tilesManager.uraDoraIndicator() : new Tile[0], node);
// }
//
// /**
// *
// * @Title: tsumoCountPoint
// *
// * @Description: 自摸更新分数
// *
// * @param playerNow
// * @param op
// *
// * @throws
// *
// */
// private void tsumoCountPoint(TsumoOperation op) {
//
// // 更新宝牌数量
// updateDora(playerNow, op.getTsumo().getTenpaiAnsNode());
//
// players[playerNow].tsumoAddPoint(op, riichiStick, dealerStick);
// for (int i = 0; i < playerNumber; ++i) {
// if (i != playerNow) {
// players[i].tsumoMinusPoint(op, riichiStick, dealerStick);
// }
// }
// }
//
// /**
// *
// * @Title: robbingKanCountPoint
// *
// * @Description: 抢杠更新分数
// *
// * @param playerNow
// * @param op
// * @param firstWinner 由于第一个赢的才能吃到立直棒，不是第一个赢的就没有立直棒的分数
// *
// * @throws
// *
// */
// private void robbingKanCountPoint(int playerWin, ChooseRobbingKanOperation op, boolean firstWinner) {
//
// // 更新宝牌数量
// updateDora(playerNow, op.getRobbingKan().getTenpaiAnsNode());
//
// players[playerWin].robbingKanAddPoint(op, firstWinner ? riichiStick : 0, firstWinner ? dealerStick : 0);
// players[playerNow].robbingKanMinusPoint(op, firstWinner ? riichiStick : 0, firstWinner ? dealerStick : 0);
// }
//
// /**
// *
// * @Title: ronCountPoint
// *
// * @Description: 荣和更新分数
// *
// * @param playerNow
// * @param op
// * @param firstWinner 由于第一个赢的才能吃到立直棒，不是第一个赢的就没有立直棒的分数
// *
// * @throws
// *
// */
// private void ronCountPoint(int playerWin, RonOperation op, boolean firstWinner) {
//
// // 更新宝牌数量
// updateDora(playerNow, op.getTenpaiAnsNode());
//
// players[playerWin].ronAddPoint(op, firstWinner ? riichiStick : 0, firstWinner ? dealerStick : 0);
// players[playerNow].ronMinusPoint(op, firstWinner ? riichiStick : 0, firstWinner ? dealerStick : 0);
// }
//
// /**
// *
// * @Title: checkEndFullGame
// *
// * @Description: 击飞和南入西入后的快速死亡规则
// *
// * @param overtime 是不是已经南入或者西入了
// *
// * @throws
// *
// */
// private boolean checkEndFullGame() {
// if (rule.enableMinPoint) {// 开启最小击飞线
// for (int i = 0; i < playerNow; ++i) {
// if (players[i].getPoint() < rule.minPoint) {
// return true;// 被击飞啦
// }
// }
// }
// if (rule.enableMaxPoint) {// 最大天边
// for (int i = 0; i < playerNow; ++i) {
// if (players[i].getPoint() > rule.maxPoint) {
// return true;// 超出天边了
// }
// }
// }
// if (overtime) {
// for (int i = 0; i < playerNow; ++i) {
// if (players[i].getPoint() > rule.minPointToFinishGame) {
// if (!rule.enableDealerContinueFirst) {// 在没有连庄优先的情况下
// return true;// 超出完场分数，比赛结束
// } else {
// if (!dealerWin) {// 如果庄家赢了，那就继续连庄
// return true;
// }
//
// int max = Integer.MIN_VALUE;
// boolean isDealer = false;
//
// for (int j = 0; j < playerNow; ++j) {
// if (players[i].getPoint() > max) {
// if (players[i].isDealer()) {
// isDealer = true;
// break;
// }
// }
// }
//
// if (isDealer) {// 庄家一位，那么庄家在可以连庄的时候也会选择结束比赛啦
// return true;
// }
// }
// }
// }
// }
//
// return false;
// }
//
// /**
// *
// * @Title: checkFourKanDraw
// *
// * @Description: 用于检查四杠散了流局的情况的代码
// *
// * @return 是否已经流局，如果流局就返回true
// *
// * @throws
// *
// */
// private boolean checkFourKanDraw() {
// if (numberOfKan == 4) {// 已经开了四个杠子了
//
// // 不允许开杠的情况下不会进到这个逻辑
// // 因此rule.enableFiveKan一定为true
// // 即当前为允许开五次杠且加杠、暗杠的牌没有放铳
//
// // 这样也算是中途流局的一种
// isDraw = true;
// noTileDraw = false;
//
// return true;
// } else if (numberOfKan == 3) {// 已经杠了三次，意味着这是第四个杠
// ++numberOfKan;
// kanSrc[playerNow] = true;
//
// // 检查是不是同一个人开杠
//
// boolean flag = false;
// for (int i = 0; i < playerNumber; ++i) {
// if (flag && kanSrc[playerNow]) {// 多人开四杠
//
// if (rule.enableFourKanDraw) {// 四杠散了流局
//
// // 等到切牌之后还没有被荣的情况下，四杠散了流局，这里只是先进行标记
// fourKanDeclear = true;
// }
//
// break;
// }
// }
//
// // 单人开四杠或者没有开启四杠散了流局的情况，不需要进行特别的处理，因为不会引起四杠散了流局
//
// } else {// 杠的数量加上这次都不足4次，没什么特殊的
// ++numberOfKan;
// kanSrc[playerNow] = true;
// }
//
// return false;
// }
//
// private void execKDOP(int playerNow, KanAndDiscardTileOperation op) {
// players[playerNow].executeKanAndDiscardTileOperation(op);
// announceOperation(op);
// }
//
// private void execGTOP(int playerNow, Tile tile, TileSource tileSource, GetTileOperation op) {
// players[playerNow].executeGetTileOperation(tile, tileSource, op);
// announceOperation(op);
// }
//
// private void announceOperation(Operation op) {
// for (int i = 0; i < playerNow; ++i) {
// TileSource player = indexSrcMap(i);
// players[(playerNow + i) % playerNow].announceOperation(player, op);
// }
// }
//
// /**
// *
// * @Title: indexLocMap
// *
// * @Description: 对于操作者自己而言的位置
// *
// * @param index
// * @return
// *
// * @throws
// *
// */
// private TileSource indexLocMap(int index) {
// switch (index) {
// case 1:
// return TileSource.NEXT_PLAYER;
// case 2:
// return TileSource.OPPOSED_PLAYER;
// case 3:
// return TileSource.LAST_PLAYER;
// default:
// return TileSource.SELF;
// }
// }
//
// /**
// *
// * @Title: indexSrcMap
// *
// * @Description: 对于非操作者的其他家的 操作者 的相对位置
// *
// * @param index
// * @return
// *
// * @throws
// *
// */
// private TileSource indexSrcMap(int index) {
// switch (index) {
// case 1:
// return TileSource.LAST_PLAYER;
// case 2:
// return TileSource.OPPOSED_PLAYER;
// case 3:
// return TileSource.NEXT_PLAYER;
// default:
// return TileSource.SELF;
// }
// }
//
// private TileType windIndexMap(int index) {
// switch (index) {
// case 1:
// return TileType.WIND_EAST;
// case 2:
// return TileType.WIND_SOUTH;
// case 3:
// return TileType.WIND_WEST;
// case 4:
// return TileType.WIND_NORTH;
// case 5:
// return TileType.WIND_EAST;
// }
// return null;
// }
//
// }
