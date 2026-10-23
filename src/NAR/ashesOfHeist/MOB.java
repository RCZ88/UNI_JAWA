package NAR.ashesOfHeist;

public class MOB extends Enemy{
    public MOB(Element element, int maxHp, int baseAtk, int baseDef, int maxEnergy) {
        super(element, maxHp, baseAtk, baseDef, 0, maxEnergy, 3);
        setSpeed(formatStat(getMobSpeed()));
    }
    public int getMobSpeed(){
        return 60 + (60 * getLevel() / 20);
    }

//    @Override
//    public int[] getEnergyCost() {
//        return new int[]{0, 100, 200};
//    }
//
//    @Override
//    public void attack(Unit unit) {
//        applyDamage(calculateFinalDamage(unit, AttackType.ATTACK), unit);
//        addEnergy(80 + getLevel());
//    }
//
//    public void attack(AttackType attackType, ArrayList<Character> target){
//
//    }
//
//    @Override
//    public void basic(Team team) {
//        for(Character c : team.getCharacters()){
//            applyDamage(calculateFinalDamage(c, AttackType.BASIC), c);
//        }
//    }
//
//    @Override
//    public void intermediate(Team team) {
//
//    }




    @Override
    public void loadAttacks() {
        addNewAttack(new Attack(AttackType.ATTACK, 0, 80 + getLevel(), MULTIPLIER_AMOUNT, Attack.makeMulti(1.0), MULTIPLIER_CONSTRUCT, AOE.SINGLE_TARGET, false, this));
        addNewAttack(new Attack(AttackType.BASIC, 100, 0, MULTIPLIER_AMOUNT, Attack.makeMulti(1.0), MULTIPLIER_CONSTRUCT, AOE.ALL_TEAM, false, this));
        addNewAttack(new Attack(AttackType.INTERMEDIATE, 200, 0, MULTIPLIER_AMOUNT, Attack.makeMulti(2.0), MULTIPLIER_CONSTRUCT, AOE.ALL_TEAM, false, this));
    }
}
