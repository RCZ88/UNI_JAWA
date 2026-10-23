package NAR.reNHlorist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FloristManager {
    private final List<Florist> florists;

    public FloristManager() {
        // TODO: Initialize the list.
        florists = new ArrayList<>();
    }

    public List<Florist> getFlorists() {
        // TODO: Return a safe view or copy.
        return Collections.unmodifiableList(florists);
    }

    public void addFlorist(Florist florist) {
        // TODO: Add the florist and persist immediately.
    }

    public void loadFlorists() {
        // TODO: Load florists from florists.txt.

    }

    public void saveFlorist(Florist florist) {
        // TODO: Persist a florist to florists.txt.
    }
}
