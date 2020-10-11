package yu.proj.jpmahjong.tiles.dealer;

import yu.proj.jpmahjong.define.Numbers;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.manager.TilesManager;

/**  
 * @ClassName: NormalDealer  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年10月6日  
 *  
 */
public class BaseDealer implements TilesDealer {

    private int playerNumber;
    private TilesManager tileManager;

    public BaseDealer(int playerNumber) {
        super();
        this.playerNumber = playerNumber;
    }

    @Override
    public Tile[][] dealTile() {

        Tile[][] ans = new Tile[playerNumber][];

        ans[0] = new Tile[Numbers.NUMBER_OF_TILES_FOR_DEALER];

        for (int i = 1; i < playerNumber; ++i) {
            ans[i] = new Tile[Numbers.NUMBER_OF_TILES_FOR_OTHERS];
        }

        for (int i = 0; i < 3; ++i) {// 每个人总共拿3次
            for (int x = 0; x < playerNumber; ++x) {// 每个玩家都需要抓牌
                for (int j = 0; j < 4; ++j) {// 一次2礅牌，共4张
                    ans[x][i * 4 + j] = tileManager.draw();
                }
            }
        }

        for (int x = 0; x < playerNumber; ++x) {// 每个玩家都补到13张
            ans[x][12] = tileManager.draw();
        }

        ans[0][13] = tileManager.draw();// 庄家再多补到14张开局

        return ans;
    }

}
