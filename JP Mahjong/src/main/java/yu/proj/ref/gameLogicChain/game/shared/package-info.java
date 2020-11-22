/**  
 * @ClassName: package-info  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年11月11日  
 *  
 */
package yu.proj.ref.gameLogicChain.game.shared;

/**
 * 本次使用较为复杂的双重责任链模式来解决游戏的极为复杂的问题
 * 
 * 1.【发牌各家发13张(A)】-> 【各家手牌处理(A)】->【牌分析器得出结果(A)】->【2】
 * 
 * 2.【亲家摸牌(S)】->【手牌处理(S)】->【3】
 * loop:
 * 
 * 3.【分析自摸，暗杠，加杠，切牌，立直,(附加九种九牌判断)(S)】->【获取UA(S)】->【x，4】
 * 
 * 4.【暗杠、加杠(S)】-》【6.抢杠(4)(A)】-》【四杠散了(A)】-》【手牌处理(S)】->【牌分析器得出结果(S)】-》【3】
 * 
 * 5.【切牌、立直(S)】-》【四家立直(A)】-》【四风连打(A)】-》【手牌处理(S)】-》【7】
 * 
 * 6.【抢杠(n)(A)】-》【获取UA(A)】->【n，x1】
 * 
 * 7.【吃碰杠荣(A)】-》【获取UA(A)】-》【集中竞价(A)】-》【3，6抢杠（3），x1，x2】
 * 
 * x.【有人自摸(A)】->【结算分数(A)】->【end】
 * 
 * x1.【有人荣和，抢杠和(A)】-》【三家荣和(A)】-》【结算点数(A)】-》【end】
 * 
 * x2.【荒牌流局(A)】-》【流局满贯(A)】-》【罚符(A)】-》【end】
 * 
 * end.【退出本次游戏】
 */
