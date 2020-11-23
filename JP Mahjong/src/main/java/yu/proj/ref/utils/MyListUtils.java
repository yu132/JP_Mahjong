package yu.proj.ref.utils;

import java.util.List;

/**  
 * @ClassName: MyListUtils  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月22日  
 *  
 */
public class MyListUtils {

    public static <T> boolean allNotNull(List<T> list) {
        for (T t : list) {
            if (t == null) {
                return false;
            }
        }
        return true;
    }

}
