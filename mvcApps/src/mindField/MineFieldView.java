package mindField;

import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.beans.PropertyChangeEvent;

import mvc.*;
/*
 * Edits:
 *   Xuefeng     3/12/21: create file
 *   Mark		 3/15/21: implement class
 *   Mark		 3/16/21: Update class to work with changes to MineField class
 * 	 Xuefeng     3/19/21: debugging (fix the row/col corresponding to x/y problem - found in general testing)
 * */
public class MineFieldView extends View{
	
	private JPanel[][] spaces; //array of pointers to panels in their position
	private int[][] spaceNumbers;
	private JPanel currentSpace; //the highlighted space to represent player position
	
	public MineFieldView(MineField m) {
		super(m);
		boolean[][] field = m.getLogicField();
		spaces = new JPanel[field.length][field[0].length];
		spaceNumbers = new int[field.length][field[0].length];
		setLayout(new GridLayout(field.length, field[0].length));
		//initialize panels
		for(int i = 0; i < field.length; i++) {
			for(int j = 0; j < field[i].length; j++) {
				spaces[i][j] = new JPanel();
				spaces[i][j].setBackground(Color.GRAY);
				
				//mark squares
				JLabel label = new JLabel();
				label.setText("?");
				spaces[i][j].add(label);
				
				//create outlines
				spaces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				add(spaces[i][j]);
				
				//if this space is a mine, incrememnt adjacent spaceNumbers squares
				if(field[i][j]) {
					for(int k = i-1; k <= i+1; k++) {
						for(int l = j-1; l <= j+1; l++) {
							if(k >= 0 && k < field.length && l >= 0 && l < field[i].length && !( k == i && l == j)) {
								//if space is inside playing field, increase count
								spaceNumbers[k][l]++;
							}
						}
					}
				}
			}
		}
		int[] loc = m.getVisited().get(0);
		currentSpace = spaces[loc[1]][loc[0]];
		m.addPropertyChangeListener(this);
	}
	
	//TODO: redraw prev position as white border
	public void paintComponent(Graphics gc) {
		super.paintComponent(gc);
		MineField mf = (MineField)model;
		boolean[][] field = mf.getLogicField(); //array of mines
		int[] position = mf.getVisited().get(mf.getVisited().size()-1); //player's current x and y coordinates
		int x = position[1];
		int y = position[0];
		
		//update every panel
		for(int i = 0; i < field.length; i++) { // i = y position
			for(int j = 0; j < field[i].length; j++) { // j = x position
				JLabel label = (JLabel)spaces[i][j].getComponent(0);


				//draw squares that have been visited differently from other squares
				if(isSpaceVisited(i, j)) {
					if(field[i][j]) {
						label.setText("X");
					}else {
						label.setText("" + spaceNumbers[i][j]);
					}
					//border
					Color prev = new Color(0.75f, 0.75f, 0.75f);
					spaces[i][j].setBorder(BorderFactory.createLineBorder(prev));
				}else{
					//space not yet visited
					label.setText("?");

					//border
					spaces[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}

			}
		}
		//draw border on current space
		spaces[y][x].setBorder(BorderFactory.createLineBorder(Color.WHITE));
		//draw border on endpoint
		int[] end = mf.getDestination();
		spaces[end[1]][end[0]].setBorder(BorderFactory.createLineBorder(Color.GREEN));
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
	}
	
	private boolean isSpaceVisited(int x, int y) {
		ArrayList<int[]> squaresVisited = ((MineField)model).getVisited();
		for(int[] square:squaresVisited) {
			if(square[1] == y && square[0] == x) {
				return true;
			}
		}
		return false;
	}
}