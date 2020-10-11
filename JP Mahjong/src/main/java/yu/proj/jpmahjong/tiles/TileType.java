package yu.proj.jpmahjong.tiles;

import java.util.HashMap;
import java.util.Map;

/**  
 * @ClassName: TileType  
 *
 * @Description: 牌的类别
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public enum TileType {

    MAN, // 万子
    PIN, // 饼子
    SOU, // 索子

    // 风牌
    WIND_EAST, // 东
    WIND_SOUTH, // 南
    WIND_WEST, // 西
    WIND_NORTH, // 北

    // 三元牌
    DRAGON_WHITE, // 白
    DRAGON_GREEN, // 发
    DRAGON_RED;// 中

    private final static Map<String, TileType> map = new HashMap<>();

    static {
        map.put("m", MAN);
        map.put("p", PIN);
        map.put("s", SOU);
        map.put("东", WIND_EAST);
        map.put("南", WIND_SOUTH);
        map.put("西", WIND_WEST);
        map.put("北", WIND_NORTH);
        map.put("白", DRAGON_WHITE);
        map.put("发", DRAGON_GREEN);
        map.put("中", DRAGON_RED);
    }

    public static TileType str2TileType(String str) {
        return map.get(str);
    }

}
