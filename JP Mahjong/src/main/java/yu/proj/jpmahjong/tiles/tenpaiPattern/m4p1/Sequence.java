package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;

/**  
 * @ClassName: Sequence  
 *
 * @Description: 顺子，这就有来源问题了，但是我们可以不追究顺子的来源，因为顺子不存在包牌的问题
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class Sequence implements Completed {

    private Tile[] tiles;

    private boolean isMakeCall;

    public Sequence(Tile[] tiles, boolean isMakeCall) {
        super();
        assert tiles.length == 3;
        this.tiles      = tiles;
        this.isMakeCall = isMakeCall;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public boolean isMakeCall() {
        return isMakeCall;
    }
}
