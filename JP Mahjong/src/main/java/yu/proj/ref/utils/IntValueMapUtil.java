package yu.proj.ref.utils;

import java.util.Map;

/**  
 * @ClassName: IntMapUtil  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月28日  
 *  
 */
public class IntValueMapUtil {

    public static <K> void acc(Map<K, Integer> map, K key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public static <K> void accAll(Map<K, Integer> map, Iterable<K> keys) {
        for (K key : keys) {
            acc(map, key);
        }
    }

    public static <K> void dec(Map<K, Integer> map, K key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    public static <K> void decAll(Map<K, Integer> map, Iterable<K> keys) {
        for (K key : keys) {
            dec(map, key);
        }
    }

    public static <K> void pluse(Map<K, Integer> map, K key, int offset) {
        map.put(key, map.getOrDefault(key, 0) + offset);
    }
}
