package NAR.ashesOfHeist;

public class BOSS  extends Enemy{
    public static final boolean CHARACTER = false;
    public static final boolean ARTIFACT = true;
    public final int index;
    private final int[]multiplierPA;
    private final int[]energyRegenPA;
    private final int[]costPA;
    private final AOE[]aoePA;
    private final boolean[]effectPA;
    private boolean charOrArtifact;

    public BOSS(int index, String name,  Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, String []attackNames, boolean charOrArtifact) {
        super(name, element, maxHp, baseAtk, baseDef, baseSpd, maxEnergy, 4, attackNames);
        this.index = index;
        this.charOrArtifact = charOrArtifact;
        multiplierPA = new int[4];
        energyRegenPA = new int[4];
        costPA = new int[4];
        aoePA = new AOE[4];
        effectPA = new boolean[4];
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

    public int getIndex() {
        return index;
    }

    public int[] getMultiplierPA() {
        return multiplierPA;
    }

    public int[] getEnergyRegenPA() {
        return energyRegenPA;
    }

    public int[] getCostPA() {
        return costPA;
    }

    public AOE[] getAoePA() {
        return aoePA;
    }

    public boolean[] getEffectPA() {
        return effectPA;
    }

    public boolean isCharOrArtifact() {
        return charOrArtifact;
    }

    public void setCharOrArtifact(boolean charOrArtifact) {
        this.charOrArtifact = charOrArtifact;
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
