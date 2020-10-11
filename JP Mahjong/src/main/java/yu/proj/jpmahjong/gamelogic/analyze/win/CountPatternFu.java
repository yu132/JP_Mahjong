package yu.proj.jpmahjong.gamelogic.analyze.win;

import static yu.proj.jpmahjong.gamelogic.analyze.CountNum.*;

import yu.proj.jpmahjong.gamelogic.analyze.CountNum;
import yu.proj.jpmahjong.rule.Rule;
import yu.proj.jpmahjong.tiles.TileType;
import yu.proj.jpmahjong.tiles.tenpaiPattern.Completed;
import yu.proj.jpmahjong.tiles.tenpaiPattern.NotCompleted;
import yu.proj.jpmahjong.tiles.tenpaiPattern.SevenPairs;
import yu.proj.jpmahjong.tiles.tenpaiPattern.ThirteenOrphans;
import yu.proj.jpmahjong.tiles.tenpaiPattern.TilePatternNode;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.FourMeldAndAPair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Pair;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.Singleton;
import yu.proj.jpmahjong.tiles.tenpaiPattern.m4p1.TripletAndQuad;

/**  
 * @ClassName: CountFu  
 *
 * @Description: 计算一个牌型的符数
 * 
 * @author 余定邦  
 *
 * @date 2020年9月21日  
 *  
 */
public class CountPatternFu {

    private Rule rule;

    private TileType prevalentWind;
    private TileType seatWind;
    private TilePatternNode tilePatternNode;
    private int tileToWin;

    private boolean isPinfu;
    private boolean isMenzenchin;

    public CountPatternFu(Rule rule, TileType prevalentWind, TileType seatWind, TilePatternNode tilePatternNode,
        int tileToWin, boolean isPinfu, boolean isMenzenchin) {
        super();
        this.rule            = rule;
        this.prevalentWind   = prevalentWind;
        this.seatWind        = seatWind;
        this.tilePatternNode = tilePatternNode;
        this.tileToWin       = tileToWin;
        this.isPinfu         = isPinfu;
        this.isMenzenchin    = isMenzenchin;
    }

    public FuAns countFu() {

        if (tilePatternNode instanceof ThirteenOrphans) {// 国士无双固定20符（其实多少符也没差，因为役满）
            return new FuAns(20, 20);
        } else if (tilePatternNode instanceof SevenPairs) {// 七对子固定25符，其他全部不计
            return new FuAns(25, 25);
        } else {// 4m1p 分析四面子一雀头的情况

            if (isPinfu) {// 平和番数固定
                return new FuAns(30, 20);
            }

            int              fuWhenRon   = 0;
            int              fuWhenTsumo = 0;

            FourMeldAndAPair m4p1        = (FourMeldAndAPair)tilePatternNode;

            for (Completed completed : m4p1.getCompleted()) {
                if (completed instanceof TripletAndQuad) {// 刻子的符数比较复杂
                    TripletAndQuad taq            = (TripletAndQuad)completed;

                    int            tileTypeNumber = CountNum.getTileIndex(taq.getTiles()[0]);

                    if ((tileTypeNumber >= M2 && tileTypeNumber <= M8) || (tileTypeNumber >= P2 && tileTypeNumber <= P8)
                        || (tileTypeNumber >= S2 && tileTypeNumber <= S8)) {// 中张刻或杠

                        if (taq.isMakeCall()) {
                            if (taq.getTiles().length == 3) {// 中张明刻 2符
                                fuWhenRon   += 2;
                                fuWhenTsumo += 2;
                            } else {// 中张明杠 8符
                                fuWhenRon   += 8;
                                fuWhenTsumo += 8;
                            }
                        } else {
                            if (taq.getTiles().length == 3) {// 中张暗刻 4符
                                fuWhenRon   += 4;
                                fuWhenTsumo += 4;
                            } else {// 中张暗杠 16符
                                fuWhenRon   += 16;
                                fuWhenTsumo += 16;
                            }
                        }
                    } else {// 幺九刻或杠

                        if (taq.isMakeCall()) {
                            if (taq.getTiles().length == 3) {// 幺九明刻 4符
                                fuWhenRon   += 4;
                                fuWhenTsumo += 4;
                            } else {// 幺九明杠 16符
                                fuWhenRon   += 16;
                                fuWhenTsumo += 16;
                            }
                        } else {
                            if (taq.getTiles().length == 3) {// 幺九暗刻 8符
                                fuWhenRon   += 8;
                                fuWhenTsumo += 8;
                            } else {// 幺九暗杠 32符
                                fuWhenRon   += 32;
                                fuWhenTsumo += 32;
                            }
                        }
                    }

                }
            }

            for (NotCompleted notCompleted : m4p1.getNotCompleted()) {

                // 这边只用分析雀头和碰，因此边张、两面和坎张听都不管
                if (notCompleted instanceof Pair) {
                    Pair pair      = (Pair)notCompleted;

                    int  tileIndex = getTileIndex(pair.getTiles()[0]);

                    if (tileIndex == tileToWin) {// 这个对子构成一副刻子

                        // 由于荣和的时候不计暗刻，因此这里自摸和荣和符数算出来是不一样的
                        if ((tileToWin >= M2 && tileToWin <= M8) || (tileToWin >= P2 && tileToWin <= P8)
                            || (tileToWin >= S2 && tileToWin <= S8)) {// 中张刻

                            fuWhenRon   += 2;// 中张明刻 2符
                            fuWhenTsumo += 4;// 中张暗刻 4符

                        } else {// 幺九刻

                            fuWhenRon   += 4;// 幺九明刻 4符
                            fuWhenTsumo += 8;// 幺九暗刻 8符
                        }
                    } else {// 由于其不构成刻子，因此是真的雀头

                        int fu = analyzePair(tileIndex);

                        fuWhenRon   += fu;
                        fuWhenTsumo += fu;
                    }
                } else if (notCompleted instanceof Singleton) {// 单骑一定是听雀头
                    int tileIndex = getTileIndex(((Singleton)notCompleted).getTile());

                    int fu        = analyzePair(tileIndex);

                    fuWhenRon   += fu;
                    fuWhenTsumo += fu;
                }
            }

            if (isMenzenchin) {// 门清荣和10符
                fuWhenRon += 10;
            }

            fuWhenTsumo += 2;// 自摸2符

            // 返回切上的符数
            return new FuAns(ceil(fuWhenRon), ceil(fuWhenTsumo));
        }
    }

