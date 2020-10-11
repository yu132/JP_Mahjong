package yu.proj.jpmahjong.tiles.meld;

import yu.proj.jpmahjong.player.TileSource;
import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: MakeCall  
 *
 * @Description: 鸣牌
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class Meld {

    private MeldType type;

    private Tile[] tiles;

    private Tile specialTile;

    private TileSource src;

    private boolean addKan;

    /**
     * 开杠的时候有几个副露了
     */
    private int makeCallWhenAddKan;

    public Meld(MeldType type, Tile[] tiles, Tile specialTile, TileSource src, boolean addKan,
        int makeCallWhenAddKan) {
        super();
        this.type               = type;
        this.tiles              = tiles;
        this.specialTile        = specialTile;
        this.src                = src;
        this.addKan             = addKan;
        this.makeCallWhenAddKan = makeCallWhenAddKan;
    }

    public MeldType getType() {
        return type;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public Tile getSpecialTile() {
        return specialTile;
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

    public void setType(MeldType type) {
        this.type = type;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public void setSpecialTile(Tile specialTile) {
        this.specialTile = specialTile;
    }

    public void setSrc(TileSource src) {
        this.src = src;
    }

    public void setAddKan(boolean addKan) {
        this.addKan = addKan;
    }

    public void setMakeCallWhenAddKan(int makeCallWhenAddKan) {
        this.makeCallWhenAddKan = makeCallWhenAddKan;
    }
}
