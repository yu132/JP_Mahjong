package yu.proj.jpmahjong.gamelogic.analyze.win;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.player.TileSource;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.Tile;
import yu.proj.jpmahjong.tiles.meld.Meld;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;
import yu.proj.jpmahjong.tiles.tenpaiPattern.SevenPairs;
import yu.proj.jpmahjong.tiles.tenpaiPattern.ThirteenOrphans;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.FourMeldAndAPair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Pair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Sequence;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Singleton;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.TripletAndQuad;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Wait2Side;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.WaitMiddle;

/**  
 * @ClassName: Tenpai  
 *
 * @Description: 检查一个13张的牌型是否已经听牌，并且返回所有的听牌可能
 * 
 * 注意：听牌不代表是否有役，是否有役需要另外检查
 *
 * @author 余定邦  
 *
 * @date 2020年9月10日  
 *  
 */
public class AnalyzeTenpai {

    private Rule rule;

    /**
     * 只计非鸣牌（暗杠也不计）的手牌
     */
    private CountNum concealedHand;

    /**
     * 由于虚听需要检查所有的手牌，因此这里需要所有的手牌
     */
    private CountNum fullHand;

    private List<Meld> makeCall;
    private List<Meld> concealedKan;

    private int[] used = new int[34];

    private Map<Integer, List<TilePatternNode>> ansMap;

    public AnalyzeTenpai(Rule rule, CountNum concealedHand, CountNum fullHand, List<Meld> makeCall,
        List<Meld> concealedKan) {
        super();
        this.rule          = rule;
        this.concealedHand = concealedHand;
        this.fullHand      = fullHand;
        this.makeCall      = makeCall;
        this.concealedKan  = concealedKan;
    }

    /**
     * 
     * 用于分析立直情况的听牌构造器
     * 和一般
     *
     * @param rule
     * @param concealedHand
     * @param makeCall
     * @param concealedKan
     * @param tileToIngnore
     */
    public AnalyzeTenpai(Rule rule, CountNum concealedHand, List<Meld> makeCall, List<Meld> concealedKan,
        int tileToIngnore) {
        super();
        this.rule          = rule;
        this.concealedHand = concealedHand;
        this.makeCall      = makeCall;
        this.concealedKan  = concealedKan;
        ++used[tileToIngnore];// 相当于直接少一张牌
    }

