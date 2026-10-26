package NAR.ashesOfHeist;

import java.util.ArrayList;
import java.util.Random;

public class Artifact {
    public static final int NUMBER_OF_STAT = 6;
    public static final int MAX_LEVEL = 3;
    private ArrayList<Stat> stats;
    private String name;
    private int level;

    public Artifact(String name){
        this.stats = new ArrayList<>(NUMBER_OF_STAT);
        this.name = name;
    }

    public static Artifact getInstance(){
        Random r = new Random();
        Artifact a = Game.ALL_ARTIFACTS.get(r.nextInt(0, Game.ALL_ARTIFACTS.size()-1));
        for(int i = 0; i<NUMBER_OF_STAT; i++){
            a.addStat(new Stat(StatType.getStatByIndex(r.nextInt(0, StatType.uniqueStatAmount()-1))));
        }
        return a;
    }

    public void upgradeLevel(){
        if(level == MAX_LEVEL) return;
        level++;
        stats.forEach(s -> s.setValue(s.getValue() * level));
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStat(Stat stat){
        this.stats.add(stat);
    }

    public Stat getStatByIndex(int index){
        return stats.get(index);
    }
    public ArrayList<Stat>getStats(){
        return stats;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
