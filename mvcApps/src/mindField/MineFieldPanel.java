package mindField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

import mvc.AppFactory;
import mvc.AppPanel;
import mvc.Utilities;
import mvc.View;
import mvc.Model;

import java.awt.*;

/*
 * Edits:
 *   Xuefeng     3/12/21: create file
 *   Eric        3/12/21: started implementing MineFieldPanel
 *   Eric        3/21/21: added message dialog popup when game is won or lost.
 *   
 * */
public class MineFieldPanel extends AppPanel{
    public MineFieldPanel(AppFactory factory) {
        super(factory);

        // this.setLayout(new BorderLayout());
        GridLayout layout = new GridLayout(4,2);
        layout.setHgap(80);
        layout.setVgap(40);

        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(layout);
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(buttonPanel);
        controlPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        String[] commands = factory.getEditCommands();
        for (int i = 0; i < commands.length; i++) {
            JButton button = new JButton(commands[i]);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        //add(buttonPanel);
        //add(view);
    }

    public static void main(String[] args) {
        MineFieldFactory factory = new MineFieldFactory();
        MineFieldPanel panel = new MineFieldPanel(factory);
        panel.display();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        super.actionPerformed(ae);
        String str = ae.getActionCommand();
        List<String> edits = Arrays.asList(factory.getEditCommands());
        MineField temp = ((MineField)model);

        // checks to see if game is over, and shows message dialog if so
        if(edits.contains(str) && temp.getGameStatus() && !temp.getMessageAlreadyDisplayed()){
            if(temp.isWinner()){
                Utilities.inform("Congratulations! You won!");
            }else{
                Utilities.inform("You stepped on a mine! Game Over!");
            }
            temp.setMessageAlreadyDisplayed(true);
            //start new game
            Model newModel = factory.makeModel();
            newModel.initSupport();
            //update view
            remove(view);
    		View newView = factory.makeView(newModel);
    		add(newView);
    		frame.validate();
    		newView.repaint();
    		view = newView;
            
            model = newModel;
            
        }
    }
}