    public Map<Integer, List<TilePatternNode>> isTenpai() {

        if (ansMap == null) {

            List<TilePatternNode> ans               = new ArrayList<>();

            boolean               isThirteenOrphans = false;

            if (makeCall.size() == 0 && concealedKan.size() == 0) {// 门清且没有暗杠 需要额外考虑七对子和国士无双

                // 国士无双
                /*
                 * 国士无双的判断略微复杂，因为需要先计算幺九牌中有几种张数为0的
                 * 最多只能有1种为0，如果有2种或以上，那么就不是国士无双听牌
                 * 然后在至多有1种牌张数为0的情况下，计算所有幺九牌的张数总数，
                 * 如果张数不到13张，那么手牌里还有别的幺九牌，那也肯定不行
                 * 最后根据之前获取的为0的张数种类数判断是不是13面
                 * 如果有1种为0，则不是，如果没有为0的，那就是
                 */
                {

                    boolean flag      = true;

                    boolean wait13    = true;

                    int[]   tileToWin = null;

                    int     sum       = 0;

                    int[]   indexes   = new int[] {M1, M9, P1, P9, S1, S9, E, S, W, N, WH, G, R};

                    Tile[]  tiles     = new Tile[13];
                    int     index     = 0;

                    for (int i = 0; i < indexes.length; ++i) {
                        int num = getCount(indexes[i]);
                        if (num == 0) {
                            if (wait13) {
                                wait13       = false;
                                tileToWin    = new int[1];
                                tileToWin[0] = indexes[i];
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        sum += num;

                        for (int j = 0; j < num; ++j) {
                            tiles[index++] = concealedHand.getTile(indexes[i], j);
                        }

                    }

                    if (wait13) {
                        tileToWin = new int[13];
                        for (int i = 0; i < indexes.length; ++i) {
                            tileToWin[i] = indexes[i];
                        }
                    }

                    if (flag && sum == 13) {
                        ThirteenOrphans to = new ThirteenOrphans(tiles, wait13, tileToWin);
                        ans.add(to);
                        isThirteenOrphans = true;
                    }

                }

                // 七对子 七对子的判断方法很简单，就是6个2+1个1
                if (!isThirteenOrphans) {// 国士无双和所有其他和牌牌型都不兼容
                    List<Completed>    comp  = new ArrayList<>();
                    List<NotCompleted> nComp = new ArrayList<>();
                    for (int i = M1; i <= R; ++i) {
                        if (getCount(i) == 2) {
                            Pair pair = new Pair(new Tile[] {concealedHand.getTile(i, 0), concealedHand.getTile(i, 1)});
                            comp.add(pair);
                        } else if (getCount(i) == 1 && nComp.size() == 0) {
                            Singleton sing = new Singleton(concealedHand.getTile(i, 0));
                            nComp.add(sing);
                        }
                    }
                    if (comp.size() == 6 && nComp.size() == 1) {
                        ans.add(new SevenPairs(comp, nComp));
                    }
                }

            }
            // 考虑4面子1雀头的情况
            if (!isThirteenOrphans) {// 国士无双和所有其他和牌牌型都不兼容

                int                meld     = makeCall.size() + concealedKan.size();// 已经有的面子数量，这些面子是副露或者暗杠，是不能改变的了

                // 预处理副露和暗杠,将其加入数组中
                List<Completed>    compNow  = new ArrayList<>();
                List<NotCompleted> nCompNow = new ArrayList<>();

                for (Meld ncm : makeCall) {
                    switch (ncm.getType()) {
                        case SEQUENCE:
                            Sequence seq = new Sequence(ncm.getTiles(), true);
                            compNow.add(seq);
                            break;

                        case TRIPLET:
                        case EXPOSED_QUAD:
                            TripletAndQuad taq = new TripletAndQuad(ncm.getTiles(), true, ncm.getSrc(), ncm.isAddKan(),
                                ncm.getMakeCallWhenAddKan());
                            compNow.add(taq);
                            break;

                        default:
                            break;
                    }
                }

                // 预处理暗杠
                for (Meld ncm : concealedKan) {
                    TripletAndQuad taq = new TripletAndQuad(ncm.getTiles(), false, TileSource.SELF, false, -1);
                    compNow.add(taq);
                }

                helper4m1p(M1, meld, 0, 0, 0, 0, compNow, nCompNow, ans, 0);
            }

            /*
             * 最后还需要将数据结构倒置一下，我们最终需要判定的是那张牌能和，而不是一大坨东西，然后去找能和的牌
             * 下一步还需要判断和的那张牌是不是有役，如果没役，那么即使别人打了那张牌，也和不了
             */
            ansMap = new HashMap<>();

            for (TilePatternNode node : ans) {
                if (node instanceof ThirteenOrphans) {
                    ThirteenOrphans to = (ThirteenOrphans)node;
                    for (int tileToWin : to.getTileToWin()) {
                        ansMap.computeIfAbsent(tileToWin, (x) -> new ArrayList<>()).add(node);
                    }
                } else if (node instanceof SevenPairs) {
                    SevenPairs sp = (SevenPairs)node;
                    for (int tileToWin : sp.getNotCompleted().get(0).getTileToWin()) {
                        ansMap.computeIfAbsent(tileToWin, (x) -> new ArrayList<>()).add(node);
                    }
                } else if (node instanceof FourMeldAndAPair) {
                    FourMeldAndAPair fmaap = (FourMeldAndAPair)node;
                    for (NotCompleted nc : fmaap.getNotCompleted()) {
                        for (int tileToWin : nc.getTileToWin()) {
                            // 是否承认虚听规则，规则详见定义的页面，只有4面子1雀头的牌型存在这种问题
                            if (rule.enableNotRealTenpai) {
                                // 如果承认虚听，那么我们就不再需要检查牌型
                                ansMap.computeIfAbsent(tileToWin, (x) -> new ArrayList<>()).add(node);
                            } else if (fullHand.getCount(tileToWin) < 4) {// 如果这种牌手上没有4张，那么就不是虚听
                                ansMap.computeIfAbsent(tileToWin, (x) -> new ArrayList<>()).add(node);
                            }
                        }
                    }
                }
            }
        }

        return ansMap;
    }



    /**
     * 
     * 计算4面子1雀头的方法大致是这样的，需要使用回溯法来进行判断
     * 首先对于1-7的牌，可以考虑将其构成 单骑、对子、两面听、坎张听、刻子、顺子
     * 对于8，可以考虑将其构成 单骑、对子、两面听、刻子
     * 对于9和字牌，则只能考虑构成 单骑、对子和刻子
     * 对于每张牌的每种情况，我们都需要进行考虑，因此需要回溯
     * 最后会剩下这样几种情况属于听牌，其余的情况都不是听牌
     * 1两面听 + 1对子 + 3面子
     * 1坎张听 + 1对子 + 3面子
     * 2对子 + 3面子、
     * 1单骑 + 4面子
     * 
     * @Title: helper4m1p  
     *
     * @Description: 确定是不是4面子1雀头的听牌牌型
     *
     * @param index         当前所选择需要去检查的牌
     * @param meld          面子的个数
     * @param pair          对子的个数
     * @param singleton     单骑的个数
     * @param wait2side     两面听的个数
     * @param waitMiddle    坎张听的个数
     * @param compNow       完整的面子的个数
     * @param nCompNow      不完整的个数
     * @param ans           回收听牌牌型结果的数据结构
     * 
     * @param checkOrder 是为了牌型检测不重复而加入的判断
     * 首先给每种形状编号，刻子-1，对子-2，单骑-3，两面听-4，坎张听-5，顺子-6
     * 然后要求必须按照序号进行遍历，例如遍历完刻子可以遍历顺子，但是遍历完顺子之后不能再去遍历刻子
     * 这样保证了顺序的唯一性，因此就不会产生重复
     *
     */
    private void helper4m1p(int index, int meld, int pair, int singleton, int wait2side, int waitMiddle,
        List<Completed> compNow, List<NotCompleted> nCompNow, List<TilePatternNode> ans, int checkOrder) {

        if (index == R + 1) {// 遍历完所有的牌了
            boolean flag = false;
            if (meld == 4 && singleton == 1) {// 1单骑 + 4面子
                flag = true;
            } else if (meld == 3 && pair == 2) {// 2对子 + 3面子
                flag = true;
            } else if (meld == 3 && pair == 1 && wait2side == 1) {// 1两面听 + 1对子 + 3面子
                flag = true;
            } else if (meld == 3 && pair == 1 && waitMiddle == 1) {// 1坎张听 + 1对子 + 3面子
                flag = true;
            }

            if (flag) {
                List<Completed>    comp  = new ArrayList<>();
                List<NotCompleted> nComp = new ArrayList<>();

                comp.addAll(compNow);

                // 当对子只有一个的时候，那么这个对子肯定是雀头，因此需要将其从未完成列表中捞出来
                // 因为我们在之前是将对子都先放到未完成里面的
                if (pair == 1) {
                    for (NotCompleted nc : nCompNow) {
                        if (nc instanceof Pair) {
                            comp.add((Pair)nc);
                        } else {
                            nComp.add(nc);
                        }
                    }
                } else {
                    nComp.addAll(nCompNow);
                }

                ans.add(new FourMeldAndAPair(comp, nComp, meld, pair, singleton, wait2side, waitMiddle));
            }

            return;// 到末尾了，应该返回了
        }

        int count = getCount(index);

        if (count == 0) {// 这种牌都没有,直接过
            helper4m1p(index + 1, meld, pair, singleton, wait2side, waitMiddle, compNow, nCompNow, ans, 0);

            // 因为下面的逻辑认为牌数不可能为0，因此这里必须拦住，进行返回
            return;
        }

        boolean is1to7 = (index >= M1 && index <= M7) || (index >= P1 && index <= P7) || (index >= S1 && index <= S7);
        boolean is8    = index == M8 || index == P8 || index == S8;

        if (count >= 3) {// 一种牌超过3张，可以视为刻子

            if (checkOrder < 1) {

                Tile[] tiles = new Tile[3];
                for (int i = 0; i < 3; ++i) {
                    tiles[i] = concealedHand.getTile(index, used[index] + i);
                }

                TripletAndQuad taq = new TripletAndQuad(tiles, false, TileSource.SELF, false, -1);
                compNow.add(taq);

                used[index] += 3;

                /*
                 * 加入了一个刻子，因此面子+1，其余不变
                 */
                helper4m1p(index, meld + 1, pair, singleton, wait2side, waitMiddle, compNow, nCompNow, ans, 1);

                used[index] -= 3;
                compNow.remove(compNow.size() - 1);
            }
        }

        if (count >= 2) {// 一种牌超过2张，可以视为对子

            /*
             * 2-2和1-3没有本质的区别，都是属于一定听不到的牌，因此可以当作是一种重复
             * 因此放弃2-2的牌型构成，仅允许1-3
             */
            if (checkOrder < 2) {

                /*
                 * 视作对子也有前提，那就是对子数量不能太多，当没有双面听和坎张听的时候，对子最多可以有两个
                 * 但是有双面听和坎张听的时候，就最多只能有1个对子
                 * 但单骑存在时，也不能有对子
                 */
                if (singleton == 0 && (pair == 0 || (pair == 1 && (wait2side + waitMiddle) == 0))) {
                    Tile[] tiles = new Tile[2];
                    for (int i = 0; i < 2; ++i) {
                        tiles[i] = concealedHand.getTile(index, used[index] + i);
                    }

                    Pair p = new Pair(tiles);

                    nCompNow.add(p);
                    used[index] += 2;

                    /*
                     * 加入了一个对子，因此对子+1，其余不变
                     */
                    helper4m1p(index, meld, pair + 1, singleton, wait2side, waitMiddle, compNow, nCompNow, ans, 2);

                    used[index] -= 2;
                    nCompNow.remove(nCompNow.size() - 1);
                }
            }
        }

        {// 因为不是0张，所以至少也有1张，因此可以视作单骑

            if (checkOrder < 3) {

                // 单骑有且只能有1个，并且单骑存在的时候，不能有面子以外的任何东西
                if (singleton == 0 && pair == 0 && wait2side == 0 && waitMiddle == 0) {
                    Singleton sing = new Singleton(concealedHand.getTile(index, used[index]));

                    nCompNow.add(sing);
                    used[index] += 1;

                    /*
                     * 加入了一个单骑，因此单骑+1，其余不变
                     */
                    helper4m1p(index, meld, pair, singleton + 1, wait2side, waitMiddle, compNow, nCompNow, ans, 3);

                    used[index] -= 1;
                    nCompNow.remove(nCompNow.size() - 1);
                }
            }
        }

        if (is1to7 || is8) {// 1-8的序数牌可以尝试构成两面听

            if (checkOrder < 4) {

                /*
                 * 两面听自己最多只有一个，条件是不能有坎张听、单骑听，对子最多有1个
                 */
                if (singleton == 0 && wait2side == 0 && waitMiddle == 0 && pair <= 1) {

                    if (getCount(index + 1) >= 1) {// 该牌的下一张牌必须要有，例如现在是3s，那么4s也必须在手上

                        Tile[] tiles = new Tile[2];

                        tiles[0] = concealedHand.getTile(index, used[index]);
                        tiles[1] = concealedHand.getTile(index + 1, used[index + 1]);

                        Wait2Side w2s = new Wait2Side(tiles);

                        nCompNow.add(w2s);
                        ++used[index];
                        ++used[index + 1];

                        helper4m1p(index, meld, pair, singleton, wait2side + 1, waitMiddle, compNow, nCompNow, ans, 4);

                        --used[index];
                        --used[index + 1];
                        nCompNow.remove(nCompNow.size() - 1);
                    }
                }
            }
        }

        if (is1to7) {// 1-7的序数牌还有坎张听和顺子

            if (checkOrder < 5) {

                /*
                 * 坎张听自己最多只有一个，条件是不能有两面听、单骑听，对子最多有1个
                 */
                if (singleton == 0 && wait2side == 0 && waitMiddle == 0 && pair <= 1) {

                    if (getCount(index + 2) >= 1) {// 该牌的下下一张牌必须要有，例如现在是3s，那么5s也必须在手上

                        Tile[] tiles = new Tile[2];

                        tiles[0] = concealedHand.getTile(index, used[index]);
                        tiles[1] = concealedHand.getTile(index + 2, used[index + 2]);

                        WaitMiddle wm = new WaitMiddle(tiles);

                        nCompNow.add(wm);
                        ++used[index];
                        ++used[index + 2];

                        helper4m1p(index, meld, pair, singleton, wait2side, waitMiddle + 1, compNow, nCompNow, ans, 5);

                        --used[index];
                        --used[index + 2];
                        nCompNow.remove(nCompNow.size() - 1);
                    }
                }
            }

            // 顺子是可以重复（例如一杯口）的，需要考虑到这点，因此要有等号
            if (checkOrder <= 6) {

                // 顺子没什么特别的要求，但是该牌的下两张牌必须要有至少1张，例如现在是3s，那么4s和5s也必须在手上
                if (getCount(index + 1) >= 1 && getCount(index + 2) >= 1) {
                    Tile[] tiles = new Tile[3];

                    for (int i = 0; i < 3; ++i) {
                        tiles[i] = concealedHand.getTile(index + i, used[index + i]);
                    }

                    Sequence sequence = new Sequence(tiles, false);

                    compNow.add(sequence);
                    ++used[index];
                    ++used[index + 1];
                    ++used[index + 2];

                    helper4m1p(index, meld + 1, pair, singleton, wait2side, waitMiddle, compNow, nCompNow, ans, 6);

                    --used[index];
                    --used[index + 1];
                    --used[index + 2];
                    compNow.remove(compNow.size() - 1);
                }
            }
        }
    }

    private int getCount(int index) {
        return concealedHand.getCount(index) - used[index];
    }


}
