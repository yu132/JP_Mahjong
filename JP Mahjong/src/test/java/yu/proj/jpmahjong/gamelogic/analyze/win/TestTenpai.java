package yu.proj.jpmahjong.gamelogic.analyze.win;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.win.AnalyzeTenpai;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.testUtil.StringToTile;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.meld.Meld;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;
import yu.proj.jpmahjong.tiles.tenpaiPattern.SevenPairs;
import yu.proj.jpmahjong.tiles.tenpaiPattern.ThirteenOrphans;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.FourMeldAndAPair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Pair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Singleton;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Wait2Side;

/**  
 * @ClassName: TestTenpai  
 *
 * @Description: (这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月16日  
 *  
 */
public class TestTenpai {

    private Rule rule = Rule.MAHJONG_SOUL_RULE;

    /**
     * 
     * @Title: test  
     *
     * @Description: 从最简单的国士无双开始测试，国士无双十三面
     *
     * @throws  
     *
     */
    @Test
    public void testThirteenOrphans() {

        List<Tile>                          concealedHand = StringToTile.getList("1s[1]", "9s[1]", "1m[1]", "9m[1]",
            "1p[1]", "9p[1]", "东[1]", "南[1]", "西[1]", "北[1]", "白[1]", "发[1]", "中[1]");

        CountNum                            counter       = new CountNum(concealedHand);
        List<Meld>              makeCall      = Collections.emptyList();
        List<Meld>              concealedKan  = Collections.emptyList();

        AnalyzeTenpai                              tenpai        = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);

        Map<Integer, List<TilePatternNode>> ans           = tenpai.isTenpai();

        Assert.assertEquals(13, ans.size());

        for (List<TilePatternNode> list : ans.values()) {
            Assert.assertEquals(list.size(), 1);

            Assert.assertTrue(list.get(0) instanceof ThirteenOrphans);
            Assert.assertTrue(((ThirteenOrphans)list.get(0)).isWait13());
        }
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 从最简单的国士无双开始测试，普通国士无双
     *
     * @throws  
     *
     */
    @Test
    public void testThirteenOrphans2() {

        String[] arr = {"1s", "9s", "1m", "9m", "1p", "9p", "东", "南", "西", "北", "白", "发", "中"};

        for (int i = 0; i < 13; ++i) {

            String[] arr1  = new String[12];
            int      index = 0;

            for (int x = 0; x < 13; ++x) {
                if (x == i) {
                    continue;
                }
                arr1[index++] = arr[x];
            }

            for (int j = 0; j < 12; ++j) {

                String[] arr2 = new String[13];

                for (int x = 0; x < 12; ++x) {
                    arr2[x] = arr1[x] + "[1]";
                }

                arr2[12] = arr1[j] + "[2]";

                List<Tile>                          concealedHand = StringToTile.getList(arr2);

                CountNum                            counter       = new CountNum(concealedHand);
                List<Meld>              makeCall      = Collections.emptyList();
                List<Meld>              concealedKan  = Collections.emptyList();

                AnalyzeTenpai                              tenpai        = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);

                Map<Integer, List<TilePatternNode>> ans           = tenpai.isTenpai();

                Assert.assertEquals(1, ans.size());

                for (List<TilePatternNode> list : ans.values()) {
                    Assert.assertEquals(list.size(), 1);

                    Assert.assertTrue(list.get(0) instanceof ThirteenOrphans);
                    Assert.assertTrue(!((ThirteenOrphans)list.get(0)).isWait13());
                }
            }
        }
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 测试七对子 
     *
     * @throws  
     *
     */
    @Test
    public void testSevenPairs() {
        List<Tile>                          concealedHand = StringToTile.getList("1s[1]", "1s[2]", "1m[1]", "1m[2]",
            "1p[1]", "1p[2]", "东[1]", "东[2]", "西[1]", "西[2]", "白[1]", "白[2]", "中[1]");

        CountNum                            counter       = new CountNum(concealedHand);
        List<Meld>              makeCall      = Collections.emptyList();
        List<Meld>              concealedKan  = Collections.emptyList();

        AnalyzeTenpai                              tenpai        = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);

        Map<Integer, List<TilePatternNode>> ans           = tenpai.isTenpai();

        Assert.assertEquals(1, ans.size());

