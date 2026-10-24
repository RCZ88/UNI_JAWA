package NAR.ashesOfHeist;

public class SUB_DPS extends Character{
    private int statusRoundLength;

    public SUB_DPS(Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, int statusRoundLength, String[]attackNames) {
        super(element, maxHp, baseAtk, baseDef, baseSpd, maxEnergy, 28, 10, attackNames);
        this.statusRoundLength = statusRoundLength;
    }


    public void applyStatus(Unit target){
        Status status = new Status(this, statusRoundLength);
        target.addStatus(status);
    }

//    @Override
//    public void uniqueSkill(Unit target) {
//        applyStatus(target);
//        consumeEnergy(getSkillCost());
//        applyDamage(calculateFinalDamage(target, AttackType.SKILL), target);
//    }
//
//    @Override
//    public void basicAttack(Unit target) {
//        this.gainEnergy();
//        applyDamage(calculateFinalDamage(target, AttackType.BASIC), target);
//    }

    public int getStatusRoundLength() {
        return statusRoundLength;
    }

    public void setStatusRoundLength(int statusRoundLength) {
        this.statusRoundLength = statusRoundLength;
    }
//
//    @Override
//    public double[] getKConst() {
//        return new double[]{0.06, 1.3};
//    }
//
//    @Override
//    public double[] getPConst() {
//        return new double[]{1.0, 5.0};
//    }


    @Override
    public void loadAttacks() {
        addNewAttack(new Attack(AttackType.ATTACK, 0, getEnergyRegen(), MULTIPLIER_AMOUNT, Attack.makeMulti(0.06, 1.0), MULTIPLIER_CONSTRUCT, AOE.SINGLE_TARGET,false,  this));
        addNewAttack(new Attack(AttackType.SKILL, getSkillCost(), 0, MULTIPLIER_AMOUNT, Attack.makeMulti(1.3, 5.0), MULTIPLIER_CONSTRUCT, AOE.ALL_ENEMY, true, this));
    }
}
