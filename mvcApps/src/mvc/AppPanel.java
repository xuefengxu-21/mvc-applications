package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AppPanel extends JPanel implements ActionListener{

    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGHT = 400;

    protected Model model;
    protected AppFactory factory;
    protected JFrame frame;
    protected JPanel controlPanel;
    protected View view;

    public AppPanel(AppFactory f) {
        factory = f;

        //make model
        model = factory.makeModel();

        //create control and view
        controlPanel = new JPanel();
        view = f.makeView(model);
        setLayout(new GridLayout(1, 2));
        add(controlPanel);
        add(view);

        controlPanel.setBackground(Color.LIGHT_GRAY);

        //set JFrame
        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        frame.setResizable(false);
    }

    public void display() {
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if(str == "About") {
            Utilities.inform(factory.about());
        }else if(str == "Help"){
            Utilities.inform(factory.getHelp());
        }else  if(str == "Save") {
            Utilities.save(model, false);
        }else if(str == "Save As") {
            Utilities.save(model, true);
        }else if(str == "Open"){
            Model newModel = Utilities.open(model);
            if(newModel != null) {
                newModel.initSupport();

                //update view
                remove(view);
                View newView = factory.makeView(newModel);
                add(newView);
                frame.validate();
                newView.repaint();
                view = newView;

                //update self
                model = newModel;
            }
        }else{
            Command cmd = factory.makeEditCommand(model, ae.getActionCommand());
            if(cmd != null) {
                try {
                    cmd.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected  JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.add(Utilities.makeMenu("File", new String[] {"Save", "Save As", "Open"}, this));
        bar.add(Utilities.makeMenu("Edit", factory.getEditCommands(), this));
        bar.add(Utilities.makeMenu("Help", new String[] {"Help", "About"}, this));
        return bar;
    }

}
