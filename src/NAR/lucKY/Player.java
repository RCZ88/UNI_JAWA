package NAR.lucKY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private String password;
    private int coins;
    private int shovelDurability;

    private int recentCoinCollected;

    public Player(String username, String password, int coins, int shovelDurability){
        this.username = username;
        this.password = password;
        this.coins = coins;
        this.shovelDurability = shovelDurability;
        recentCoinCollected = 0;
    }

    public static ArrayList<Player> loadPlayerData(String filepath){
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

    public static void savePlayer(Player player, String filepath){
        String line = String.format("%s,%s,%d,%d\n", player.username, player.password,  player.coins, player.shovelDurability);
        Path path = Path.of(filepath);
        try{
            Files.writeString(path, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }catch(IOException e){
            System.out.printf("Failed to Save file. Error: %s", e.getMessage());
        }
    }

    public void deductCoin(){
        this.coins -= this.recentCoinCollected;
        this.recentCoinCollected = 0;
    }

    public void deductShovelDurability(int amt){
        if(shovelDurability - amt < 0){
            shovelDurability = -1;
        }
        shovelDurability -= amt;
    }
    public void addCoin(int amt){
        coins += amt;
    }

    public boolean hasShovel(){
        return shovelDurability == -1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }


    public int getShovelDurability() {
        return shovelDurability;
    }

    public void setShovelDurability(int shovelDurability) {
        this.shovelDurability = shovelDurability;
    }

    public int getRecentCoinCollected() {
        return recentCoinCollected;
    }

    public void setRecentCoinCollected(int recentCoinCollected) {
        this.recentCoinCollected = recentCoinCollected;
    }
}

