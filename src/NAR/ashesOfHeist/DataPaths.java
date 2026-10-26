package NAR.ashesOfHeist;

import java.nio.file.Path;

public enum DataPaths {

    ALLIES("Allies.txt"),
    ALLIES_LEVEL("AlliesLevel.txt"),
    ARTIFACT_NAMES("ArtifactNames.txt"),
    ARTIFACTS_OWNED("Artifacts.txt"),
    ARTIFACT_SAMPLE("Artifacts_sample.txt"),
    BOSS_DEFEATED("BossDefeated.txt"),
    BOSSES("Bosses.txt"),
    SPRITES("Sprite.txt"),
    MOBS("Mobs.txt"),
    NAR_LOGO("narlogo_ascii.txt"),
    TITLE("Title.txt");

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
