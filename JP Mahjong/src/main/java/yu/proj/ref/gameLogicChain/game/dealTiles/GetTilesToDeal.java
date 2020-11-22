package yu.proj.ref.gameLogicChain.game.dealTiles;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.gameLogicChain.game.GameLogicData;
import yu.proj.ref.gameLogicChain.game.TaskStep;
import yu.proj.ref.player.Player;
import yu.proj.ref.tile.Tile;
import yu.proj.ref.utils.KeyValuePair;

/**  
 * @ClassName: GetTilesToDeal  
 *
 * @Description: 发牌task，step1：从发牌器获取全部的要发的牌，并根据Player来进行划分，
 *               最终每个玩家会分到13张牌，按照每个玩家摸3次，每次摸4张，共12张，庄家先开始摸的顺序，
 *               最后每个人再摸一张，凑齐13张
 *
 * @author 余定邦  
 *
 * @date 2020年11月12日  
 *  
 */

public class GetTilesToDeal extends AbstractDealTilesTaskStep {

    public GetTilesToDeal(GameLogicData mainData, DealTilesTaskSharedData taskData) {
        super(mainData, taskData);
    }

    @Override
    public void doStepDuty() {
        initPlayerTilesTempDataStructure();
        insertGetTilesToTempDataStruct();
    }

    private void initPlayerTilesTempDataStructure() {
        taskData.setPlayersTiles(new ArrayList<>());

        // 这里初始化player没有按照0-size的顺序，而是从dealerIndex-size&&0-dealerIndex，这里是有重新排序player的必要性的
        // 由于摸牌是从庄家开始摸牌，而不是从东一局的庄家（起家）开始摸牌，因此这样能够保证顺序的正确性
        initPlayerTilesStoreDataStructureFromIndex1ToIndex2(mainData.getDealerIndex(), mainData.getPlayers().size());
        initPlayerTilesStoreDataStructureFromIndex1ToIndex2(0, mainData.getDealerIndex());
    }

    private void initPlayerTilesStoreDataStructureFromIndex1ToIndex2(int index1, int index2) {
        for (int i = index1; i < index2; ++i) {
            taskData.getPlayersTiles().add(new KeyValuePair<>(mainData.getPlayers().get(i), new ArrayList<>()));
        }
    }

    private void insertGetTilesToTempDataStruct() {
        List<KeyValuePair<Player, List<Tile>>> list = taskData.getPlayersTiles();

        get12Tiles(list);
        getLastTiles(list);
    }

    // 总共转3圈，每圈每个玩家摸4张牌，共12张
    private void get12Tiles(List<KeyValuePair<Player, List<Tile>>> list) {
        for (int totalCycleIs3 = 0; totalCycleIs3 < 3; ++totalCycleIs3) {
            for (int eachPlayer = 0; eachPlayer < 4; ++eachPlayer) {
                for (int get4Tiles = 0; get4Tiles < 4; ++get4Tiles) {
                    list.get(eachPlayer).getValue().add(mainData.getGameTilesManager().draw());
                }
            }
        }
    }

    // 最后一张牌
    private void getLastTiles(List<KeyValuePair<Player, List<Tile>>> list) {
        for (int i = 0; i < 4; ++i) {
            list.get(i).getValue().add(mainData.getGameTilesManager().draw());
        }
    }

    @Override
    public TaskStep getNextTaskStep() {
        return new DealTilesToPlayer(mainData, taskData);
    }

}
