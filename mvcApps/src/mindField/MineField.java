package mindField;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import mvc.Model;

/*
 * Edits:
 *   Xuefeng     3/12/21: create file
 *   Xuefeng     3/14/21: edit
 *   Xuefeng     3/15/21: edit  modify logicField, change()
 *   Xuefeng     3/16/21: edit  finished `throw exceptions`
 *   Eric        3/17/21: fixed mine generation
 *   Xuefeng     3/19/21: debugging
 *   Eric        3/21/21: changed so no duplicate mines or mines on start/end points
 * 
 * */

public class MineField extends Model{

    private int rows;
    private int cols;
    public static double minePercentage = 0.05;
    private int num_mines = (int)((minePercentage/100) * rows * cols);
    private int [] cur_position;
    private int [] destination;
    private boolean [][] logicField;
    private boolean gameEnds;
    private boolean winner;
    private ArrayList <int []> visited;

    private boolean messageAlreadyDisplayed;

    public MineField () {

        rows = 10;
        cols = 10;

        num_mines = (int)((minePercentage) * rows * cols);
        System.out.println("num of mines: " + num_mines);

        destination = new int[] {rows-1, cols-1};
        cur_position = new int[] {0,0};
        visited = new ArrayList<int []>();
        visited.add(cur_position);
        gameEnds = false;
        winner = false;
        messageAlreadyDisplayed = false;

        // generate a minefield
        logicField = createNewField();
        fieldWithMines(num_mines);

    }

    private boolean [][] createNewField() {
        boolean[][] newField = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++ ) newField[i][j] = false;
        }
        return newField;
    }

    // generates mines randomly and puts them into the field
    private void fieldWithMines ( int numOfMines) {

        Random rand = new Random();
        int ranRow;
        int ranCol;

        // creates a list of all possible mine positions
        ArrayList<int[]> minePos = new ArrayList<int[]>();
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                minePos.add(new int[]{i,j});
            }
        }

        // removes starting point and finishing point as possible mine spots.
        minePos.remove(minePos.size()-1);
        minePos.remove(0);

        // generates mines using minePos
        for (int i = 0; i < numOfMines; i++){
            int index = rand.nextInt(minePos.size());
            ranRow = minePos.get(index)[0];
            ranCol = minePos.get(index)[1];
            minePos.remove(index);

            logicField [ranRow][ranCol] = true;
            System.out.println("mine position: " + ranRow + " , " + ranCol);
        }

    }

    private int [] new_position (int row_change, int col_change) {
        int cur_row = cur_position[0];
        int cur_col = cur_position[1];

        cur_row = cur_row + row_change;
        cur_col = cur_col + col_change;

        return new int [] {cur_row,cur_col};
    }

    private boolean argumentCheck (int [] nPosition) {
        // return false if new position does not pass the check

        int cur_x = nPosition[0];
        int cur_y = nPosition[1];

        // game status check
        if (gameEnds){
            return false;
        }

        // boundary check
        if (cur_x >= rows || cur_y >= cols || cur_x < 0 || cur_y < 0) try {
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            System.out.println("position out of bound");
            return false;
        }

        // mine check
        if (logicField[cur_x][cur_y]) try {
            throw new InterruptedException();
        } catch (InterruptedException ex){
            System.out.println("step on a mine!");
            System.out.println("mine position: " + cur_x + " , " + cur_y);  // for debug

            gameEnds = true;
            return true;        // return the mine position to view and stop the game
        }

        // destination check
        if (cur_x == destination[0] && cur_y == destination[1]) try {
            throw new InterruptedException(" You Won ");
        } catch (InterruptedException ex){
            System.out.println("WON!");
            gameEnds = true;
            winner = true;
            return false;
        }
        return true;
    }


    /**
     *  command_in => logicField update, cur_location update, presentationField update
     */
    public void change(int x_change, int y_change) {

        // convert to a {x,y} format
        int[] nPosition = new_position(x_change,y_change);

        // do the game check
        if (gameEnds == false && argumentCheck(nPosition)){
            // if passed update the current position, and record the path.
            cur_position = nPosition;
            visited.add(cur_position);
            changed();
        }
    }

    public ArrayList<int[]> getVisited () {
        return visited;
    }

    public boolean [][] getLogicField() { return logicField;}

    public int[] getFieldSize () {
        return new int[]{rows, cols};
    }

    public int [] getDestination() { return destination;}

    public boolean getGameStatus() { return gameEnds;}

    public boolean isWinner(){ return winner;}

    public void setMessageAlreadyDisplayed(boolean b){
        messageAlreadyDisplayed = b;
    }
    public boolean getMessageAlreadyDisplayed(){
        return messageAlreadyDisplayed;
    }

    @Override
    public Color getColor() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "This is a minefield";
    }










}