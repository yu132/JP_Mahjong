package yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1;

import yu.proj.jpmahjong.player.TileSource;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;

/**  
 * @ClassName: TripletAndQuad  
 *
 * @Description: 刻子或者杠子，这个就有来源问题了，涉及到四杠子，大三元和大四喜的包牌问题
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class TripletAndQuad implements Completed {

    private Tile[] tiles;

    private boolean makeCall;

    private TileSource src;

    private boolean addKan;

    // 这里的计数是计算副露和暗杠的总数量，而不是仅仅计算副露的数量
    private int makeCallWhenAddKan;

    public TripletAndQuad(Tile[] tiles, boolean makeCall, TileSource src, boolean addKan, int makeCallWhenAddKan) {
        super();
        assert tiles.length != 3 || tiles.length != 4;
        this.tiles              = tiles;
        this.makeCall           = makeCall;
        this.src                = src;
        this.addKan             = addKan;
        this.makeCallWhenAddKan = makeCallWhenAddKan;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public boolean isMakeCall() {
        return makeCall;
    }

    public TileSource getSrc() {
        return src;
    }

    public boolean isAddKan() {
        return addKan;
    }

    public int getMakeCallWhenAddKan() {
        return makeCallWhenAddKan;
    }
}
