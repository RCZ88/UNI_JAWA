package NAR.ashesOfHeist;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class Game {
    public static final ArrayList<Ally> ALL_ALLY = new ArrayList<>();
    public static final ArrayList<BOSS> ALL_BOSSES = new ArrayList<>();
    public static final HashMap<String, Artifact> ALL_ARTIFACTS = new HashMap<>();
    public static final ArrayList<MOB> ALL_MOBS = new ArrayList<>();
    public static final HashMap<String, String> ALL_UNIT_SPRITE = new HashMap<>();
    public static Player CURRENT_PLAYER;

    public static void loadGame(){
        loadAllSprite();
        loadCharacters();
        loadBosses();
        loadAllArtifacts();
    }

    public static void loadAllSprite(){
        try {
            List<String>lines = Files.readAllLines(DataPaths.SPRITES.getPath());
            String prevSprite = lines.getFirst();
            ALL_UNIT_SPRITE.put(prevSprite, "");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i<lines.size(); i++){
                if((i+1) % 9 == 1){
                    ALL_UNIT_SPRITE.put(lines.get(i), "");
                    prevSprite = lines.get(i);
                    continue;
                }
                if((i+1) % 9 == 0){
                    ALL_UNIT_SPRITE.replace(prevSprite, sb.toString());
                    sb = new StringBuilder();
                    continue;
                }
                sb.append(lines.get(i)).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadCharacters(){
        try {
            List<String> lines = Files.readAllLines(DataPaths.ALLIES.getPath());
            lines.removeFirst();
            for (String line : lines) {
                //11, 12
                String[]vars = line.split(",");
                Ally c = null;
                String []skillNames = new String[4];
                skillNames[AttackType.ATTACK.getIndex()] = vars[11];
                skillNames[AttackType.SKILL.getIndex()] = vars[12];
                c = switch (vars[2]) {
                    case "DPS" ->
                            new DPS(vars[1], Element.getElementByString(vars[3]), Integer.parseInt(vars[3]), Integer.parseInt(vars[4]), Integer.parseInt(vars[5]), Integer.parseInt(vars[6]), Integer.parseInt(vars[7]), skillNames);
                    case "SUB_DPS" ->
                            new SUB_DPS(vars[1], Element.getElementByString(vars[3]), Integer.parseInt(vars[3]), Integer.parseInt(vars[4]), Integer.parseInt(vars[5]), Integer.parseInt(vars[6]), Integer.parseInt(vars[7]), 3, skillNames);
                    case "SUPPORT" ->
                            new SUPPORT(vars[1], Element.getElementByString(vars[3]), Integer.parseInt(vars[3]), Integer.parseInt(vars[4]), Integer.parseInt(vars[5]), Integer.parseInt(vars[6]), Integer.parseInt(vars[7]), skillNames);
                    default -> c;
                };
                assert c != null;
                c.setIndex(ALL_ALLY.size());
                ALL_ALLY.add(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadBosses(){
        try {
            List<String>lines = Files.readAllLines(DataPaths.BOSSES.getPath());
            int index = 0;
            for (String line : lines) {
                String[] items = line.split(",");
                String[]attackNames = new String[4];
                for(int i = 0; i<4; i++){
                    attackNames[i] = items[29+i];
                }
                BOSS b = new BOSS(index++, items[0], Element.getElementByString(items[1]), Integer.parseInt(items[4]), Integer.parseInt(items[5]),
                        Integer.parseInt(items[6]), Integer.parseInt(items[7]), Integer.parseInt(items[8]), attackNames,
                        items[3].equalsIgnoreCase("character") ? BOSS.CHARACTER : BOSS.ARTIFACT);
                for(int i = 0; i<4; i++){
                    b.getMultiplierPA()[i] = Integer.parseInt(items[i+9]);
                    b.getEnergyRegenPA()[i] = Integer.parseInt(items[i+13]);
                    b.getCostPA()[i] = Integer.parseInt(items[i+17]);
                    b.getAoePA()[i] = AOE.getAOE(Integer.parseInt(items[i+21]));
                    b.getEffectPA()[i] = !items[i + 25].equals("false");
                }
                ALL_BOSSES.add(b);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadAllArtifacts(){
        try {
            List<String> lines = Files.readAllLines(DataPaths.ARTIFACT_NAMES.getPath());
            for (String line : lines) {
                ALL_ARTIFACTS.put(line, new Artifact(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadPlayer(){
        try {
            Player player = new Player();
            List<String>lines = Files.readAllLines(DataPaths.ARTIFACTS_OWNED.getPath());
            HashMap<Artifact, Integer>artifactOwned = new HashMap<>();
            for(String line : lines){
                String[]items = line.split(",");
                Artifact artifact = ALL_ARTIFACTS.get(items[0]);
                for(int i = 0; i<Artifact.NUMBER_OF_STAT; i++){
                    String[]statsDetails = items[i+2].split(":");
                    Stat stat = new Stat(StatType.getStatByString(statsDetails[0]));
                    stat.setValue(Integer.parseInt(statsDetails[1]));
                    artifact.addStat(stat);
                }
                artifactOwned.put(artifact, Integer.parseInt(items[8]));
            }
            player.setArtifacts(new ArrayList<>(artifactOwned.keySet()));

            lines = Files.readAllLines(DataPaths.ALLIES_LEVEL.getPath());
            for (String line : lines) {
                String[]vars = line.split(",");
                if(Objects.equals(vars[1], "0")){
                    continue;
                }
                Ally ally = ALL_ALLY.get(Integer.parseInt(vars[0]));
                artifactOwned.forEach((artifact, index) -> {
                    if(index == ally.getIndex()){
                        ally.addArtifact(artifact);
                    }
                });
                player.addAlly(ally);
            }
            CURRENT_PLAYER = player;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
