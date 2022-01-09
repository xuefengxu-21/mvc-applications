package plague;

import mvc.AppPanel;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

import java.util.List;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 20; // the radius of infection
    public static int PERCENT_INFECTED = 50; // chance of getting infected
    public static int INITIAL_PERCENT_INFECTED = 10; // initial chance of infection

    @Override
    public void populate() {
        for(int i = 0; i < 25; i++)
        {
            this.addAgent(new Host());
        }
    }

    private int infectedAgents = (getAgents().size()*INITIAL_PERCENT_INFECTED)/100;

    //counts the infected agents for stats
    public double infectedAgentsPercent()
    {
        double percent = 0;
        infectedAgents = 0;
        for(int i = 0; i< getAgents().size(); i++)
        {
            Host current = (Host)getAgents().get(i);
            if(current.isInfected())
            {
                infectedAgents++;
            }
        }
        percent = (infectedAgents*100/getAgents().size());
        return percent;
    }

    public String[] getStats()
    {
        String[] stats = new String[3];
        stats[0] = "#agents = " + this.size();
        stats[1] = "clock = " + this.getClock();
        stats[2] = "% infected = " + this.infectedAgentsPercent();
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }

}
