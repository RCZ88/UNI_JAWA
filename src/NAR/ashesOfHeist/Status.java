package NAR.ashesOfHeist;

public class Status {
    private Unit from;
    private int roundsRemaining;
    private Element element;

    public Status(Unit from, int roundsRemaining){
        this.from = from;
        this.roundsRemaining = roundsRemaining;
        this.element = from.getElement();
    }

    public int getStatusDmg(){
        int dmg = (int) element.getMultiplier() * from.getLevel() + 1;
        switch(element){
            case FIRE -> dmg *= Stat.sumStat(from.getAtk());
            case WATER -> dmg *= Stat.sumStat(from.getDef());
            case GRASS -> dmg *= Stat.sumStat(from.getMaxHp());
        }
        return dmg;
    }

    public int deductRoundsRemaining(){
        if(roundsRemaining > 0) return --roundsRemaining;
        return roundsRemaining;
    }
}
