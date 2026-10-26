package NAR.ashesOfHeist;

import java.util.ArrayList;
import java.util.Comparator;

public class Team {
//    DPS dps;
//    SUB_DPS subDps;
//    SUPPORT support;
    public static final int DPS_INDEX = 0;
    public static final int SUB_DPS_INDEX = 1;
    public static final int SUPPORT_INDEX = 2;

    private ArrayList<Ally> allies;
    private ArrayList<Integer> characterOrder;
    private int teamLevel;

    public void sortBySpeed(){
        ArrayList<Ally> tempSorted = new ArrayList<>(allies);
        tempSorted.sort(Comparator.comparingInt(o -> Stat.sumStat(o.getSpeed())));
        for(int i = 0; i< allies.size(); i++){
            for(int j = 0; j< allies.size(); j++){
                if(tempSorted.get(i) == allies.get(j)){
                    characterOrder.set(i, j);
                    break;
                }
            }
        }
    }
    public void calculateTeamLevel(){
        teamLevel = 0;
        for(Ally c : allies){
            teamLevel += c.getLevel();
        }
    }

    public Ally getNextCharacterTurn(int curTurn){
        int nextTurn = curTurn;
        if(curTurn + 1 > 2){
            nextTurn = 0;
        }else{
            nextTurn++;
        }
        return allies.get(characterOrder.get(nextTurn));
    }

    public DPS getDps(){
        return (DPS) allies.get(DPS_INDEX);
    }
    public SUB_DPS getSubDps(){
        return (SUB_DPS) allies.get(SUB_DPS_INDEX);
    }
    public SUPPORT getSupport(){
        return (SUPPORT) allies.get(SUPPORT_INDEX);
    }

    public void addAlly(Ally ally){
        this.allies.add(ally);
    }

    public ArrayList<Integer> getCharacterOrder() {
        return characterOrder;
    }

    public void setCharacterOrder(ArrayList<Integer> characterOrder) {
        this.characterOrder = characterOrder;
    }

    public int getTeamLevel() {
        return teamLevel;
    }

    public void setTeamLevel(int teamLevel) {
        this.teamLevel = teamLevel;
    }

    public ArrayList<Ally> getAllies() {
        return allies;
    }

    public void setCharacters(ArrayList<Ally> allies) {
        this.allies = allies;
    }
}
