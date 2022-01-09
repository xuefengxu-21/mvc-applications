package prisonerDilemma;

public class Tit4Tat extends Strategy {

    public static boolean cooperate() {
        // if they were cheated, they will cheat
        return !myPrisoner.getCheatedStatus();
    }
    public Tit4Tat (){name = "Tit4Tat";}
}
