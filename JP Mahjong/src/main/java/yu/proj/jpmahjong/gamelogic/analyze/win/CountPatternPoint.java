package yu.proj.jpmahjong.gamelogic.analyze.win;

import java.util.HashMap;
import java.util.Map;

import yu.proj.jpmahjong.rule.Rule;

/**  
 * @ClassName: CountPatternPoint  
 *
 * @Description: 计算一个牌型在自摸或荣和时候的打点
 *
 * @author 余定邦  
 *
 * @date 2020年9月22日  
 *  
 */
public class CountPatternPoint {

    private NotDealerTable tableWhenNotDealerWin = new NotDealerTable();

    private DealerTable tableWhenDealerWin = new DealerTable();

    private Rule rule;

    public CountPatternPoint(Rule rule) {
        super();
        this.rule = rule;
    }

    public PointWhenNotDealerWin pointWhenYakumanAndNotDealerWin(int yakuman) {
        return tableWhenNotDealerWin.pointWhenYakuman(yakuman);
    }

    public PointWhenNotDealerWin pointWhenNotDealerWin(int fu, int han) {
        return tableWhenNotDealerWin.point(fu, han);
    }

    public PointWhenDealerWin pointWhenYakumanAndDealerWin(int yakuman) {
        return tableWhenDealerWin.pointWhenYakuman(yakuman);
    }

    public PointWhenDealerWin pointWhenDealerWin(int fu, int han) {
        return tableWhenDealerWin.point(fu, han);
    }

    /**
     * 
     * @ClassName: NotDealerTable  
     *
     * @Description: 子家打点表  
     *
     * @author 余定邦  
     *
     * @date 2020年9月22日  
     *
     */
    private class NotDealerTable {

        private Map<String, PointWhenNotDealerWin> map = new HashMap<>();

        NotDealerTable() {
            map.put("yakuman", new PointWhenNotDealerWin(32000, 8000, 16000));
            map.put("3*mangan", new PointWhenNotDealerWin(24000, 6000, 12000));
            map.put("2*mangan", new PointWhenNotDealerWin(16000, 4000, 8000));
            map.put("1.5*mangan", new PointWhenNotDealerWin(12000, 3000, 6000));
            map.put("mangan", new PointWhenNotDealerWin(8000, 2000, 4000));

            map.put("20-4", new PointWhenNotDealerWin(0, 1300, 2600));
            map.put("25-4", new PointWhenNotDealerWin(6400, 1600, 3200));
            map.put("30-4", new PointWhenNotDealerWin(7700, 2000, 3900));

            map.put("20-3", new PointWhenNotDealerWin(0, 700, 1300));
            map.put("25-3", new PointWhenNotDealerWin(3200, 800, 1600));
            map.put("30-3", new PointWhenNotDealerWin(3900, 1000, 2000));
            map.put("40-3", new PointWhenNotDealerWin(5200, 1300, 2600));
            map.put("50-3", new PointWhenNotDealerWin(6400, 1600, 3200));
            map.put("60-3", new PointWhenNotDealerWin(7700, 2000, 3900));

            map.put("20-2", new PointWhenNotDealerWin(0, 400, 700));
            map.put("25-2", new PointWhenNotDealerWin(1600, 0, 0));
            map.put("30-2", new PointWhenNotDealerWin(2000, 500, 1000));
            map.put("40-2", new PointWhenNotDealerWin(2600, 700, 1300));
            map.put("50-2", new PointWhenNotDealerWin(3200, 800, 1600));
            map.put("60-2", new PointWhenNotDealerWin(3900, 1000, 2000));
            map.put("70-2", new PointWhenNotDealerWin(4500, 1200, 2300));
            map.put("80-2", new PointWhenNotDealerWin(5200, 1300, 2600));
            map.put("90-2", new PointWhenNotDealerWin(5800, 1500, 2900));
            map.put("100-2", new PointWhenNotDealerWin(6400, 1600, 3200));
            map.put("110-2", new PointWhenNotDealerWin(7100, 1800, 3600));

            map.put("30-1", new PointWhenNotDealerWin(1000, 300, 500));
            map.put("40-1", new PointWhenNotDealerWin(1300, 400, 700));
            map.put("50-1", new PointWhenNotDealerWin(1600, 400, 800));
            map.put("60-1", new PointWhenNotDealerWin(2000, 500, 1000));
            map.put("70-1", new PointWhenNotDealerWin(2300, 600, 1200));
            map.put("80-1", new PointWhenNotDealerWin(2600, 700, 1300));
            map.put("90-1", new PointWhenNotDealerWin(2900, 800, 1500));
            map.put("100-1", new PointWhenNotDealerWin(3200, 800, 1600));
            map.put("110-1", new PointWhenNotDealerWin(3600, 0, 0));

            // 0番只比较符数，其他的算来也没用
            map.put("30-0", new PointWhenNotDealerWin(30, 0, 0));
            map.put("40-0", new PointWhenNotDealerWin(40, 0, 0));
            map.put("50-0", new PointWhenNotDealerWin(50, 0, 0));
            map.put("60-0", new PointWhenNotDealerWin(60, 0, 0));
            map.put("70-0", new PointWhenNotDealerWin(70, 0, 0));
            map.put("80-0", new PointWhenNotDealerWin(80, 0, 0));
            map.put("90-0", new PointWhenNotDealerWin(90, 0, 0));
            map.put("100-0", new PointWhenNotDealerWin(100, 0, 0));
            map.put("110-0", new PointWhenNotDealerWin(110, 0, 0));
        }

