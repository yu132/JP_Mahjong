package yu.proj.ref.rule.win;

/**  
 * @ClassName: WinYakuRestrict  
 *
 * @Description: 和牌之前对役的要求
 *
 * @author 余定邦  
 *
 * @date 2020年11月16日  
 *  
 */
public enum WinYakuRestrict {

    ONE_PATTERN_HAS_YAKU_OR_ACCIDENTAL_YAKU, // 有后付，即听牌之后只要有一种听的类型有役，那么当摸到那种有役的牌，
                                             // 就可以和牌，无役时偶然役可和

    ALL_PATTERNS_HAVE_YAKU_OR_ACCIDENTAL_YAKU;// 无后付，应当属于中付，必须要所有的听的牌型有役才可以和，当然偶然役是可以和的

    // 前付规则略微复杂，并且没有什么资料，多个地方说法不一，因此这里先不枚举前付
}
