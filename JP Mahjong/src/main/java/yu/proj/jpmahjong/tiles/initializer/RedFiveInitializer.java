package yu.proj.jpmahjong.tiles.initializer;

import static yu.proj.jpmahjong.tiles.TileType.*;

import java.util.Arrays;

import yu.proj.jpmahjong.define.Numbers;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;

/**  
 * @ClassName: NoRedFiveInitializer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class RedFiveInitializer implements TilesInitializer {

    private int redFiveManNumber;
    private int redFivePinNumber;
    private int redFiveSouNumber;

    private Tile[] tiles = null;

    public RedFiveInitializer(int redFiveManNumber, int redFivePinNumber, int redFiveSouNumber) {
        super();
        this.redFiveManNumber = redFiveManNumber;
        this.redFivePinNumber = redFivePinNumber;
        this.redFiveSouNumber = redFiveSouNumber;
    }

    @Override
    public Tile[] init() {

        if (tiles == null) {

            tiles = new Tile[Numbers.NUMBER_OF_TILES];

            int index = 0;

            index = GenNumbers(tiles, index, MAN, "m", redFiveManNumber);// 万子

            index = GenNumbers(tiles, index, PIN, "p", redFivePinNumber);// 饼子

            index = GenNumbers(tiles, index, SOU, "s", redFiveSouNumber);// 索子

            // 风牌
            for (int i = 1; i <= 4; ++i) { // 东风
                tiles[index++] = new Tile(WIND_EAST, "东", 0, i, false);
            }
            for (int i = 1; i <= 4; ++i) { // 南风
                tiles[index++] = new Tile(WIND_SOUTH, "南", 0, i, false);
            }
            for (int i = 1; i <= 4; ++i) { // 西风
                tiles[index++] = new Tile(WIND_WEST, "西", 0, i, false);
            }
            for (int i = 1; i <= 4; ++i) { // 北风
                tiles[index++] = new Tile(WIND_NORTH, "北", 0, i, false);
            }

            // 三元牌
            for (int i = 1; i <= 4; ++i) { // 白
                tiles[index++] = new Tile(DRAGON_WHITE, "白", 0, i, false);
            }
            for (int i = 1; i <= 4; ++i) { // 发
                tiles[index++] = new Tile(DRAGON_GREEN, "发", 0, i, false);
            }
            for (int i = 1; i <= 4; ++i) { // 中
                tiles[index++] = new Tile(DRAGON_RED, "中", 0, i, false);
            }
        }

        return Arrays.copyOf(tiles, tiles.length);
    }

    private int GenNumbers(Tile[] tiles, int index, TileType type, String strId, int numOfRedFive) {
        for (int i = 1; i <= 9; ++i) {
            if (i != 5) {
                for (int j = 1; j <= 4; ++j) {
                    tiles[index++] = new Tile(type, strId, i, j, false);
                }
            } else {
                for (int j = 1; j <= 4; ++j) {
                    tiles[index++] = new Tile(type, strId, i, j, j > 4 - numOfRedFive);
                }
            }
        }
        return index;
    }

}
