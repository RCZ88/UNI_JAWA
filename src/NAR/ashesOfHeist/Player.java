package NAR.ashesOfHeist;

import java.util.ArrayList;

public class Player {
    private ArrayList<Artifact>artifacts;
    private Team team;


    public boolean isArtifactUpgradable(int artifactIndex, int statIndex){
        Stat statByIndex = artifacts.get(artifactIndex).getStatByIndex(statIndex);
        if(statByIndex.getLevel() == Stat.MAX_LEVEL){
            System.out.println("Max Level Reached!");
            return false;
        }
        if(artifacts.size()-1 < statByIndex.getLevel()+1){
            System.out.println("Not Enough Artifacts!");
            return false;
        }
        return true;
    }

    public void upgradeArtifact(int artifactIndex, int statIndex, ArrayList<Integer>sacs){
        Stat stat = artifacts.get(artifactIndex).getStatByIndex(statIndex);
        if(sacs.size() < stat.getLevel()+1){
            System.out.println("Please Select " + stat.getLevel()+1 + " Artifacts to Sacrifice");
            return;
        }
        sacs.forEach(i -> artifacts.remove((int)i));
        stat.upgradeLevel();
        System.out.println("Upgraded Successfully!");
    }
}
