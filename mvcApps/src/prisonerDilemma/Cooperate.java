package prisonerDilemma;

public class Cooperate extends Strategy { //always cooperate
    public Cooperate (){name = "Cooperate";}
    public static boolean cooperate() {
        return true;
    }
}
