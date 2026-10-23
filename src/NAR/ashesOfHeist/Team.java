package NAR.ashesOfHeist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Team {
//    DPS dps;
//    SUB_DPS subDps;
//    SUPPORT support;
    public static final int DPS_INDEX = 0;
    public static final int SUB_DPS_INDEX = 1;
    public static final int SUPPORT_INDEX = 2;

    private ArrayList<Character> characters;
    private ArrayList<Integer> characterOrder;
    private int teamLevel;

    public void sortBySpeed(){
        ArrayList<Character> tempSorted = new ArrayList<>(characters);
        tempSorted.sort(Comparator.comparingInt(o -> Stat.sumStat(o.getSpeed())));
        for(int i = 0; i<characters.size(); i++){
            for(int j = 0; j<characters.size(); j++){
                if(tempSorted.get(i) == characters.get(j)){
                    characterOrder.set(i, j);
                    break;
                }
            }
        }
    }
    public void calculateTeamLevel(){
        teamLevel = 0;
        for(Character c : characters){
            teamLevel += c.getLevel();
        }
    }

    public Character getNextCharacterTurn(int curTurn){
        int nextTurn = curTurn;
        if(curTurn + 1 > 2){
            nextTurn = 0;
        }else{
            nextTurn++;
        }
        return characters.get(characterOrder.get(nextTurn));
    }

    public DPS getDps(){
        return (DPS) characters.get(DPS_INDEX);
    }
    public SUB_DPS getSubDps(){
        return (SUB_DPS) characters.get(SUB_DPS_INDEX);
    }
    public SUPPORT getSupport(){
        return (SUPPORT) characters.get(SUPPORT_INDEX);
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

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
