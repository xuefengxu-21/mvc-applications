package plague;

import mvc.Model;
import mvc.View;
import simstation.SimStationFactory;

public class PlagueFactory extends SimStationFactory {

    public PlagueFactory()
    {

    }

    public Model makeModel() { return new PlagueSimulation(); }

    public View makeView(Model model) {
        return new PlagueView((PlagueSimulation)model);
    }

    public String getTitle() { return "Plague Simulation";}
}