        for (List<TilePatternNode> list : ans.values()) {
            Assert.assertEquals(list.size(), 1);

            Assert.assertTrue(list.get(0) instanceof SevenPairs);

            Assert.assertEquals(((SevenPairs)list.get(0)).getCompleted().size(), 6);

            for (Completed comp : ((SevenPairs)list.get(0)).getCompleted()) {
                Assert.assertTrue(comp instanceof Pair);
            }

            Assert.assertEquals(((SevenPairs)list.get(0)).getNotCompleted().size(), 1);

            NotCompleted ncomp = ((SevenPairs)list.get(0)).getNotCompleted().get(0);

            Assert.assertTrue(ncomp instanceof Singleton);

            Assert.assertEquals(((Singleton)ncomp).getTileToWin()[0], R);
        }
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 测试七对子和二杯口的复合情况
     *
     * @throws  
     *
     */
    @Test
    public void testSevenPairs2() {
        List<Tile>                          concealedHand = StringToTile.getList("1s[1]", "1s[2]", "2s1m[1]", "2s[2]",
            "3s[1]", "3s[2]", "6s[1]", "6s[2]", "7s[1]", "7s[2]", "8s[1]", "中[2]", "中[1]");

        CountNum                            counter       = new CountNum(concealedHand);
        List<Meld>              makeCall      = Collections.emptyList();
        List<Meld>              concealedKan  = Collections.emptyList();

        AnalyzeTenpai                              tenpai        = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);

        Map<Integer, List<TilePatternNode>> ans           = tenpai.isTenpai();

        Assert.assertEquals(2, ans.size());

        for (List<TilePatternNode> list : ans.values()) {
            for (TilePatternNode tpn : list) {
                if (tpn instanceof SevenPairs) {

                    Assert.assertEquals(((SevenPairs)tpn).getCompleted().size(), 6);

                    for (Completed comp : ((SevenPairs)tpn).getCompleted()) {
                        Assert.assertTrue(comp instanceof Pair);
                    }

                    Assert.assertEquals(((SevenPairs)tpn).getNotCompleted().size(), 1);

                    NotCompleted ncomp = ((SevenPairs)tpn).getNotCompleted().get(0);

                    Assert.assertTrue(ncomp instanceof Singleton);

                    Assert.assertEquals(S8, ((Singleton)ncomp).getTileToWin()[0]);

                } else {
                    Assert.assertTrue(tpn instanceof FourMeldAndAPair);

                    int[] tileToWin = ((Wait2Side)((FourMeldAndAPair)tpn).getNotCompleted().get(0)).getTileToWin();

                    Assert.assertEquals(S5, tileToWin[0]);
                    Assert.assertEquals(S8, tileToWin[1]);
                }
            }
        }
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 测试4面子1雀头的终极情况，九莲宝灯
     *
     * @throws  
     *
     */
    @Test
    public void test4m1p() {
        List<Tile>                          concealedHand = StringToTile.getList("1p[1]", "1p[2]", "1p[3]", "2p[1]",
            "3p[1]", "4p[1]", "5p[1]", "6p[2]", "7p[1]", "8p[2]", "9p[1]", "9p[2]", "9p[4]");

        CountNum                            counter       = new CountNum(concealedHand);
        List<Meld>              makeCall      = Collections.emptyList();
        List<Meld>              concealedKan  = Collections.emptyList();

        AnalyzeTenpai                              tenpai        = new AnalyzeTenpai(rule, counter, makeCall, concealedKan);

        Map<Integer, List<TilePatternNode>> ans           = tenpai.isTenpai();

        System.out.println();
        System.out.println();

        // 分析情况
        // 111 23 456 789 99 两面听 1 4
        // 11 123 456 78 999 两面听 6 9

        // 11 123 456 789 99 双碰听 1 9

        // 111 2 345 678 999 单骑听 2
        // 111 234 5 678 999 单骑听 5
        // 111 234 567 8 999 单骑听 8

        // 11 12 345 678 999 边张听 3
        // 111 234 567 89 99 边张听 7

        // 111 234 56 789 99 两面听 4 7
        // 11 123 45 678 999 两面听 3 6


        Assert.assertEquals(2, ans.get(P1).size());
        Assert.assertEquals(1, ans.get(P2).size());
        Assert.assertEquals(2, ans.get(P3).size());
        Assert.assertEquals(2, ans.get(P4).size());
        Assert.assertEquals(1, ans.get(P5).size());
        Assert.assertEquals(2, ans.get(P6).size());
        Assert.assertEquals(2, ans.get(P7).size());
        Assert.assertEquals(1, ans.get(P8).size());
        Assert.assertEquals(2, ans.get(P9).size());
    }

    // TODO 手牌非门清的听牌和不听

    public static void main(String[] args) {
        new TestTenpai().test4m1p();
    }

}
