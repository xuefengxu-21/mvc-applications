package mindField;

import mvc.*;

/*
 * Edits:
 * 
 *   Xuefeng     3/12/21: create file
 *   Eric        3/12/21: edit file
 *   Eric        3/14/21: started implementing MineFieldFactory
 *   Eric        3/15/21: can now create MoveCommands
 *   Eric        3/17/21: small change to how MoveCommand is constructed
 *   Eric        3/21/21: deleted some unnecessary  code
 *
 * */
public class MineFieldFactory implements AppFactory{

    public static int DIRECTION_NORTH = 1;
    public static int DIRECTION_WEST = 2;
    public static int DIRECTION_EAST = 3;
    public static int DIRECTION_SOUTH = 4;
    public static int DIRECTION_NORTHWEST = 5;
    public static int DIRECTION_NORTHEAST = 6;
    public static int DIRECTION_SOUTHWEST = 7;
    public static int DIRECTION_SOUTHEAST = 8;

    @Override
    public Model makeModel() {
        return new MineField();
    }

    @Override
    public View makeView(Model m) {
        return new MineFieldView((MineField)m);
    }

    public String[] getEditCommands() { return new String[] {"NW", "N", "NE", "W", "E", "SW", "S", "SE"}; }

    @Override
    public Command makeEditCommand(Model model, String type) {
        switch(type){
            case "N":
                return new MoveCommand(model, DIRECTION_NORTH);
            case "W":
                return new MoveCommand(model, DIRECTION_WEST);
            case "E":
                return new MoveCommand(model, DIRECTION_EAST);
            case "S":
                return new MoveCommand(model, DIRECTION_SOUTH);
            case "NW":
                return new MoveCommand(model, DIRECTION_NORTHWEST);
            case "NE":
                return new MoveCommand(model, DIRECTION_NORTHEAST);
            case "SW":
                return new MoveCommand(model, DIRECTION_SOUTHWEST);
            case "SE":
                return new MoveCommand(model, DIRECTION_SOUTHEAST);

        }

        return null;
    }

    public String getTitle() { return "MineField 1.0"; }

    public String[] getHelp() {
        return new String[] {"Click a direction to move.",
                "The number on a square indicates how many mines are located in the 8 adjacent squares.",
                "Start from the top left, and make your way to the bottom right to win!"};
    }

    public String about() {
        return "Minefield version 1.0. Copyright 2021 by CS151-S1 Team 5";
    }

}
