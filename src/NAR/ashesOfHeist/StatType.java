package NAR.ashesOfHeist;

public enum StatType {
    HP(100, 400, 1, 2.5, false),
    ATTACK(100, 400, 0, 5,false),
    DEFENSE(100, 400,4, 12.5, false),
    SPEED(10, 30,6, 20, false),
    ENERGY_CAPACITY(30, 60,5, 1,false),
    CRIT_RATE(2, 5, 2, false),
    CRIT_DAMAGE(10, 20, 3, false),
    FIRE_DMG_BONUS(15, 25, 7, true),
    WATER_DMG_BONUS(15, 25, 8, true),
    GRASS_DMG_BONUS(15, 25, 9, true);

    private int min;
    private int max;
    private int index;
    private double mobDivisor;
    private boolean isDmgBonus;

    StatType(int min, int max, int index, boolean isDmgBonus){
        this.min = min;
        this.max = max;
        this.index = index;
        this.isDmgBonus = isDmgBonus;
    }
    StatType(int min, int max, int index, double mobDivisor, boolean isDmgBonus){
        this.min = min;
        this.max = max;
        this.mobDivisor = mobDivisor;
        this.index = index;
        this.isDmgBonus = isDmgBonus;
    }
    public static StatType getStatByIndex(int index){
        StatType []statTypes = new StatType[]{
                ATTACK, HP, CRIT_RATE, CRIT_DAMAGE,
                DEFENSE, ENERGY_CAPACITY, SPEED,
                FIRE_DMG_BONUS, WATER_DMG_BONUS, GRASS_DMG_BONUS
        };
        return statTypes[index];
    }
    public static StatType getStatByString(String s) {
        switch (s.trim().toLowerCase()) {
            case "hp" -> { return HP; }
            case "attack" -> { return ATTACK; }
            case "defense" -> { return DEFENSE; }
            case "speed" -> { return SPEED; }
            case "energy_capacity" -> { return ENERGY_CAPACITY; }
            case "crit_rate" -> { return CRIT_RATE; }
            case "crit_damage" -> { return CRIT_DAMAGE; }
            case "fire_damage_bonus", "fire_dmg_bonus" -> { return FIRE_DMG_BONUS; }
            case "water_damage_bonus", "water_dmg_bonus" -> { return WATER_DMG_BONUS; }
            case "grass_damage_bonus", "grass_dmg_bonus" -> { return GRASS_DMG_BONUS; }
            default -> throw new IllegalArgumentException("Unknown stat: " + s);
        }
    }


    public static int uniqueStatAmount(){
        return 10;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRange(){
        return max - min;
    }
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    public boolean isDmgBonus() {
        return isDmgBonus;
    }

    public void setDmgBonus(boolean dmgBonus) {
        isDmgBonus = dmgBonus;
    }

    public double getMobDivisor() {
        return mobDivisor;
    }

    public void setMobDivisor(double mobDivisor) {
        this.mobDivisor = mobDivisor;
    }
}
