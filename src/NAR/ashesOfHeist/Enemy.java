package NAR.ashesOfHeist;

public abstract class Enemy extends Unit{

    static final MultiplierConstruct MULTIPLIER_CONSTRUCT = (multipliers, unit) -> multipliers[0] * getStat(unit.getAtk());

    static final int MULTIPLIER_AMOUNT = 1;

    public static int[] formatStat(int i){
        return new int[]{i};
    }

    public static double[] formatStat(double i){
        return new double[]{i};
    }

    public static int getStat(int[]i){
        return i[0];
    }

    public static double getStat(double[]i){
        return i[0];
    }


//    public abstract double[] getMultiplier();

    public Enemy(String name, Element element, int maxHp, int baseAtk, int baseDef, int speed, int maxEnergy, int atkTypeCount, String[]attackNames) {
        super(name, element, maxHp, baseAtk, baseDef, speed, maxEnergy, atkTypeCount, attackNames);

    }


//    @Override
//    public void attack(Unit unit){
//        int B = Stat.sumStat(super.getAtk());
//        int L = super.getLevel();
//        int dmg = B + B * (L/5);
//        applyDamage(dmg, unit);
//    }

}
