package yu.proj.jpmahjong.player;

/**  
 * @ClassName: PlayerLocation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public enum TileSource {
    NEXT_PLAYER, OPPOSED_PLAYER, LAST_PLAYER, TILE_WALL, SELF;

    public static TileSource getTileSouce(int playerIndex) {
        switch (playerIndex) {
            case 1:
                return LAST_PLAYER;
            case 2:
                return OPPOSED_PLAYER;
            case 3:
                return NEXT_PLAYER;
        }
        return null;
    }
}
