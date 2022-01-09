package plague;

import simstation.Agent;
import simstation.Simulation;
import simstation.SimulationView;

import java.awt.*;
import java.util.Iterator;

public class PlagueView extends SimulationView {

    public PlagueView(Simulation simModel) {
        super(simModel);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Iterator list = this.agents.iterator();

        while (list.hasNext()) {
            Host host = (Host) list.next();
            Graphics2D g2d = (Graphics2D) gc;
            int x = host.getXc();
            int y = host.getYc();
            g2d.setBackground(Color.GRAY);
            if(host.isInfected()) {
                g2d.setColor(Color.RED);
                g2d.drawOval(x,y,10,10);
                g2d.fillOval(x, y, 10, 10);
            }
            else if(!host.isInfected())
            {
                g2d.setColor(Color.GREEN);
                g2d.fillOval(x, y, 10, 10);
            }

        }
    }
}
