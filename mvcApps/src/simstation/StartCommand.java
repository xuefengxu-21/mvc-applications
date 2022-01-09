package simstation;

import mvc.Command;
import mvc.Model;

public class StartCommand extends Command {
    public StartCommand(Model model) {
        super((Simulation)model);
        ((Simulation) model).start();
    }
}
