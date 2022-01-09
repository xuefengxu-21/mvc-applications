package randomWalk;

import mvc.AppPanel;
import simstation.Simulation;
import simstation.SimulationPanel;

public class randomwkSimulation extends Simulation {
    @Override
    public void populate(){
        for (int i=0; i< 3; i++) {
            addAgent(new rwlkAgent());
        }
    }

    public static void main (String [] args) {
        AppPanel panel = new SimulationPanel(new rwlkFactory());
        panel.display();
    }
}
