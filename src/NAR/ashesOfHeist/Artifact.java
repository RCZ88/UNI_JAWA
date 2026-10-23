package NAR.ashesOfHeist;

import java.util.ArrayList;

public class Artifact {
    private ArrayList<Stat> stats;

    public Stat getStatByIndex(int index){
        return stats.get(index);
    }
    public ArrayList<Stat>getStats(){
        return stats;
    }
}
