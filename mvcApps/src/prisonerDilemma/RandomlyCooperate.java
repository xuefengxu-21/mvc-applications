package prisonerDilemma;

import mvc.Utilities;

public class RandomlyCooperate extends Strategy { // randomly cooperates
    public RandomlyCooperate (){name = "RandomlyCooperate";}

    public static boolean cooperate() {
        return Utilities.rng.nextBoolean();
    }
}
