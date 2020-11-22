package yu.proj.ref.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**  
 * @ClassName: Entry  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */

@Getter
@AllArgsConstructor
public class KeyValuePair<K, V> {

    private K key;
    private V value;

}
