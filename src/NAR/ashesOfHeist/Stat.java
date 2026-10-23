package NAR.ashesOfHeist;

public class Stat {
    public static final int CHANCE = 60;
    public static final int MAX_LEVEL = 3;

    private StatType statType;
    private int level;

    private int originalValue;
    private int value;

    public Stat(StatType statType){
        this.statType = statType;
        this.level = 0;
        rollValue();
    }

    private void rollValue(){
        originalValue = (int) Math.floor((Math.random() * statType.getRange() + statType.getMin()));
        value = originalValue;
    }

    public int getLevel() {
        return level;
    }
    public void upgradeLevel(){
        if(level == MAX_LEVEL) return;
        level++;
        value = originalValue * level;
    }

    public static int sumStat(int[]stat){
        int sum = 0;
        for (int v : stat) {
            sum += v;
        }
        return sum;
    }
    public static double sumStat(double[]stat){
        double sum = 0;
        for (double v : stat) {
            sum += v;
        }
        return sum;
    }


    public int getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(int originalValue) {
        this.originalValue = originalValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StatType getStatType() {
        return statType;
    }

    public void setStatType(StatType statType) {
        this.statType = statType;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
