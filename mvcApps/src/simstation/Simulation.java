package simstation;

import java.util.*;
import java.util.Timer;

import mvc.*;

import javax.swing.*;

public class Simulation extends Model {

    // private static int POPULATION = 50;

    private Timer timer;
    private int clock = 0;
    private List<Agent> agents;

    public Simulation() {
        super();
        agents = new LinkedList<Agent>();
        populate();
        for(Agent agent:agents){
            agent.setManager(this);
        }

    }

    @Override
    public String toString(){return "simulation";}

    public void populate(){};

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }


    public void start() {

        for(Agent agent: agents){
            Thread thread = new Thread(agent);
            thread.start();
        }

        startTimer();

    }

    public void suspend() {
        for(Agent agent:agents){
            agent.suspend();
        };
    }

    public void resume() {
        for(Agent agent:agents){
            agent.resume();
        }
    }

    public void stop() {
        for(Agent agent:agents){
            agent.stop();
        };
        stopTimer();
    }

    public synchronized Agent getNeighbor(Agent a, double radius) {
        Agent neighbor = null;
        boolean found = false;
        int i = Utilities.rng.nextInt(agents.size());
        int start = i;
        while(!found)
        {
            Agent candidate = agents.get(i);
            if(candidate !=a && a.distance(a,candidate) < radius)
            {
                neighbor = agents.get(i);
                found = true;
            }
            else
            {
                i = (i+1) % agents.size();
                if(i==start)
                {
                    break;
                }
            }
        }
        return neighbor;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void addAgent(Agent a) {
        this.agents.add(a);
    }

    public int size()
    {
        return agents.size();
    }

    public synchronized int getClock()
    {
        return clock;
    }

    //override for different stats
    public String[] getStats()
    {
        String[] stats = new String[2];
        stats[0] = "#agents = " + this.size();
        stats[1] = "clock = " + this.getClock();
        return stats;
    }

}

