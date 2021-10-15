package edu.unca.csci312;

import java.util.ArrayList;
import java.util.Scanner;

public class Gameboard {

	Player opponentPlayer;
	Player agentPlayer;

	Cell[][] currentBoard = new Cell[8][8];


	// Constructor - defines a game board
	public Gameboard() {
		setGameboard();
	}
	/**
	 * creates a gameboard
	 */
	private void setGameboard() {
		//int mineCount = 0;

		// Initialize each cell on 8x8 grid with a dash
		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[i].length; j++) {
				currentBoard[i][j] = new Cell("-", i, j);
			}
		}

		currentBoard[3][3].setCellState("W");
		currentBoard[4][4].setCellState("W");
		currentBoard[3][4].setCellState("B");
		currentBoard[4][3].setCellState("B");


	}

	protected String initializePlayers(){
		Scanner scan = new Scanner(System.in);
		String userInput = null;
		System.out.println("C - Please enter what color the program will be");

		try {
			userInput = scan.nextLine();
			if (!userInput.equals("I B") && !userInput.equals("I W"))
				throw new Exception("C - Invalid initialization");
		} catch (Exception e) {
			System.out.println("C - Invalid input. Please try again.");
			initializePlayers();
		}

		if(userInput.equals("I B")) {
			agentPlayer = new Player("B",30);
			opponentPlayer = new Player("W",30);
			System.out.println("R B");

		} else {
			agentPlayer = new Player("W",30);
			opponentPlayer = new Player("B",30);
			System.out.println("R W");
			}
			
		//scan.close();
		return userInput;
	}

	private boolean validMoveCheck(Player curPlayer, Cell playerMove) {

		//System.out.println("C - validMoveCheck: " + "row: " + playerMove.getRow() + " col: "+ playerMove.getColumn());

		int opponentDiskCount = 0;
		boolean validMove = false;
		boolean checkRight = true;
		boolean checkLeft = true;
		boolean checkUp = true;
		boolean checkDown = true;
		boolean checkNW = true;
		boolean checkNE = true;
		boolean checkSW = true;
		boolean checkSE = true;
		int i,j=0;


		// check right
		i = playerMove.getColumn()+1;

		while(checkRight && i <= 7){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals("-") || currentBoard[playerMove.getRow()][i].getCellState().equals("*")){ 
				checkRight = false;
			} else {
				if(!currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
					i++;
					opponentDiskCount++;
				} else {
					checkRight = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i <= 7){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
				//System.out.println("C - Moving right");
				return validMove = true;
			}
		}

		// check left
		i = playerMove.getColumn()-1;
		opponentDiskCount = 0;

		while(checkLeft && i >= 0){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals("-") || currentBoard[playerMove.getRow()][i].getCellState().equals("*")){ 
				checkLeft = false;
			} else {
				if(!currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
					i--;
					opponentDiskCount++;
				} else {
					checkLeft = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i >= 0){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
				//System.out.println("C - Moving left");
				return validMove = true;
			}
		}


		// check up
		i = playerMove.getRow()-1;
		opponentDiskCount = 0;

		while(checkUp && i >= 0){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals("-") || currentBoard[i][playerMove.getColumn()].getCellState().equals("*")){ 
				checkUp = false;
			} else {
				if(!currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
					i--;
					opponentDiskCount++;
				} else {
					checkUp = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i >= 0){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
				//System.out.println("C - Moving up");
				return validMove = true;			}
		}

		// check down
		i = playerMove.getRow()+1;
		opponentDiskCount = 0;

		while(checkDown && i <= 7){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals("-") || currentBoard[i][playerMove.getColumn()].getCellState().equals("*")){ 
				checkDown = false;
			} else {
				if(!currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
					i++;
					opponentDiskCount++;
				} else {
					checkDown = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i <= 7){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
				//System.out.println("C - Moving down");
				return validMove = true;			}
		}
		
		// check NW
		i = playerMove.getRow()-1;
		j = playerMove.getColumn()-1;
		opponentDiskCount = 0;

		while(checkNW && i >= 0 && j >= 0){
			if(currentBoard[i][j].getCellState().equals("-") || currentBoard[i][j].getCellState().equals("*")){
				checkNW = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i--;
					j--;
					opponentDiskCount++;
			} 	else {
				checkNW = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i >= 0 && j >=0){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				//System.out.println("C - Moving NW");
				return validMove = true;
			}
		}


		// check NE
		i = playerMove.getRow()-1;
		j = playerMove.getColumn()+1;
		opponentDiskCount = 0;

		while(checkNE && i >= 0 && j <= 7){
			if(currentBoard[i][j].getCellState().equals("-") || currentBoard[i][j].getCellState().equals("*")){
				checkNE = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i--;
					j++;
					opponentDiskCount++;
			} 	else {
				checkNE = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i >= 0 && j <= 7){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				//System.out.println("C - Moving NE");
				return validMove = true;
			}
		}


		// check SW
		i = playerMove.getRow()+1;
		j = playerMove.getColumn()-1;
		opponentDiskCount = 0;

		while(checkSW && i <= 7 && j >= 0){
			if(currentBoard[i][j].getCellState().equals("-") || currentBoard[i][j].getCellState().equals("*")){
				checkSW = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i++;
					j--;
					opponentDiskCount++;
			} 	else {
				checkSW = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i <= 7 && j >= 0){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				//System.out.println("C - Moving SW");
				return validMove = true;			}
		}


		// check SE
		i = playerMove.getRow()+1;
		j = playerMove.getColumn()+1;
		opponentDiskCount = 0;

		while(checkSE && i <= 7 && j <= 7){
			if(currentBoard[i][j].getCellState().equals("-") || currentBoard[i][j].getCellState().equals("*")){
				checkSE = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i++;
					j++;
					opponentDiskCount++;
			} 	else {
				checkSE = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i <= 7 && j <= 7){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				//System.out.println("C - Moving SE");
				return validMove = true;			
			}
		}


		return validMove;
	}


	private void flipDisks(Player curPlayer, Cell playerMove) {

		//System.out.println("C - flipDisks: " + "row: " + playerMove.getRow() + " col: "+ playerMove.getColumn());

		int opponentDiskCount = 0;
		boolean validMove = false;
		boolean checkRight = true;
		boolean checkLeft = true;
		boolean checkUp = true;
		boolean checkDown = true;
		boolean checkNW = true;
		boolean checkNE = true;
		boolean checkSW = true;
		boolean checkSE = true;
		int i,j=0;

		// flipping right
		i = playerMove.getColumn()+1;

		while(checkRight && i <= 7){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals("-")){ 
				checkRight = false;
			} else {
				if(!currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
					i++;
					opponentDiskCount++;
				} else {
					checkRight = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i <= 7){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
				i = playerMove.getColumn()+1;
				while(opponentDiskCount != 0){
					currentBoard[playerMove.getRow()][i].setCellState(curPlayer.getColor());
					i++;
					opponentDiskCount--;
				}
				//System.out.println("C - Flipped right");
			}
		}
		

		// flipping left
		i = playerMove.getColumn()-1;
		opponentDiskCount = 0;

		while(checkLeft && i >= 0){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals("-")){ 
				checkLeft = false;
			} else {
				if(!currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
					i--;
					opponentDiskCount++;
				} else {
					checkLeft = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i >= 0){
			if(currentBoard[playerMove.getRow()][i].getCellState().equals(curPlayer.getColor())) {
				i = playerMove.getColumn()-1;
				while(opponentDiskCount != 0){
					currentBoard[playerMove.getRow()][i].setCellState(curPlayer.getColor());
					i--;
					opponentDiskCount--;
				}
				//System.out.println("C - flipping left");
			}
		}

		
		// flipping up
		i = playerMove.getRow()-1;
		opponentDiskCount = 0;

		while(checkUp && i >= 0){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals("-")){ 
				checkUp = false;
			} else {
				if(!currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
					i--;
					opponentDiskCount++;
				} else {
					checkUp = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i >= 0){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
				i = playerMove.getRow()-1;
				while(opponentDiskCount != 0){
					currentBoard[i][playerMove.getColumn()].setCellState(curPlayer.getColor());
					i--;
					opponentDiskCount--;
				}

				//System.out.println("C - flipping up");
			}
		}

		
		// check down
		i = playerMove.getRow()+1;
		opponentDiskCount = 0;

		while(checkDown && i <= 7){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals("-")){ 
				checkDown = false;
			} else {
				if(!currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
					i++;
					opponentDiskCount++;
				} else {
					checkDown = false;
				}	
			}
		}

		if(opponentDiskCount > 0 && i <= 7){
			if(currentBoard[i][playerMove.getColumn()].getCellState().equals(curPlayer.getColor())) {
				i = playerMove.getRow()+1;
				while(opponentDiskCount != 0){
					currentBoard[i][playerMove.getColumn()].setCellState(curPlayer.getColor());
					i++;
					opponentDiskCount--;
				}
				//System.out.println("C - flipping down");

			}
		}
		
		
		// flip NW
		i = playerMove.getRow()-1;
		j = playerMove.getColumn()-1;
		opponentDiskCount = 0;

		while(checkNW && i >= 0 && j >= 0){
			if(currentBoard[i][j].getCellState().equals("-")){
				checkNW = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i--;
					j--;
					opponentDiskCount++;
			} 	else {
				checkNW = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i >= 0 && j >=0){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				i = playerMove.getRow()-1;
				j = playerMove.getColumn()-1;
				while(opponentDiskCount != 0){
					currentBoard[i][j].setCellState(curPlayer.getColor());
					i--;
					j--;
					opponentDiskCount--;
				}
				//System.out.println("C - flipping NW");

			}
		}

		
		// check NE
		i = playerMove.getRow()-1;
		j = playerMove.getColumn()+1;
		opponentDiskCount = 0;

		while(checkNE && i >= 0 && j <= 7){
			if(currentBoard[i][j].getCellState().equals("-")){
				checkNE = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i--;
					j++;
					opponentDiskCount++;
			} 	else {
				checkNE = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i >= 0 && j <= 7){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				i = playerMove.getRow()-1;
				j = playerMove.getColumn()+1;
				while(opponentDiskCount != 0){
					currentBoard[i][j].setCellState(curPlayer.getColor());
					i--;
					j++;
					opponentDiskCount--;
				}
				//System.out.println("C - flipping NE");

			}
		}

		
		// check SW
		i = playerMove.getRow()+1;
		j = playerMove.getColumn()-1;
		opponentDiskCount = 0;

		while(checkSW && i <= 7 && j >= 0){
			if(currentBoard[i][j].getCellState().equals("-")){
				checkSW = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i++;
					j--;
					opponentDiskCount++;
			} 	else {
				checkSW = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i <= 7 && j >= 0){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				i = playerMove.getRow()+1;
				j = playerMove.getColumn()-1;
				while(opponentDiskCount != 0){
					currentBoard[i][j].setCellState(curPlayer.getColor());
					i--;
					j++;
					opponentDiskCount--;
				}
				//System.out.println("C - flipping SW");
			}
		}

		
		// check SE
		i = playerMove.getRow()+1;
		j = playerMove.getColumn()+1;
		opponentDiskCount = 0;

		while(checkSE && i <= 7 && j <= 7){
			if(currentBoard[i][j].getCellState().equals("-")){
				checkSE = false;
			} else { 
				if(!currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
					i++;
					j++;
					opponentDiskCount++;
			} 	else {
				checkSE = false;
				}
			}	
		}

		if(opponentDiskCount > 0 && i <= 7 && j <= 7){
			if(currentBoard[i][j].getCellState().equals(curPlayer.getColor())){
				i = playerMove.getRow()+1;
				j = playerMove.getColumn()+1;
				while(opponentDiskCount != 0){
					currentBoard[i][j].setCellState(curPlayer.getColor());
					i++;
					j++;
					opponentDiskCount--;
				}
				//System.out.println("C - flipping SE");
			}
		}

		
	}



	private Cell getMyMove(){
		Scanner scan = new Scanner(System.in);
		String userInput = null;
		Cell curCell = new Cell("-", 0, 0);

		System.out.println();
		System.out.println("C - Enter where you would like to move: ");
		
		try {
			userInput = scan.nextLine();
			if (!userInput.startsWith(opponentPlayer.getColor()))
				throw new Exception("C - Invalid color");
		} catch (Exception e) {
			System.out.println("C - Invalid color. Please try again.");
			return getMyMove();
		}

		// opponent passes
		try {
			if (userInput.equals(opponentPlayer.getColor())) {
				if (getAvailableMoves(opponentPlayer).isEmpty()) {
					return null;
				} else {
					throw new Exception("C - Invalid input");
				}
			} 
		} catch (Exception e) {
			System.out.println("C - There are available moves, try again.");
			return getMyMove();
		}

		char col = userInput.charAt(2);
		System.out.println("C - Column: " + col);
		curCell.setCellState(opponentPlayer.getColor());

		try {
			if (col != 'a' && col != 'b' && col != 'c' && col != 'd' && col != 'e' && col != 'f' && col != 'g' && col != 'h')
				throw new Exception("C - Invalid column");
		} catch (Exception e) {
			System.out.println("C - Invalid column. Please try again.");
			return getMyMove();
		}
		switch(col){
			case 'a':
				curCell.setColumn(0);
				break;
			case 'b':
				curCell.setColumn(1);
				break;
			case 'c':
				curCell.setColumn(2);
				break;
			case 'd':
				curCell.setColumn(3);
				break;
			case 'e':
				curCell.setColumn(4);
				break;
			case 'f':
				curCell.setColumn(5);
				break;
			case 'g':
				curCell.setColumn(6);
				break;
			case 'h':
				curCell.setColumn(7);
				break;
		}

		char row = userInput.charAt(4);
		System.out.println("C - Row " + row);
		System.out.println(opponentPlayer.getColor() + " " + col + " " + row);

		try {
			if (row != '1' && row != '2' && row != '3' && row != '4' && row != '5' && row != '6' && row != '7' && row != '8')
				throw new Exception("Invalid row");
		} catch (Exception e) {
			System.out.println("C - Invalid row. Please try again.");
			return getMyMove();
		}
		curCell.setRow(Character.getNumericValue(row)-1);
		System.out.println("C - Current board cell state: " + currentBoard[curCell.getRow()][curCell.getColumn()].getCellState());
		try {
			if(currentBoard[curCell.getRow()][curCell.getColumn()].getCellState().equals("B") || currentBoard[curCell.getRow()][curCell.getColumn()].getCellState().equals("W")){
				throw new Exception("C - Cell already contains a piece");
			}
		} catch (Exception e) {
			System.out.println("C - Invalid move. Cell already contains a piece. Please try again.");
			return getMyMove();
		}


		//scan.close();
		System.out.println("C - ***User input: " + userInput);
		return curCell;
	}



	private Cell getAgentMove() {

		Cell agentCell = new Cell(agentPlayer.getColor(),0,0);


		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[i].length; j++) {
					if (currentBoard[i][j].getCellState().equals("-")) {
						agentCell.setRow(i);
						agentCell.setColumn(j);
						if(validMoveCheck(agentPlayer, agentCell)){
							//System.out.println("C - Agent moved here: row: "+ agentCell.getRow() + " col: " + agentCell.getColumn());
							System.out.println();
							System.out.println(agentPlayer.getColor() + " " + agentCell.getColumn() + " " + agentCell.getRow());
							return agentCell;
						} 
					}
			}
		}
		// no moves available for the agent, passing
		return null;
	}


	private ArrayList<Cell> getAvailableMoves(Player p) {

		Cell c = new Cell(p.getColor(),0,0);
		ArrayList<Cell> availableMoves = new ArrayList<Cell>();

		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[i].length; j++) {
					if (currentBoard[i][j].getCellState().equals("-")) {
						c.setRow(i);
						c.setColumn(j);
						if(validMoveCheck(p, c)){
							//System.out.println("C - Added to available moves: row: "+ c.getRow() + " col: " + c.getColumn());
							//System.out.println(p.getColor() + " " + c.getColumn() + " " + c.getRow());
							availableMoves.add(c);
							//currentBoard[i][j].setCellState("*");
						}
					}
			}
		}
		
		return availableMoves;
	}

	private void showHumanMoves(Player p) {

		Cell c = new Cell(p.getColor(),0,0);

		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[i].length; j++) {
				if (currentBoard[i][j].getCellState().equals("-")) {
					c.setRow(i);
					c.setColumn(j);
					if(validMoveCheck(p, c)){
						currentBoard[i][j].setCellState("*");
					}
				}

			}
		}
	}

	private void clearStars(){

		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[i].length; j++) {
				if (currentBoard[i][j].getCellState().equals("*")) {
						currentBoard[i][j].setCellState("-");
				}
			}
		}
	}



	private void updateBoard(Cell c) {

		//System.out.println("C - updateBoard - cell state: " + c.getCellState() + " col: " + c.getColumn() + " row: " + c.getRow());

		currentBoard[c.getRow()][c.getColumn()].setCellState(c.getCellState());


	}


	/**
	 * chekcs to see if the user has won the game
	 * @return unknownCellsCount
	 */
	
	 private boolean checkWinner() {
		int diskCount = 0;
		int b = 0;
		int w = 0;
		boolean gameOver = false;
		
		for (int i = 0; i < currentBoard.length; i++) {
			for (int j = 0; j < currentBoard[i].length; j++) {
				if (currentBoard[i][j].getCellState().equals("B") || currentBoard[i][j].getCellState().equals("W")) {
					diskCount++;
					if(currentBoard[i][j].getCellState().equals("B")){
						b++;
					} else {
						w++;
					}
				}
			}
		}


		if((getAvailableMoves(opponentPlayer).isEmpty() && getAvailableMoves(agentPlayer).isEmpty()) || diskCount == 64){
			System.out.println("C - Game over");
		
			System.out.println("C - Black: " + b);
			System.out.println("C - White: " + w);

			if (b > w) {
				System.out.println("C - Black won!");
			} else {
				System.out.println("C - White won!");
			}

			System.out.println("C - Disk count: " + diskCount);

			gameOver = true;
		}

		return gameOver;
	}

	/**
	 * runs the game
	 */
	public void run() {

		boolean gameOn = true;
		boolean agentMove = false;

		if(agentPlayer.getColor().equals("B")){
			agentMove = true;
		} 

		while (gameOn) {

			// agent players move
			if (agentMove) {
				Cell a = getAgentMove();
				if (a == null) {
					System.out.println("C - Agent passes");
					System.out.println(agentPlayer.getColor());
				} else {
					flipDisks(agentPlayer, a);
					updateBoard(a);
					agentPlayer.setDiskNum(agentPlayer.getDiskNum() - 1);
				}
				agentMove = false;
				showHumanMoves(opponentPlayer);
				printBoard();
			} else {

				// opponent players move
				Cell c = getMyMove();
				if (c == null) {
					System.out.println("C - Opponent passes");
					System.out.println(opponentPlayer.getColor());
				} else {
					flipDisks(opponentPlayer, c);
					updateBoard(c);
					opponentPlayer.setDiskNum(opponentPlayer.getDiskNum() - 1);
				}
				agentMove = true;
				clearStars();
				printBoard();
			}

			if (gameOn && checkWinner()) {
				gameOn = false;
			
				System.out.println("C - You have WON the world othello championship!! Congratulations!!!!!.");
			}

			if (gameOn) {
				//printBoard();
			}
		}

	}

	public void printBoard(){

		System.out.print("C-   ");//beginning 2 spaces
		for(int i = 0; i < currentBoard.length; ++i)
		   System.out.print(" " + (char)(i + 'A') +"  ");//print letters seperately.
		
		for(int i = 0; i < currentBoard.length; i++){
			for(int j = 0; j < currentBoard.length; j++){
				//currentBoard[i][j];  
			}
		}
		
		for(int i = 0; i < currentBoard.length; i++){
			System.out.println();
			for(int j = 0; j < currentBoard.length; j++){
				if(j == 0){
					System.out.print("C- ");
					System.out.print(i + 1);
					System.out.print("| ");
				}
				System.out.print(currentBoard[i][j] + " | ");  
			}
		}
		}
}
