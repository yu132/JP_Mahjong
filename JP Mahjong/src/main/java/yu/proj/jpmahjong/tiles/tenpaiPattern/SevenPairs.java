package yu.proj.jpmahjong.tiles.tenpaiPattern;

import java.util.List;

/**  
 * @ClassName: SevenPair  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class SevenPairs implements TilePatternNode {

    private List<Completed> completed;

    private List<NotCompleted> notCompleted;

    public SevenPairs(List<Completed> completed, List<NotCompleted> notCompleted) {
        super();
        this.completed    = completed;
        this.notCompleted = notCompleted;
    }

    public List<Completed> getCompleted() {
        return completed;
    }

    public List<NotCompleted> getNotCompleted() {
        return notCompleted;
    }

}