    /**
     * 
     * @Title: analyzePair  
     *
     * @Description: 分析雀头
     *
     * @return 雀头的符数
     *
     */
    private int analyzePair(int tileTypeNumberOfPair) {
        int fu = 0;
        if (rule.enablePrevalentWindAndseatWindPairCnoutAs4Fu) {
            if (tileTypeNumberOfPair == honorTileIndex(prevalentWind)) {// 场风雀头 2符
                fu += 2;
            }
            if (tileTypeNumberOfPair == honorTileIndex(seatWind)) {// 自风雀头 2符
                fu += 2;
            } else if (tileTypeNumberOfPair == WH || tileTypeNumberOfPair == G || tileTypeNumberOfPair == R) {// 三元雀头 2符
                fu += 2;
            }
        } else {
            if (prevalentWind == seatWind) {
                if (tileTypeNumberOfPair == honorTileIndex(prevalentWind)) {// 按照规则，连风雀头记作2符
                    fu += 2;
                }
            } else if (tileTypeNumberOfPair == honorTileIndex(prevalentWind)) {// 场风雀头 2符
                fu += 2;
            } else if (tileTypeNumberOfPair == honorTileIndex(seatWind)) {// 自风雀头 2符
                fu += 2;
            } else if (tileTypeNumberOfPair == WH || tileTypeNumberOfPair == G || tileTypeNumberOfPair == R) {// 三元雀头 2符
                fu += 2;
            }
        }
        return fu;
    }

    /**
     * 
     * @Title: ceil  
     *
     * @Description: TODO(这里用一句话描述这个方法的作用)  
     *
     * @param fu
     * 
     * @return 切上的符数
     *
     */
    private int ceil(int fu) {
        return (int)(Math.ceil(fu / 10.0) * 10);
    }

    public class FuAns {
        private int fuWhenRon;
        private int fuWhenTsumo;

        public FuAns(int fuWhenRon, int fuWhenTsumo) {
            super();
            this.fuWhenRon   = fuWhenRon;
            this.fuWhenTsumo = fuWhenTsumo;
        }

        public int getFuWhenRon() {
            return fuWhenRon;
        }

        public void setFuWhenRon(int fuWhenRon) {
            this.fuWhenRon = fuWhenRon;
        }

        public int getFuWhenTsumo() {
            return fuWhenTsumo;
        }

        public void setFuWhenTsumo(int fuWhenTsumo) {
            this.fuWhenTsumo = fuWhenTsumo;
        }
    }

}
