package yu.proj.jpmahjong.rule;

/**  
 * @ClassName: Rule  
 *
 * @Description: 和麻将相关的问题
 *
 * @author 余定邦  
 *
 * @date 2020年9月12日  
 *  
 */
public class Rule {

    public final static Rule MAHJONG_SOUL_RULE = new Rule(true, true, false, false, true, false, false, true, true,
        true, false, true, false, true, 0, false, 0, false, true, true, false, true, true, false, true, true, true,
        true, false, false, 1, 1, 1, true, 30000, true, true, true, 1,
        ConcealedKanWhenRiichi.ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_PATTERN_NOT_CHANGE, true, false);

    /**
     * 是否开启大三元的包牌规则
     */
    public final boolean enableResponsibilityForBigThreeDragon;

    /**
     * 是否开启大四喜的包牌规则
     */
    public final boolean enableResponsibilityForFourBigWind;

    /**
     * 是否开启四杠子的包牌规则
     */
    public final boolean enableResponsibilityForFourQuads;

    /**
     * 就是有役满的时候，是不是还计算宝牌的累计役满，其余的役还是不算
     */
    public final boolean enableCountDoraWhenHasYaukuman;

    /**
     * 是否认为累计役满是13番封顶，如果认为，那么无论有多少番，那么都是记为一个累计役满
     * 如果不认为封顶，那么每13番记作一个累计役满，即26番记作双倍累计役满，以此类推
     */
    public final boolean enable13HanCeilingOfAddUpYakuman;

    /**
     * 完成绿一色的时候，是否需要发
     */
    public final boolean enableNeedGreenDragonAsAllGreen;

    /**
     * 是否认可纯正绿一色，和上面的规则enableNeedGreenDragonAsAllGreen不能同时为true
     */
    public final boolean enableTrueAllGreen;

    /**
     * 即食断，如果可以食断，那么就当非门清的时候，也认可断幺九，如果不可食断
     * 那么非门清的时候，不计断幺九
     */
    public final boolean enableDanyaoAsYakuWhenMakeCall;

    /**
     * 即后付，如果可以后付，那么即使听牌的牌型只有1种有役那么就可以和那张有役的牌型
     * 但是无后付不一样，如果听牌的牌型中有一种没有役，那么就不能和，即使你在听别的有役的牌型
     */
    public final boolean enableNotAllTileToWinHasYakuCanWin;

    /**
     * 一色四同顺能被记作二杯口吗，还是只记作一杯口
     */
    public final boolean enablePureFourSequenceAsTwicePureDoubleSequence;

    /**
     * 是否承认虚听算是听牌，虚听即手上拿着4张一样的牌，最后还听这张牌，这被称为虚听
     * 
     * 而如果手上听的牌没有役或者已经在场上没有了，那么叫做形式听牌，虽然也不是真的听牌
     * 但是在流局的时候不罚符
     */
    public final boolean enableNotRealTenpai;

    /**
     * 连风的雀头记作4符吗，还是2符
     */
    public final boolean enablePrevalentWindAndseatWindPairCnoutAs4Fu;

    /**
     * 是否开启切上满贯，即30符4翻、60符3翻记作满贯
     */
    public final boolean enableCeilToMangan;

    /**
     * 设置最小天边，如果开启这个规则且有1名玩家低于这个分数，那么比赛结束
     */
    public final boolean enableMinPoint;

    public final int minPoint;

    /**
     * 设置最大天边，如果开启这个规则且有1名玩家高于这个分数，那么比赛结束
     */
    public final boolean enableMaxPoint;

    public final int maxPoint;

    /**
     * 是否开启头跳，即只有离放铳家最近的那个玩家能够和牌
     * 
     * 开启了头跳的情况下，三家荣和流局自动失效，因为不可能出现三家荣和的情况
     */
    public final boolean enableFirstWinnerWin;

    /**
     * 是否开启四风连打流局规则
     */
    public final boolean enableFourContinueWindDraw;

    /**
     * 是否开启四杠散了，如果不开启，那么四杠之后根据是否允许五杠的规则继续比赛
     */
    public final boolean enableFourKanDraw;

    /**
     * 是否允许杠第五次，如果允许，那么第五次杠之后，如果不是加杠放铳，那么不摸岭上牌
     * 直接流局，如果不允许第五次杠，那么开了四次杠之后不允许再杠牌
     */
    public final boolean enableFiveKan;

    /**
     * 是否开启四家立直流局
     */
    public final boolean enableFourRiichiDraw;

    /**
     * 是否开启九种九牌流局
     */
    public final boolean enableNineDifferntTerminalsandHonorsDraw;

    /**
     * 是否开启三家和了流局
     */
    public final boolean enableThreeRonDraw;

    /**
     * 是否开启舍张振听
     */
    public final boolean enableDiscardFuriten;

    /**
     * 是否开启同巡振听
     */
    public final boolean enableTemporaryFuriten;

