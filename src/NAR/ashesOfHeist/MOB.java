package NAR.ashesOfHeist;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

public class MOB extends Enemy{
    private static final int MOB_PER_DUNGEON = 4;
    private final Consumer<DungeonTier>[]statScalers = new Consumer[]{
            (Consumer<DungeonTier>)super::scaleAtk, (Consumer<DungeonTier>)super::scaleDef,
            (Consumer<DungeonTier>)super::scaleHp, (Consumer<DungeonTier>)super::scaleEnergy,
            (Consumer<DungeonTier>)super::scaleSpeed
    };

    public MOB(Element element) {
        super(element, 600, 25, 75, 60, 200, 3, new String[]{"Pre-Training", "NAR-26", "NAR-27"});

    }

    public static ArrayList<MOB> generateMobs(Team team, DungeonTier dungeonTier){
        Random r = new Random();
        ArrayList<MOB>mobs = new ArrayList<>();
        for(int i = 0; i<MOB_PER_DUNGEON; i++){
            MOB mob = new MOB(Element.getAllElements()[r.nextInt(1,3)]);
            mob.setLevel(team.getTeamLevel());
            for (Consumer<DungeonTier> statScaler : mob.statScalers) {
                statScaler.accept(dungeonTier);
            }
            mobs.add(mob);
        }
        return mobs;
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
