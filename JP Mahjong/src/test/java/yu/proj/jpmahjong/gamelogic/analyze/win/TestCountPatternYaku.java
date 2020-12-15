// package yu.proj.jpmahjong.gamelogic.analyze.win;
//
// import static yu.proj.jpmahjong.yaku.Yaku.*;
//
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;
// import java.util.Set;
//
// import org.junit.Assert;
// import org.junit.Test;
//
// import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
// import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternYaku.YakuAns;
// import yu.proj.jpmahjong.player.TileSource;
// import yu.proj.jpmahjong.rule.Rule;
// import yu.proj.jpmahjong.testUtil.RuleFactory;
// import yu.proj.jpmahjong.testUtil.StringToTile;
// import yu.proj.jpmahjong.tiles.Tile;
// import yu.proj.jpmahjong.tiles.TileType;
// import yu.proj.jpmahjong.tiles.meld.Meld;
// import yu.proj.jpmahjong.tiles.meld.MeldType;
// import yu.proj.jpmahjong.tiles.tenpaiPattern.SevenPairs;
// import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;
// import yu.proj.jpmahjong.yaku.Yaku;
//
/// **
// * @ClassName: TestCountPatternYaku
// *
// * @Description: TODO(这里用一句话描述这个类的作用)
// *
// * @author 余定邦
// *
// * @date 2020年9月18日
// *
// */
// public class TestCountPatternYaku {
//
// private Rule rule = Rule.MAHJONG_SOUL_RULE;
//
// private Tile[] doraIndicator = {StringToTile.str2Tile("东[1]")};
//
// /* 测试目录
// *
// * 1.断幺九 √
// * 2.自风 √
// * 3.场风 √
// * 4.白 √
// * 5.发 √
// * 6.中 √
// * 7.平和 √
// * 8.一杯口、二杯口 √
// * 9.三色同刻 √
// * 10.三杠子、四杠子 √
// * 11.对对和 √
// * 12.三暗刻、四暗刻和四暗刻单骑 √
// * 13.小三元、大三元 √
// * 14.混老头、清老头 √
// * 15.七对子 √
// * 16.混全带幺九、纯全带幺九 √
// * 17.一气通贯 √
// * 18.三色同顺 √
// * 19.混一色、清一色 √
// * 20.字一色 √
// * 21.国士无双（包括国士无双十三面） √
// * 22.绿一色 √
// * 23.小四喜、大四喜 √
// * 24.九莲宝灯、纯正九莲宝灯 √
// */
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 断幺九
// *
// */
// @Test
// public void testDanyao() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "3s[2]", "3m[3]", "4m[1]", "5m[1]", "6p[1]", "6p[1]",
// "6p[1]", "4p[1]", "4p[1]", "2s[1]", "2s[1]", "2s[2]");
//
// test(concealedHand, 2, Arrays.asList(DANYAO), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 红宝牌
// *
// */
// @Test
// public void testRedFive() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "3s[2]", "3m[3]", "4m[1]", "0m[1]", "6p[1]", "6p[1]",
// "6p[1]", "4p[1]", "4p[1]", "2s[1]", "2s[1]", "2s[2]");
//
// test(concealedHand, 2, Arrays.asList(DANYAO), Arrays.asList(), 1);
// }
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 自风、场风
// *
// */
// @Test
// public void testWind() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "2s[2]", "3s[3]", "1p[1]", "北[1]", "北[2]", "北[3]",
// "东[4]", "东[2]", "东[3]", "西[1]", "西[2]", "西[3]");
//
// test(concealedHand, 1, Arrays.asList(PREVALENT_WIND_E, SEAT_WIND_E), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 白发
// *
// */
// @Test
// public void testDRAGON() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "2s[2]", "3s[3]", "白[1]", "白[1]", "白[2]", "发[3]",
// "发[4]", "发[2]", "东[3]", "西[1]", "西[2]", "西[3]");
//
// test(concealedHand, 1, Arrays.asList(DRAGON_G, DRAGON_WH), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 中
// *
// */
// @Test
// public void testDRAGON2() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "2s[2]", "3s[3]", "1p[1]", "北[1]", "北[2]", "北[3]",
// "中[4]", "中[2]", "中[3]", "西[1]", "西[2]", "西[3]");
//
// test(concealedHand, 1, Arrays.asList(DRAGON_R), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 平和 断幺
// *
// */
// @Test
// public void testPinfu() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "3m[3]", "4m[1]", "5m[1]", "6p[1]", "7p[1]",
// "8p[1]", "4p[1]", "5p[1]", "6p[1]", "8s[1]", "8s[2]");
//
// test(concealedHand, 2, Arrays.asList(DANYAO, PINFU), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testDanyao
// *
// * @Description: 非平和
// *
// */
// @Test
// public void testPinfu2() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "3m[3]", "4m[1]", "5m[1]", "6p[1]", "7p[1]",
// "8p[1]", "4p[1]", "5p[1]", "6p[1]", "白[1]", "白[2]");
//
// test(concealedHand, 2, Arrays.asList(), Arrays.asList(PINFU), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 一杯口
// *
// */
// @Test
// public void testPureDoubleSequence() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "3s[3]", "4s[1]", "5s[1]", "6p[1]", "7p[1]",
// "8p[1]", "4p[1]", "5p[1]", "6p[1]", "5s[1]", "8s[2]");
//
// test(concealedHand, 1, Arrays.asList(PURE_DOUBLE_SEQUENCE), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 二杯口
// *
// */
// @Test
// public void testTwicePureDoubleSequence() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "5s[3]", "3s[1]", "4s[1]", "5s[1]",
// "6p[1]", "7p[1]", "8p[1]", "6p[1]", "7p[1]", "8p[1]", "8m[2]");
//
// List<Yaku> yakuForSevenPairs = Arrays.asList(SEVEN_PAIRS, DANYAO);
// List<Yaku> yakuFor4m1p = Arrays.asList(TWICE_PURE_DOUBLE_SEQUENCE, DANYAO);
//
// test(concealedHand, 1, 0, (set, tpn) -> {
// if (tpn instanceof SevenPairs) {
// for (Yaku shouldHave : yakuForSevenPairs) {
// Assert.assertTrue("" + shouldHave, set.contains(shouldHave));
// }
// } else {
// for (Yaku shouldHave : yakuFor4m1p) {
// Assert.assertTrue("" + shouldHave, set.contains(shouldHave));
// }
// }
// });
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 二杯口 一色四同顺
// *
// * 有两种解释方法 8m 8m, 3s 4s 5s, 3s 4s 5s, 3s 4s 4s, 3s 4s 5s
// *
// * 或者 8m 8m, 3s 3s 3s, 4s 4s 4s, 5s 5s 5s, 3s 4s 5s
// *
// * 因此最后会有两种解释方法，最终会产生两种牌型，但是计算成2杯口肯定更大
// *
// */
// @Test
// public void testTwicePureDoubleSequence2() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "5s[3]", "3s[1]", "4s[1]", "5s[1]", "3s[1]",
// "4s[2]", "5s[3]", "3s[1]", "4s[2]", "5s[3]", "8m[2]");
//
// test(concealedHand, 1, 0, (set, tpn) -> {
// Assert.assertTrue(set.contains(THREE_CONCEALED_TRIPLETS) || set.contains(TWICE_PURE_DOUBLE_SEQUENCE));
// });
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 非门清 不计二杯口
// *
// */
// @Test
// public void testTwicePureDoubleSequence3() {
// List<Tile> concealedHand =
// StringToTile.getList("3s[1]", "4s[2]", "5s[3]", "3s[1]", "4s[2]", "5s[3]", "8m[2]");
//
// Meld ncm =
// new Meld(MeldType.SEQUENCE, StringToTile.getArray("3s[1]", "4s[2]", "5s[3]"),
// StringToTile.str2Tile("3s[1]"), TileSource.LAST_PLAYER, false, 0);
//
// Meld ncm2 =
// new Meld(MeldType.SEQUENCE, StringToTile.getArray("3s[1]", "4s[2]", "5s[3]"),
// StringToTile.str2Tile("3s[1]"), TileSource.LAST_PLAYER, false, 0);
//
// List<Meld> makeCall = Arrays.asList(ncm, ncm2);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, 0, (set, tpn) -> {
// Assert.assertTrue(!set.contains(TWICE_PURE_DOUBLE_SEQUENCE));
// }, false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 三色同刻
// *
// */
// @Test
// public void testTripleTriplets() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "3s[2]", "3s[3]", "3p[1]", "3p[1]", "3p[1]", "3m[1]",
// "3m[1]", "3m[1]", "5p[1]", "6p[1]", "7p[1]", "8s[2]");
//
// test(concealedHand, 1, Arrays.asList(TRIPLE_TRIPLETS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 三色同顺
// *
// */
// @Test
// public void testMixedTripleSequence() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "5s[3]", "3p[1]", "4p[1]", "5p[1]", "3m[1]",
// "4m[1]", "5m[1]", "9p[1]", "9p[1]", "9p[1]", "8s[2]");
//
// test(concealedHand, 1, Arrays.asList(MIXED_TRIPLE_SEQUENCE), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 对对和
// *
// */
// @Test
// public void testAllTriplets() {
// List<Tile> concealedHand = StringToTile.getList("4s[1]", "4s[2]", "4s[3]", "6s[1]", "6s[2]",
// "6s[3]", "8m[2]", "8p[1]", "8p[1]", "8p[1]");
//
// Meld ncm =
// new Meld(MeldType.TRIPLET, StringToTile.getArray("3s[1]", "3s[2]", "3s[3]"),
// StringToTile.str2Tile("3s[1]"), TileSource.OPPOSED_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, 0, (set, tpn) -> {
// Assert.assertTrue(set.contains(ALL_TRIPLETS));
// }, false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 三杠子
// *
// */
// @Test
// public void testThreeQuads() {
// List<Tile> concealedHand = StringToTile.getList("6s[1]", "6s[2]", "6s[3]", "8p[1]");
//
// Meld ncm = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("3s[1]", "3s[2]", "3s[3]", "3s[4]"), StringToTile.str2Tile("3s[1]"),
// TileSource.OPPOSED_PLAYER, false, 0);
//
// Meld ncm1 = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("4s[1]", "4s[2]", "4s[3]", "4s[4]"), StringToTile.str2Tile("4s[1]"),
// TileSource.OPPOSED_PLAYER, false, 0);
//
// Meld ncm2 = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("5s[1]", "5s[2]", "5s[3]", "5s[4]"), StringToTile.str2Tile("5s[1]"),
// TileSource.OPPOSED_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm, ncm1, ncm2);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, Arrays.asList(ALL_TRIPLETS, THREE_QUADS), Arrays.asList(), 0,
// false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 四杠子
// *
// */
// @Test
// public void testFourQuads() {
//
// Rule oldRule = rule;
//
// RuleFactory rf = new RuleFactory(oldRule);
//
// rf.setEnableResponsibilityForFourQuads(true);// 开启包牌规则
//
// rule = rf.getRule();
//
// List<Tile> concealedHand = StringToTile.getList("8p[1]");
//
// Meld ncm = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("3s[1]", "3s[2]", "3s[3]", "3s[4]"), StringToTile.str2Tile("3s[1]"),
// TileSource.LAST_PLAYER, true, 1);
//
// Meld ncm1 = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("4s[1]", "4s[2]", "4s[3]", "4s[4]"), StringToTile.str2Tile("4s[1]"),
// TileSource.LAST_PLAYER, false, 0);
//
// Meld ncm2 = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("5s[1]", "5s[2]", "5s[3]", "5s[4]"), StringToTile.str2Tile("5s[1]"),
// TileSource.NEXT_PLAYER, false, 0);
//
// Meld ncm3 = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("6s[1]", "6s[2]", "6s[3]", "6s[4]"), StringToTile.str2Tile("6s[1]"),
// TileSource.OPPOSED_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm, ncm1, ncm2, ncm3);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, 0, (yakuAns, tpn, a) -> {
// Assert.assertTrue(yakuAns.getYaku().contains(FOUR_QUADS));
// Assert.assertEquals(yakuAns.getFourQuadSource(), TileSource.OPPOSED_PLAYER);
// }, false);
//
// rule = oldRule;
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 三暗刻
// *
// */
// @Test
// public void testThreeConcealedTriplets() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "3s[2]", "3s[3]", "3p[1]", "3p[1]", "3p[1]", "3m[1]",
// "3m[1]", "3m[1]", "5p[1]", "6p[1]", "7p[1]", "8s[2]");
//
// test(concealedHand, 1, Arrays.asList(TRIPLE_TRIPLETS, THREE_CONCEALED_TRIPLETS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 四暗刻单骑
// *
// */
// @Test
// public void testSingleWaitFourConcealedTriplets() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "3s[2]", "3s[3]", "3p[1]", "3p[1]", "3p[1]", "3m[1]",
// "3m[1]", "3m[1]", "5p[1]", "5p[1]", "5p[1]", "8s[2]");
//
// test(concealedHand, 1, Arrays.asList(SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 四暗刻
// *
// */
// @Test
// public void testFourConcealedTriplets() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "3s[2]", "3s[3]", "3p[1]", "3p[1]", "3p[1]", "3m[1]",
// "3m[1]", "3m[1]", "5p[1]", "5p[1]", "8s[1]", "8s[2]");
//
// test(concealedHand, 2, Arrays.asList(FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 三暗刻
// *
// */
// @Test
// public void testThreeConcealedTripletsWhenTsumo() {
// List<Tile> concealedHand = StringToTile.getList("3s[1]", "4s[2]", "5s[3]", "3p[1]", "3p[1]", "3p[1]", "3m[1]",
// "3m[1]", "3m[1]", "5p[1]", "5p[1]", "8s[1]", "8s[2]");
//
// test(concealedHand, 2, Arrays.asList(THREE_CONCEALED_TRIPLETS_WHEN_TSUMO), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 小三元
// *
// */
// @Test
// public void testThreeLittleDragon() {
// List<Tile> concealedHand = StringToTile.getList("白[1]", "白[2]", "白[3]", "发[1]", "发[1]", "发[1]", "中[1]", "中[1]",
// "4p[1]", "5p[1]", "6p[1]", "8s[1]", "9s[2]");
//
// test(concealedHand, 1, Arrays.asList(LITTLE_THREE_DRAGONS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 大三元、字一色
// *
// */
// @Test
// public void testThreeBigDragonAndAllHonors() {
// List<Tile> concealedHand = StringToTile.getList("白[1]", "白[2]", "白[3]", "发[1]", "发[1]", "发[1]", "中[1]", "中[1]",
// "中[1]", "南[1]", "南[1]", "南[1]", "北[2]");
//
// test(concealedHand, 1, Arrays.asList(BIG_THREE_DRAGONS, ALL_HONORS), Arrays.asList(), 0);
//
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 大三元 包牌
// *
// */
// @Test
// public void testThreeBigDragon2() {
// List<Tile> concealedHand = StringToTile.getList("8p[1]");
//
// Meld ncm = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("白[1]", "白[2]", "白[3]", "白[4]"), StringToTile.str2Tile("白[1]"),
// TileSource.LAST_PLAYER, true, 4);
//
// Meld ncm1 =
// new Meld(MeldType.TRIPLET, StringToTile.getArray("中[1]", "中[2]", "中[3]"),
// StringToTile.str2Tile("中[1]"), TileSource.OPPOSED_PLAYER, false, 0);
//
// Meld ncm2 =
// new Meld(MeldType.SEQUENCE, StringToTile.getArray("4s[1]", "5s[2]", "6s[3]"),
// StringToTile.str2Tile("5s[1]"), TileSource.LAST_PLAYER, false, 0);
//
// Meld ncm3 =
// new Meld(MeldType.TRIPLET, StringToTile.getArray("发[1]", "发[2]", "发[3]"),
// StringToTile.str2Tile("发[1]"), TileSource.NEXT_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm, ncm1, ncm2, ncm3);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, 0, (yakuAns, tpn, a) -> {
// Assert.assertTrue(yakuAns.getYaku().contains(BIG_THREE_DRAGONS));
// Assert.assertEquals(yakuAns.getThreeDragonSource(), TileSource.NEXT_PLAYER);
// }, false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 混老头
// *
// */
// @Test
// public void testAllTerminalsAndHonors() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "1s[2]", "9p[3]", "9p[1]", "发[1]", "发[1]", "中[1]",
// "中[1]", "1m[1]", "1m[1]", "南[1]", "南[1]", "北[2]");
//
// test(concealedHand, 1, Arrays.asList(ALL_TERMINALS_AND_HONORS, SEVEN_PAIRS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 清老头
// *
// */
// @Test
// public void testAllTerminals() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "1s[2]", "1s[3]", "9p[1]", "9p[1]", "9p[1]", "1m[1]",
// "1m[1]", "1m[1]", "9m[1]", "9m[1]", "9m[1]", "9s[2]");
//
// test(concealedHand, 1, Arrays.asList(SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS, ALL_TERMINALS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 混全带幺九
// *
// */
// @Test
// public void testHalfOutsideHand() {
// List<Tile> concealedHand =
// StringToTile.getList("7s[1]", "8s[1]", "9s[1]", "中[1]", "中[1]", "中[1]", "1m[1]", "南[1]", "南[1]", "南[2]");
//
// Meld ncm = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("1p[1]", "1p[2]", "1p[3]", "1p[4]"), StringToTile.str2Tile("1p[1]"),
// TileSource.OPPOSED_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, Arrays.asList(HALF_OUTSIDE_HAND), Arrays.asList(), 0, false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 纯全带幺九
// *
// */
// @Test
// public void testFullOutsideHand() {
// List<Tile> concealedHand = StringToTile.getList("7s[1]", "8s[1]", "9s[1]", "1p[1]", "2p[1]",
// "3p[1]", "1m[1]", "7m[1]", "8m[1]", "9m[2]");
//
// Meld ncm = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("1p[1]", "1p[2]", "1p[3]", "1p[4]"), StringToTile.str2Tile("1p[1]"),
// TileSource.OPPOSED_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, Arrays.asList(FULL_OUTSIDE_HAND), Arrays.asList(), 0, false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 一气通贯
// *
// */
// @Test
// public void testRureStraight() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "2s[2]", "3s[3]", "4s[1]", "5s[1]", "6s[1]", "7s[1]",
// "8s[1]", "9s[1]", "5p[1]", "6p[1]", "7p[1]", "8m[2]");
//
// test(concealedHand, 1, Arrays.asList(PURE_STRAIGHT), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 混一色
// *
// */
// @Test
// public void testHalfFlush() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "2s[2]", "3s[3]", "4s[1]", "5s[1]", "6s[1]", "7s[1]",
// "8s[1]", "9s[1]", "东[1]", "东[1]", "东[1]", "南[2]");
//
// test(concealedHand, 1, Arrays.asList(HALF_FLUSH), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 清一色
// *
// */
// @Test
// public void testFullFlush() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "2s[2]", "3s[3]", "4s[1]", "5s[1]", "6s[1]", "7s[1]",
// "8s[1]", "9s[1]", "1s[1]", "1s[1]", "1s[1]", "4s[2]");
//
// test(concealedHand, 2, Arrays.asList(FULL_FLUSH), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 绿一色
// *
// */
// @Test
// public void testAllGreen() {
// List<Tile> concealedHand = StringToTile.getList("2s[1]", "2s[2]", "2s[3]", "3s[1]", "3s[1]", "3s[1]", "4s[1]",
// "4s[1]", "4s[1]", "6s[1]", "6s[1]", "6s[1]", "发[2]");
//
// test(concealedHand, 1, Arrays.asList(ALL_GREENS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 国士无双
// *
// */
// @Test
// public void testthirteenOrphans() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "9s[2]", "1p[1]", "9p[1]", "1m[1]", "9m[1]", "白[1]",
// "发[1]", "中[1]", "东[1]", "南[1]", "西[2]", "西[2]");
//
// test(concealedHand, 1, Arrays.asList(THIRTEEN_ORPHANS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 国士无双十三面
// *
// */
// @Test
// public void testthirteenOrphans2() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "9s[2]", "1p[1]", "9p[1]", "1m[1]", "9m[1]", "白[1]",
// "发[1]", "中[1]", "东[1]", "南[1]", "西[2]", "北[2]");
//
// test(concealedHand, 13, Arrays.asList(THIRTEEN_WAIT_THIRTEEN_ORPHANS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 小四喜
// *
// */
// @Test
// public void testFourLittleWind() {
// List<Tile> concealedHand = StringToTile.getList("东[1]", "东[2]", "东[3]", "南[1]", "南[1]", "南[1]", "西[1]", "西[1]",
// "4p[1]", "5p[1]", "北[1]", "北[1]", "北[2]");
//
// test(concealedHand, 2, Arrays.asList(FOUR_LITTLE_WINDS), Arrays.asList(), 0);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 大四喜 包牌
// *
// */
// @Test
// public void testFourBigWind() {
// List<Tile> concealedHand = StringToTile.getList("8p[1]");
//
// Meld ncm = new Meld(MeldType.EXPOSED_QUAD,
// StringToTile.getArray("东[1]", "东[2]", "东[3]", "东[4]"), StringToTile.str2Tile("东[1]"),
// TileSource.LAST_PLAYER, true, 4);
//
// Meld ncm1 =
// new Meld(MeldType.TRIPLET, StringToTile.getArray("南[1]", "南[2]", "南[3]"),
// StringToTile.str2Tile("南[1]"), TileSource.OPPOSED_PLAYER, false, 0);
//
// Meld ncm2 =
// new Meld(MeldType.SEQUENCE, StringToTile.getArray("北[1]", "北[2]", "北[3]"),
// StringToTile.str2Tile("北[1]"), TileSource.NEXT_PLAYER, false, 0);
//
// Meld ncm3 =
// new Meld(MeldType.TRIPLET, StringToTile.getArray("西[1]", "西[2]", "西[3]"),
// StringToTile.str2Tile("西[1]"), TileSource.LAST_PLAYER, false, 0);
//
//
// List<Meld> makeCall = Arrays.asList(ncm, ncm1, ncm2, ncm3);
//
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 1, 0, (yakuAns, tpn, a) -> {
// Assert.assertTrue(yakuAns.getYaku().contains(FOUR_BIG_WINDS));
// Assert.assertEquals(yakuAns.getBigWindSource(), TileSource.LAST_PLAYER);
// }, false);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 九莲宝灯
// *
// */
// @Test
// public void testNineGate() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "1s[2]", "1s[3]", "1s[1]", "2s[1]",
// "3s[1]", "4s[1]", "6s[1]", "7s[1]", "8s[1]", "9s[1]", "9s[1]", "9s[2]");
//
// List<Meld> makeCall = Collections.emptyList();
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 2, 0, (yakuAns, tpn, tileToWin) -> {
// if (tileToWin == CountNum.S5) {
// Assert.assertTrue(yakuAns.getYaku().contains(NINE_GATES));
// }
// }, true);
// }
//
// /**
// *
// * @Title: testPureDoubleSequence
// *
// * @Description: 纯正九莲宝灯
// *
// */
// @Test
// public void testTrueNineGate() {
// List<Tile> concealedHand = StringToTile.getList("1s[1]", "1s[2]", "1s[3]", "2s[1]", "3s[1]",
// "4s[1]", "5s[1]", "6s[1]", "7s[1]", "8s[1]", "9s[1]", "9s[1]", "9s[2]");
//
// List<Meld> makeCall = Collections.emptyList();
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, 9, 0, (yakuAns, tpn, tileToWin) -> {
// Assert.assertTrue(yakuAns.getYaku().contains(TRUE_NINE_GATES));
// }, true);
// }
//
// private void test(List<Tile> concealedHand, List<Meld> makeCall, List<Meld> concealedKan,
// int tenpaiTypeNum, List<Yaku> yakuShouldHave, List<Yaku> yakuShouldNotHave, int redFive, boolean isMenzenchin) {
// CountNum counter = new CountNum(concealedHand);
//
// AnalyzeTenpai tenpai = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);
//
// Map<Integer, List<TilePatternNode>> ans = tenpai.isTenpai();
//
// counter.add(makeCall, concealedKan);
//
// Assert.assertEquals(tenpaiTypeNum, ans.size());
//
// for (Entry<Integer, List<TilePatternNode>> entry : ans.entrySet()) {
//
// for (TilePatternNode tpn : entry.getValue()) {
// CountPatternYaku countPatternYaku =
// new CountPatternYaku(rule, TileType.WIND_EAST, TileType.WIND_EAST, tpn, entry.getKey(), counter);
// YakuAns yakuAns = countPatternYaku.countYaku();
//
// Assert.assertTrue(yakuAns.isMenzenchin() == isMenzenchin);
//
//
// Assert.assertEquals(redFive, yakuAns.getRedFive());
//
// Set<Yaku> yakuSet = yakuAns.getYaku();
//
// for (Yaku shouldHave : yakuShouldHave) {
// Assert.assertTrue("" + shouldHave, yakuSet.contains(shouldHave));
// }
//
// for (Yaku shouldNotHave : yakuShouldNotHave) {
// Assert.assertTrue("" + shouldNotHave, !yakuSet.contains(shouldNotHave));
// }
// }
// }
// }
//
// private void test(List<Tile> concealedHand, int tenpaiTypeNum, List<Yaku> yakuShouldHave,
// List<Yaku> yakuShouldNotHave, int redFive) {
//
// List<Meld> makeCall = Collections.emptyList();
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, tenpaiTypeNum, yakuShouldHave, yakuShouldNotHave, redFive, true);
// }
//
//
// private void test(List<Tile> concealedHand, List<Meld> makeCall, List<Meld> concealedKan,
// int tenpaiTypeNum, int redFive, TestYaku testYaku, boolean isMenzenchin) {
// CountNum counter = new CountNum(concealedHand);
//
// AnalyzeTenpai tenpai = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);
//
// Map<Integer, List<TilePatternNode>> ans = tenpai.isTenpai();
//
// counter.add(makeCall, concealedKan);
//
// Assert.assertEquals(tenpaiTypeNum, ans.size());
//
// for (Entry<Integer, List<TilePatternNode>> entry : ans.entrySet()) {
//
// for (TilePatternNode tpn : entry.getValue()) {
// CountPatternYaku countPatternYaku =
// new CountPatternYaku(rule, TileType.WIND_EAST, TileType.WIND_EAST, tpn, entry.getKey(), counter);
// YakuAns yakuAns = countPatternYaku.countYaku();
//
// Assert.assertTrue(yakuAns.isMenzenchin() == isMenzenchin);
//
//
// Assert.assertEquals(redFive, yakuAns.getRedFive());
//
// Set<Yaku> yakuSet = yakuAns.getYaku();
//
// testYaku.testYaku(yakuSet, tpn);
// }
// }
// }
//
// private void test(List<Tile> concealedHand, List<Meld> makeCall, List<Meld> concealedKan,
// int tenpaiTypeNum, int redFive, TestYakuAns testYaku, boolean isMenzenchin) {
// CountNum counter = new CountNum(concealedHand);
//
// AnalyzeTenpai tenpai = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);
//
// Map<Integer, List<TilePatternNode>> ans = tenpai.isTenpai();
//
// counter.add(makeCall, concealedKan);
//
// Assert.assertEquals(tenpaiTypeNum, ans.size());
//
// for (Entry<Integer, List<TilePatternNode>> entry : ans.entrySet()) {
//
// for (TilePatternNode tpn : entry.getValue()) {
// CountPatternYaku countPatternYaku =
// new CountPatternYaku(rule, TileType.WIND_EAST, TileType.WIND_EAST, tpn, entry.getKey(), counter);
// YakuAns yakuAns = countPatternYaku.countYaku();
//
// Assert.assertTrue(yakuAns.isMenzenchin() == isMenzenchin);
//
//
// Assert.assertEquals(redFive, yakuAns.getRedFive());
//
// testYaku.testYaku(yakuAns, tpn, entry.getKey());
// }
// }
// }
//
//
// private void test(List<Tile> concealedHand, int tenpaiTypeNum, int redFive, TestYaku testYaku) {
// List<Meld> makeCall = Collections.emptyList();
// List<Meld> concealedKan = Collections.emptyList();
//
// test(concealedHand, makeCall, concealedKan, tenpaiTypeNum, redFive, testYaku, true);
// }
//
// interface TestYaku {
// public void testYaku(Set<Yaku> yakuSet, TilePatternNode tpn);
// }
//
// interface TestYakuAns {
// public void testYaku(YakuAns yakuAns, TilePatternNode tpn, int tileToWin);
// }
//
// public static void main(String[] args) {
// new TestCountPatternYaku().testNineGate();
// }
// }