    /**
     * 是否开启立直振听
     */
    public final boolean enableRiichiFuriten;

    /**
     * 是否会由于不足起番而不能荣和时导致的立直振听
     * 
     * 由于不足番而导致立直振听其实感觉就很不合理
     * 因为一般来说立直就足够一番起和，而不会由于无役而不能和
     * 因此为了防止见逃，就设置的立直振听
     * 
     * 但是由于番缚变高了，那么立直就有可能由于不足番而没法荣和
     * 
     * 因此设置这样一个选项，来防止出现这种情况，这里如果设置为false的话
     * 那么就当作同巡振听来处理，而不是那么苛刻的立直振听
     */
    public final boolean enableNotEnoughHanToRonCausedRiichiFuriten;

    /**
     * 是否开启现物食替，即吃完之后，不能打出和吃的牌相同的牌
     */
    public final boolean enableSameTileReplace;

    /**
     * 是否开启筋食替，即吃完之后，不能打出吃的牌另一边的牌
     */
    public final boolean enableTendonReplace;

    /**
     * 赤五万的数量
     */
    public final int redFiveManNumber;

    /**
     * 赤五饼的数量
     */
    public final int redFivePinNumber;

    /**
     * 赤五索的数量
     */
    public final int redFiveSouNumber;

    /**
     * 是否设定完场的最低分数，如果在All Last结束时第一位还没有达到这个分数
     * 那么如果东风场就要南入，南风场就要西入
     */
    public final boolean enableEnoughPointToFinishGame;

    public final int minPointToFinishGame;

    /**
     * 当第一位在All Last中达到最低分数的时候，是否可以立刻结束比赛
     */
    public final boolean enable1stWinWhenEnoughPoint;

    /**
     * 是否开启连庄优先，如果在分数不够导致西入或者南入的时候，有一家达到最低完场分数
     * 但是庄家没有达到且没人击飞且继续连庄的情况下，那么庄家继续连庄而不是结束比赛
     */
    public final boolean enableDealerContinueFirst;

    /**
     * 国士无双能不能抢暗杠
     */
    public final boolean enableThirteenOrphansRobbingConcealedKan;

    /**
     * 番缚，即最少需要几番才能赢，不是严格的番缚
     * 即只要累计的番足够就可以和
     */
    public final int minHanToWin;

    /**
     * 立直后暗杠的规则
     */
    public final ConcealedKanWhenRiichi concealedKanWhenRiichi;

    /**
     * 是否开启立直后暗杠的严格模式，如果需要严格模式的话，那么不仅需要检查听的最大打点的牌
     * 甚至对于任意一种解释方式，上述规则都必须成立
     */
    public final boolean enableStrictModeOfConcealedKanWhenRiichi;

    /**
     * TNG = True Nine Gate 纯正九莲宝灯
     * TWTO = Thirteen-wait Thirteen Orphans 国士无双十三面
     * SWFCT = Single-wait Four Concealed Triplets 四暗刻单骑
     * 
     * BOH = Blessing of Heaven 天和
     * 
     * 缩写是因为不缩写这个变量的名字太长了，会导致自动格式化后的代码极丑，所以就缩写了
     * 
     * 这个规则是：是否在天和时，准确要求摸到的14张需要按照一定的顺序，
     * 才能和纯正九莲宝灯或国士无双十三面或四暗刻单骑
     * 对于纯正九莲宝灯来说，就是前13张摸到1112345678999的牌型，而最后第14张为任意同花色的牌
     * 对于国士无双十三面来说，就是前13张为13张不同的幺九牌，而最后第14张为任意一张幺九牌
     * 对于四暗刻单骑来说同理，如果前13张是单骑听，那么就记为四暗刻单骑，否则只记为普通四暗刻
     * 
     * 吐槽一下：这个规则真的有必要吗，神tm天和纯九，神tm天和国士十三，不要命了吗
     * 土豪氪钱，欧皇氪命。。。。和纯九已经只剩九年寿命了，天和纯九是明天嗝屁吗
     * 
     * 建议为true，节约欧皇寿命
     */
    public final boolean enableNeedExactPatternForTNGAndTWTOAndSWFCTWhenBOH;

