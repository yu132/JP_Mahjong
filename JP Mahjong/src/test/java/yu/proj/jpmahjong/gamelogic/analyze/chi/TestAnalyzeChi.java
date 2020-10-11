package yu.proj.jpmahjong.gamelogic.analyze.chi;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.testUtil.StringToTile;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: TestAnalyzeChi  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月16日  
 *  
 */
public class TestAnalyzeChi {

    /**
     * 
     * @Title: test  
     *
     * @Description: 顺序吃  
     *
     * @throws  
     *
     */
    @Test
    public void test() {
        List<Tile>              colcealedHand = StringToTile.getList("3s[1]", "4s[2]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 2);

        Assert.assertTrue(map.containsKey(S2));
        Assert.assertTrue(map.containsKey(S5));
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 顺序吃2
     *
     * @throws  
     *
     */
    @Test
    public void test2() {
        List<Tile>              colcealedHand = StringToTile.getList("1s[1]", "2s[2]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 1);

        Assert.assertTrue(map.containsKey(S3));
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 顺序吃3
     *
     * @throws  
     *
     */
    @Test
    public void test3() {
        List<Tile>              colcealedHand = StringToTile.getList("8s[1]", "9s[2]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 1);

        Assert.assertTrue(map.containsKey(S7));
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 顺序吃4 无红宝牌
     *
     * @throws  
     *
     */
    @Test
    public void test4() {
        List<Tile>              colcealedHand = StringToTile.getList("4s[1]", "5s[2]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 2);

        Assert.assertTrue(map.containsKey(S3));
        Assert.assertTrue(map.containsKey(S6));

        Chi chi = map.get(S3).get(0);

        Assert.assertFalse(chi.hasRedTile());
        Assert.assertTrue(chi.hasNormalTile());
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 顺序吃5 有红宝牌
     *
     * @throws  
     *
     */
    @Test
    public void test5() {
        List<Tile>              colcealedHand = StringToTile.getList("4s[1]", "0s[4]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 2);

        Assert.assertTrue(map.containsKey(S3));
        Assert.assertTrue(map.containsKey(S6));

        Chi chi = map.get(S3).get(0);

        Assert.assertTrue(chi.hasRedTile());
        Assert.assertFalse(chi.hasNormalTile());
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 顺序吃6 有红宝牌且有普通牌
     *
     * @throws  
     *
     */
    @Test
    public void test6() {
        List<Tile>              colcealedHand = StringToTile.getList("4s[1]", "0s[4]", "5s[3]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 2);

        Assert.assertTrue(map.containsKey(S3));
        Assert.assertTrue(map.containsKey(S6));

        Chi chi = map.get(S3).get(0);

        Assert.assertTrue(chi.hasRedTile());
        Assert.assertTrue(chi.hasNormalTile());
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 坎张吃 
     *
     * @throws  
     *
     */
    @Test
    public void test11() {
        List<Tile>              colcealedHand = StringToTile.getList("1s[1]", "3s[4]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 1);

        Assert.assertTrue(map.containsKey(S2));
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 坎张吃2 无红宝牌
     *
     * @throws  
     *
     */
    @Test
    public void test12() {
        List<Tile>              colcealedHand = StringToTile.getList("3s[1]", "5s[1]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 1);

        Assert.assertTrue(map.containsKey(S4));

        Chi chi = map.get(S4).get(0);

        Assert.assertFalse(chi.hasRedTile());
        Assert.assertTrue(chi.hasNormalTile());
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 坎张吃3 有红宝牌
     *
     * @throws  
     *
     */
    @Test
    public void test13() {
        List<Tile>              colcealedHand = StringToTile.getList("3s[1]", "0s[4]", "1p[1]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 1);

        Assert.assertTrue(map.containsKey(S4));

        Chi chi = map.get(S4).get(0);

        Assert.assertTrue(chi.hasRedTile());
        Assert.assertFalse(chi.hasNormalTile());
    }

    /**
     * 
     * @Title: test  
     *
     * @Description: 坎张吃4 红宝牌、普通牌都有
     *
     * @throws  
     *
     */
    @Test
    public void test14() {
        List<Tile>              colcealedHand = StringToTile.getList("3s[1]", "0s[4]", "5s[2]", "1p[2]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 1);

        Assert.assertTrue(map.containsKey(S4));

        Chi chi = map.get(S4).get(0);

        Assert.assertTrue(chi.hasRedTile());
        Assert.assertTrue(chi.hasNormalTile());
    }


    /**
     * 
     * @Title: test  
     *
     * @Description: 多面吃
     *
     * @throws  
     *
     */
    @Test
    public void test20() {
        List<Tile>              colcealedHand =
            StringToTile.getList("1s[1]", "2s[1]", "3s[1]", "4s[1]", "5s[1]", "6s[1]", "7s[1]", "8s[1]", "9s[1]");

        CountNum                countNum      = new CountNum(colcealedHand);

        AnalyzeChi              analyzeChi    = new AnalyzeChi();

        Map<Integer, List<Chi>> map           = analyzeChi.analyzeChi(countNum);

        Assert.assertEquals(map.size(), 9);
    }

}
