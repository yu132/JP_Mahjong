package yu.proj.ref;

import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.Kita;
import yu.proj.ref.meld.Meld;
import yu.proj.ref.ops.AbstractGainAndExposedTileOperation;
import yu.proj.ref.ops.AddKanOperation;
import yu.proj.ref.ops.ConcealedKanOperation;
import yu.proj.ref.ops.DiscardOperation;
import yu.proj.ref.ops.DrawOperation;
import yu.proj.ref.ops.ExposedKanOperation;
import yu.proj.ref.ops.PonOperation;

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
public interface TileCounter {

    /**
     * @Title: count  
     * @Description: 返回牌型对应的没有鸣牌（吃碰杠（包括暗杠））的牌的数量，用于吃碰杠判断下的计数，
     */
    int countInHand(TileType tileType);


    /**
     * @Title: countKanAs3Tile  
     * @Description: 将杠的4张牌当作3张，并且将红宝牌当作一般的牌进行计数时，牌的数量
     *               用于牌型判断时的计数
     */
    int countKanAs3TileAndRedAsNormal(TileType tileType);

    List<Meld> getMeld();

    void chi(AbstractGainAndExposedTileOperation chi);

    void pon(PonOperation pon);

    void exposedKan(ExposedKanOperation kan);

    /**
     * @Title: draw  
     * @Description: 摸的牌 
     */
    void draw(DrawOperation draw);

    void concealedKan(ConcealedKanOperation kan);

    void discard(DiscardOperation discard);

    void kita(Kita kita);

    void addKan(AddKanOperation kan);

}
