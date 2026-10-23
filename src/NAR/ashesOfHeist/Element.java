package NAR.ashesOfHeist;

public enum Element {
    FIRE("burn", 0, 6, 0.4),
    WATER("drowning", 1, 7, 0.4),
    GRASS("poison", 2, 8, 0.36);

    private final String effect;
    private final int index;
    private final int hashIndex;
    private final double multiplier;
    private Element(String element, int index, int hashIndex, double multiplier){
        this.effect = element;
        this.index = index;
        this.hashIndex = hashIndex;
        this.multiplier = multiplier;
    }

    public String getEffect() {
        return effect;
    }

    public int getIndex() {
        return index;
    }

    public double getMultiplier() {
        return multiplier;
    }
    public int getHashIndex() {
        return hashIndex;
    }
}
