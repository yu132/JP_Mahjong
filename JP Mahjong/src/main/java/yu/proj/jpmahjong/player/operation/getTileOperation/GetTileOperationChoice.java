package yu.proj.jpmahjong.player.operation.getTileOperation;

import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.chi.Chi;
import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.ExposedKan;
import yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan.Pon;
import yu.proj.jpmahjong.gamelogic.analyze.win.GetFinalTenpaiTileAndPoint.TenpaiAnsNode;
import yu.proj.jpmahjong.tiles.Tile;

/***

@ClassName: GetTileOperation
 *
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 * @author 余定邦
 *
 * @date 2020年9月23日
 *
 */
 public class GetTileOperationChoice {

 private Tile tile;

 private List<Chi> chi;
 private Pon pon;
 private ExposedKan kan;
 private TenpaiAnsNode ron;

 private boolean furiten;
 private boolean canRon;

 private boolean canDraw;

 public GetTileOperationChoice(Tile tile, List<Chi> chi, Pon pon, ExposedKan kan, TenpaiAnsNode ron, boolean furiten,
 boolean canRon, boolean canDraw) {
 super();
 this.tile = tile;
 this.chi = chi;
 this.pon = pon;
 this.kan = kan;
 this.ron = ron;
 this.furiten = furiten;
 this.canRon = canRon;
 this.canDraw = canDraw;
 }

 public Tile getTile() {
 return tile;
 }

 public List<Chi> getChi() {
 return chi;
 }

 public Pon getPon() {
 return pon;
 }

 public ExposedKan getKan() {
 return kan;
 }

 public TenpaiAnsNode getRon() {
 return ron;
 }

 public void setRon(TenpaiAnsNode ron) {
 this.ron = ron;
 }

 public boolean isFuriten() {
 return furiten;
 }

 public boolean canRon() {
 return canRon;
 }

 public boolean canDraw() {
 return canDraw;
 }

 }
