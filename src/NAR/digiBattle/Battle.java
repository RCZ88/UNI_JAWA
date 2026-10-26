package NAR.digiBattle;

import java.util.*;
import java.util.stream.Collectors;

public class Battle {
    public enum ActionKind { ATTACK, SKILL, DEFEND }
    public static class Action {
        public final ActionKind kind; public final Skill skill;
        public Action(ActionKind kind, Skill skill){this.kind=kind;this.skill=skill;}
    }
    public static class Placement {
        public final List<Integer> slots; public final List<Digimon> targets;
        public Placement(List<Integer> slots, List<Digimon> targets){
            this.slots=slots;
            this.targets=targets;
        }
    }

    private final List<Digimon> playerSlots; // exactly 5 slots; null means empty
    private final List<Digimon> enemySlots;  // exactly 5 slots; null means empty
    private final Random random;
    private final Deque<String> recentLog = new ArrayDeque<>();

    public Battle(List<Digimon> playerSlots, List<Digimon> enemySlots, Random random) {
        this.playerSlots=playerSlots; this.enemySlots=enemySlots; this.random=random;
    }
    public List<String> getRecentLog(){return new ArrayList<>(recentLog);}

    private void log(String message) {
        recentLog.add(message);
        if(recentLog.size()> 3) recentLog.removeFirst();
    }
    public boolean isOver() {
        boolean allDead = true;
        for (Digimon playerSlot : playerSlots) {
            if(playerSlot.getHp() != 0){
                allDead = false;
                break;
            }
        }
        if(allDead)return allDead;
        for (Digimon enemySlot : enemySlots) {
            if(enemySlot.getHp() != 0){
                allDead = false;
                break;
            }
        }
        return allDead;
    }
    public boolean playerWon() {
        boolean oneAlive = false;
        for (Digimon playerSlot : playerSlots) {
            if(playerSlot.getHp() != 0){
                oneAlive = true;
                break;
            }
        }
        boolean enemiesDead = true;
        for (Digimon enemySlot : enemySlots) {
            if(enemySlot.getHp() != 0){
                enemiesDead = false;
                break;
            }
        }
        return oneAlive && enemiesDead;
    }
    public List<Digimon> calculateTurnOrder() {
        ArrayList<Digimon>combined = new ArrayList<>(playerSlots);
        combined.addAll(enemySlots);
        combined.sort((e1, e2) -> e2.getSpeed() - e1.getSpeed());

        return combined;
    }
    public List<Placement> getValidPlacements(Range range, boolean targetEnemy) {
        ArrayList<ArrayList<Integer>>indexes = Skill.getPlacements(range, targetEnemy ?
                enemySlots.stream().map(Digimon::getAtSlot).collect(Collectors.toCollection(ArrayList::new)) :
                playerSlots.stream().map(Digimon::getAtSlot).collect(Collectors.toCollection(ArrayList::new)));
        ArrayList<Placement>placements = new ArrayList<>();

        for (ArrayList<Integer> index : indexes) {
            Vector<Digimon> digi = new Vector<>();
            index.forEach(i ->digi.add(targetEnemy ? enemySlots.get(i) : playerSlots.get(i)));

            placements.add(new Placement(index, new ArrayList<>(digi)));
        }
        return placements;
    }
    public int calculateDamage(Digimon attacker, Digimon defender, int power, boolean normalAttack) {
        double swing = attacker.getAttack() * power / 50.0;
        double mitigation = 30.0/(30.0+defender.getDefence());
        double multiplier = 1;
        if(!normalAttack){
            if(attacker.getElement().strength == defender.getElement().index){
                multiplier = 1.5;
            }else if(attacker.getElement().weakness == defender.getElement().index){
                multiplier = 0.5;
            }
        }
        boolean crit = new Random().nextInt(1,10) == 1 ;
        multiplier *= crit ? 1.5 : 1;
        double combined = swing * mitigation * multiplier * defender.consumeGuardMultiplier();
        return  (int)combined;
    }
    public void performAction(Digimon actor, Action action, List<Integer> targetSlots,
                              boolean targetEnemy) {
        // TODO: Ignore defeated actor. Attack costs no MP and has power 50 / Single range.
        // Skill must have enough MP and spend its MP cost. DAMAGE uses calculateDamage;
        // HEAL restores power HP up to max HP; BUFF/DEBUFF alter chosen stats for duration.
        // Apply the effect to living targets in targetSlots and log damage/healing/knockouts.

    }
    public void performEnemyTurn(Digimon enemy) {
        // TODO: 40% normal attack, 50% random affordable skill, 10% defend.
        // If no affordable skill exists, fall back to another legal action.
        // For attacks choose a placement that covers the most living player Digimon.
    }
    public void runRound() {
        // TODO: Recalculate turn order each round. Each living Digimon acts once in speed
        // order, skipping defeated actors and stopping when battle ends. The UI/caller
        // supplies the player's chosen action; enemy turns use performEnemyTurn().
        // Tick temporary effects at one consistent point in the round/turn cycle.
    }
    public List<Digimon> getDefeatedEnemies() {
        // TODO: Return enemy Digimon at 0 HP, for EXP calculation.
        return new ArrayList<>();
    }
    public List<Digimon> getSurvivingPlayers() {
        // TODO: Return living player Digimon, for EXP distribution.
        return new ArrayList<>();
    }
}
