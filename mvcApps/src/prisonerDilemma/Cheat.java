package prisonerDilemma;


public class Cheat extends Strategy { // always cheats
    private String name;
    public Cheat (){name = "Cheat";}
    public static boolean cooperate() {
        return false;
    }
}
