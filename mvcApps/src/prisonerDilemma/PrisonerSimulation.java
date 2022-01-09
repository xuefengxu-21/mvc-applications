package prisonerDilemma;

import mvc.AppPanel;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

import java.awt.*;


public class PrisonerSimulation extends Simulation {

    @Override
    public void populate() {

        for(int i=0; i<10; i++){
            addAgent((new Prisoner(new Cooperate(), Color.BLACK)));
        }

        for(int i=0; i<10; i++){
            addAgent((new Prisoner(new Cheat(), Color.BLUE)));
        }

        for(int i=0; i<10; i++){
            addAgent((new Prisoner(new RandomlyCooperate(), Color.GREEN)));
        }

        for(int i=0; i<10; i++){
            addAgent((new Prisoner(new Tit4Tat(), Color.RED)));
        }

    }

    public int getAverage(Strategy s) {
        int count = 0; // # of prisoners with strategy s
        int fitnessCount = 0; // total fitness of all prisoners with strategy s

        for(Agent agent: getAgents()){
            Prisoner prisoner = (Prisoner) agent;
            Strategy strategy = prisoner.getStrategy();

            if (strategy.getName() == s.getName()){
                fitnessCount += prisoner.fitness;
                count++;
            }
        }
        return (fitnessCount / count);
    }

    public String[] getStats() // The statistics box should show the average fitness for each strategy.
    {
        String[] stats = new String[6];
        stats[0] = "#agents = " + this.size();
        stats[1] = "clock = " + this.getClock();
        stats[2] = "cheater average fitness = " + this.getAverage(new Cheat());
        stats[3] = "cooperator average fitness = " + this.getAverage(new Cooperate());
        stats[4] = "random average fitness = " + this.getAverage(new RandomlyCooperate());
        stats[5] = "tit4tat average fitness = " + this.getAverage(new Tit4Tat());
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PrisonerFactory());
        panel.display();
    }
}
