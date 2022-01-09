package simstation;

import mvc.*;

import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends AppPanel {

    public SimulationPanel(AppFactory factory) {
        super(factory);

        GridLayout layout = new GridLayout (4,2);
        layout.setHgap(80);
        layout.setVgap(40);

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(layout);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(buttonPanel);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(30,0,0,0));

        String[] commands = factory.getEditCommands();
        for (int i=0; i<commands.length; i++){
            JButton button = new JButton(commands[i]);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
    }

    public static void main(String[] args) {

        AppPanel panel = new SimulationPanel(new SimStationFactory());
        panel.display();
    }



}
