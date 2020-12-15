// package yu.proj.jpmahjong.testUtil;
//
// import yu.proj.jpmahjong.rule.Rule;
//
/// **
// * @ClassName: RuleFactory
// *
// * @Description: TODO(这里用一句话描述这个类的作用)
// *
// * @author 余定邦
// *
// * @date 2020年9月19日
// *
// */
// public class RuleFactory {
//
// /**
// * 是否开启大三元的包牌规则
// */
// public boolean enableResponsibilityForBigThreeDragon;
//
// /**
// * 是否开启大四喜的包牌规则
// */
// public boolean enableResponsibilityForFourBigWind;
//
// /**
// * 是否开启四杠子的包牌规则
// */
// public boolean enableResponsibilityForFourQuads;
//
// /**
// * 就是有役满的时候，是不是还计算宝牌的累计役满，其余的役还是不算
// */
// public boolean enableCountDoraWhenHasYaukuman;
//
// /**
// * 是否认为累计役满是13番封顶，如果认为，那么无论有多少番，那么都是记为一个累计役满
// * 如果不认为封顶，那么每13番记作一个累计役满，即26番记作双倍累计役满，以此类推
// */
// public boolean enable13HanCeilingOfAddUpYakuman;
//
// /**
// * 完成绿一色的时候，是否需要发
// */
// public boolean enableNeedGreenDragonAsAllGreen;
//
// /**
// * 是否认可纯正绿一色，和上面的规则enableNeedGreenDragonAsAllGreen不能同时为true TODO
// */
// public boolean enableTrueAllGreen;
//
// /**
// * 即食断，如果可以食断，那么就当非门清的时候，也认可断幺九，如果不可食断
// * 那么非门清的时候，不计断幺九
// */
// public boolean enableDanyaoAsYakuWhenMakeCall;
//
// /**
// * 即后付，如果可以后付，那么即使听牌的牌型只有1种有役那么就可以和那张有役的牌型
// * 但是无后付不一样，如果听牌的牌型中有一种没有役，那么就不能和，即使你在听别的有役的牌型
// */
// public boolean enableNotAllTileToWinHasYakuCanWin;
//
// /**
// * 一色四通顺能被记作二杯口吗，还是只记作一杯口
// */
// public boolean enablePureFourSequenceAsTwicePureDoubleSequence;
//
// /**
// * 是否承认虚听算是听牌，虚听即手上拿着4张一样的牌，最后还听这张牌，这被称为虚听
// *
// * 而如果手上听的牌没有役或者已经在场上没有了，那么叫做形式听牌，虽然也不是真的听牌
// * 但是在流局的时候不罚符
// */
// public boolean enableNotRealTenpai;
//
// /**
// * 连风的雀头记作4符吗，还是2符
// */
// public boolean enablePrevalentWindAndseatWindPairCnoutAs4Fu;
//
// /**
// * 是否开启切上满贯，即30符4翻、60符3翻记作满贯
// */
// public boolean enableCeilToMangan;
//
// /**
// * 设置最小天边，如果开启这个规则且有1名玩家低于这个分数，那么比赛结束
// */
// public boolean enableMinPoint;
//
// public int minPoint;
//
// /**
// * 设置最大天边，如果开启这个规则且有1名玩家高于这个分数，那么比赛结束
// */
// public boolean enableMaxPoint;
//
// public int maxPoint;
//
// /**
// * 是否开启头朓，即只有离放铳家最近的那个玩家能够和牌
// */
// public boolean enbaleFirstWinnerWin;
//
// /**
// * 是否开启四风连打流局规则
// */
// public boolean enableFourContinueWindDraw;
//
// /**
// * 是否开启四杠散了，如果不开启，那么四杠之后根据是否允许五杠的规则继续比赛
// */
// public boolean enableFourKanDraw;
//
// /**
// * 是否允许杠第五次，如果允许，那么第五次杠之后，如果不是加杠放铳，那么不摸岭上牌
// * 直接流局，如果不允许第五次杠，那么开了四次杠之后不允许再杠牌
// */
// public boolean enableFiveKan;
//
// /**
// * 是否开启四家立直流局
// */
// public boolean enableFourRiichiDraw;
//
// /**
// * 是否开启九种九牌流局
// */
// public boolean enableNineDifferntTerminalsandHonorsDraw;
//
// /**
// * 是否开启三家和了流局
// */
// public boolean enableThreeRonDraw;
//
// /**
// * 是否开启舍张振听
// */
// public boolean enableDiscardFuriten;
//
// /**
// * 是否开启同巡振听
// */
// public boolean enableTemporaryFuriten;
//
// /**
// * 是否开启立直振听
// */
// public boolean enableRiichiFuriten;
//
// /**
// * 是否开启现物食替，即吃完之后，不能打出和吃的牌相同的牌
// */
// public boolean enableSameTileReplace;
//
// /**
// * 是否开启筋食替，即吃完之后，不能打出吃的牌另一边的牌
// */
// public boolean enableTendonReplace;
//
// /**
// * 赤五万的数量
// */
// public int redFiveManNumber;
//
// /**
// * 赤五饼的数量
// */
// public int redFivePinNumber;
//
// /**
// * 赤五索的数量
// */
// public int redFiveSouNumber;
//
// /**
// * 是否设定完场的最低分数，如果在All Last结束时第一位还没有达到这个分数
// * 那么如果东风场就要南入，南风场就要西入
// */
// public boolean enableEnoughPointToFinishGame;
//
// public int minPointToFinishGame;
//
// /**
// * 当第一位在All Last中达到最低分数的时候，是否可以立刻结束比赛
// */
// public boolean enable1stWinWhenEnoughPoint;
//
// /**
// * 是否开启连庄优先，如果在分数不够导致西入或者南入的时候，有一家达到最低分数
// * 但是庄家没有达到且没人击飞且继续连庄的情况下，那么庄家继续连庄而不是结束比赛
// */
// public boolean enableDealerContinueFirst;
//
// /**
// * 国士无双能不能抢暗杠
// */
// public final boolean enableThirteenOrphansRobbingConcealedKan;
//
// public RuleFactory(Rule rule) {
// super();
// this.enableResponsibilityForBigThreeDragon = rule.enableResponsibilityForBigThreeDragon;
// this.enableResponsibilityForFourBigWind = rule.enableResponsibilityForFourBigWind;
// this.enableResponsibilityForFourQuads = rule.enableResponsibilityForFourQuads;
// this.enableCountDoraWhenHasYaukuman = rule.enableCountDoraWhenHasYaukuman;
// this.enable13HanCeilingOfAddUpYakuman = rule.enable13HanCeilingOfAddUpYakuman;
// this.enableNeedGreenDragonAsAllGreen = rule.enableNeedGreenDragonAsAllGreen;
// this.enableTrueAllGreen = rule.enableTrueAllGreen;
// this.enableDanyaoAsYakuWhenMakeCall = rule.enableDanyaoAsYakuWhenMakeCall;
// this.enableNotAllTileToWinHasYakuCanWin = rule.enableNotAllTileToWinHasYakuCanWin;
// this.enablePureFourSequenceAsTwicePureDoubleSequence = rule.enablePureFourSequenceAsTwicePureDoubleSequence;
// this.enableNotRealTenpai = rule.enableNotRealTenpai;
// this.enablePrevalentWindAndseatWindPairCnoutAs4Fu = rule.enablePrevalentWindAndseatWindPairCnoutAs4Fu;
// this.enableCeilToMangan = rule.enableCeilToMangan;
// this.enableMinPoint = rule.enableMinPoint;
// this.minPoint = rule.minPoint;
// this.enableMaxPoint = rule.enableMaxPoint;
// this.maxPoint = rule.maxPoint;
// this.enbaleFirstWinnerWin = rule.enbaleFirstWinnerWin;
// this.enableFourContinueWindDraw = rule.enableFourContinueWindDraw;
// this.enableFourKanDraw = rule.enableFourKanDraw;
// this.enableFiveKan = rule.enableFiveKan;
// this.enableFourRiichiDraw = rule.enableFourRiichiDraw;
// this.enableNineDifferntTerminalsandHonorsDraw = rule.enableNineDifferntTerminalsandHonorsDraw;
// this.enableThreeRonDraw = rule.enableThreeRonDraw;
// this.enableDiscardFuriten = rule.enableDiscardFuriten;
// this.enableTemporaryFuriten = rule.enableTemporaryFuriten;
// this.enableRiichiFuriten = rule.enableRiichiFuriten;
// this.enableSameTileReplace = rule.enableSameTileReplace;
// this.enableTendonReplace = rule.enableTendonReplace;
// this.redFiveManNumber = rule.redFiveManNumber;
// this.redFivePinNumber = rule.redFivePinNumber;
// this.redFiveSouNumber = rule.redFiveSouNumber;
// this.enableEnoughPointToFinishGame = rule.enableEnoughPointToFinishGame;
// this.minPointToFinishGame = rule.minPointToFinishGame;
// this.enable1stWinWhenEnoughPoint = rule.enable1stWinWhenEnoughPoint;
// this.enableDealerContinueFirst = rule.enableDealerContinueFirst;
// this.enableThirteenOrphansRobbingConcealedKan = rule.enableThirteenOrphansRobbingConcealedKan;
// }
//
// public Rule getRule() {
// return new Rule(enableResponsibilityForBigThreeDragon, enableResponsibilityForFourBigWind,
// enableResponsibilityForFourQuads, enableCountDoraWhenHasYaukuman, enable13HanCeilingOfAddUpYakuman,
// enableNeedGreenDragonAsAllGreen, enableTrueAllGreen, enableDanyaoAsYakuWhenMakeCall,
// enableNotAllTileToWinHasYakuCanWin, enablePureFourSequenceAsTwicePureDoubleSequence, enableNotRealTenpai,
// enablePrevalentWindAndseatWindPairCnoutAs4Fu, enableCeilToMangan, enableMinPoint, minPoint, enableMaxPoint,
// maxPoint, enbaleFirstWinnerWin, enableFourContinueWindDraw, enableFourKanDraw, enableFiveKan,
// enableFourRiichiDraw, enableNineDifferntTerminalsandHonorsDraw, enableThreeRonDraw, enableDiscardFuriten,
// enableTemporaryFuriten, enableRiichiFuriten, enableSameTileReplace, enableTendonReplace, redFiveManNumber,
// redFivePinNumber, redFiveSouNumber, enableEnoughPointToFinishGame, minPointToFinishGame,
// enable1stWinWhenEnoughPoint, enableDealerContinueFirst, enableThirteenOrphansRobbingConcealedKan);
// }
//
// public boolean isEnableResponsibilityForBigThreeDragon() {
// return enableResponsibilityForBigThreeDragon;
// }
//
// public void setEnableResponsibilityForBigThreeDragon(boolean enableResponsibilityForBigThreeDragon) {
// this.enableResponsibilityForBigThreeDragon = enableResponsibilityForBigThreeDragon;
// }
//
// public boolean isEnableResponsibilityForFourBigWind() {
// return enableResponsibilityForFourBigWind;
// }
//
// public void setEnableResponsibilityForFourBigWind(boolean enableResponsibilityForFourBigWind) {
// this.enableResponsibilityForFourBigWind = enableResponsibilityForFourBigWind;
// }
//
// public boolean isEnableResponsibilityForFourQuads() {
// return enableResponsibilityForFourQuads;
// }
//
// public void setEnableResponsibilityForFourQuads(boolean enableResponsibilityForFourQuads) {
// this.enableResponsibilityForFourQuads = enableResponsibilityForFourQuads;
// }
//
// public boolean isEnableCountDoraWhenHasYaukuman() {
// return enableCountDoraWhenHasYaukuman;
// }
//
// public void setEnableCountDoraWhenHasYaukuman(boolean enableCountDoraWhenHasYaukuman) {
// this.enableCountDoraWhenHasYaukuman = enableCountDoraWhenHasYaukuman;
// }
//
// public boolean isEnable13HanCeilingOfAddUpYakuman() {
// return enable13HanCeilingOfAddUpYakuman;
// }
//
// public void setEnable13HanCeilingOfAddUpYakuman(boolean enable13HanCeilingOfAddUpYakuman) {
// this.enable13HanCeilingOfAddUpYakuman = enable13HanCeilingOfAddUpYakuman;
// }
//
// public boolean isEnableNeedGreenDragonAsAllGreen() {
// return enableNeedGreenDragonAsAllGreen;
// }
//
// public void setEnableNeedGreenDragonAsAllGreen(boolean enableNeedGreenDragonAsAllGreen) {
// this.enableNeedGreenDragonAsAllGreen = enableNeedGreenDragonAsAllGreen;
// }
//
// public boolean isEnableTrueAllGreen() {
// return enableTrueAllGreen;
// }
//
// public void setEnableTrueAllGreen(boolean enableTrueAllGreen) {
// this.enableTrueAllGreen = enableTrueAllGreen;
// }
//
// public boolean isEnableDanyaoAsYakuWhenMakeCall() {
// return enableDanyaoAsYakuWhenMakeCall;
// }
//
// public void setEnableDanyaoAsYakuWhenMakeCall(boolean enableDanyaoAsYakuWhenMakeCall) {
// this.enableDanyaoAsYakuWhenMakeCall = enableDanyaoAsYakuWhenMakeCall;
// }
//
// public boolean isEnableNotAllTileToWinHasYakuCanWin() {
// return enableNotAllTileToWinHasYakuCanWin;
// }
//
// public void setEnableNotAllTileToWinHasYakuCanWin(boolean enableNotAllTileToWinHasYakuCanWin) {
// this.enableNotAllTileToWinHasYakuCanWin = enableNotAllTileToWinHasYakuCanWin;
// }
//
// public boolean isEnablePureFourSequenceAsTwicePureDoubleSequence() {
// return enablePureFourSequenceAsTwicePureDoubleSequence;
// }
//
// public void
// setEnablePureFourSequenceAsTwicePureDoubleSequence(boolean enablePureFourSequenceAsTwicePureDoubleSequence) {
// this.enablePureFourSequenceAsTwicePureDoubleSequence = enablePureFourSequenceAsTwicePureDoubleSequence;
// }
//
// public boolean isEnableNotRealTenpai() {
// return enableNotRealTenpai;
// }
//
// public void setEnableNotRealTenpai(boolean enableNotRealTenpai) {
// this.enableNotRealTenpai = enableNotRealTenpai;
// }
//
// public boolean isEnablePrevalentWindAndseatWindPairCnoutAs4Fu() {
// return enablePrevalentWindAndseatWindPairCnoutAs4Fu;
// }
//
// public void setEnablePrevalentWindAndseatWindPairCnoutAs4Fu(boolean enablePrevalentWindAndseatWindPairCnoutAs4Fu) {
// this.enablePrevalentWindAndseatWindPairCnoutAs4Fu = enablePrevalentWindAndseatWindPairCnoutAs4Fu;
// }
//
// public boolean isEnableCeilToMangan() {
// return enableCeilToMangan;
// }
//
// public void setEnableCeilToMangan(boolean enableCeilToMangan) {
// this.enableCeilToMangan = enableCeilToMangan;
// }
//
// public boolean isEnableMinPoint() {
// return enableMinPoint;
// }
//
// public void setEnableMinPoint(boolean enableMinPoint) {
// this.enableMinPoint = enableMinPoint;
// }
//
// public int getMinPoint() {
// return minPoint;
// }
//
// public void setMinPoint(int minPoint) {
// this.minPoint = minPoint;
// }
//
// public boolean isEnableMaxPoint() {
// return enableMaxPoint;
// }
//
// public void setEnableMaxPoint(boolean enableMaxPoint) {
// this.enableMaxPoint = enableMaxPoint;
// }
//
// public int getMaxPoint() {
// return maxPoint;
// }
//
// public void setMaxPoint(int maxPoint) {
// this.maxPoint = maxPoint;
// }
//
// public boolean isEnbaleFirstWinnerWin() {
// return enbaleFirstWinnerWin;
// }
//
// public void setEnbaleFirstWinnerWin(boolean enbaleFirstWinnerWin) {
// this.enbaleFirstWinnerWin = enbaleFirstWinnerWin;
// }
//
// public boolean isEnableFourContinueWindDraw() {
// return enableFourContinueWindDraw;
// }
//
// public void setEnableFourContinueWindDraw(boolean enableFourContinueWindDraw) {
// this.enableFourContinueWindDraw = enableFourContinueWindDraw;
// }
//
// public boolean isEnableFourKanDraw() {
// return enableFourKanDraw;
// }
//
// public void setEnableFourKanDraw(boolean enableFourKanDraw) {
// this.enableFourKanDraw = enableFourKanDraw;
// }
//
// public boolean isEnableFiveKan() {
// return enableFiveKan;
// }
//
// public void setEnableFiveKan(boolean enableFiveKan) {
// this.enableFiveKan = enableFiveKan;
// }
//
// public boolean isEnableFourRiichiDraw() {
// return enableFourRiichiDraw;
// }
//
// public void setEnableFourRiichiDraw(boolean enableFourRiichiDraw) {
// this.enableFourRiichiDraw = enableFourRiichiDraw;
// }
//
// public boolean isEnableNineDifferntTerminalsandHonorsDraw() {
// return enableNineDifferntTerminalsandHonorsDraw;
// }
//
// public void setEnableNineDifferntTerminalsandHonorsDraw(boolean enableNineDifferntTerminalsandHonorsDraw) {
// this.enableNineDifferntTerminalsandHonorsDraw = enableNineDifferntTerminalsandHonorsDraw;
// }
//
// public boolean isEnableThreeRonDraw() {
// return enableThreeRonDraw;
// }
//
// public void setEnableThreeRonDraw(boolean enableThreeRonDraw) {
// this.enableThreeRonDraw = enableThreeRonDraw;
// }
//
// public boolean isEnableDiscardFuriten() {
// return enableDiscardFuriten;
// }
//
// public void setEnableDiscardFuriten(boolean enableDiscardFuriten) {
// this.enableDiscardFuriten = enableDiscardFuriten;
// }
//
// public boolean isEnableTemporaryFuriten() {
// return enableTemporaryFuriten;
// }
//
// public void setEnableTemporaryFuriten(boolean enableTemporaryFuriten) {
// this.enableTemporaryFuriten = enableTemporaryFuriten;
// }
//
// public boolean isEnableRiichiFuriten() {
// return enableRiichiFuriten;
// }
//
// public void setEnableRiichiFuriten(boolean enableRiichiFuriten) {
// this.enableRiichiFuriten = enableRiichiFuriten;
// }
//
// public boolean isEnableSameTileReplace() {
// return enableSameTileReplace;
// }
//
// public void setEnableSameTileReplace(boolean enableSameTileReplace) {
// this.enableSameTileReplace = enableSameTileReplace;
// }
//
// public boolean isEnableTendonReplace() {
// return enableTendonReplace;
// }
//
// public void setEnableTendonReplace(boolean enableTendonReplace) {
// this.enableTendonReplace = enableTendonReplace;
// }
//
// public int getRedFiveManNumber() {
// return redFiveManNumber;
// }
//
// public void setRedFiveManNumber(int redFiveManNumber) {
// this.redFiveManNumber = redFiveManNumber;
// }
//
// public int getRedFivePinNumber() {
// return redFivePinNumber;
// }
//
// public void setRedFivePinNumber(int redFivePinNumber) {
// this.redFivePinNumber = redFivePinNumber;
// }
//
// public int getRedFiveSouNumber() {
// return redFiveSouNumber;
// }
//
// public void setRedFiveSouNumber(int redFiveSouNumber) {
// this.redFiveSouNumber = redFiveSouNumber;
// }
//
// public boolean isEnableEnoughPointToFinishGame() {
// return enableEnoughPointToFinishGame;
// }
//
// public void setEnableEnoughPointToFinishGame(boolean enableEnoughPointToFinishGame) {
// this.enableEnoughPointToFinishGame = enableEnoughPointToFinishGame;
// }
//
// public int getMinPointToFinishGame() {
// return minPointToFinishGame;
// }
//
// public void setMinPointToFinishGame(int minPointToFinishGame) {
// this.minPointToFinishGame = minPointToFinishGame;
// }
//
// public boolean isEnable1stWinWhenEnoughPoint() {
// return enable1stWinWhenEnoughPoint;
// }
//
// public void setEnable1stWinWhenEnoughPoint(boolean enable1stWinWhenEnoughPoint) {
// this.enable1stWinWhenEnoughPoint = enable1stWinWhenEnoughPoint;
// }
//
// public boolean isEnableDealerContinueFirst() {
// return enableDealerContinueFirst;
// }
//
// public void setEnableDealerContinueFirst(boolean enableDealerContinueFirst) {
// this.enableDealerContinueFirst = enableDealerContinueFirst;
// }
//
// public boolean isEnableThirteenOrphansRobbingConcealedKan() {
// return enableThirteenOrphansRobbingConcealedKan;
// }
//
// }
