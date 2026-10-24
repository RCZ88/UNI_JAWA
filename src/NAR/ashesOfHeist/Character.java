package NAR.ashesOfHeist;

import java.util.ArrayList;

public abstract class Character extends Unit{
    private static final int ATK_COST = 0;
    protected static final int K_INDEX = 1;
    protected static final int P_INDEX = 0;
    protected static final int MULTIPLIER_AMOUNT = 2;
    protected static final MultiplierConstruct MULTIPLIER_CONSTRUCT = (multipliers, unit) -> multipliers[P_INDEX] + unit.getLevel() * multipliers[K_INDEX];

    private int skillCost;
    private int baseEnergyRegen;
    private Team ofTeam;
    private ArrayList<Artifact> artifacts;
    private int curXp;


    public Character(Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, int skillCost, int energyRegen, String[] attackNames) {
        super(element, maxHp, baseAtk, baseDef, baseSpd, maxEnergy, 3, attackNames);
        this.artifacts = new ArrayList<>();
        this.skillCost = skillCost;
        this.baseEnergyRegen = energyRegen;
    }

    public int getXpToNextLevel(){
        return (int) (8 * Math.pow(1.1, (getLevel()-1))) - curXp;
    }

    protected  int getEnergyRegen(){
        return baseEnergyRegen + getLevel();
    }

    public void receiveXp(int amount){
        int remaining = amount;
        int required = getXpToNextLevel();
        while(remaining > required){
            upgradeLevel();
            remaining-= required;
            curXp = 0;
            required = getXpToNextLevel();
        }
        curXp+= remaining;
    }

    public void updateAttackStat(){
        int B = super.getAtk()[BASE_STAT];
        int L = super.getLevel();
        super.updateBaseAtk((B + B * (L / 25)) * (1 + 3 * (L / 50)));
    }


    public void applyArtifactToStat(){
        for(Artifact artifact : artifacts){
            for(Stat stat : artifact.getStats()){
                if(stat.getStatType().isDmgBonus()){
                    super.addElementDamageBonus(stat.getStatType().getIndex(), stat.getValue());
                    continue;
                }
                actions.get(stat.getStatType().getIndex()).accept(stat.getValue());
            }
        }
    }
//
//    @Override
//    public double getDamageMultiplier(AttackType attackType){
//        return (getPConst()[attackType.getIndex()]) + getLevel() * getKConst()[attackType.getIndex()];
//    }
//
//    public abstract double[] getKConst();
//    public abstract double[] getPConst();

    public void acceptHealing(int amt){
        super.setCurrentHp(getCurrentHp() + amt);
    }
    public void gainEnergy(){
        addEnergy(baseEnergyRegen + getLevel());
    }


    public int getSkillCost() {
        return skillCost;
    }

    public void setSkillCost(int skillCost) {
        this.skillCost = skillCost;
    }

    public int getBaseEnergyRegen() {
        return baseEnergyRegen;
    }

    public void setBaseEnergyRegen(int baseEnergyRegen) {
        this.baseEnergyRegen = baseEnergyRegen;
    }

    public ArrayList<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(ArrayList<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Team getOfTeam() {
        return ofTeam;
    }

    public void setOfTeam(Team ofTeam) {
        this.ofTeam = ofTeam;
    }

//    public abstract void uniqueSkill(Unit target);
//    public abstract void basicAttack(Unit target);
//    public abstract int getScalingStat();
}