    public Rule(boolean enableResponsibilityForBigThreeDragon, boolean enableResponsibilityForFourBigWind,
        boolean enableResponsibilityForFourQuads, boolean enableCountDoraWhenHasYaukuman,
        boolean enable13HanCeilingOfAddUpYakuman, boolean enableNeedGreenDragonAsAllGreen, boolean enableTrueAllGreen,
        boolean enableDanyaoAsYakuWhenMakeCall, boolean enableNotAllTileToWinHasYakuCanWin,
        boolean enablePureFourSequenceAsTwicePureDoubleSequence, boolean enableNotRealTenpai,
        boolean enablePrevalentWindAndseatWindPairCnoutAs4Fu, boolean enableCeilToMangan, boolean enableMinPoint,
        int minPoint, boolean enableMaxPoint, int maxPoint, boolean enableFirstWinnerWin,
        boolean enableFourContinueWindDraw, boolean enableFourKanDraw, boolean enableFiveKan,
        boolean enableFourRiichiDraw, boolean enableNineDifferntTerminalsandHonorsDraw, boolean enableThreeRonDraw,
        boolean enableDiscardFuriten, boolean enableTemporaryFuriten, boolean enableRiichiFuriten,
        boolean enableNotEnoughHanToRonCausedRiichiFuriten, boolean enableSameTileReplace, boolean enableTendonReplace,
        int redFiveManNumber, int redFivePinNumber, int redFiveSouNumber, boolean enableEnoughPointToFinishGame,
        int minPointToFinishGame, boolean enable1stWinWhenEnoughPoint, boolean enableDealerContinueFirst,
        boolean enableThirteenOrphansRobbingConcealedKan, int minHanToWin,
        ConcealedKanWhenRiichi concealedKanWhenRiichi, boolean enableStrictModeOfConcealedKanWhenRiichi,
        boolean enableNeedExactPatternForTNGAndTWTOAndSWFCTWhenBOH) {
        super();
        this.enableResponsibilityForBigThreeDragon              = enableResponsibilityForBigThreeDragon;
        this.enableResponsibilityForFourBigWind                 = enableResponsibilityForFourBigWind;
        this.enableResponsibilityForFourQuads                   = enableResponsibilityForFourQuads;
        this.enableCountDoraWhenHasYaukuman                     = enableCountDoraWhenHasYaukuman;
        this.enable13HanCeilingOfAddUpYakuman                   = enable13HanCeilingOfAddUpYakuman;
        this.enableNeedGreenDragonAsAllGreen                    = enableNeedGreenDragonAsAllGreen;
        this.enableTrueAllGreen                                 = enableTrueAllGreen;
        this.enableDanyaoAsYakuWhenMakeCall                     = enableDanyaoAsYakuWhenMakeCall;
        this.enableNotAllTileToWinHasYakuCanWin                 = enableNotAllTileToWinHasYakuCanWin;
        this.enablePureFourSequenceAsTwicePureDoubleSequence    = enablePureFourSequenceAsTwicePureDoubleSequence;
        this.enableNotRealTenpai                                = enableNotRealTenpai;
        this.enablePrevalentWindAndseatWindPairCnoutAs4Fu       = enablePrevalentWindAndseatWindPairCnoutAs4Fu;
        this.enableCeilToMangan                                 = enableCeilToMangan;
        this.enableMinPoint                                     = enableMinPoint;
        this.minPoint                                           = minPoint;
        this.enableMaxPoint                                     = enableMaxPoint;
        this.maxPoint                                           = maxPoint;
        this.enableFirstWinnerWin                               = enableFirstWinnerWin;
        this.enableFourContinueWindDraw                         = enableFourContinueWindDraw;
        this.enableFourKanDraw                                  = enableFourKanDraw;
        this.enableFiveKan                                      = enableFiveKan;
        this.enableFourRiichiDraw                               = enableFourRiichiDraw;
        this.enableNineDifferntTerminalsandHonorsDraw           = enableNineDifferntTerminalsandHonorsDraw;
        this.enableThreeRonDraw                                 = enableThreeRonDraw;
        this.enableDiscardFuriten                               = enableDiscardFuriten;
        this.enableTemporaryFuriten                             = enableTemporaryFuriten;
        this.enableRiichiFuriten                                = enableRiichiFuriten;
        this.enableNotEnoughHanToRonCausedRiichiFuriten         = enableNotEnoughHanToRonCausedRiichiFuriten;
        this.enableSameTileReplace                              = enableSameTileReplace;
        this.enableTendonReplace                                = enableTendonReplace;
        this.redFiveManNumber                                   = redFiveManNumber;
        this.redFivePinNumber                                   = redFivePinNumber;
        this.redFiveSouNumber                                   = redFiveSouNumber;
        this.enableEnoughPointToFinishGame                      = enableEnoughPointToFinishGame;
        this.minPointToFinishGame                               = minPointToFinishGame;
        this.enable1stWinWhenEnoughPoint                        = enable1stWinWhenEnoughPoint;
        this.enableDealerContinueFirst                          = enableDealerContinueFirst;
        this.enableThirteenOrphansRobbingConcealedKan           = enableThirteenOrphansRobbingConcealedKan;
        this.minHanToWin                                        = minHanToWin;
        this.concealedKanWhenRiichi                             = concealedKanWhenRiichi;
        this.enableStrictModeOfConcealedKanWhenRiichi           = enableStrictModeOfConcealedKanWhenRiichi;
        this.enableNeedExactPatternForTNGAndTWTOAndSWFCTWhenBOH = enableNeedExactPatternForTNGAndTWTOAndSWFCTWhenBOH;
    }


}
