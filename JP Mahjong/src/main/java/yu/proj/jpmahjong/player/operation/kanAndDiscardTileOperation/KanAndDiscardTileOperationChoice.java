package yu.proj.jpmahjong.player.operation.kanAndDiscardTileOperation;

import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.AddKan;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.ConcealedKan;
import yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita.Kita;
import yu.proj.jpmahjong.gamelogic.analyze.win.AnalyzeTsumo.Tsumo;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: DiscardTileOperation  
 *
 * @Description: 弃牌操作
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class KanAndDiscardTileOperationChoice {

    private List<AddKan> addKan;

    private List<ConcealedKan> concealedKan;

    private Kita kita;

    private Tsumo tsumo;

    private List<Tile> disCardTileToRiichi;

    private List<Tile> discardTile;

    private boolean nineDifferntTerminalsandHonors;

    public KanAndDiscardTileOperationChoice(List<AddKan> addKan, List<ConcealedKan> concealedKan, Kita kita,
        Tsumo tsumo, List<Tile> disCardTileToRiichi, List<Tile> discardTile, boolean nineDifferntTerminalsandHonors) {
        super();
        this.addKan                         = addKan;
        this.concealedKan                   = concealedKan;
        this.kita                           = kita;
        this.tsumo                          = tsumo;
        this.disCardTileToRiichi            = disCardTileToRiichi;
        this.discardTile                    = discardTile;
        this.nineDifferntTerminalsandHonors = nineDifferntTerminalsandHonors;
    }

    public List<AddKan> getAddKan() {
        return addKan;
    }

    public List<ConcealedKan> getConcealedKan() {
        return concealedKan;
    }

    public Kita getKita() {
        return kita;
    }

    public Tsumo getTsumo() {
        return tsumo;
    }

    public List<Tile> getDisCardTileToRiichi() {
        return disCardTileToRiichi;
    }

    public List<Tile> getDiscardTile() {
        return discardTile;
    }

    public void setTsumo(Tsumo tsumo) {
        this.tsumo = tsumo;
    }

    public void setAddKan(List<AddKan> addKan) {
        this.addKan = addKan;
    }

    public void setConcealedKan(List<ConcealedKan> concealedKan) {
        this.concealedKan = concealedKan;
    }

    public boolean hasNineDifferntTerminalsandHonors() {
        return nineDifferntTerminalsandHonors;
    }
}