        public PointWhenNotDealerWin pointWhenYakuman(int yakuman) {
            return new PointWhenNotDealerWin(32000 * yakuman, 8000 * yakuman, 16000 * yakuman);
        }

        public PointWhenNotDealerWin point(int fu, int han) {
            if (han >= 13) {// 累计役满
                return map.get("yakuman");
            } else if (han >= 11) {// 三倍满
                return map.get("3*mangan");
            } else if (han >= 8) {// 倍满
                return map.get("2*mangan");
            } else if (han >= 6) {// 跳满
                return map.get("1.5*mangan");
            } else if (han == 5 || (han == 4 && fu >= 40) || (han == 3 && fu >= 70)) {// 满贯
                return map.get("mangan");
            } else if (rule.enableCeilToMangan && ((han == 4 && fu >= 30) || (han == 3 && fu >= 60))) {// 切上满贯
                return map.get("mangan");
            } else {
                return map.get(fu + "-" + han);
            }
        }

    }

    /**
     * 
     * @ClassName: NotDealerTable  
     *
     * @Description: 子家打点表  
     *
     * @author 余定邦  
     *
     * @date 2020年9月22日  
     *
     */
    private class DealerTable {

        private Map<String, PointWhenDealerWin> map = new HashMap<>();

        DealerTable() {
            map.put("yakuman", new PointWhenDealerWin(48000, 16000));
            map.put("3*mangan", new PointWhenDealerWin(36000, 12000));
            map.put("2*mangan", new PointWhenDealerWin(24000, 8000));
            map.put("1.5*mangan", new PointWhenDealerWin(18000, 6000));
            map.put("mangan", new PointWhenDealerWin(12000, 4000));

            map.put("20-4", new PointWhenDealerWin(0, 2600));
            map.put("25-4", new PointWhenDealerWin(9600, 3200));
            map.put("30-4", new PointWhenDealerWin(11600, 3900));

            map.put("20-3", new PointWhenDealerWin(0, 1300));
            map.put("25-3", new PointWhenDealerWin(4800, 1600));
            map.put("30-3", new PointWhenDealerWin(5800, 2000));
            map.put("40-3", new PointWhenDealerWin(7700, 2600));
            map.put("50-3", new PointWhenDealerWin(9600, 3200));
            map.put("60-3", new PointWhenDealerWin(11600, 3900));

            map.put("20-2", new PointWhenDealerWin(0, 700));
            map.put("25-2", new PointWhenDealerWin(2400, 0));
            map.put("30-2", new PointWhenDealerWin(2900, 1000));
            map.put("40-2", new PointWhenDealerWin(3900, 1300));
            map.put("50-2", new PointWhenDealerWin(4800, 1600));
            map.put("60-2", new PointWhenDealerWin(5800, 2000));
            map.put("70-2", new PointWhenDealerWin(6800, 2300));
            map.put("80-2", new PointWhenDealerWin(7700, 2600));
            map.put("90-2", new PointWhenDealerWin(8700, 2900));
            map.put("100-2", new PointWhenDealerWin(9600, 3200));
            map.put("110-2", new PointWhenDealerWin(10600, 3600));

            map.put("30-1", new PointWhenDealerWin(1500, 500));
            map.put("40-1", new PointWhenDealerWin(2000, 700));
            map.put("50-1", new PointWhenDealerWin(2400, 800));
            map.put("60-1", new PointWhenDealerWin(2900, 1000));
            map.put("70-1", new PointWhenDealerWin(3400, 1200));
            map.put("80-1", new PointWhenDealerWin(3900, 1300));
            map.put("90-1", new PointWhenDealerWin(4400, 1500));
            map.put("100-1", new PointWhenDealerWin(4800, 1600));
            map.put("110-1", new PointWhenDealerWin(5300, 0));

            // 0番只比较符数，其他的算来也没用
            map.put("30-0", new PointWhenDealerWin(30, 0));
            map.put("40-0", new PointWhenDealerWin(40, 0));
            map.put("50-0", new PointWhenDealerWin(50, 0));
            map.put("60-0", new PointWhenDealerWin(60, 0));
            map.put("70-0", new PointWhenDealerWin(70, 0));
            map.put("80-0", new PointWhenDealerWin(80, 0));
            map.put("90-0", new PointWhenDealerWin(90, 0));
            map.put("100-0", new PointWhenDealerWin(100, 0));
            map.put("110-0", new PointWhenDealerWin(110, 0));
        }

