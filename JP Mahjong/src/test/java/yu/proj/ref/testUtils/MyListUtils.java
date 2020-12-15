package yu.proj.ref.testUtils;

import java.util.HashSet;
import java.util.List;

/**  
 * @ClassName: Lists  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月14日  
 *  
 */
public class MyListUtils {

    // 元素不重复时可使用
    public static <E> boolean containSameElement(List<E> list1, List<E> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

}
