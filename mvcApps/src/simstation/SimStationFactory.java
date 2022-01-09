package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimStationFactory implements AppFactory {


    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model model) {
        return new SimulationView((Simulation)model);
    }

    @Override
    public String getTitle() {
        return "SimStation";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Click a direction to move a space in the respective direction."};
    }

    @Override
    public String about() {
        return "SimStation version 1.0. Copyright 2021 by Logan Joven, Ekta Halani, and Xuefeng Xu";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Suspend", "Resume", "Stats", "Stop"};
    }

    @Override
    public Command makeEditCommand(Model model, String name) {
        if (name == "Start")
            return new StartCommand(model);
        if (name == "Suspend")
            return new SuspendCommand(model);
        if (name == "Resume")
            return new ResumeCommand(model);
        if (name == "Stats")
            return new StatsCommand(model);
        if (name == "Stop")
            return new StopCommand(model);
        return null;
    }
}