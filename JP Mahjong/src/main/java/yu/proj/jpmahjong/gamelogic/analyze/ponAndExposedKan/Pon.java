package yu.proj.jpmahjong.gamelogic.analyze.ponAndExposedKan;

/**  
 * @ClassName: Pon  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月11日  
 *  
 */
public class Pon {

    private int tileToPon;

    private int normalCount;

    private int redFiveCount;

    public Pon(int tileToPon, int normalCount, int redFiveCount) {
        super();
        this.tileToPon    = tileToPon;
        this.normalCount  = normalCount;
        this.redFiveCount = redFiveCount;
    }

    public int getTileToPon() {
        return tileToPon;
    }

    public int getNormalCount() {
        return normalCount;
    }

    public int getRedFiveCount() {
        return redFiveCount;
    }

}
