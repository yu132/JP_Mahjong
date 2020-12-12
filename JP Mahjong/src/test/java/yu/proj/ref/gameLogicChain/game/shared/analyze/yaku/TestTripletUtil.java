package yu.proj.ref.gameLogicChain.game.shared.analyze.yaku;

import static org.junit.Assert.*;
import static yu.proj.ref.tile.TileType.*;

import java.util.function.Function;

import yu.proj.ref.gameLogicChain.game.shared.analyze.TestAnalyzeData;
import yu.proj.ref.tile.TileType;
import yu.proj.ref.tile.Yaku;

/**  
 * @ClassName: TestTripletUtil  
 *
 * @Description: TODO(这里用一句话描述这个类的作用)  
 *
 * @author 余定邦  
 *
 * @date 2020年12月12日  
 *  
 */
public class TestTripletUtil {

    private TestAnalyzeData taData = new TestAnalyzeData();

    private YakuAnalyzer analyzer;

    private Yaku containYaku;

    public TestTripletUtil(TileType tripletType, Yaku containYaku, YakuAnalyzer analyzer) {
        super();

        initTiles(tripletType);

        this.containYaku = containYaku;
        this.analyzer = analyzer;
    }

    private void initTiles(TileType tripletType) {
        taData.draw(tripletType, tripletType, tripletType);

        taData.draw(MAN_4, MAN_4, MAN_4);

        taData.draw(MAN_6, MAN_6, MAN_6);

        taData.draw(MAN_8, MAN_8, MAN_8);

        taData.draw(PIN_2);
    }

    public void test(Function<TestAnalyzeData, YakuAnalyzeData> yaDataGetter) {

        analyzer.analyzeYaku(yaDataGetter.apply(taData), taData.yakuManager);

        assertTrue(taData.bothContainYaku(containYaku));
    }

    public void test() {
        test((taData) -> taData.yaData(taData.getFirstTenpai(PIN_2), PIN_2));
    }

    public TestAnalyzeData getTaData() {
        return taData;
    }

}
