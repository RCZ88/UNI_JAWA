package NAR.ashesOfHeist;

public class DPS extends Ally {

    public DPS(String name, Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, String[]attackNames) {
        super(name, element, maxHp, baseAtk, baseDef, baseSpd, maxEnergy, 10, 6, attackNames);
    }

//    @Override
//    public void uniqueSkill(Unit target) {
//        if(this.getCurrentEnergy()<10){
//            System.out.println("Not Enough Energy! (Required: 10)");
//            return;
//        }
//        applyDamage(calculateFinalDamage(target,AttackType.SKILL , (double) getCurrentEnergy() /(getLevel()*10)), target);
//        removeAllEnergy();
//    }
//
//    @Override
//    public void basicAttack(Unit target) {
//        this.gainEnergy();
//        applyDamage(calculateFinalDamage(target, AttackType.BASIC), target);
//    }
//
//    @Override
//    public double[] getKConst() {
//        return new double[]{0.06, 6.0};
//    }
//
//    @Override
//    public double[] getPConst() {
//        return new double[]{1.0, 5.0};
//    }

    @Override
    public void loadAttacks() {
        addNewAttack(new Attack(AttackType.ATTACK, 0, getEnergyRegen(), MULTIPLIER_AMOUNT, Attack.makeMulti(0.06, 1.0), MULTIPLIER_CONSTRUCT, AOE.SINGLE_TARGET, false, this));
        addNewAttack(new Attack(AttackType.SKILL, this.getCurrentEnergy(), 0, 10, 3, Attack.makeMulti(6.0, 5.0), (multipliers, unit) -> multipliers[P_INDEX] * unit.getLevel() * multipliers[K_INDEX] * (double) getCurrentEnergy() /(getLevel()*10), AOE.ALL_ENEMY, false, this));
    }
}
