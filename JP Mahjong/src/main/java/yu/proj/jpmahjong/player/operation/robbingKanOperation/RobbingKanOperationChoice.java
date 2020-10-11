package yu.proj.jpmahjong.player.operation.robbingKanOperation;

import yu.proj.jpmahjong.gamelogic.analyze.robbingKan.AnalyzeRobbingKan.RobbingKan;

/**  
 * @ClassName: RobbingKanOperationChoice  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月30日  
 *  
 */
public class RobbingKanOperationChoice {

    private RobbingKan robbingKan;

    private boolean canRobbingKan;

    public RobbingKanOperationChoice(RobbingKan robbingKan, boolean canRobbingKan) {
        super();
        this.robbingKan    = robbingKan;
        this.canRobbingKan = canRobbingKan;
    }

    public RobbingKan getRobbingKan() {
        return robbingKan;
    }

    public void setRobbingKan(RobbingKan robbingKan) {
        this.robbingKan = robbingKan;
    }

    public boolean canRobbingKan() {
        return canRobbingKan;
    }

}
