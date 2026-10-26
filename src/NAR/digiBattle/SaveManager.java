package NAR.digiBattle;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Persistence for digibattle-save.txt. UI decides when save/load is offered. */
public class SaveManager {
    private static final Path SAVE_PATH = Path.of("digibattle-save.txt");
    private static final ArrayList<Digimon> ALL_DIGIMON_SPECIES = new ArrayList<>();

    public boolean saveExists() throws IOException {
        // TODO: Return whether SAVE_PATH exists.
        return Files.exists(SAVE_PATH) && !Files.readAllLines(SAVE_PATH).isEmpty();
    }
    public void save(Game game) throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();
        for (Digimon formationSlot : game.getFormationSlots()) {
            sb.append(formationSlot.getAtSlot()).append(",")
                    .append(formationSlot.getSpecies()).append(",")
                    .append(formationSlot.getLevel()).append(",")
                    .append(formationSlot.getExp()).append("\n");
            lines.add(sb.toString());
        }
        Files.writeString(SAVE_PATH, String.join("\n", lines));
    }
    public boolean loadInto(Game game) throws IOException {
        List<String> lines = Files.readAllLines(SAVE_PATH);
        for (String line : lines) {
            String[]items = line.split(",");
            int slot = Integer.parseInt(items[0]);
            String species = items[1];
            int level = Integer.parseInt(items[2]);
            int xp = Integer.parseInt(items[3]);
            int stageIndex = Integer.parseInt(items[4]);
            if(slot < 0 || slot > 4)return false;
            if(!ALL_DIGIMON_SPECIES.stream().map(Digimon::getSpecies).collect(Collectors.toCollection(ArrayList::new)).contains(species))return false;
            if(!(xp >= 0 && xp < level * 80)) return false;
            if(level > Stage.stages[stageIndex].evolveReq) return false;
            Digimon d = ALL_DIGIMON_SPECIES.stream().filter(ds -> ds.getSpecies().equals(species)).collect(Collectors.toCollection(ArrayList::new)).getFirst();
            d.setLevel(level);
            d.setExp(xp);
            d.setAtSlot(slot);
            game.getFormationSlots().add(d);
        }
        return true;
    }
}
