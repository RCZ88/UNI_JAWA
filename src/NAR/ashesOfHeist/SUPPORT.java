package NAR.ashesOfHeist;

public class SUPPORT extends Character {

    public SUPPORT(Element element, int maxHp, int baseAtk, int baseDef, int baseSpd, int maxEnergy, String[]attackNames) {
        super(element, maxHp, baseAtk, baseDef, baseSpd, maxEnergy, 24, 4, attackNames);
    }



    public void healTeam(){
        double multiplier = 0;
        if(getElement() == Element.FIRE || getElement() == Element.WATER){
            multiplier = 0.6 + 0.02*getLevel();
        }else{
            multiplier = 0.2 * 0.014 * getLevel();
        }
        for (Character character : this.getOfTeam().getCharacters()) {
            character.acceptHealing((int) (multiplier * getScalingStat()));
        }
    }

//    @Override
//    public void uniqueSkill(Unit target) {
//        consumeEnergy(getSkillCost());
//        healTeam();
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
//        return new double[]{0.02, 0.02};
//    }
//
//    @Override
//    public double[] getPConst() {
//        return new double[]{0.6, 0.6};
//    }


    @Override
    public void loadAttacks() {
        addNewAttack(new Attack(AttackType.ATTACK, 0, getEnergyRegen(), MULTIPLIER_AMOUNT, Attack.makeMulti(0.02, 0.6), MULTIPLIER_CONSTRUCT, AOE.SINGLE_TARGET, false, this));
    }

}
