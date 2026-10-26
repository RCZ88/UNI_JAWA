package NAR.digiBattle;

import java.util.*;

/** Move metadata. Battle.java applies the move's effects. */
public class Skill {
    public enum Type { DAMAGE, HEAL, BUFF, DEBUFF }
//    public enum Range { SINGLE, PAIR, TRIPLE, SPREAD_PAIR, SPREAD_TRIPLE }

    private final String name;
    private final Type type;
    private final int power, mpCost, duration;
    private final Range range;

    public Skill(String name, Type type, int power, int mpCost, Range range, int duration) {
        this.name = name; this.type = type; this.power = power;
        this.mpCost = mpCost; this.range = range; this.duration = duration;
    }
    public String getName() { return name; }
    public Type getType() { return type; }
    public int getPower() { return power; }
    public int getMpCost() { return mpCost; }
    public Range getRange() { return range; }
    public int getDuration() { return duration; }

    public static ArrayList<ArrayList<Integer>> getPlacements(Range range,  ArrayList<Integer>targetIndexes) {
        // TODO: Return every legal set of covered slot indexes across slots 0..4.
        // SINGLE: [0]...[4]; PAIR: [0,1]...[3,4]; TRIPLE: [0,1,2]...[2,3,4].
        // SPREAD_PAIR: [0,2], [1,3], [2,4]; SPREAD_TRIPLE: [0,2,4].
        ArrayList<ArrayList<Integer>> indexes = new ArrayList<>();
        for(int i = 0; i<Range.TARGET_SLOTS_COUNT; i++){
            ArrayList<Integer>is = range.getIndexOfRelative(i);
            is.retainAll(targetIndexes);
            indexes.add(is);
        }

        return indexes;
    }
}
