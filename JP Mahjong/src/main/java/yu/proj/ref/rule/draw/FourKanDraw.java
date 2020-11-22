package yu.proj.ref.rule.draw;

import lombok.Getter;

/**  
 * @ClassName: FourKanDraw  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月16日  
 *  
 */

@Getter
public class FourKanDraw {

    public static enum FourKanDrawType {
        ENABLE, // 开启之后，除非一个人杠了四次，否则就会流局
        DISABLE;
    }

    public static enum FiveKanRule {
        ENABLE, // 开启之后，除了加杠放铳以外，不摸岭上牌直接流局
        DISABLE;
    }

    private FourKanDrawType fourKanDrawType;

    private FiveKanRule fiveKanRule;

    public FourKanDraw(FourKanDrawType fourKanDrawType, FiveKanRule fiveKanRule) {
        super();

        assert fourKanDrawType != null && fiveKanRule != null;

        this.fourKanDrawType = fourKanDrawType;
        this.fiveKanRule     = fiveKanRule;
    }
}
