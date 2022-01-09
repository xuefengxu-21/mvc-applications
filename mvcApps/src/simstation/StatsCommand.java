package simstation;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
        Utilities.inform(((Simulation)model).getStats());
    }
}
