package yu.proj.ref.meld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.Tile;

/**  
 * @ClassName: Meld  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@AllArgsConstructor
@ToString
public class Meld {

    private Tile[] tiles;

    private MeldSource src;

}
