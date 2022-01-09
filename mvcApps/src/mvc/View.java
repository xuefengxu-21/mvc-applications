package mvc;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View extends JPanel implements PropertyChangeListener {
	
    protected Model model;
    
    public View(Object model) {
        this.model = (Model)model;
        this.model.addPropertyChangeListener(this);
    }

    public void paintComponent(Graphics gc){ super.paintComponent(gc); };

    public void propertyChange(PropertyChangeEvent arg0) {
        repaint();
    }
}
