package yu.proj.ref.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import yu.proj.ref.rule.chii.ChiiRule;
import yu.proj.ref.rule.danyao.DanyaoRule;
import yu.proj.ref.rule.draw.FourContinueWindDraw;
import yu.proj.ref.rule.draw.FourKanDraw;
import yu.proj.ref.rule.draw.FourRiichiDraw;
import yu.proj.ref.rule.draw.MaxWinnerNumRule;
import yu.proj.ref.rule.draw.NineDifferntTerminalsandHonorsDraw;
import yu.proj.ref.rule.point.CeilToManganRule;
import yu.proj.ref.rule.point.PointRule;
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
 * @ClassName: GameRule  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月15日  
 *  
 */
@Builder(toBuilder = true)
@AllArgsConstructor
public class GameRule {

    public final ResponsibilityRule.BigThreeDragon responsibilityForBigThreeDragon;// 大三元包牌

    public final ResponsibilityRule.FourBigWind responsibilityForFourBigWind;// 大四喜包牌

    public final ResponsibilityRule.FourQuads responsibilityForFourQuads;// 四杠子包牌

    public final GreenDragonInAllGreenRule allGreenRule;// 绿一色设定

    public final DanyaoRule danyaoRule;// 食断

    public final WinYakuRestrict yakuRestrict;// 后付

    public final MinHanToWinRestrict minHanToWinRestrict;// 番缚

    public final NotRealTenpaiRule notRealTenpaiRule;// 虚听

    public final MaxWinnerNumRule maxWinnerNumRule;// 头跳 和 三家和了流局

    public final FourContinueWindDraw fourContinueWindDraw;// 四风连打流局

    public final FourKanDraw fourKanDraw;// 四杠散了流局 和 第五杠 的设定

    public final FourRiichiDraw fourRiichiDraw;// 四家立直流局

    public final NineDifferntTerminalsandHonorsDraw nineDifferntTerminalsandHonorsDraw;// 九种九牌流局

    public final PureFourSequenceRule pureFourSequenceRule;// 一色四同顺被认为是二杯口还是一杯口

    public final PrevalentWindAndseatWindPairRule prevalentWindAndseatWindPairRule;// 连风雀头记作2符还是4符

    public final CeilToManganRule ceilToManganRule;// 切上满贯

    public final PointRule pointRule;// 天边 和 最小完场分数

    public final ChooseNotToContinueInAllLastRule chooseNotToContinueInAllLastRule;// 第一位All Last不继续的设定

    public final DealerContinueFirstRule dealerContinueFirstRule;// 南入、西入之后，连庄优先设定

    public final ThirteenOrphansRobbingConcealedKanRule thirteenOrphansRobbingConcealedKanRule;// 国士无双抢暗杠

    public final ConcealedKanWhenRiichiRule concealedKanWhenRiichiRule;// 立直后暗杠的规则

    public final NeedExactPatternForBlessingOfHeavenRule needExactPatternForBlessingOfHeavenRule;// 天和时第14张的规定

    public final ChiiRule chiiRule;// 是否允许吃牌
}
