package yu.proj.ref.gameLogicChain.game.shared.playerTilesManager;

import java.util.ArrayList;
import java.util.List;

import yu.proj.ref.tile.TileType;
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

    void addSequence(Sequence sequence) {
        sequences.add(sequence);
    }

    void addTriplet(Triplet triplet) {
        triplets.add(triplet);
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
    }

    void addExposedKanQuad(ExposedKanQuad exposedKanQuad) {
        exposedKanQuads.add(exposedKanQuad);
    }

    void addConcealedKanQuad(ConcealedKanQuad concealedKanQuad) {
        concealedKanQuads.add(concealedKanQuad);
    }

    void addAddKanQuad(AddKanQuad addKanQuad) {
        addKanQuads.add(addKanQuad);
    }

    void addKita(Kita kita) {
        kitas.add(kita);
    }

    public int sizeOfMakeCall() {
        return sequences.size() + triplets.size() + exposedKanQuads.size() + concealedKanQuads.size()
            + addKanQuads.size();
    }

}