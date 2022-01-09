package flockingBird;

import mvc.Model;
import mvc.View;
import simstation.SimStationFactory;

public class FlockingFactory extends SimStationFactory {
    public Model makeModel() {return new FlockingSimulation();}
    public View makeView(Model model) {return new FlockingBirdView((FlockingSimulation)model);}
    public String getTitle() { return "Flocking Birds Simulation";}
}
