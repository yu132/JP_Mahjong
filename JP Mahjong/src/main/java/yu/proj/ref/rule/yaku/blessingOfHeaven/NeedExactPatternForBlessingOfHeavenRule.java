package yu.proj.ref.rule.yaku.blessingOfHeaven;

/**  
 * @ClassName: NeedExactPatternForBlessingOfHeavenRule  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月19日  
 *  
 */
public enum NeedExactPatternForBlessingOfHeavenRule {

    /**
     * 这个规则是：是否在天和时，准确要求摸到的14张需要按照一定的顺序，
     * 才能和纯正九莲宝灯或国士无双十三面或四暗刻单骑
     * 对于纯正九莲宝灯来说，就是前13张摸到1112345678999的牌型，而最后第14张为任意同花色的牌
     * 对于国士无双十三面来说，就是前13张为13张不同的幺九牌，而最后第14张为任意一张幺九牌
     * 对于四暗刻单骑来说同理，如果前13张是单骑听，那么就记为四暗刻单骑，否则只记为普通四暗刻
     * 
     * 吐槽一下：这个规则真的有必要吗，神tm天和纯九，神tm天和国士十三，不要命了吗
     * 土豪氪钱，欧皇氪命。。。。和纯九已经只剩九年寿命了，天和纯九是明天嗝屁吗
     * 
     * 建议为true，节约欧皇寿命
     */

    ENABLE, DISABLE;

}
