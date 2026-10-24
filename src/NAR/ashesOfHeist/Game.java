package NAR.ashesOfHeist;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Game {
    public static final ArrayList<Character> ALL_CHARACTER = new ArrayList<>();
    public static final ArrayList<Enemy> ALL_ENEMIES = new ArrayList<>();

    public static void loadCharacters(){
        try {
            Files.readAllLines(DataPaths.CHARACTERS.getPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
