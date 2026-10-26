package NAR.ashesOfHeist;

import java.lang.Character;
import java.util.ArrayList;

public class Player {
    private ArrayList<Artifact>artifacts;
    private Team team;
    private int characterLevelCap;
    private int artifactLevelCap;
    private ArrayList<Integer>bossesDefeatedIndex;

    public Player(){
        artifacts = new ArrayList<>();
        bossesDefeatedIndex = new ArrayList<>();
        team = new Team();
        characterLevelCap = 20;
        artifactLevelCap = 1;
    }


    public boolean isArtifactUpgradable(int artifactIndex){
        Artifact artifact = artifacts.get(artifactIndex);
        if(artifact.getLevel() == Artifact.MAX_LEVEL){
            System.out.println("Max Level Reached!");
            return false;
        }
        if(artifacts.size()-1 < artifact.getLevel()+1){
            System.out.println("Not Enough Artifacts!");
            return false;
        }
        return true;
    }

    public void upgradeArtifact(int artifactIndex, ArrayList<Integer>selectedArtifact){
        Artifact a = artifacts.get(artifactIndex);
        if(selectedArtifact.size() < a.getLevel()+1){
            System.out.println("Please Select " + a.getLevel()+1 + " Artifacts to Sacrifice");
            return;
        }
        selectedArtifact.forEach(i -> artifacts.remove((int)i));
        a.upgradeLevel();
        System.out.println("Upgraded Successfully!");
    }

    public void
    increaseArtifactCap(){
        if(artifactLevelCap == Artifact.MAX_LEVEL)return;
        artifactLevelCap++;
    }

    public void increaseCharacterCap(){
        if(characterLevelCap == Ally.MAX_LEVEL)return;
        if(characterLevelCap == 20) characterLevelCap += 20;
        characterLevelCap+=10;
    }

    public void addAlly(Ally ally){

    }

    public int getArtifactLevelCap() {
        return artifactLevelCap;
    }

    public void setArtifactLevelCap(int artifactLevelCap) {
        this.artifactLevelCap = artifactLevelCap;
    }

    public int getCharacterLevelCap() {
        return characterLevelCap;
    }

    public void setCharacterLevelCap(int characterLevelCap) {
        this.characterLevelCap = characterLevelCap;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList<Artifact> artifacts) {
        this.artifacts = artifacts;
    }


    public ArrayList<Integer> getBossesDefeatedIndex() {
        return bossesDefeatedIndex;
    }

    public void setBossesDefeatedIndex(ArrayList<Integer> bossesDefeatedIndex) {
        this.bossesDefeatedIndex = bossesDefeatedIndex;
    }
}
