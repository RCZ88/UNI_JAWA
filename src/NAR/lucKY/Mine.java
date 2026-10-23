package NAR.lucKY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Mine {
    private Tile [][]mine;
    private int mineSize;
    private Player player;

    public Mine(int mineSize) {
        this.mineSize = mineSize;
    }

    public void initMine(){

    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void doAction(int x, int y){
        mine[y][x].handleInteraction();;
    }

    public void loadMineFromFile(String filepath){
        Path path = Path.of(filepath);
        ArrayList<String> row = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(path);
            if(lines.size() != mineSize && lines.getFirst().length() != mineSize){
                System.out.println("Invalid Data Type!");
                return;
            }
            for(int i = 0; i<lines.size(); i++){
                String line = lines.get(i);
                for(int j = 0; j< line.length(); j++){
                    char c = line.charAt(j);
                    if(c > '0' && c < '4'){
                        mine[i][j] = new Treasure(i, j, c-'0');
                        continue;
                    }
                    switch(c){
                        case '.':
                            mine[i][j] = new Dirt(i, j);
                        case '#':
                            mine[i][j] = new Rock(i, j, 3);
                        case 'X':
                            mine[i][j] = new Bomb(i, j);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMine(String filepath){
        ArrayList<String>mineString = new ArrayList<>();
        for(int i = 0; i<mineSize; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j< mineSize; j++) {
                sb.append(mine[i][j].getTt().getRep());
            }
            mineString.add(sb.toString());
        }
        String saveDat = String.join("\n", mineString);
        Path path = Path.of(filepath);
        try{
            Files.writeString(path, saveDat);
        }catch(IOException e){
            System.out.printf("Failed to Save file. Error: %s", e.getMessage());
        }
    }

    public void printMine(){
        for(Tile[]row : mine){
            for(Tile tile : row){
                System.out.println(tile.getTt().getRep());
            }
        }
    }


    /*

    static ArrayList<Player> loadPlayerData(String filepath){
        Path path = Path.of(filepath);
        ArrayList<Player> players = new ArrayList<>();
        try{
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] values = line.split(",");
                Player player = new Player(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3]));
                players.add(player);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    static void savePlayer(Player player, String filepath){
        String line = String.format("%s,%s,%d,%d\n", player.username, player.password,  player.coins, player.shoverDurability);
        Path path = Path.of(filepath);
        try{
            Files.writeString(path, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch(IOException e){
            System.out.printf("Failed to Save file. Error: %s", e.getMessage());
        }
    }
    * */

    public Tile[][] getMine() {
        return mine;
    }

    public void setMine(Tile[][] mine) {
        this.mine = mine;
    }


}
