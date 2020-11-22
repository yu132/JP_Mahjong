package yu.proj.ref.rule.redFive;

import lombok.Getter;

/**  
 * @ClassName: RedFiveNumber  
 *
 * @Description: 红宝牌的数量 
 *
 * @author 余定邦  
 *
 * @date 2020年11月16日  
 *  
 */

@Getter
public class RedFiveNumber {

    private int redFiveManNumber;

    private int redFivePinNumber;

    private int redFiveSouNumber;

    public RedFiveNumber(int redFiveManNumber, int redFivePinNumber, int redFiveSouNumber) {
        super();

        assert checkTileNumber(redFiveManNumber) && checkTileNumber(redFivePinNumber)
            && checkTileNumber(redFiveSouNumber);

        this.redFiveManNumber = redFiveManNumber;
        this.redFivePinNumber = redFivePinNumber;
        this.redFiveSouNumber = redFiveSouNumber;
    }

    private boolean checkTileNumber(int num) {
        return num >= 0 && num <= 4;
    }

}
