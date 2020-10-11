package yu.proj.jpmahjong.gamelogic.analyze.addKanAndConcealedKanAndKita;

import yu.proj.jpmahjong.tiles.meld.Meld;

/**  
 * @ClassName: AddKan  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月11日  
 *  
 */
public class AddKan {

    private int tileToKan;

    /*
     * 加杠要注意一个问题，由于四杠子需要包牌，出现4杠子后要检查所有的加杠，在加杠的时候
     * 杠的数量是不是4，如果是加杠使得四杠子产生，那么没有人需要包牌
     */
    private Meld triplet;

    public AddKan(int tileToKan, Meld triplet) {
        super();
        this.tileToKan = tileToKan;
        this.triplet   = triplet;
    }

    public int getTileToKan() {
        return tileToKan;
    }

    public Meld getTriplet() {
        return triplet;
    }

}
