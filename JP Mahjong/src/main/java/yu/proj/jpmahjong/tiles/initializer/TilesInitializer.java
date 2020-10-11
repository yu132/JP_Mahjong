package yu.proj.jpmahjong.tiles.initializer;

import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: TilesInitializer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public interface TilesInitializer {

    /**
     * @Title: init  
     *
     * @Description: 初始化牌局中使用的牌，日麻中就需要考虑是否使用赤宝牌 
     *
     * @return Tile[]  所有牌构成的数组
     * 
     */
    Tile[] init();

}
