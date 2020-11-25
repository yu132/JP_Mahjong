package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import static yu.proj.ref.tile.TileType.*;

import java.util.Collections;
import java.util.EnumMap;

import yu.proj.ref.tile.Tile;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: PlayerTileGetter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月23日  
 *  
 */

public class PlayerTileInHandGetterImpl implements PlayerTileInHandGetter {

    private PlayerTileManagerImpl playerTileManager;

    private EnumMap<TileType, Integer> used = new EnumMap<>(TileType.class);

    public PlayerTileInHandGetterImpl(PlayerTileManagerImpl playerTileManager) {
        super();
        this.playerTileManager = playerTileManager;
    }

    @Override
    public Tile claim(TileType tileType) {

        if (!hasEnoughTileToClaim(tileType)) {
            if (tileType.getRed() != NONE) {
                return claim(tileType.getRed());// 没有牌的时候，去获取红宝牌作为替代
            }
            assert false;// 没有多的牌可以获取了
        }

        Tile tile = getTile(tileType);
        claimThisTile(tileType);

        return tile;
    }

    private void claimThisTile(TileType tileType) {
        used.put(tileType, index(tileType) + 1);
    }

    private Tile getTile(TileType tileType) {
        return playerTileManager.getTilesInHand().get(tileType).get(index(tileType));
    }

    private Integer index(TileType tileType) {
        return used.getOrDefault(tileType, 0);
    }

    private boolean hasEnoughTileToClaim(TileType tileType) {
        return playerTileManager.getTilesInHand().getOrDefault(tileType, Collections.emptyList())
            .size() > index(tileType);
    }

    @Override
    public void reclaim(TileType tileType) {
        if (index(tileType) == 0) {
            if (tileType.getRed() != NONE) {
                reclaim(tileType.getRed());
                return;
            }
            assert false;
        }

        used.put(tileType, index(tileType) - 1);
    }

}
