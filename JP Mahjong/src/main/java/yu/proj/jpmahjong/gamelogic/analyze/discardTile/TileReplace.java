package yu.proj.jpmahjong.gamelogic.analyze.discardTile;

import java.util.Iterator;
import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.player.operation.getTileOperation.ChiOperation;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: TileReplace  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年10月10日  
 *  
 */
public class TileReplace {

    public static void tileReplace(List<Tile> tileAns, ChiOperation chi, Rule rule) {
        if (chi != null) {
            for (Iterator<Tile> it = tileAns.iterator(); it.hasNext();) {// 食替
                Tile tile = it.next();

                if (!rule.enableTendonReplace) {
                    if (CountNum.getTileIndex(tile) == chi.getChi().getTendonReplace()) {// 筋食替
                        it.remove();
                    }
                }
                if (!rule.enableSameTileReplace) {
                    if (CountNum.getTileIndex(tile) == chi.getChi().getTileToChi()) {// 现物食替
                        it.remove();
                    }
                }
            }
        }
    }

}
