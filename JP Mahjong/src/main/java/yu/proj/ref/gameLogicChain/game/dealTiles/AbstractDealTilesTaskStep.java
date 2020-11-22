package yu.proj.ref.gameLogicChain.game.dealTiles;

import yu.proj.ref.gameLogicChain.game.AbstractTaskStep;
import yu.proj.ref.gameLogicChain.game.GameLogicData;

/**  
 * @ClassName: AbstractDealTilesTaskStep  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月13日  
 *  
 */
public abstract class AbstractDealTilesTaskStep extends AbstractTaskStep {

    protected DealTilesTaskSharedData taskData;

    public AbstractDealTilesTaskStep(GameLogicData mainData, DealTilesTaskSharedData taskData) {
        super(mainData);
        this.taskData = taskData;
    }

}
