package yu.proj.jpmahjong.define;

/**  
 * @ClassName: Numbers  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public class Numbers {

    /**
     * 一样的牌会有4张
     */
    public final static int NUMBER_OF_SAME_TILES = 4;

    /**
     * 一共有3类数牌，每类9种；字牌有7种，4种风牌，3种三元牌
     */
    public final static int NUMBER_OF_DIFFERENT_TILE = 3 * 9 + 4 + 3;

    /**
     * 牌的总数
     */
    public final static int NUMBER_OF_TILES = NUMBER_OF_SAME_TILES * NUMBER_OF_DIFFERENT_TILE;// 136

    /**
     * 每次荒牌流局会剩下14张牌，这14张是王牌，是不会被摸进场内的
     * 其中包括10张宝牌指示牌和4张岭上牌
     * 岭上牌每被摸走一张，就要从海底补一张进王牌，因此王牌的数量总是不变的，为14张
     */
    public final static int NUMBER_OF_LAST_TILES = 14;

    /**
     * 岭上牌是4张
     */
    public final static int NUMBER_OF_TILES_FOR_KAN = 4;

    /**
     * 每个玩家最多的手牌为14张
     */
    public final static int NUMBER_OF_TILES_FOR_HAND_OF_EACH_PLAYER = 14;

    /**
     * 发牌时发给亲家的牌为14张
     */
    public final static int NUMBER_OF_TILES_FOR_DEALER = 14;

    /**
     * 发牌时发给除亲家外的其他玩家的牌为13张
     */
    public final static int NUMBER_OF_TILES_FOR_OTHERS = 13;

    /**
     * 一根立直棒的分数为1k
     */
    public final static int RIICHI_STICK_POINT = 1000;

    /*
     * 一根本场棒的分数为100，但是每家都要付，因此总分价值300分
     */
    public final static int DEALER_STICK_POINT = 100;

}
