package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import yu.proj.ref.ops.tilesRelated.AbstractGainAndExposedAllTileOperation;
import yu.proj.ref.ops.tilesRelated.AddKanOperation;
import yu.proj.ref.ops.tilesRelated.ConcealedKanOperation;
import yu.proj.ref.ops.tilesRelated.DiscardOperation;
import yu.proj.ref.ops.tilesRelated.DrawOperation;
import yu.proj.ref.ops.tilesRelated.ExposedKanOperation;
import yu.proj.ref.ops.tilesRelated.KitaOperation;
import yu.proj.ref.ops.tilesRelated.PonOperation;
import yu.proj.ref.tile.TileType;

/**  
 * @ClassName: TileCounter  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */
public interface PlayerTileManager {

    /**
     * @Title: count  
     * @Description: 返回牌型对应的没有鸣牌（吃碰杠（包括暗杠））的牌的数量，用于吃碰杠判断下的计数，
     */
    int countInHand(TileType tileType);

    int countNormalAndRedInHand(TileType tileType);

    /**
     * @Title: countKanAs3Tile  
     * @Description: 将杠的4张牌当作3张，并且将红宝牌当作一般的牌进行计数时，牌的数量
     *               用于牌型判断时的计数
     */
    int countKanAs3TileAndRedAsNormal(TileType tileType);

    PlayerExposedTilesManager getPlayerExposedTilesManager();

    void chi(AbstractGainAndExposedAllTileOperation chi);

    void pon(PonOperation pon);

    void exposedKan(ExposedKanOperation kan);

    /**
     * @Title: draw  
     * @Description: 摸的牌 
     */
    void draw(DrawOperation draw);

    void concealedKan(ConcealedKanOperation kan);

    void discard(DiscardOperation discard);

    void kita(KitaOperation kita);

    void addKan(AddKanOperation kan);

}
