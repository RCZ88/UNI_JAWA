package NAR.ashesOfHeist;

public class BOSS  extends Enemy{
    private int[]multiplierPA;
    private int[]energyRegenPA;
    private int[]costPA;
    private AOE[]aoePA;
    private boolean[]effectPA;

    public BOSS(Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, String []attackNames) {
        super(element, maxHp, baseAtk, baseDef, baseSpd, maxEnergy, 4, attackNames);
    }

    @Override
    public void loadAttacks() {
        for(AttackType at : AttackType.ATTACK_TYPES){
            if(at == AttackType.SKILL){
                continue;
            }
            int i = at.getIndex();
            addNewAttack(new Attack(at, costPA[i], energyRegenPA[i], MULTIPLIER_AMOUNT, Attack.makeMulti( multiplierPA[i]), MULTIPLIER_CONSTRUCT, aoePA[i], effectPA[i], this));
        }
    }

    public int[] getMultiplierPA() {
        return multiplierPA;
    }

    public void setMultiplierPA(int[] multiplierPA) {
        this.multiplierPA = multiplierPA;
    }

    public int[] getEnergyRegenPA() {
        return energyRegenPA;
    }

    public void setEnergyRegenPA(int[] energyRegenPA) {
        this.energyRegenPA = energyRegenPA;
    }

    public int[] getCostPA() {
        return costPA;
    }

    public void setCostPA(int[] costPA) {
        this.costPA = costPA;
    }

    public AOE[] getAoePA() {
        return aoePA;
    }

    public void setAoePA(AOE[] aoePA) {
        this.aoePA = aoePA;
    }

    public boolean[] getEffectPA() {
        return effectPA;
    }

    public void setEffectPA(boolean[] effectPA) {
        this.effectPA = effectPA;
    }

    //
//    @Override
//    public int[] getEnergyCost() {
//        return new int[]{0, };
//    }
//
//    @Override
//    public void basic(Team team) {
//
//    }
//
//    @Override
//    public void intermediate(Team team) {
//
//    }
//
//
//    @Override
//    public void attack(Unit unit) {
//
//    }
//
//    @Override
//    public double[] getMultiplier() {
//        return new double[]{1.0, 1.0, 2.0, 4.0};
//    }
}
