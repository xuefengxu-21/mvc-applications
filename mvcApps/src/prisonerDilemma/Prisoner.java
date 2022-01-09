package prisonerDilemma;

import simstation.Agent;
import simstation.Heading;

import java.awt.*;

public class Prisoner extends Agent {

    public int fitness = 0;
    private boolean partnerCheated = false;
    public Strategy strategy;
    private Color color;

    public Prisoner() {
        super();
        heading = Heading.random();
        strategy = new Cooperate();
    }


    public Prisoner(Strategy strategy, Color color){
        super();
        heading = Heading.random();
        this.strategy = strategy;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public boolean getCheatedStatus() {
        return partnerCheated;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy s) {
        strategy = s;
    }


    public boolean cooperate() {
        return Strategy.cooperate();
    }

    @Override
    public void update() {
        move(10);   // move around to find a new neighbor
        heading = Heading.random(); // random direction
        Prisoner opponent = (Prisoner) this.simulation.getNeighbor(this, 10); // find a neighbor, set them as opponent, can change radius to be larger
        updateFitness(opponent);
    }

    private synchronized void updateFitness(Prisoner opponent) {
        if (opponent != null) {
            if (opponent.cooperate() && this.cooperate()) { // both cooperate
                opponent.updateFitness(3);
                opponent.partnerCheated = false;
                this.updateFitness(3);
                this.partnerCheated = false;
            } else if (opponent.cooperate() && !this.cooperate()) { // opponent cooperates, this cheats
                opponent.updateFitness(0); // unnecessary
                opponent.partnerCheated = true;
                this.updateFitness(5);
                this.partnerCheated = false;
            } else if (!opponent.cooperate() && this.cooperate()) { // opponent cheats, this cooperates
                opponent.updateFitness(5);
                opponent.partnerCheated = false;
                this.updateFitness(0);
                this.partnerCheated = true;
            } else if (!opponent.cooperate() && !this.cooperate()) { // both cheat
                opponent.updateFitness(1);
                opponent.partnerCheated = true;
                this.updateFitness(1);
                this.partnerCheated = true;
            }
        }
    }


    @Override
    public void move(int step) {
        int[] move_to = parseHeading(heading);
        xc += move_to[0] * step;
        yc += move_to[1] * step;

        if(xc > 400) xc=0;
        if(xc < 0) xc = 400;
        if(yc > 400) yc=0;
        if(yc < 0) yc = 400;

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

    public void updateFitness(int amt) {
        this.fitness += amt;
    }
}
