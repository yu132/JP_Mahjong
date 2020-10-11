package yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita;

import java.util.ArrayList;
import java.util.List;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.tiles.meld.Meld;
import yu.proj.jpmahjong.tiles.meld.MeldType;

/**  
 * @ClassName: AnalyzeAddKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月24日  
 *  
 */
public class AnalyzeAddKan {

    public List<AddKan> analyzeAddKan(CountNum concealedHand, List<Meld> makeCall, boolean riichi) {

        if (riichi) {
            return null;
        }

        List<AddKan> list = new ArrayList<>();

        for (Meld ncm : makeCall) {
            if (ncm.getType() == MeldType.TRIPLET) {
                int tileIndex = CountNum.getTileIndex(ncm.getTiles()[0]);
                if (concealedHand.getCount(tileIndex) == 1) {
                    list.add(new AddKan(tileIndex, ncm));
                }
            }
        }

        return list.size() != 0 ? list : null;
    }

}
