package yu.proj.ref.rule.yaku.riichi;

import lombok.Getter;

/**  
 * @ClassName: ConcealedKanWhenRiichi  
 *
 * @Description: 立直后暗杠的相关规则  
 *
 * @author 余定邦  
 *
 * @date 2020年9月26日  
 *  
 */

@Getter
public class ConcealedKanWhenRiichiRule {

    /**
     * 是否开启立直后暗杠的严格模式，如果需要严格模式的话，那么不仅需要检查听的最大打点的牌
     * 甚至对于任意一种解释方式，上述规则都必须成立
     */
    private boolean strictMode;

    private ConcealedKanWhenRiichiType concealedKanWhenRiichiType;

    public ConcealedKanWhenRiichiRule(boolean strictMode, ConcealedKanWhenRiichiType concealedKanWhenRiichiType) {
        super();

        assert concealedKanWhenRiichiType != null;

        this.strictMode                 = strictMode;
        this.concealedKanWhenRiichiType = concealedKanWhenRiichiType;
    }

    public static enum ConcealedKanWhenRiichiType {
        /**
         * 从上往下以此为规则松到规则紧
         * 
         * 如果最上面都可以，那么下面的情况也都应当可以
         * 
         * 如果最下面的规则都不允许，那么上面的规则也一定不允许
         */

        /**
         * 是否立直的时候甚至允许如果能够保持听牌状态的话，可以随意暗杠
         * 即使暗杠的4张牌都在手中，而不是摸进来的牌进行暗杠，即一定导致听牌牌型的改变
         */
        ALLOW_CONCEALED_KAN_WHEN_RIICHI_EVEN_NOT_DRAW,

        /**
         * 是否影响听的牌的数量时也可以暗杠
         */
        ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_NUMBER_NOT_CHANGE,

        /**
         * 是否影响听牌的数量和形式时也可以暗杠
         */
        ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TENPAI_PATTERN_NOT_CHANGE,

        /**
         * 是否牌型改变的时候也可以暗杠
         */
        ALLOW_CONCEALED_KAN_WHEN_RIICHI_IF_TILE_PATTERN_NOT_CHANGE,

        /**
         * 是否立直之后不准暗杠
         */
        NOT_ALLOW_CONCEALED_KAN_WHEN_RIICHI;
    }


}
