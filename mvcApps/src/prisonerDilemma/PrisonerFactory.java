package prisonerDilemma;

import mvc.Model;
import mvc.View;
import simstation.SimStationFactory;

public class PrisonerFactory extends SimStationFactory {
    public Model makeModel() { return new PrisonerSimulation(); }
    public View makeView(Model model) { return new PrisonerView((PrisonerSimulation)model); }
    public String getTitle() { return "Prisoner Dilemma Tournament"; }

}
