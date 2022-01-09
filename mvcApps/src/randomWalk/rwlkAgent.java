package randomWalk;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

public class rwlkAgent extends Agent {

    public rwlkAgent (){
        super();
        heading = Heading.random();
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);
    }
}
