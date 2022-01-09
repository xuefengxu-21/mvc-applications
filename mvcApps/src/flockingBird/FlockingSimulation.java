package flockingBird;

import mvc.AppPanel;
import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationPanel;

import java.util.Iterator;
import java.util.List;

import static java.lang.Math.sqrt;

public class FlockingSimulation extends Simulation {
    @Override
    public void populate() {
        for (int i=0; i < 20; i++) {
            addAgent(new Bird());
        }
    }

    @Override
    public Agent getNeighbor(Agent agent, double radius) {

        //boolean foundNeighbor = false;
        List<Agent> birds = this.getAgents();
        Iterator it = birds.iterator();

        int x = agent.getXc();
        int y = agent.getYc();

        while (it.hasNext()){
            Bird nearBird = (Bird) it.next();
            int x1 = nearBird.getXc();
            int y1 = nearBird.getYc();

            double distance = sqrt((x1-x)^2 + (y1-y)^2);
            if (distance <= radius){
                return nearBird;
            }
        }

        return null;
    }

    @Override
    public String [] getStats() {
        String[] stats = new String[10];
        int [] speedList = new int[10];
        for (int i=0; i<10; i++){
            speedList[i] = 0;
        }

        //suspend();
        // get speed from each agent
        for (Agent agent: getAgents()){
            Bird bird = (Bird)agent;
            speedList[bird.speed-1] += 1;
        }

        for (int i=0; i<10; i++){
            stats[i] = "# Bird in speed " + (i+1) + " is: " + speedList[i];
        }

        return stats;
    }

    public static void main (String [] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }
}
