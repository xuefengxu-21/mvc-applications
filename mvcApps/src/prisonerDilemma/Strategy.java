package prisonerDilemma;

abstract class Strategy {
    public static Prisoner myPrisoner;
    public String name;

    public Strategy(){}
    public String getName(){return name;}
    static boolean cooperate() {
        return false;
    }
}
