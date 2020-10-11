package yu.proj.jpmahjong.tiles.manager;

import java.util.NoSuchElementException;

import yu.proj.jpmahjong.tiles.Tile;

/**  
 * @ClassName: TilesManager  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月8日  
 *  
 */
public interface TilesManager {

    /**
     * 
     * @Title: isStarted  
     *
     * @Description: 是否已经开局了
     *
     * @return
     *
     * @throws  
     *
     */
    boolean isStarted();

    /**
     * 
     * @Title: isDraw  
     *
     * @Description: 判断是否流局，在每次摸牌之前判断
     *
     * @return boolean 是否流局
     *
     */
    boolean isDraw();

    /**
     * 
     * @Title: tileToDrawNum  
     *
     * @Description: 还剩下几张牌可以摸
     *
     * @return 剩下的牌的数量
     *
     */
    int tileToDrawNum();

    /**
     * 
     * @Title: draw  
     *
     * @Description: 摸牌 
     *
     * @return Tile 摸的那张牌
     *
     * @throws NoSuchElementException 当已经流局的时候，抛出该错误，表示没有可以摸的牌了
     * 
     */
    Tile draw();

    /**
     * 
     * @Title: kan  
     *
     * @Description: 杠牌
     *
     * @return Tile 杠到的岭上牌，如果岭上没有牌了，则返回null
     * 
     * @throws NoSuchElementException 试图在海底加杠，杠或暗杠时抛出该错误，因为王牌不能减少
     * 
     */
    Tile kan();

    /**
     * 
     * @Title: dora  
     *
     * @Description: 查看当前的宝牌 
     *
     * @return Tile[] 当前的宝牌 
     *
     */
    Tile[] doraIndicator();

    /**
     * 
     * @Title: uraDora  
     *
     * @Description: 对局结束时查看里宝牌  
     *
     * @return Tile[] 当前的里宝牌   
     *
     */
    Tile[] uraDoraIndicator();

}
