package plague;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;
import java.awt.*;

public class Host extends Agent {
    private boolean infected;
    private int resistance;
    public Color color;
    private int luck;

    public Host()
    {
        super();
        luck = mvc.Utilities.rng.nextInt(100);
        resistance = PlagueSimulation.PERCENT_INFECTED;
        //resistance = Utilities.rng.nextInt(PlagueSimulation.PERCENT_INFECTED);
        heading = Heading.random();
        // born infected if the luck is less than initial % infected
        infected = luck < PlagueSimulation.INITIAL_PERCENT_INFECTED;
    }

    public synchronized boolean isInfected()
    {
        return infected;
    }

    //randomly infects agents
    public synchronized void infect()
    {
        if(!this.isInfected())
        {
            /* luck can be rerolled if the virus is meant to be more random, otherwise the initial luck will determine more
            * luck = mvc.Utilities.rng.nextInt(100);
            */
            this.infected = (luck < this.resistance);
        }
    }

    public void update()
    {
        move(3);
        if(this.isInfected())
        {
            Host neighbor = (Host) this.simulation.getNeighbor(this, PlagueSimulation.VIRULENCE);
            if(neighbor != null && !neighbor.isInfected())
            {
                neighbor.infect();
            }
            move(3);
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

}
