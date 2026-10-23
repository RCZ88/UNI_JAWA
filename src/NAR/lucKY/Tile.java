package NAR.lucKY;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Tile {
    private int x;
    private int y;
    private TileType tt;


    public Tile(int x, int y, TileType tt) {
        this.x = x;
        this.y = y;
        this.tt = tt;
    }

    abstract void handleInteraction();


    public TileType getTt() {
        return tt;
    }

    public void setTt(TileType tt) {
        this.tt = tt;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}

class Treasure extends Tile{
    static final int [][]tierRange = new int[][]{
            {20,4},
            {40,8},
            {60,12}
    };
    static final int[][]tierDeviation = new int[][]{
            {20, 14},
            {4, 2}
    };


    private int tier;

    public Treasure(int x, int y, int tier) {
        super(x, y, getTT(tier));
        initTierRange();
    }

    private void initTierRange(){
        for(int i = 0; i<3; i++){
            tierRange[i][0] = 20 * i;
            tierRange[i][1] = 4 * i;
        }
    }

    public int getCoinAmount(boolean withShovel){
        int i = withShovel ? 1 : 0;
        return tierRange[tier][i] * tierDeviation[i][0] +
                ThreadLocalRandom.current().nextInt(0, tierDeviation[i][1]);
    }

    public static TileType getTT(int tier){
        return switch (tier) {
            case 1 -> TileType.TIER_1_T;
            case 2 -> TileType.TIER_2_T;
            case 3 -> TileType.TIER_3_T;
            default -> null;
        };
    }

    @Override
    void handleInteraction() {
        Player player = Main.getPlayer();
        player.addCoin(getCoinAmount(player.hasShovel()));
    }
}

class Bomb extends Tile{
    public Bomb(int x, int y ) {
        super(x, y, TileType.BOMB);
    }

    @Override
    void handleInteraction() {
        Main.getPlayer().deductCoin();
    }
}

class Rock extends Tile{
    int shovelDamage;
    public Rock(int x, int y, int shovelDamage) {
        super(x, y, TileType.ROCK);
        this.shovelDamage = shovelDamage;
    }

    @Override
    void handleInteraction() {

    }
}

class Dirt extends Tile{

    public Dirt(int x, int y) {
        super(x, y, TileType.DIRT);
    }

    @Override
    void handleInteraction() {

    }
}
