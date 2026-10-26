package NAR.digiBattle;

public enum Element {

    FIRE(0, 2, 1), PLANT(1, 0, 2), WATER(2, 1, 0);
    final int weakness;
    final int strength;
    final int index;
    Element(int index, int weakness, int strength){
        this.index = index;
        this.weakness = weakness;
        this.strength = strength;
    }
}
