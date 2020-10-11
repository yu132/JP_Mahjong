package yu.proj.jpmahjong.gamelogic.analyze.win;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.FinalTenpaiAns;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: AnalyzeTsumo  
 *
 * @Description: 分析听的牌是否有自摸的可能（0番也认为是可能，因为还有非牌型的役）
 *
 * @author 余定邦  
 *
 * @date 2020年9月24日  
 *  
 */
public class AnalyzeTsumo {

    public Tsumo analyzeTsumo(FinalTenpaiAns tenpaiAns, Tile drawTile) {

        if (drawTile == null) {// 不是摸牌或者杠，因此是不可能和的
            return null;
        }

        int           drawTileIndex = CountNum.getTileIndex(drawTile);

        TenpaiAnsNode tenpaiAnsNode = tenpaiAns.getTsumo().get(drawTileIndex);

        if (tenpaiAnsNode == null) {
            return null;
        }

        return new Tsumo(drawTile, tenpaiAnsNode);
    }

    public static class Tsumo {

        private Tile drawTile;

        private TenpaiAnsNode tenpaiAnsNode;

        public Tsumo(Tile drawTile, TenpaiAnsNode tenpaiAnsNode) {
            super();
            this.drawTile      = drawTile;
            this.tenpaiAnsNode = tenpaiAnsNode;
        }

        public Tile getDrawTile() {
            return drawTile;
        }

        public TenpaiAnsNode getTenpaiAnsNode() {
            return tenpaiAnsNode;
        }

    }

}
