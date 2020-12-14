package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import yu.proj.ref.tile.TileType;
import yu.proj.ref.tilePatternElement.Meld;
import yu.proj.ref.tilePatternElement.Sequence;
import yu.proj.ref.tilePatternElement.Triplet;
import yu.proj.ref.tilePatternElement.exposedTile.AddKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.ConcealedKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.ExposedKanQuad;
import yu.proj.ref.tilePatternElement.exposedTile.Kita;

/**
 * @ClassName : PlayerExposedTilesManager  
 * @Description : TODO(这里用一句话描述这个类的作用)  
 * @author  余定邦  
 * @date  2020年11月22日  
 */
public class PlayerExposedTilesManager {

    List<Sequence> sequences = new ArrayList<>();
    List<Triplet> triplets = new ArrayList<>();
    List<ExposedKanQuad> exposedKanQuads = new ArrayList<>();
    List<ConcealedKanQuad> concealedKanQuads = new ArrayList<>();
    List<Kita> kitas = new ArrayList<>();
    List<AddKanQuad> addKanQuads = new ArrayList<>();

    // 用于包牌分析的两个顺序链表
    // 前者是为了四杠子分析使用的，其保证了加杠的正确顺序，当发生加杠的时候，会将原有的刻子删除，然后在最后添加加杠
    List<Meld> tripletAndQuadOrder = new LinkedList<>();
    // 后者是为了大三元和大四喜包牌分析使用，其忽略加杠，保证是副露的先后顺序
    List<Meld> tripletAndQuadOrderIgnoreAddKan = new LinkedList<>();

    void addSequence(Sequence sequence) {
        sequences.add(sequence);
    }

    void addTriplet(Triplet triplet) {
        triplets.add(triplet);
        tripletAndQuadOrder.add(triplet);
        tripletAndQuadOrderIgnoreAddKan.add(triplet);
    }

    boolean containTriplet(Triplet triplet) {
        return triplets.contains(triplet);
    }

    public boolean containTriplet(TileType tripletTileType) {
        for (Triplet triplet : triplets) {
            if (triplet.getSpecialTile().getTileType().sameNormalType(tripletTileType)) {
                return true;
            }
        }
        return false;
    }

    void removeTriplet(Triplet triplet) {
        triplets.remove(triplet);
        tripletAndQuadOrder.remove(triplet);
    }

    void addExposedKanQuad(ExposedKanQuad exposedKanQuad) {
        exposedKanQuads.add(exposedKanQuad);
        tripletAndQuadOrder.add(exposedKanQuad);
        tripletAndQuadOrderIgnoreAddKan.add(exposedKanQuad);
    }

    void addConcealedKanQuad(ConcealedKanQuad concealedKanQuad) {
        concealedKanQuads.add(concealedKanQuad);
        tripletAndQuadOrder.add(concealedKanQuad);
        tripletAndQuadOrderIgnoreAddKan.add(concealedKanQuad);
    }

    void addAddKanQuad(AddKanQuad addKanQuad) {
        addKanQuads.add(addKanQuad);
        tripletAndQuadOrder.add(addKanQuad);
    }

    void addKita(Kita kita) {
        kitas.add(kita);
    }

    public int sizeOfMakeCall() {
        return sequences.size() + triplets.size() + exposedKanQuads.size() + concealedKanQuads.size()
            + addKanQuads.size();
    }

    public int sizeOfMakeCallExceptConcealedKan() {
        return sequences.size() + triplets.size() + exposedKanQuads.size() + concealedKanQuads.size()
            + addKanQuads.size();
    }

}