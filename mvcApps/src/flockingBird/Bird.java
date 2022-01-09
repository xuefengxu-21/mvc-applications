package flockingBird;

import mvc.Utilities;
import simstation.Agent;
import simstation.Heading;

import java.awt.*;

public class Bird extends Agent {
    private float r,g,b;
    private Color color;
    private static double RADIUS = 5;
    public int speed;

    public Bird () {
        super();
        heading = Heading.random();
        setRandColor();
        color = new Color(r,g,b);
    }

    public void update(){

        Bird neighbor = (Bird) simulation.getNeighbor(this, RADIUS);

        if (neighbor != null && simulation.getClock() > 1){

            heading = neighbor.heading;
            speed = neighbor.speed;
            color = neighbor.color;
            move(speed);
        }else {
            heading = Heading.random();
            speed = Utilities.rng.nextInt(10)+1;
            move(speed);
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


    public Color getColor(){
        return color;
    }

    public int getSpeed(){
        return speed;
    }

    private void setRandColor(){
        r = Utilities.rng.nextFloat();
        g = Utilities.rng.nextFloat();
        b = Utilities.rng.nextFloat();
        //System.out.println(r+ " " + g + " " +b);
    }

}
