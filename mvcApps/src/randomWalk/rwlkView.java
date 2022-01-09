package randomWalk;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;
import java.util.List;

public class rwlkView extends SimulationView {
    private List<Agent> agents;
    public rwlkView(Simulation simModel) {
        super(simModel);
        agents = simModel.getAgents();
    }


}
