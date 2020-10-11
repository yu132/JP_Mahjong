package yu.proj.jpmahjong.gamelogic.analyze.win;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;
import static yu.proj.jpmahjong.yaku.Yaku.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.player.TileSource;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;
import yu.proj.jpmahjong.tiles.tenpaiPattern.ThirteenOrphans;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.FourMeldAndAPair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Pair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Sequence;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Singleton;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.TripletAndQuad;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Wait2Side;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.WaitMiddle;
import yu.proj.jpmahjong.yaku.Yaku;

/**  
 * @ClassName: HasYaku  
 *
 * @Description: 判断手上的牌型是不是至少有一个役，这里不计宝牌，因为每次宝牌指示牌更新后，宝牌都需要重新计算，而宝牌更新的时候
 *               手牌却不一定更新，因此增加了没必要的更新情况，因此最后当和了之后再计算宝牌也是可以的，反正都还需要计算里宝牌的数量
 *               红宝牌就可以先计算，因为红宝牌是固定的，只会随着手牌的变化而变化，因此无所谓
 *
 * @author 余定邦  
 *
 * @date 2020年9月9日  
 *  
 */
public class CountPatternYaku {

    private Rule rule;

    private TileType prevalentWind;
    private TileType seatWind;
    private TilePatternNode tilePatternNode;
    private int tileToWin;

    /**
     * 必须是完整的手牌统计，因为接下来会有根据牌的计数进行役的判断的代码
     * 因此如果有牌因为是鸣牌而没有被计在这里，那么就会出错
     */
    private CountNum fullHand;

    public CountPatternYaku(Rule rule, TileType prevalentWind, TileType seatWind, TilePatternNode tilePatternNode,
        int tileToWin, CountNum fullHand) {
        super();
        this.rule            = rule;
        this.prevalentWind   = prevalentWind;
        this.seatWind        = seatWind;
        this.tilePatternNode = tilePatternNode;
        this.tileToWin       = tileToWin;
        this.fullHand        = fullHand;
    }

