package yu.proj.jpmahjong.player.operation.robbingKanOperation;

import yu.proj.jpmahjong.gamelogic.analyze.robbingKan.AnalyzeRobbingKan.RobbingKan;

/**  
 * @ClassName: ChooseRobbingKanOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月30日  
 *  
 */
public class ChooseRobbingKanOperation implements RobbingKanOperation {

    private RobbingKan robbingKan;

    public ChooseRobbingKanOperation(RobbingKan robbingKan) {
        super();
        this.robbingKan = robbingKan;
    }

    public RobbingKan getRobbingKan() {
        return robbingKan;
    }

}
