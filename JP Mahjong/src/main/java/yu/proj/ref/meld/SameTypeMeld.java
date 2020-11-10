package yu.proj.ref.meld;

import yu.proj.ref.Tile;

/**  
 * @ClassName: SameTypeMeld  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月10日  
 *  
 */
public abstract class SameTypeMeld extends Meld {

    public SameTypeMeld(Tile[] tiles, MeldSource src) {
        super(tiles, src);

        assert checkTileType(tiles);
    }

    private static boolean checkTileType(Tile[] tiles) {
        for (int i = 1; i < tiles.length; ++i) {
            if (!(tiles[0].getTileType() == tiles[1].getTileType())) {
                return false;
            }
        }
        return true;
    }

}
