package yu.proj.jpmahjong.define.tiles.manager;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import yu.proj.jpmahjong.define.Numbers;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.initializer.RedFiveInitializer;
import yu.proj.jpmahjong.tiles.manager.FourPlayerManager;

/**  
 * @ClassName: TestBaseManager  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class TestBaseManager {

    /**
     * 
     * @Title: test1  
     *
     * @Description: 基本测试，一次完整的摸牌，直到流局
     *
     * @return void 
     *
     * @throws  
     *
     */
    @Test
    public void test1() {
        RedFiveInitializer redInit = new RedFiveInitializer(1, 1, 1);

        Tile[]             tiles   = redInit.init();

        FourPlayerManager        bm      = new FourPlayerManager(tiles);

        Assert.assertEquals("发[4]", bm.doraIndicator()[0].toString());
        Assert.assertEquals("发[3]", bm.uraDoraIndicator()[0].toString());

        for (int i = 0; i < Numbers.NUMBER_OF_TILES - Numbers.NUMBER_OF_LAST_TILES; ++i) {
            Assert.assertEquals(tiles[i], bm.draw());
        }

        Assert.assertTrue(bm.isDraw());

        try {
            bm.draw();// 这里应该会由于荒牌流局，理应抛出一个异常
            Assert.fail();
        } catch (Exception e) {

        }

        try {
            bm.kan();// 这里应该会由于海底开杠，理应抛出一个异常
            Assert.fail();
        } catch (Exception e) {

        }
    }

    /**
     * 
     * @Title: test2  
     *
     * @Description: 测试开杠和宝牌  
     *
     * @return void 
     *
     * @throws  
     *
     */
    @Test
    public void test2() {

        RedFiveInitializer redInit = new RedFiveInitializer(1, 1, 1);
        Tile[]             tiles   = redInit.init();

        FourPlayerManager        bm      = new FourPlayerManager(tiles);

        // 未开杠时的宝牌
        Assert.assertEquals(1, bm.doraIndicator().length);
        Assert.assertEquals(1, bm.uraDoraIndicator().length);
        Assert.assertEquals("发[4]", bm.doraIndicator()[0].toString());
        Assert.assertEquals("发[3]", bm.uraDoraIndicator()[0].toString());

        // 第一次开杠
        Assert.assertEquals("中[4]", bm.kan().toString());

        Assert.assertEquals(2, bm.doraIndicator().length);
        Assert.assertEquals(2, bm.uraDoraIndicator().length);

        Assert.assertEquals("发[4]", bm.doraIndicator()[0].toString());
        Assert.assertEquals("发[2]", bm.doraIndicator()[1].toString());

        Assert.assertEquals("发[3]", bm.uraDoraIndicator()[0].toString());
        Assert.assertEquals("发[1]", bm.uraDoraIndicator()[1].toString());

        // 第二次开杠
        Assert.assertEquals("中[3]", bm.kan().toString());

        Assert.assertEquals(3, bm.doraIndicator().length);
        Assert.assertEquals(3, bm.uraDoraIndicator().length);

        Assert.assertEquals("发[4]", bm.doraIndicator()[0].toString());
        Assert.assertEquals("发[2]", bm.doraIndicator()[1].toString());
        Assert.assertEquals("白[4]", bm.doraIndicator()[2].toString());

        Assert.assertEquals("发[3]", bm.uraDoraIndicator()[0].toString());
        Assert.assertEquals("发[1]", bm.uraDoraIndicator()[1].toString());
        Assert.assertEquals("白[3]", bm.uraDoraIndicator()[2].toString());

        // 第三次开杠
        Assert.assertEquals("中[2]", bm.kan().toString());

        Assert.assertEquals(4, bm.doraIndicator().length);
        Assert.assertEquals(4, bm.uraDoraIndicator().length);

        Assert.assertEquals("发[4]", bm.doraIndicator()[0].toString());
        Assert.assertEquals("发[2]", bm.doraIndicator()[1].toString());
        Assert.assertEquals("白[4]", bm.doraIndicator()[2].toString());
        Assert.assertEquals("白[2]", bm.doraIndicator()[3].toString());

        Assert.assertEquals("发[3]", bm.uraDoraIndicator()[0].toString());
        Assert.assertEquals("发[1]", bm.uraDoraIndicator()[1].toString());
        Assert.assertEquals("白[3]", bm.uraDoraIndicator()[2].toString());
        Assert.assertEquals("白[1]", bm.uraDoraIndicator()[3].toString());

        // 第四次开杠
        Assert.assertEquals("中[1]", bm.kan().toString());

        Assert.assertEquals(5, bm.doraIndicator().length);
        Assert.assertEquals(5, bm.uraDoraIndicator().length);

        Assert.assertEquals("发[4]", bm.doraIndicator()[0].toString());
        Assert.assertEquals("发[2]", bm.doraIndicator()[1].toString());
        Assert.assertEquals("白[4]", bm.doraIndicator()[2].toString());
        Assert.assertEquals("白[2]", bm.doraIndicator()[3].toString());
        Assert.assertEquals("北[4]", bm.doraIndicator()[4].toString());

        Assert.assertEquals("发[3]", bm.uraDoraIndicator()[0].toString());
        Assert.assertEquals("发[1]", bm.uraDoraIndicator()[1].toString());
        Assert.assertEquals("白[3]", bm.uraDoraIndicator()[2].toString());
        Assert.assertEquals("白[1]", bm.uraDoraIndicator()[3].toString());
        Assert.assertEquals("北[3]", bm.uraDoraIndicator()[4].toString());

        Assert.assertEquals(null, bm.kan());
    }

    /**
     * 
     * @Title: test3  
     *
     * @Description: 测试王牌数量不变
     *
     * @return void 
     *
     * @throws  
     *
     */
    @Test
    public void test3() {

        RedFiveInitializer redInit = new RedFiveInitializer(1, 1, 1);
        Tile[]             tiles   = redInit.init();


        for (int x = 1; x <= 4; ++x) {
            FourPlayerManager bm = new FourPlayerManager(tiles);

            for (int j = 0; j < x; ++j) {
                bm.kan();
            }

            for (int i = 0; i < Numbers.NUMBER_OF_TILES - Numbers.NUMBER_OF_LAST_TILES - x; ++i) {
                bm.draw();
            }

            Assert.assertTrue(bm.isDraw());

            try {
                bm.draw();// 这里应该会由于荒牌流局，理应抛出一个异常
                Assert.fail();
            } catch (Exception e) {

            }
        }
    }

    /**
     * 
     * @Title: test4  
     *
     * @Description: 测试海底开杠
     *
     * @return void 
     *
     * @throws  
     *
     */
    @Test
    public void test4() {
        RedFiveInitializer redInit = new RedFiveInitializer(1, 1, 1);
        Tile[]             tiles   = redInit.init();

        for (int x = 1; x <= 4; ++x) {
            FourPlayerManager bm = new FourPlayerManager(tiles);

            for (int i = 1; i <= x; ++i) {
                bm.kan();
            }

            for (int i = 0; i < Numbers.NUMBER_OF_TILES - Numbers.NUMBER_OF_LAST_TILES - x; ++i) {
                bm.draw();
            }

            try {
                bm.kan();// 这里应该会由于海底开杠，理应抛出一个异常
                Assert.fail();
            } catch (Exception e) {

            }
        }
    }

}
