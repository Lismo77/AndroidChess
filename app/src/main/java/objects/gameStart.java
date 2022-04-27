package objects;

import java.util.Scanner;

public class gameStart {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String WHITE = "White";
		String BLACK = "Black";
		String turn = WHITE;
		String winner = "";
		
		ChessBoard gameBoard = new ChessBoard();
		boolean gameOver = false;
		boolean illegalMove = false;
		
		while(gameOver == false) {
			if (!illegalMove)
				System.out.println(gameBoard.toString() + "\n");
			System.out.print(turn + "'s move: ");
			String nextMove = s.nextLine();
			
			if (nextMove.equals("resign")) {
				gameOver = true;
				if (turn.equals(WHITE)) {
					winner = BLACK;
				} else {
					winner = WHITE;
				}
				
			} else {
				int move = gameBoard.attemptMove(gameBoard.toMove(nextMove));
				if (move == -1) {
					System.out.println("Illegal move, try again");
					illegalMove = true;
				} else {
					illegalMove = false;
					if (move == 1) {
						winner = "draw";
						gameOver = true;
					} else {
						gameOver = gameBoard.checkmate(turn);
						if (gameOver) {
							System.out.println("Checkmate");
							winner = turn;
						} else
							System.out.println();
						if (turn == WHITE) {
							gameBoard.turn = BLACK;
							turn = BLACK;
						} else {
							gameBoard.turn = WHITE;
							turn = WHITE;
						}
					}
				}
			}
			
			
		}
		if (winner == "draw") {
			System.out.println(winner);
		} else {
			System.out.println(winner + " wins");
		}
		

	}

}
