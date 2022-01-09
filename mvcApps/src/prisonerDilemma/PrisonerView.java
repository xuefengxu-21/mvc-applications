package prisonerDilemma;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;

public class PrisonerView extends SimulationView {
    public PrisonerView(Simulation simModel) {
        super(simModel);
        agents = simModel.getAgents();
    }

    @Override
    public void paintComponent (Graphics gc) {
        super.paintComponent(gc);

        for (Agent agent: agents) {
            Graphics2D g2d = (Graphics2D)gc;
            int x = agent.getXc();
            int y = agent.getYc();
            g2d.setColor(agent.getColor());
            //System.out.println("from view: " + agent.getColor());
            g2d.fillOval(x,y,10,10);
        }
    }
}
