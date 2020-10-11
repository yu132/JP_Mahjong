package yu.proj.jpmahjong.define.tiles.initializer;

import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.initializer.RedFiveInitializer;

/**  
 * @ClassName: TestRedFiveInitializer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class TestRedFiveInitializer {

    public static void main(String[] args) {
        RedFiveInitializer redinit = new RedFiveInitializer(1, 1, 1);
        Tile[]             tiles   = redinit.init();

        for (Tile tile : tiles) {
            System.out.println(tile);
        }
    }

}
