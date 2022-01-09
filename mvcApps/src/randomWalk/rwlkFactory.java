package randomWalk;

import simstation.*;
import mvc.*;

public class rwlkFactory extends SimStationFactory{

    public Model makeModel() {return new randomwkSimulation();}

    public View makeView(Model model) {return new rwlkView((randomwkSimulation)model);}
    public String getTitle() { return "Random Walks_Snow";}
}
