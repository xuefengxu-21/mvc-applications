package simstation;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public abstract class Agent implements Serializable, Runnable {
    public String name;
    public Heading heading;
    public int xc;
    public int yc;
    public boolean suspended;
    public boolean stopped;

    protected Thread myThread;
    protected Simulation simulation;

    public Agent() {
        //heading = Heading.random();
        myThread = null;
        suspended = false;
        stopped = false;

        Random rand = new Random();
        xc = rand.nextInt(300);
        yc = rand.nextInt(300);
        name = "Agent " + rand.nextInt(50);
    }

    public void setManager(Simulation manager) {
        this.simulation = manager;
    }

    public void run() {
        myThread = Thread.currentThread();

        while (!isStopped()) {
            try {
                update();
                //System.out.println("updated");
                Thread.sleep(100);
                checkSuspended();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }

    //public void start() { run();}

    // thread stuff
    public synchronized void stop() {
        stopped = true;
    }

    public synchronized boolean isStopped() {
        return stopped;
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized boolean isSuspended() {
        return suspended;
    }

    public synchronized void resume() {
        notify();
    }

    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch (InterruptedException e) {
            //simulation.println(e.getMessage());
        }
    }

    private synchronized void checkSuspended() {
        try {
            while (!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public abstract void update();

    public void move(int step) {
        int[] move_to = parseHeading(this.heading);
        xc += move_to[0] * step;
        yc += move_to[1] * step;

        simulation.changed();
    }

    private int[] parseHeading(Heading heading) {
        switch (heading) {
            case EAST:
                return (new int[]{0, 1});
            case WEST:
                return (new int[]{0, -1});
            case SOUTH:
                return (new int[]{1, 0});
            case NORTH:
                return (new int[]{-1, 0});
        }
        return (new int[]{0, 0});
    }

    public int getXc() {
        return xc;
    }

    public int getYc() {
        return yc;
    }

    public Color getColor(){return null;}

    //gets the distance between agent and agent2
    public double distance(Agent agent1, Agent agent2)
    {
        int x1 = agent1.getXc();
        int y1 = agent1.getYc();
        int x2 = agent2.getXc();
        int y2 = agent2.getYc();
        double distance = Math.sqrt((double)((x2-x1)^2 + (y2-y1)^2));
        return distance;
    }




    // runningStrategy is not implementing for this level

    class runningStrategy {
        runningStrategy() {
            algorithm();
        }

        void algorithm() {
        }
    }

    class onStart extends runningStrategy {
        void algorithm() {

        }
    }

    class onExit extends runningStrategy {
        void algorithm() {

        }
    }

    class onInterrupted extends runningStrategy {
        void algorithm() {

        }
    }


}