package NAR.ashesOfHeist;

public enum StatType {
    HP(100, 400, 1, false),
    ATTACK(100, 400, 0, false),
    DEFENSE(100, 400,4, false),
    SPEED(10, 30,6, false),
    ENERGY_CAPACITY(30, 60,5, false),
    CRIT_RATE(2, 5, 2, false),
    CRIT_DAMAGE(10, 20, 3, false),
    FIRE_DMG_BONUS(15, 25, 6, true),
    WATER_DMG_BONUS(15, 25, 7, true),
    GRASS_DMG_BONUS(15, 25, 8, true);

    private int min;
    private int max;
    private int index;



    private boolean isDmgBonus;

    StatType(int min, int max, int index, boolean isDmgBonus){
        this.min = min;
        this.max = max;
        this.index = index;
        this.isDmgBonus = isDmgBonus;
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
}
