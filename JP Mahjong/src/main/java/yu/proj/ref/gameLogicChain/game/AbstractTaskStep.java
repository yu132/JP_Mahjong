package yu.proj.ref.gameLogicChain.game;

import lombok.AllArgsConstructor;

/**  
 * @ClassName: AbstractTaskStep  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月13日  
 *  
 */

@AllArgsConstructor
public abstract class AbstractTaskStep implements TaskStep {

    protected GameLogicData mainData;

    @Override
    public void execute() {
        doStepDuty();
        getNextTaskStep().execute();
    }

    protected abstract void doStepDuty();

    protected abstract TaskStep getNextTaskStep();

}
