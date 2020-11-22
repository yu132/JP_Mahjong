package yu.proj.ref.rule;

import yu.proj.ref.rule.GameRule.GameRuleBuilder;
import yu.proj.ref.rule.chii.ChiiRule;
import yu.proj.ref.rule.danyao.DanyaoRule;
import yu.proj.ref.rule.draw.FourContinueWindDraw;
import yu.proj.ref.rule.draw.FourKanDraw;
import yu.proj.ref.rule.draw.FourKanDraw.FiveKanRule;
import yu.proj.ref.rule.draw.FourKanDraw.FourKanDrawType;
import yu.proj.ref.rule.draw.FourRiichiDraw;
import yu.proj.ref.rule.draw.MaxWinnerNumRule;
import yu.proj.ref.rule.draw.NineDifferntTerminalsandHonorsDraw;
import yu.proj.ref.rule.point.CeilToManganRule;
import yu.proj.ref.rule.point.PointRule;
import yu.proj.ref.rule.point.PointRule.PointLimit;
import yu.proj.ref.rule.point.PrevalentWindAndseatWindPairRule;
import yu.proj.ref.rule.responsibility.ResponsibilityRule;
import yu.proj.ref.rule.tenpai.NotRealTenpaiRule;
import yu.proj.ref.rule.win.ChooseNotToContinueInAllLastRule;
import yu.proj.ref.rule.win.DealerContinueFirstRule;
import yu.proj.ref.rule.win.MinHanToWinRestrict;
import yu.proj.ref.rule.win.WinYakuRestrict;
import yu.proj.ref.rule.yaku.allGreen.GreenDragonInAllGreenRule;
import yu.proj.ref.rule.yaku.blessingOfHeaven.NeedExactPatternForBlessingOfHeavenRule;
import yu.proj.ref.rule.yaku.pureFourSequence.PureFourSequenceRule;
import yu.proj.ref.rule.yaku.riichi.ConcealedKanWhenRiichiRule;
import yu.proj.ref.rule.yaku.thirteenOrphans.ThirteenOrphansRobbingConcealedKanRule;

/**  
 * @ClassName: MahjongSoulRule  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月21日  
 *  
 */
public class MahjongSoulRule {

    public final static GameRule mahjongSoulDefaultFourPalyerRule;

    static {
        GameRuleBuilder builder = new GameRuleBuilder();

        builder.responsibilityForBigThreeDragon(ResponsibilityRule.BigThreeDragon.ENABLE)// 大三元包牌
            .responsibilityForFourBigWind(ResponsibilityRule.FourBigWind.ENABLE)// 大四喜包牌
            .responsibilityForFourQuads(ResponsibilityRule.FourQuads.DISABLE)// 四杠子不包牌
            
            .allGreenRule(GreenDragonInAllGreenRule.NOT_GREEN_DRAGON_CAN_BE_ALL_GREEN)// 绿一色不用发
            
            .danyaoRule(DanyaoRule.BOTH_MAKE_CALL_AND_MENZENCHIN)// 有食断
            .yakuRestrict(WinYakuRestrict.ONE_PATTERN_HAS_YAKU_OR_ACCIDENTAL_YAKU)// 有后付
            
            .minHanToWinRestrict(MinHanToWinRestrict.ONE_HAN)// 番缚1番
            
            .notRealTenpaiRule(NotRealTenpaiRule.DISABLE)// 不准虚听
            
            .maxWinnerNumRule(MaxWinnerNumRule.THREE_PLAYER_WIN)// 允许3家和
            
            .fourContinueWindDraw(FourContinueWindDraw.ENABLE)// 有四风连打流局
            .fourKanDraw(new FourKanDraw(FourKanDrawType.ENABLE, FiveKanRule.DISABLE))// 有四杠散了流局，同时如果一家杠4次后，不允许再杠
            .fourRiichiDraw(FourRiichiDraw.ENABLE)// 有四家立直流局
            .nineDifferntTerminalsandHonorsDraw(NineDifferntTerminalsandHonorsDraw.ENABLE)// 有九种九牌流局
            
            .pureFourSequenceRule(PureFourSequenceRule.AS_TWICE_PURE_DOUBLE_SEQUENCE)// 一色四同顺认为是二杯口
            
            .prevalentWindAndseatWindPairRule(PrevalentWindAndseatWindPairRule.COUNT_AS_4_FU)// 连风雀头记为4符
            
            .ceilToManganRule(CeilToManganRule.DISABLE)// 没有切上满贯
            
            .pointRule(new PointRule(new PointLimit(true, 0), PointRule.NO_LIMIT, new PointLimit(true, 30000)))// 0分击飞，30000完场
            
            .chooseNotToContinueInAllLastRule(ChooseNotToContinueInAllLastRule.DEFAULT_YES)// 默认All Last的时候，庄家第一位自动结束比赛
            .dealerContinueFirstRule(DealerContinueFirstRule.DEFAULT_YES)// 默认南入西入的时候，庄家在可以连庄的时候优先连庄
            
            .thirteenOrphansRobbingConcealedKanRule(ThirteenOrphansRobbingConcealedKanRule.ENABLE)// 国士无双可以抢暗杠
            
            .concealedKanWhenRiichiRule(new ConcealedKanWhenRiichiRule(false, // 只有在不改变听牌牌型的时候，才允许在立直后暗杠
                ConcealedKanWhenRiichiRule.ConcealedKanWhenRiichiType.ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_PATTERN_NOT_CHANGE))
            
            // 天和时，默认就是国士无双十三面、纯正九莲宝灯、四暗刻单骑，而不用管牌型顺序
            .needExactPatternForBlessingOfHeavenRule(NeedExactPatternForBlessingOfHeavenRule.DISABLE)

            .chiiRule(ChiiRule.ENABLE_CHII);// 可以吃

        mahjongSoulDefaultFourPalyerRule = builder.build();
    }

}
