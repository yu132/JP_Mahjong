package yu.proj.jpmahjong.player.operation.getTileOperation;

import yu.proj.jpmahjong.player.operation.Operation;

/**  
 * @ClassName: GetTileOperation  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月29日  
 *  
 */
public interface GetTileOperation extends Operation {

    static int compare(GetTileOperation o1, GetTileOperation o2) {
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {
            return o1.index() - o2.index();
        }
    }

    int index();

}
