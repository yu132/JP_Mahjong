package yu.proj.jpmahjong.tiles;

/**  
 * @ClassName: Tile  
 *
 * @Description: 一张牌
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class Tile implements Comparable<Tile> {

    /**
     * 牌的种类
     */
    private TileType type;

    /**
     * 字牌的字母ID，或者数字牌的赤宝牌的标记
     */
    private String strId;

    /**
     * 万子，饼子和索子的数字ID
     */
    private int intId;

    // private int uid;

    private boolean isRed;

    /**
     * 用于排序的ID
     * 
     * 生成规则
     * 
     * 牌型ID；牌型内ID；四张一样牌的区分ID
     * 
     */
    private int sortId;

    public Tile(TileType type, String strId, int intId, int uid, boolean isRed) {
        super();
        this.type  = type;
        this.strId = strId;
        this.intId = intId;
        // this.uid = uid;
        this.isRed = isRed;
        sortId     = (type.ordinal() + 1) * 100 + intId * 10 + uid;
        if (intId == 0) {
            toStringStr = strId + '[' + uid + ']';
        } else {
            toStringStr = (isRed ? 0 : intId) + strId + '[' + uid + ']';
        }
    }

    public TileType getType() {
        return type;
    }

    public String getStrId() {
        return strId;
    }

    public int getIntId() {
        return intId;
    }

    public boolean isRed() {
        return isRed;
    }

    /**
     * 用于理牌时，整理牌的顺序，以及判定牌型时所需要理清牌序的问题
     */
    @Override
    public int compareTo(Tile o) {
        return Integer.compare(sortId, o.sortId);
    }

    private String toStringStr;

    @Override
    public String toString() {
        return toStringStr;
    }

    @Override
    public int hashCode() {
        final int prime  = 31;
        int       result = 1;
        result = prime * result + intId;
        result = prime * result + sortId;
        result = prime * result + ((strId == null) ? 0 : strId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile)obj;
        if (intId != other.intId)
            return false;
        if (sortId != other.sortId)
            return false;
        if (strId == null) {
            if (other.strId != null)
                return false;
        } else if (!strId.equals(other.strId))
            return false;
        if (type != other.type)
            return false;
        return true;
    }
}
