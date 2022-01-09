package simstation;

import mvc.Utilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Heading {
    EAST, WEST, SOUTH, NORTH;

    private static final List<Heading> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();

    public static Heading random() {
        return VALUES.get(Utilities.rng.nextInt(SIZE));
    }

}
