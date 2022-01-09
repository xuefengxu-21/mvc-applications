package simstation;
import mvc.*;

import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class SimulationView extends View {

    // private list of agent
    public List<Agent> agents;


    public SimulationView (Simulation simModel) {
        super (simModel);
        agents = simModel.getAgents();
    }

    public void paintComponent (Graphics gc) {
        super.paintComponent(gc);

        for (Agent agent: agents) {
            Graphics2D g2d = (Graphics2D)gc;
            int x = agent.getXc();
            int y = agent.getYc();
            g2d.setColor(Color.RED);
            g2d.fillOval(x,y,10,10);
        }
    }





}