package edu.unca.csci312;

import java.util.Scanner;

public class Othello {



	/**
	 * asks the user if they would like to play again. if 'y' run game again. if 'n' end program
	 * @return playAgain - reruns the game if user enters 'y'
	 */
	private static boolean playAgain() {
		Scanner scan = new Scanner(System.in);
		System.out.println("C - Would you like to play again?");
		String userResponse = scan.nextLine();
		boolean playAgain = false;

		if (userResponse.matches("(?i)yes|y")) {
			playAgain = true;
		} else if (!userResponse.matches("(?i)no|n")) {
			System.out.println("C - Invalid input. Please try again.");
			playAgain = playAgain();
		}
		//scan.close();
		return playAgain;
	}
	/**
	 * creates gameboard and runs game
	 * @param args
	 */
	public static void main(String args[]) {
		boolean playGame = true;

		while (playGame) {
			Gameboard myGameBoard = new Gameboard();
			myGameBoard.initializePlayers();
			myGameBoard.printBoard();
			myGameBoard.run();
			playGame = playAgain();
		}
	}
}



// to do
/*
update disk counts after moves
put a star down for available moves - bug in available moves list
when a player is out of disks and has valid move then take a disk from other player
print agent letter col and row
*/