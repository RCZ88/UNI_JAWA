package NAR.digiBattle;

import java.util.ArrayList;

public enum Range {
    SINGLE(1, 0),
    PAIR(2, 0,1),
    TRIPLE(3,0,1,2),
    SPREAD_PAIR(2, 0,2),
    SPREAD_TRIPLE(3, 0,2,4);
    public final ArrayList<Integer> relativeIndexes;
    public final int targetCount;
    public static final int TARGET_SLOTS_COUNT = 5;
    Range(int targetCount, int ...rIndex){
        this.targetCount = targetCount;
        relativeIndexes = new ArrayList<>(targetCount);
        for (int index : rIndex) {
            relativeIndexes.add(index);
        }
    }

    public ArrayList<Integer> getIndexOfRelative(int curIndex){
        ArrayList<Integer>indexes = new ArrayList<>();
        for (Integer relativeIndex : relativeIndexes) {
            indexes.add((curIndex + relativeIndex) % TARGET_SLOTS_COUNT);
        }
        return indexes;
    }

}