    /**
     * 
     * @Title: HasPatternYaku  
     *
     * @Description: 计算和牌相关的役的番数
     * 
     * 不考虑的役：立直、一发、双立直、门前清自摸和 、抢杠、岭上开花、海底摸月、河底捞鱼、天和、地和
     * 
     * 1.断幺九    √  
     * 2.自风   √  
     * 3.场风   √  
     * 4.白   √ 
     * 5.发   √ 
     * 6.中   √ 
     * 7.平和   √ 
     * 8.一杯口、二杯口  √ 
     * 9.三色同刻  √ 
     * 10.三杠子、四杠子   √
     * 11.对对和  √
     * 12.三暗刻、四暗刻和四暗刻单骑  √
     * 13.小三元、大三元   √
     * 14.混老头、清老头 √
     * 15.七对子
     * 16.混全带幺九、纯全带幺九 √
     * 17.一气通贯 √
     * 18.三色同顺  √
     * 19.混一色、清一色  √
     * 20.字一色  √
     * 21.国士无双（包括国士无双十三面）  √
     * 22.绿一色 √
     * 23.小四喜、大四喜  √
     * 24.九莲宝灯、纯正九莲宝灯 √
     *
     */
    public YakuAns countYaku() {

        Set<Yaku> yaku = new HashSet<>();

        /* 国士无双 */
        if (tilePatternNode instanceof ThirteenOrphans) {
            ThirteenOrphans to = (ThirteenOrphans)tilePatternNode;

            // 由于国士无双和别的役满不兼容，因此就无需考虑有没有别的牌型，直接返回就可以
            // 如果考虑累计役满复合的问题，那可能还需要计算宝牌的数量
            // int doraCount = rule.enableCountDoraWhenHasYaukuman ? countDora() : 0;

            if (to.isWait13()) {// 十三面
                yaku.add(THIRTEEN_WAIT_THIRTEEN_ORPHANS);
                return new YakuAns(yaku, 0, true, true, null, null, null);
            } else {// 普通的国士无双
                yaku.add(THIRTEEN_ORPHANS);
                return new YakuAns(yaku, 0, true, true, null, null, null);
            }

        } else if (tilePatternNode instanceof FourMeldAndAPair) {// 考虑4面子1雀头的情况

            int              pWIndex                      = CountNum.honorTileIndex(prevalentWind);
            int              sWIndex                      = CountNum.honorTileIndex(seatWind);

            // 包牌相关的内容
            TileSource       bigWindSource                = null;
            TileSource       threeDragonSource            = null;
            TileSource       fourQuadSource               = null;

            FourMeldAndAPair m4p1                         = (FourMeldAndAPair)tilePatternNode;

            // 先统计一下顺子、刻子和杠子的相关信息

            // 这里有一点缺陷就是没有考虑到听牌牌型能构成的顺子和刻子，这要注意，并没有统计全部的数量
            int              concealedTripletAndQuadCount = 0, tripletCount = 0, quadCount = 0, concealedKanCount = 0;
            int              colcealedSequenceCount       = 0, sequenceCount = 0;

            int[]            kanNumber                    = new int[34];

            for (Completed completed : m4p1.getCompleted()) {
                if (completed instanceof TripletAndQuad) {
                    TripletAndQuad taq = (TripletAndQuad)completed;

                    if (taq.getTiles().length == 3) {
                        ++tripletCount;
                    } else {
                        ++quadCount;
                        ++kanNumber[getTileIndex(taq.getTiles()[0])];
                        if (!taq.isMakeCall()) {
                            ++concealedKanCount;
                        }
                    }

                    if (!taq.isMakeCall()) {
                        ++concealedTripletAndQuadCount;
                    }

                } else if (completed instanceof Sequence) {
                    Sequence seq = (Sequence)completed;

                    ++sequenceCount;

                    if (!seq.isMakeCall()) {
                        ++colcealedSequenceCount;
                    }
                }
            }

            // 没有明顺子、明刻和明杠或者加杠，那么就是门前清
            boolean isMenzenchin =
                tripletCount + quadCount - concealedTripletAndQuadCount + sequenceCount - colcealedSequenceCount == 0;

            /* 大小四喜 */
            int     ECount       = countKanAs3Tile(E, kanNumber);
            int     SCount       = countKanAs3Tile(S, kanNumber);
            int     WCount       = countKanAs3Tile(W, kanNumber);
            int     NCount       = countKanAs3Tile(N, kanNumber);

            /*
             * 这里的sum可以用于判断大小四喜，首先我们只统计四张风的数量，这样就限制在了风牌，不会计算别的牌
             * 其次，我们每种风牌最多计算3张，去掉了杠的那张，这样保证风牌的数量最多为12张
             * 
             * 因此，如果是大四喜，那么最后sum应该是12，即每个风都有一个刻子
             * 如果是小四喜，那么sum就应该为11，有一个风少了一张牌，即3种风有刻子，1种只有对子
             */
            int     sumWind      = ECount + SCount + WCount + NCount;

            if (sumWind == 12) {// 大四喜
                yaku.add(FOUR_BIG_WINDS);

                // 大四喜包牌，如果有4副风牌的明刻，那么如果开启包牌规则，那么最后一个人打出风牌的人需要包牌
                // 这里不需要考虑加杠的问题，反正最后一个使得4个风副露的人一定需要包牌
                if (rule.enableResponsibilityForFourBigWind && concealedTripletAndQuadCount == 0) {
                    TripletAndQuad taq = (TripletAndQuad)m4p1.getCompleted().get(3);

                    bigWindSource = taq.getSrc();
                }
            } else if (sumWind == 11) {// 小四喜
                yaku.add(FOUR_LITTLE_WINDS);
            }

            /* 大小三元 */
            int WhCount   = countKanAs3Tile(WH, kanNumber);
            int GCount    = countKanAs3Tile(G, kanNumber);
            int RCount    = countKanAs3Tile(R, kanNumber);

            /*
             * 和上面大小四喜的计数方式同样，这里就不再赘述了
             */
            int sumDragon = WhCount + GCount + RCount;

            if (sumDragon == 9) {// 大三元

                yaku.add(BIG_THREE_DRAGONS);

                // 大三元包牌，大三元包牌就稍微有点麻烦了，因为即使有3个副露，那也不一定是大三元的副露
                // 因此还需要再遍历一次，找到最后的需要包牌的家伙

                if (rule.enableResponsibilityForBigThreeDragon) {
                    int        countDragonMakeCall = 0;
                    TileSource lastDragonMakeCall  = null;

                    for (Completed completed : m4p1.getCompleted()) {
                        if (completed instanceof TripletAndQuad) {
                            TripletAndQuad taq = (TripletAndQuad)completed;
                            if (CountNum.isTileThisType(WH, taq.getTiles()[0])
                                || CountNum.isTileThisType(G, taq.getTiles()[0])
                                || CountNum.isTileThisType(R, taq.getTiles()[0])) {
                                if (taq.isMakeCall()) {
                                    ++countDragonMakeCall;
                                    lastDragonMakeCall = taq.getSrc();
                                }
                            }
                        }
                    }
                    // 如果真的有3个明刻或明杠，那么最后一个三元牌的副露的来源就活该包牌
                    // 即使最后加杠了，也没有什么影响
                    if (countDragonMakeCall == 3) {
                        threeDragonSource = lastDragonMakeCall;
                    }
                }
            }

            /* 字一色 */

            // 依旧同理，计算除去杠子那多出去一张外的字牌数量
            // 如果字牌数量有14张，那就全是字牌，就是字一色
            int sumHonor = sumWind + sumDragon;

            if (sumHonor == 14) {// 字一色
                yaku.add(ALL_HONORS);
            }

            /* 四杠子 */
            if (quadCount == 4) {// 四杠子
                yaku.add(FOUR_QUADS);

                /*
                 * 四杠子也需要考虑包牌的问题，其实最重要的问题就是最后一个杠是加杠还是暗杠还是明杠
                 * 这三种情况中只有明杠是需要包牌的
                 * 
                 * 因此需要先判断最后一个副露是暗杠吗，是的话就不包牌
                 * 然后去判断最后一个杠是加杠还是明杠，是加杠那也不是
                 * 如果是明杠，那么还需要判断这个明杠是不是最后一个杠
                 * 因为即使是最后一个副露，那么也有可能是在这个副露之后
                 * 还在前面的明刻上加杠了，如果前面加杠在这个最后的大明杠之后
                 * 那么也不包牌
                 */

                if (rule.enableResponsibilityForFourQuads) {
                    TripletAndQuad last = (TripletAndQuad)m4p1.getCompleted().get(3);

                    if (last.isMakeCall()) {// 不是暗杠
                        if (!last.isAddKan()) {// 不是加杠

                            boolean flag = true;

                            for (int i = 0; i < 3; ++i) {
                                TripletAndQuad quad = (TripletAndQuad)m4p1.getCompleted().get(i);

                                // 最后的杠子不是大明杠而是加杠
                                if (quad.isAddKan() && quad.getMakeCallWhenAddKan() == 4) {
                                    flag = false;
                                    break;
                                }
                            }

                            // 满足上述种种条件，最后这个人还是需要包牌
                            if (flag) {
                                fourQuadSource = last.getSrc();
                            }

                        }
                    }
                }
            }

            /* 绿一色 */
            int s2Count     = countKanAs3Tile(S2, kanNumber);
            int s3Count     = countKanAs3Tile(S3, kanNumber);
            int s4Count     = countKanAs3Tile(S4, kanNumber);
            int s6Count     = countKanAs3Tile(S6, kanNumber);
            int s8Count     = countKanAs3Tile(S8, kanNumber);

            /*
             * 计算规则和前面类似，根据绿一色的规则，我们需要计算2、3、4、6、8条和发的数量
             * 如果总数为14张，那么就是绿一色
             */

            int sumAllGreen = s2Count + s3Count + s4Count + s6Count + s8Count + GCount;

            if (sumAllGreen == 14) {// 绿一色

                if (rule.enableNeedGreenDragonAsAllGreen) {// 开启需要发才算绿一色的规则

                    if (GCount != 0) {
                        yaku.add(ALL_GREENS);
                    }

                } else if (rule.enableTrueAllGreen) {// 开启没有发算纯正绿一色的规则

                    if (GCount != 0) {
                        yaku.add(ALL_GREENS);
                    } else {
                        yaku.add(TRUE_ALL_GREENS);
                    }

                } else {// 一般规则，直接计算为绿一色
                    yaku.add(ALL_GREENS);
                }

            }

            // 这里是清一色的计算过程，先计算清一色的原因是由于九莲宝灯需要提前判断清一色，需要获取清一色的颜色
            int manCount = 0, pinCount = 0, souCount = 0;

            for (int i = M1; i <= M9; ++i) {
                manCount += countKanAs3Tile(i, kanNumber);
            }

            for (int i = P1; i <= P9; ++i) {
                pinCount += countKanAs3Tile(i, kanNumber);
            }

            for (int i = S1; i <= S9; ++i) {
                souCount += countKanAs3Tile(i, kanNumber);
            }

            boolean isManFullFlush = manCount == 14;
            boolean isPinFullFlush = pinCount == 14;
            boolean isSouFullFlush = souCount == 14;

            boolean isFullFlush    = isManFullFlush || isPinFullFlush || isSouFullFlush;

            /* 九莲宝灯 */

            // 九莲的首要条件就是要求一定要门清和没有暗杠，并且必须是清一色
            if (isMenzenchin && concealedKanCount == 0 && isFullFlush) {
                int     base = (isManFullFlush ? M1 : isPinFullFlush ? P1 : S1) - 1;

                // 1和9至少有3张以上
                boolean flag = count(base + 1) >= 3 && count(base + 9) >= 3;

                if (flag) {
                    for (int i = 2; i <= 8; ++i) {
                        if (count(base + i) == 0) {// 2-8至少有1张
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {

                        // 纯正九莲宝灯
                        if (count(tileToWin) == 4 || count(tileToWin) == 2) {

                            yaku.add(TRUE_NINE_GATES);

                        } else {// 普通九莲宝灯

                            yaku.add(NINE_GATES);

                        }

                    }
                }
            }

            /* 清老头 */
            int m1Count     = countKanAs3Tile(M1, kanNumber);
            int m9Count     = countKanAs3Tile(M9, kanNumber);
            int p1Count     = countKanAs3Tile(P1, kanNumber);
            int p9Count     = countKanAs3Tile(P9, kanNumber);
            int s1Count     = countKanAs3Tile(S1, kanNumber);
            int s9Count     = countKanAs3Tile(S9, kanNumber);

            int sumTerminal = m1Count + m9Count + p1Count + p9Count + s1Count + s9Count;

            // 清老头 就是14张1牌和9牌
            if (sumTerminal == 14) {

                yaku.add(ALL_TERMINALS);

            }

            /* 四暗刻 */
            if (concealedTripletAndQuadCount == 4) {// 四暗刻单骑，无论荣和还是自摸，都是双倍役满

                yaku.add(SINGLE_WAIT_FOUR_CONCEALED_TRIPLETS);

            } else if (concealedTripletAndQuadCount == 3) {// 三个暗刻

                // 还有两个对子，如果自摸的话，就是四暗刻役满，不是的话，计算为三暗刻
                // 在这里是这样操作的，由于只有四暗刻一种役需要区分自摸和荣和
                // 因此当四暗刻的时候，将三暗刻和四暗刻同时加入到役的列表中
                // 最终怎么和，根据场上待定
                if (m4p1.getPair() == 2) {
                    yaku.add(FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO);// 自摸的话就是四暗刻
                }

            }

            /*
             * 这里当存在役的时候，那么就肯定是役满了，其他的小的役不再计算，根据规则，可能还需要计算宝牌的数量
             * 但是有一个例外，那就是当只有四暗刻这个役的时候，还需要计算别的役，因为四暗刻不自摸是不算的
             */
            if (yaku.size() > 1 || (yaku.size() == 1 && !yaku.contains(FOUR_CONCEALED_TRIPLETS_WHEN_TSUMO))) {
                if (!rule.enableCountDoraWhenHasYaukuman) {
                    return new YakuAns(yaku, 0, isMenzenchin, true, bigWindSource, threeDragonSource, fourQuadSource);
                } else {
                    // int dora = countDora();
                    int redFive = countRedFive();
                    return new YakuAns(yaku, redFive, isMenzenchin, true, bigWindSource, threeDragonSource,
                        fourQuadSource);
                }
            }

            /* 三暗刻 */
            if (concealedTripletAndQuadCount == 3) {// 三个暗刻就是三暗刻

                yaku.add(THREE_CONCEALED_TRIPLETS);

            } else if (concealedTripletAndQuadCount == 2) {// 两个暗刻时，双碰听自摸的时候也算三暗刻

                if (m4p1.getPair() == 2) {// 是双碰听
                    yaku.add(THREE_CONCEALED_TRIPLETS_WHEN_TSUMO);// 自摸的话就是三暗刻
                }

            }


            /* 小三元 */
            if (sumDragon == 8) {// 小三元
                yaku.add(LITTLE_THREE_DRAGONS);
            }

            /* 三杠子 */
            if (quadCount == 3) {// 三杠子
                yaku.add(THREE_QUADS);
            }


            /* 清一色、混一色 */

            // 计算过程见九莲宝灯判定前一点的位置

            if (isFullFlush) {// 清一色

                yaku.add(FULL_FLUSH);

            } else if (manCount + sumHonor == 14) {// 万子混一色

                yaku.add(HALF_FLUSH);

            } else if (pinCount + sumHonor == 14) {// 饼子混一色

                yaku.add(HALF_FLUSH);

            } else if (souCount + sumHonor == 14) {// 索子混一色

                yaku.add(HALF_FLUSH);

            }

            /* 混老头 */

            // 如果加上字牌才是14张，那么就是混老头
            if (sumTerminal + sumHonor == 14) {
                yaku.add(ALL_TERMINALS_AND_HONORS);
            }

            /* 对对和 */

            /*
             * 对对和的判断是这样的，如果有4个刻子或杠子，那么剩下的肯定是单骑，那么也肯定是对对和
             * 如果只有3个刻子或杠子，那么剩下的必须是2个对子，即必须是双碰听，否则一定不是对对和
             */
            if (tripletCount + quadCount == 4 || (tripletCount + quadCount == 3 && m4p1.getPair() == 2)) {
                yaku.add(ALL_TRIPLETS);
            }

            /* 断幺九 */

            // 断幺九和混老头彻底相反，如果没有幺九牌，那么就是断幺九

            if (sumTerminal + sumHonor == 0) {

                // 开启食断规则的话，那么还需要门前清才能算作是断幺九
                if (rule.enableDanyaoAsYakuWhenMakeCall || isMenzenchin) {
                    yaku.add(DANYAO);
                }
            }

            /* 役牌：场风、自风 */
            if (ECount == 3) {// 东风刻子
                if (pWIndex == E) {
                    yaku.add(PREVALENT_WIND_E);
                }
                if (sWIndex == E) {
                    yaku.add(SEAT_WIND_E);
                }
            }
            if (SCount == 3) {// 南风刻子
                if (pWIndex == S) {
                    yaku.add(PREVALENT_WIND_S);
                }
                if (sWIndex == S) {
                    yaku.add(SEAT_WIND_S);
                }
            }
            if (WCount == 3) {// 西风刻子
                if (pWIndex == W) {
                    yaku.add(PREVALENT_WIND_W);
                }
                if (sWIndex == W) {
                    yaku.add(SEAT_WIND_W);
                }
            }
            if (NCount == 3) {// 北风刻子
                if (pWIndex == N) {
                    yaku.add(PREVALENT_WIND_N);
                }
                if (sWIndex == N) {
                    yaku.add(SEAT_WIND_N);
                }
            }

            /* 役牌：白发中*/
            if (WhCount == 3) {// 白刻子
                yaku.add(DRAGON_WH);
            }
            if (GCount == 3) {// 发刻子
                yaku.add(DRAGON_G);
            }
            if (RCount == 3) {// 中刻子
                yaku.add(DRAGON_R);
            }

            /* 平和 */

            /*
             * 平和的判断比较复杂，其必须要有3个现成的顺子，一个对子，一个两面听
             * 两面听一定要听两面，边张听和坎张听都不行，并且对对子也有要求，
             * 要求对子不能是白发中和场风和自风
             */
            if (isMenzenchin) {// 门清限定
                if (sequenceCount == 3 && m4p1.getPair() == 1 && m4p1.getWait2side() == 1) {// 3顺子1对子

                    NotCompleted nc = m4p1.getNotCompleted().get(0);

                    if (nc instanceof Wait2Side && ((Wait2Side)nc).isWait2()) {// 两面听

                        List<Completed> c        = m4p1.getCompleted();
                        Pair            pair     = (Pair)c.get(c.size() - 1);
                        int             pairType = getTileIndex(pair.getTiles()[0]);

                        if (pairType != WH && pairType != G && pairType != R && pairType != pWIndex
                            && pairType != sWIndex) {// 雀头的要求
                            yaku.add(PINFU);
                        }
                    }
                }
            }

            // 统计顺子和刻子的数量，顺子和刻子需要考虑到完整牌型的统计，需要把听的那张牌也构成顺子和刻子
            // 这里的统计是对每一类数牌而言的，不统计字牌的原因是由于接下来需要判断的番都只和数牌有关
            // 计算顺子的时候，按照最低的那张牌的序号计算，即3s,4s,5s的顺子，记录在3s的序号上
            int[] eachSequenceCount = new int[27], eachTripletCount = new int[27];

            // 雀头的判定，由于上面的牌型都很神奇的没有关心雀头的问题
            // 但是在混全带幺九和纯全带幺九中，雀头是一个必须关心的问题，因此不得不考虑
            int   headIndex         = -1;

            for (Completed completed : m4p1.getCompleted()) {
                if (completed instanceof TripletAndQuad) {
                    TripletAndQuad taq  = (TripletAndQuad)completed;
                    Tile           tile = taq.getTiles()[0];
                    if (tile.getIntId() != 0) {// 和字有关的刻子不要
                        ++eachTripletCount[getTileIndex(tile)];
                    }
                } else if (completed instanceof Sequence) {
                    Sequence seq = (Sequence)completed;

                    ++eachSequenceCount[getTileIndex(seq.getTiles()[0])];
                }
            }

            for (NotCompleted notCompleted : m4p1.getNotCompleted()) {

                // 为什么不在上面考虑对子构成刻子的问题呢？是因为如果能构成刻子，证明其还不完善
                // 如果是完善的刻子，已经被移送到Completed里，那么也不用考虑了
                // 而不完善的才保留在NotCompleted里
                if (notCompleted instanceof Pair) {
                    Pair pair = (Pair)notCompleted;

                    Tile tp   = pair.getTiles()[0];

                    if (getTileIndex(tp) == tileToWin) {// 这个对子构成一副刻子
                        if (tp.getIntId() != 0) {// 和字有关的刻子不要
                            ++eachTripletCount[tileToWin];
                        }
                    } else {// 由于其不构成刻子，因此是真的雀头
                        headIndex = CountNum.getTileIndex(pair.getTiles()[0]);
                    }
                } else if (notCompleted instanceof Wait2Side) {
                    Wait2Side w2s = (Wait2Side)notCompleted;

                    if (getTileIndex(w2s.getTiles()[0]) == tileToWin + 1) {// 听的牌在两面听的低部分
                        ++eachSequenceCount[tileToWin];
                    } else {
                        ++eachSequenceCount[getTileIndex(w2s.getTiles()[0])];
                    }
                } else if (notCompleted instanceof WaitMiddle) {// 坎张听的话一定是坎张中低的那张起头
                    WaitMiddle wm = (WaitMiddle)notCompleted;

                    ++eachSequenceCount[getTileIndex(wm.getTiles()[0])];

                } else if (notCompleted instanceof Singleton) {// 单骑最终也一定要形成雀头
                    headIndex = CountNum.getTileIndex(((Singleton)notCompleted).getTile());
                }
            }

            /* 一杯口和二杯口 */
            if (isMenzenchin) {// 门清限定
                int sumPureDoubleSequence = 0;

                for (int i = M1; i <= S7; ++i) {
                    // 3个一样的顺子也是只能记作一杯口，但是4个一样的顺子就需要按照规则来定义了
                    // 有的规则是记作二杯口的，有的不计
                    if (rule.enablePureFourSequenceAsTwicePureDoubleSequence) {
                        sumPureDoubleSequence += eachSequenceCount[i] / 2;
                    } else if (eachSequenceCount[i] >= 2) {
                        ++sumPureDoubleSequence;
                    }
                }

                if (sumPureDoubleSequence == 1) {// 一杯口
                    yaku.add(PURE_DOUBLE_SEQUENCE);
                } else if (sumPureDoubleSequence == 2) {// 二杯口
                    yaku.add(TWICE_PURE_DOUBLE_SEQUENCE);
                }
            }

            /* 三色同刻 */
            for (int i = M1; i <= M9; ++i) {
                // 三种颜色的刻子总数为3，由于一种颜色的刻子最多就1副，所以这样计算一定是每个各一副的
                if (eachTripletCount[i] + eachTripletCount[i + 9] + eachTripletCount[i + 18] == 3) {
                    yaku.add(TRIPLE_TRIPLETS);
                    break;
                }
            }

            /* 三色同顺 */

            // 逻辑和三色同刻一样
            for (int i = M1; i <= M7; ++i) {
                if (eachSequenceCount[i] + eachSequenceCount[i + 9] + eachSequenceCount[i + 18] == 3) {
                    yaku.add(MIXED_TRIPLE_SEQUENCE);
                    break;
                }
            }

            /* 一气通贯 */
            if (eachSequenceCount[M1] >= 1 && eachSequenceCount[M4] >= 1 && eachSequenceCount[M7] >= 1) {
                // 万子的一气通贯
                yaku.add(PURE_STRAIGHT);
            } else if (eachSequenceCount[P1] >= 1 && eachSequenceCount[P4] >= 1 && eachSequenceCount[P7] >= 1) {
                // 饼子的一气通贯
                yaku.add(PURE_STRAIGHT);
            } else if (eachSequenceCount[S1] >= 1 && eachSequenceCount[S4] >= 1 && eachSequenceCount[S7] >= 1) {
                // 索子的一气通贯
                yaku.add(PURE_STRAIGHT);
            }

            /* 混全带幺九、纯全带幺九 */

            /*
             * 同样使用计数法来计算，即将包含1和9的顺子，刻子和杠子都记作3张牌，
             * 同时雀头的也同样需要计数，如果有14张这样的牌，那么就是纯全带幺九
             * 如果和字牌共计14张，那么就是混全带幺九
             */
            int sumOutsideHand = 0;

            sumOutsideHand += (eachSequenceCount[M1] + eachSequenceCount[P1] + eachSequenceCount[S1]) * 3;
            sumOutsideHand += (eachSequenceCount[M7] + eachSequenceCount[P7] + eachSequenceCount[S7]) * 3;

            sumOutsideHand += (eachTripletCount[M1] + eachTripletCount[P1] + eachTripletCount[S1]) * 3;
            sumOutsideHand += (eachTripletCount[M9] + eachTripletCount[P9] + eachTripletCount[S9]) * 3;

            if (headIndex == M1 || headIndex == M9 || headIndex == P1 || headIndex == P9 || headIndex == S1
                || headIndex == S9) {
                sumOutsideHand += 2;
            }

            if (sumOutsideHand == 14) {// 纯全带幺九
                yaku.add(FULL_OUTSIDE_HAND);
            } else if (sumOutsideHand + sumHonor == 14) {// 混全带幺九

                // 混老头一定是混全带幺九，因此不重复计算
                if (!yaku.contains(ALL_TERMINALS_AND_HONORS)) {
                    yaku.add(HALF_OUTSIDE_HAND);
                }
            }


            // 四面子一雀头的牌型的番就计算完了，七对子的牌型由于不兼容并且能和的役很少，因此单独计算
            // 这里就直接返回了
            // int dora = countDora();
            int redFive = countRedFive();
            return new YakuAns(yaku, redFive, isMenzenchin, false, bigWindSource, threeDragonSource, fourQuadSource);


        } else {// tilePatternNode instanceof SevenPairs
            /*
             * 接下来考虑七对子的情况
             * 
             * 七对子能够复合的番很少
             * 如果将其和四面子一雀头的情况一起分析，会很麻烦，因此这里单独计算
             * 
             * 字一色
             * 清一色
             * 混一色
             * 混老头
             * 断幺九
             */

            // 七对子里面不可能会有杠子，因此直接计数即可，非常简单
            int sumHonor = count(E) + count(S) + count(W) + count(N) + count(WH) + count(G) + count(R);

            /* 字一色 大七星 */
            if (sumHonor == 14) {
                yaku.add(ALL_HONORS);

                // 由于字一色能够构成的役满就一种，因此如果真的是大七星，那么就直接返回就可以了
                if (!rule.enableCountDoraWhenHasYaukuman) {
                    return new YakuAns(yaku, 0, true, true, null, null, null);
                } else {
                    // int dora = countDora();
                    int redFive = countRedFive();
                    return new YakuAns(yaku, redFive, true, true, null, null, null);
                }
            }

            yaku.add(SEVEN_PAIRS);// 七对子

            /* 清一色 */
            int manCount = 0, pinCount = 0, souCount = 0;

            for (int i = M1; i <= M9; ++i) {
                manCount += count(i);
            }

            for (int i = P1; i <= P9; ++i) {
                pinCount += count(i);
            }

            for (int i = S1; i <= S9; ++i) {
                souCount += count(i);
            }

            if (manCount == 14 || pinCount == 14 || souCount == 14) {// 清一色
                yaku.add(FULL_FLUSH);
            } else if (manCount + sumHonor == 14 || pinCount + sumHonor == 14 || souCount + sumHonor == 14) {// 混一色
                yaku.add(HALF_FLUSH);
            }

            /* 混老头、断幺九 */
            int sumTerminal = count(M1) + count(M9) + count(P1) + count(P9) + count(S1) + count(S9);

            if (sumTerminal + sumHonor == 14) {// 混老头
                yaku.add(ALL_TERMINALS_AND_HONORS);
            } else if (sumTerminal + sumHonor == 0) {// 断幺九

                // 由于七对子一定是门清，因此无需考虑有无食断的问题
                yaku.add(DANYAO);
            }

            // int dora = countDora();
            int redFive = countRedFive();
            return new YakuAns(yaku, redFive, true, false, null, null, null);
        }

    }

    private int count(int index) {
        return fullHand.getCount(index) + (tileToWin == index ? 1 : 0);
    }

    private int countKanAs3Tile(int index, int[] kan) {
        return count(index) - kan[index];
    }

    private int countRedFive() {
        return fullHand.getRCount(TileType.MAN) + fullHand.getRCount(TileType.PIN) + fullHand.getRCount(TileType.SOU);
    }

    public static class YakuAns {

        private Set<Yaku> yaku;

        private int redFive;

        private int dora;
        private int uraDora;

        private boolean isMenzenchin;
        private boolean isYakuman;

        private TileSource bigWindSource;
        private TileSource threeDragonSource;
        private TileSource fourQuadSource;

        public YakuAns(Set<Yaku> yaku, int redFive, boolean isMenzenchin, boolean isYakuman, TileSource bigWindSource,
            TileSource threeDragonSource, TileSource fourQuadSource) {
            super();
            this.yaku              = yaku;
            this.redFive           = redFive;
            this.isMenzenchin      = isMenzenchin;
            this.isYakuman         = isYakuman;
            this.bigWindSource     = bigWindSource;
            this.threeDragonSource = threeDragonSource;
            this.fourQuadSource    = fourQuadSource;
        }

        public Set<Yaku> getYaku() {
            return yaku;
        }

        public boolean isYakuman() {
            return isYakuman;
        }

        public int getRedFive() {
            return redFive;
        }

        public boolean isMenzenchin() {
            return isMenzenchin;
        }

        public TileSource getBigWindSource() {
            return bigWindSource;
        }

        public TileSource getThreeDragonSource() {
            return threeDragonSource;
        }

        public TileSource getFourQuadSource() {
            return fourQuadSource;
        }

        public int getDora() {
            return dora;
        }

        public void setDora(int dora) {
            this.dora = dora;
        }

        public int getUraDora() {
            return uraDora;
        }

        public void setUraDora(int uraDora) {
            this.uraDora = uraDora;
        }

        public void addYaku(Yaku yaku) {
            this.yaku.add(yaku);
        }

        public void removeYaku(Yaku yaku) {
            this.yaku.remove(yaku);
        }
    }
}
