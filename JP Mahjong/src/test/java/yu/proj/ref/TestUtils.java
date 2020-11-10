package yu.proj.ref;

import org.junit.Assert;

/**  
 * @ClassName: Utils  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */
public class TestUtils {

    public static void expectExceptionOrError(Runnable runnable) {
        boolean flag = false;
        try {
            runnable.run();
            flag = true;
        } catch (Throwable t) {
            // success
        }
        if (flag) {
            Assert.fail("should throw Exception or Error");
        }
    }

}
