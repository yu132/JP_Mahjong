package yu.proj.ref.gameLogicChain.game.shared.gameTilesInitializer;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: GameTilesInitializerImpl  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */
public class FourPlayerGameTilesInitializerImpl implements GameTilesInitializer {

    private final static int NUMBER_OF_TILES = 136;

    private int redFiveManNumber;
    private int redFivePinNumber;
    private int redFiveSouNumber;

    public FourPlayerGameTilesInitializerImpl(int redFiveManNumber, int redFivePinNumber, int redFiveSouNumber) {
        super();

        assert checkNumber(redFiveManNumber) && checkNumber(redFivePinNumber) && checkNumber(redFiveSouNumber);

        this.redFiveManNumber = redFiveManNumber;
        this.redFivePinNumber = redFivePinNumber;
        this.redFiveSouNumber = redFiveSouNumber;
    }

    private boolean checkNumber(int numberOfTiles) {
        return numberOfTiles >= 0 && numberOfTiles <= 4;
    }

    @Override
    public Tile[] initializeTiles() {

        List<Tile> tiles = new ArrayList<>();

        initFiveTiles(tiles);

        initOtherTiles(tiles);

        return tiles.toArray(new Tile[NUMBER_OF_TILES]);
    }

    private void initFiveTiles(List<Tile> tiles) {
        initFiveTiles(redFiveManNumber, TileType.MAN_5, TileType.MAN_5_RED, tiles);
        initFiveTiles(redFivePinNumber, TileType.PIN_5, TileType.PIN_5_RED, tiles);
        initFiveTiles(redFiveSouNumber, TileType.SOU_5, TileType.SOU_5_RED, tiles);
    }

    private void initFiveTiles(int numOfRed, TileType normalType, TileType redType, List<Tile> tiles) {
        initTileFromIndexToIndex(normalType, 0, 4 - numOfRed, tiles);
        initTileFromIndexToIndex(redType, 4 - numOfRed, 4, tiles);
    }

    private void initOtherTiles(List<Tile> tiles) {
        for (TileType type : TileType.values()) {
            if (isnotFiveOrNone(type)) {
                initTileFromIndexToIndex(type, 0, 4, tiles);
            }
        }
    }

    private void initTileFromIndexToIndex(TileType type, int indexFrom, int indexTo, List<Tile> tiles) {
        for (int i = indexFrom; i < indexTo; ++i) {
            tiles.add(Tile.of(type, i));
        }
    }

    private boolean isnotFiveOrNone(TileType type) {
        return !(type == TileType.MAN_5 || type == TileType.MAN_5_RED || type == TileType.PIN_5
            || type == TileType.PIN_5_RED || type == TileType.SOU_5 || type == TileType.SOU_5_RED
            || type == TileType.NONE);
    }

}
