package yu.proj.jpmahjong.gamelogic.analyze.win;

import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternPoint.PointWhenDealerWin;
import yu.proj.jpmahjong.gamelogic.analyze.win.CountPatternPoint.PointWhenNotDealerWin;

/**  
 * @ClassName: CountPatternPoint  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年9月22日  
 *  
 */
public class TestCountPatternPoint {

    public static PointWhenDealerWin pointWhenDealerWin(int fu, int han) {

        if (han >= 13) {// 累计役满
            return new PointWhenDealerWin(48000, 16000);
        } else if (han >= 11) {// 三倍满
            return new PointWhenDealerWin(36000, 12000);
        } else if (han >= 8) {// 倍满
            return new PointWhenDealerWin(24000, 8000);
        } else if (han >= 6) {// 跳满
            return new PointWhenDealerWin(18000, 6000);
        } else if (han == 5) {
            return new PointWhenDealerWin(12000, 4000);
        }

        int a = (int)(fu * Math.pow(2, 2 + han));

        if (a >= 2000) {
            return new PointWhenDealerWin(12000, 4000);
        }

        return new PointWhenDealerWin(ceil(6 * a), ceil(2 * a));
    }

    public static PointWhenNotDealerWin pointWhenNotDealerWin(int fu, int han) {

        if (han >= 13) {// 累计役满
            return new PointWhenNotDealerWin(32000, 8000, 16000);
        } else if (han >= 11) {// 三倍满
            return new PointWhenNotDealerWin(24000, 6000, 12000);
        } else if (han >= 8) {// 倍满
            return new PointWhenNotDealerWin(16000, 4000, 8000);
        } else if (han >= 6) {// 跳满
            return new PointWhenNotDealerWin(12000, 3000, 6000);
        } else if (han == 5) {
            return new PointWhenNotDealerWin(8000, 2000, 4000);
        }

        int a = (int)(fu * Math.pow(2, 2 + han));

        if (a >= 2000) {
            return new PointWhenNotDealerWin(8000, 2000, 4000);
        }

        return new PointWhenNotDealerWin(ceil(4 * a), ceil(1 * a), ceil(2 * a));
    }

    private static int ceil(int point) {
        return (int)(Math.ceil(point / 100.0) * 100);
    }

    // public static void main(String[] args) {
    // int[] fu = {20, 25, 30, 40, 50, 60, 70, 80, 90, 100, 110};
    // int[] han = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    //
    // CountPatternPoint cpp = new CountPatternPoint(Rule.MAHJONG_SOUL_RULE);
    //
    // for (int i = 0; i < fu.length; ++i) {
    // for (int j = 0; j < han.length; ++j) {
    // if (han[j] == 1 && (fu[i] == 20 || fu[i] == 25)) {
    // continue;
    // }
    // PointWhenNotDealerWin pwndw1 = cpp.pointWhenNotDealerWin(fu[i], han[j]);
    // PointWhenNotDealerWin pwndw2 = pointWhenNotDealerWin(fu[i], han[j]);
    //
    // if (!pwndw1.equals(pwndw2)) {
    // System.out.println(fu[i] + "-" + han[j]);
    //
    // System.out.println(pwndw1);
    // System.out.println(pwndw2);
    //
    // System.out.println();
    // }
    // }
    // }
    //
    // for (int i = 0; i < fu.length; ++i) {
    // for (int j = 0; j < han.length; ++j) {
    // if (han[j] == 1 && (fu[i] == 20 || fu[i] == 25)) {
    // continue;
    // }
    // PointWhenDealerWin pwdw1 = cpp.pointWhenDealerWin(fu[i], han[j]);
    // PointWhenDealerWin pwdw2 = pointWhenDealerWin(fu[i], han[j]);
    //
    // if (!pwdw1.equals(pwdw2)) {
    // System.out.println(fu[i] + "-" + han[j]);
    //
    // System.out.println(pwdw1);
    // System.out.println(pwdw2);
    //
    // System.out.println();
    // }
    // }
    // }
    // }

    public static void main(String[] args) {
        int[] fu = {20, 25, 30, 40, 50, 60, 70, 80, 90, 100, 110};
        for (int han = 1; han < 14; ++han) {
            for (int han2 = 1; han2 < 14; ++han2) {
                for (int i = 0; i < fu.length; ++i) {
                    for (int j = 0; j < fu.length; ++j) {
                        PointWhenNotDealerWin pwndw1 = pointWhenNotDealerWin(fu[j], han2);
                        PointWhenNotDealerWin pwndw2 = pointWhenNotDealerWin(fu[i], han);

                        if (pwndw1.getPointWhenRon() == pwndw2.getPointWhenRon()) {

                            // if (han != han2) {
                            // System.out.println(han + " " + han2);
                            // }

                            pwndw1 = pointWhenNotDealerWin(fu[j], han2 + 2);
                            pwndw2 = pointWhenNotDealerWin(fu[i], han + 2);

                            if (pwndw1.getPointWhenRon() != pwndw2.getPointWhenRon()
                                && pwndw1.getPointWhenRon() < 8000) {
                                System.out.println(fu[j] + " " + han2);
                                System.out.println(fu[i] + " " + han);
                                System.out.println();
                            }
                        }
                    }
                }
            }
        }
    }

}
