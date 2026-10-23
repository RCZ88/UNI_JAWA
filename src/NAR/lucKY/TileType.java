package NAR.lucKY;

public enum TileType {
    TIER_1_T('1'),
    TIER_2_T('2'),
    TIER_3_T('3'),
    DIRT('.'),
    ROCK('#'),
    BOMB('X');
    private char rep;
    private TileType(char rep){
        this.rep = rep;
    }

    public char getRep() {
        return rep;
    }

    public void setRep(char rep) {
        this.rep = rep;
    }
}
