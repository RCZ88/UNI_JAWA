package NAR.digiBattle;

import java.util.*;
import java.util.stream.Collectors;

/** Adventure state: starter choices, party formation, enemy generation, progression. */
public class Game {
    private final List<Digimon> party = new ArrayList<>();
    private final Random random = new Random();

    public List<Digimon> getParty(){return new ArrayList<>(party);}

    public List<Digimon> createStarterChoices() {

        return new ArrayList<>();
    }
    public boolean selectStarter(Digimon choice) {
        if(party.size() >= 3) return false;
        party.add(choice);
        return true;
    }
    public List<Digimon> getFormationSlots() {
        Vector<Digimon>digimons = new Vector<>();
        party.forEach(d -> digimons.add(d.getAtSlot(), d));
        return new ArrayList<>(digimons);
    }
    public void swapFormationSlots(int from, int to, ArrayList<Digimon>slots) {
        if(!(from >= 0 && from <=4 && to >=0 && to <=4))return;
        Digimon temp = slots.get(to);
        slots.set(to, slots.get(from));
        slots.set(from, temp);
    }
    public void awardExperience(Battle battle) {
        if(!battle.playerWon())return;
        int sum = battle.getDefeatedEnemies().stream().mapToInt(defeatedEnemy -> 60 * defeatedEnemy.getLevel()).sum();
        battle.getSurvivingPlayers().forEach(d -> d.gainExp(sum));
    }
    public List<Digimon> getEligibleForDigivolution() {
        return party.stream().filter(d->{
            if(d.getStage() == Stage.ULTIMATE)return false;
            return d.getLevel() == d.getStage().evolveReq;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
