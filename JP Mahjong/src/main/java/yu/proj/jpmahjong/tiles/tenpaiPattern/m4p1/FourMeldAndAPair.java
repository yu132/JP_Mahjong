package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import java.util.List;

import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;

/**  
 * @ClassName: FourMeldAndAPair  
 *
 * @Description: 4面子1雀头的组成方式
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class FourMeldAndAPair implements TilePatternNode {

    private List<Completed> completed;

    private List<NotCompleted> notCompleted;

    private int meld, pair, singleton, wait2side, waitMiddle;

    public FourMeldAndAPair(List<Completed> completed, List<NotCompleted> notCompleted, int meld, int pair,
        int singleton, int wait2side, int waitMiddle) {
        super();
        this.completed    = completed;
        this.notCompleted = notCompleted;
        this.meld         = meld;
        this.pair         = pair;
        this.singleton    = singleton;
        this.wait2side    = wait2side;
        this.waitMiddle   = waitMiddle;
    }

    public List<Completed> getCompleted() {
        return completed;
    }

    public List<NotCompleted> getNotCompleted() {
        return notCompleted;
    }

    public int getMeld() {
        return meld;
    }

    public int getPair() {
        return pair;
    }

    public int getSingleton() {
        return singleton;
    }

    public int getWait2side() {
        return wait2side;
    }

    public int getWaitMiddle() {
        return waitMiddle;
    }
}
