package mindField;
import mvc.*;

/*
 * Edits:
 *   Xuefeng     3/12/21: create file
 *   Xuefeng     3/14/21: edit
 *   Xuefeng     3/15/21: define direction variables to cooperate with factory
 *   Eric        3/17/21: fixed an issue with MoveCommand
 * 
 * */

public class MoveCommand extends Command {



    /**
     * NORTH, WEST, EAST, SOUTH
     * NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST
     */
    public static int [][] directions ={
        {-1,0}, {0,-1}, {0,1}, {1,0},
        {-1,-1}, {-1,1}, {1,-1}, {1,1}
    };

    private int directionCode;

    public MineField model;
    public MoveCommand(Model model) {
        super(model);
    }

    public MoveCommand(Model model, int direction){
        super(model);
        this.model = (MineField) model;
        directionCode = direction;
    }

    public void execute() {
        int x_change = directions[directionCode -1][0];
        int y_change = directions[directionCode -1][1];

        model.change(x_change, y_change);

    }
}
