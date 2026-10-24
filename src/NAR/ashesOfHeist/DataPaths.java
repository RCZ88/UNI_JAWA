package NAR.ashesOfHeist;

import java.nio.file.Path;

public enum DataPaths {

    CHARACTERS("Characters.txt"),
    CHARACTER_LEVEL("CharacterLevel.txt"),
    ARTIFACTS("Artifacts.txt"),
    ARTIFACT_SAMPLE("Artifacts_sample.txt"),
    BOSS_DEFEATED("BossDefeated.txt");

    private final String path;
    DataPaths(String path){
        this.path = path;
    }

    public String getPathString() {
        return path;
    }
    public Path getPath(){
        return Path.of(path);
    }

}
