package NAR.ashesOfHeist;

public enum Element {
    FIRE("burn", 0, 7, 0.4),
    WATER("drowning", 1, 8, 0.4),
    GRASS("poison", 2, 9, 0.36);

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

    public static Element[]getAllElements(){
        return new Element[]{FIRE, WATER, GRASS};
    }

    public static Element getElementByString(String s){
        return switch (s.toLowerCase()) {
            case "fire" -> FIRE;
            case "water" -> WATER;
            case "grass" -> GRASS;
            default -> null;
        };
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