        public PointWhenDealerWin pointWhenYakuman(int yakuman) {
            return new PointWhenDealerWin(48000 * yakuman, 16000 * yakuman);
        }

        public PointWhenDealerWin point(int fu, int han) {
            if (han >= 13) {// 累计役满
                return map.get("yakuman");
            } else if (han >= 11) {// 三倍满
                return map.get("3*mangan");
            } else if (han >= 8) {// 倍满
                return map.get("2*mangan");
            } else if (han >= 6) {// 跳满
                return map.get("1.5*mangan");
            } else if (han == 5 || (han == 4 && fu >= 40) || (han == 3 && fu >= 70)) {// 满贯
                return map.get("mangan");
            } else if (rule.enableCeilToMangan && ((han == 4 && fu >= 30) || (han == 3 && fu >= 60))) {// 切上满贯
                return map.get("mangan");
            } else {
                return map.get(fu + "-" + han);
            }
        }
    }

    public static class PointWhenDealerWin {

        private int pointWhenRon;
        private int pointWhenTsumoFromNotDealer;

        public PointWhenDealerWin(int pointWhenRon, int pointWhenTsumoFromNotDealer) {
            super();
            this.pointWhenRon                = pointWhenRon;
            this.pointWhenTsumoFromNotDealer = pointWhenTsumoFromNotDealer;
        }

        public int getPointWhenRon() {
            return pointWhenRon;
        }

        public int getPointWhenTsumoFromNotDealer() {
            return pointWhenTsumoFromNotDealer;
        }

        @Override
        public int hashCode() {
            final int prime  = 31;
            int       result = 1;
            result = prime * result + pointWhenRon;
            result = prime * result + pointWhenTsumoFromNotDealer;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PointWhenDealerWin other = (PointWhenDealerWin)obj;
            if (pointWhenRon != other.pointWhenRon)
                return false;
            if (pointWhenTsumoFromNotDealer != other.pointWhenTsumoFromNotDealer)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "PointWhenDealerWin [pointWhenRon=" + pointWhenRon + ", pointWhenTsumoFromNotDealer="
                + pointWhenTsumoFromNotDealer + "]";
        }
    }

    public static class PointWhenNotDealerWin {

        private int pointWhenRon;
        private int pointWhenTsumoFromNotDealer;
        private int pointWhenTsumoFromDealer;

        public PointWhenNotDealerWin(int pointWhenRon, int pointWhenTsumoFromNotDealer, int pointWhenTsumoFromDealer) {
            super();
            this.pointWhenRon                = pointWhenRon;
            this.pointWhenTsumoFromNotDealer = pointWhenTsumoFromNotDealer;
            this.pointWhenTsumoFromDealer    = pointWhenTsumoFromDealer;
        }

        public int getPointWhenRon() {
            return pointWhenRon;
        }

        public int getPointWhenTsumoFromNotDealer() {
            return pointWhenTsumoFromNotDealer;
        }

        public int getPointWhenTsumoFromDealer() {
            return pointWhenTsumoFromDealer;
        }

        @Override
        public int hashCode() {
            final int prime  = 31;
            int       result = 1;
            result = prime * result + pointWhenRon;
            result = prime * result + pointWhenTsumoFromDealer;
            result = prime * result + pointWhenTsumoFromNotDealer;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PointWhenNotDealerWin other = (PointWhenNotDealerWin)obj;
            if (pointWhenRon != other.pointWhenRon)
                return false;
            if (pointWhenTsumoFromDealer != other.pointWhenTsumoFromDealer)
                return false;
            if (pointWhenTsumoFromNotDealer != other.pointWhenTsumoFromNotDealer)
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "PointWhenNotDealerWin [pointWhenRon=" + pointWhenRon + ", pointWhenTsumoFromNotDealer="
                + pointWhenTsumoFromNotDealer + ", pointWhenTsumoFromDealer=" + pointWhenTsumoFromDealer + "]";
        }
    }
}
