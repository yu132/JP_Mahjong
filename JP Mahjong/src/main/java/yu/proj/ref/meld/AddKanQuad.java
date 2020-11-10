package yu.proj.ref.meld;

import lombok.Getter;
import lombok.ToString;
import yu.proj.ref.Tile;

/**  
 * @ClassName: AddKanQuad  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月8日  
 *  
 */

@Getter
@ToString(callSuper = true)
public class AddKanQuad extends SameTypeMeld {

    // 加杠中两个特殊牌中，区分放在上下的两张牌
    private Tile lowTile;
    private Tile upperTile;

    // 加杠的时候，加杠的玩家已经有多少个鸣牌了
    private int numOfMakeCallsWhenAddKan;

    private AddKanQuad(Tile[] tiles, MeldSource src, Tile lowTile, Tile upperTile, int numsOfMakeCallsWhenAddKan) {
        // 加杠的src依然是原来的碰对应的src，目的是为了保留原有的碰的样式，这对于日麻鸣牌的摆放规则很重要
        super(tiles, src);
        this.lowTile                   = lowTile;
        this.upperTile                 = upperTile;
        this.numOfMakeCallsWhenAddKan = numsOfMakeCallsWhenAddKan;
    }


    public static AddKanQuad of(Tile[] tiles, MeldSource src, Tile lowTile, Tile upperTile,
        int numsOfMakeCallsWhenAddKan) {

        assert src != MeldSource.SELF && tiles.length == 4;

        return new AddKanQuad(tiles, src, lowTile, upperTile, numsOfMakeCallsWhenAddKan);
    }

}