package com.example.chess.objects;

import android.util.Log;

import java.util.ArrayList;

/**
 * @author Liam Clarke and Manav Mistry
 * ChessBoard object populated by spaces that either contain pieces or nothing.
 */
public class ChessBoard {

	static String WHITE = "White";
	static String BLACK = "Black";
	public Space board[][] = new Space[8][8];
	public ArrayList<Piece> whitePieces = new ArrayList<>();
	public ArrayList<Piece> blackPieces = new ArrayList<>();
	public String turn = WHITE;
	
	public ChessBoard(ChessBoard another) {
		this.board = another.board;
		this.whitePieces = another.whitePieces;
		this.blackPieces = another.blackPieces;
		this.turn = another.turn;
	}
	
	/**
	 * ChessBoard constructor. Creates empty spaces and populates board with pieces in starting positions
	 */
	public ChessBoard() {
		
		//creating the spaces on the chess board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				char column = 'a';
				switch (j) {
				case 0 :
					column = 'a';
					break;
				case 1 :
					column = 'b';
					break;
				case 2 :
					column = 'c';
					break;
				case 3 :
					column = 'd';
					break;
				case 4 :
					column = 'e';
					break;
				case 5 :
					column = 'f';
					break;
				case 6 :
					column = 'g';
					break;
				case 7 :
					column = 'h';
					break;
				}
				if ((i + j) % 2 == 0) {
					board[i][j] = new Space(8 - i, column, false);
				} else {
					board[i][j] = new Space(8 - i, column, true);
				}
			}
		}
		
		//setting up the black pieces.
		String clr = "Black";
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				Piece newPiece = null;
				if (i == 1) {
					newPiece = new Pawn(clr, board[i][j]);
				} else {
					switch (j) {
					case 0 :
						newPiece = new Rook(clr, board[i][j]);
						break;
					case 1 : 
						newPiece = new Knight(clr, board[i][j]);
						break;
					case 2 : 
						newPiece = new Bishop(clr, board[i][j]);
						break;
					case 3 : 
						newPiece = new Queen(clr, board[i][j]);
						break;
					case 4 : 
						newPiece = new King(clr, board[i][j]);
						break;
					case 5 : 
						newPiece = new Bishop(clr, board[i][j]);
						break;
					case 6 : 
						newPiece = new Knight(clr, board[i][j]);
						break;
					case 7 : 
						newPiece = new Rook(clr, board[i][j]);
						break;
					}
				}
				blackPieces.add(newPiece);
				board[i][j].setPiece(newPiece);
			}
		}
		
		//setting up the white pieces
		clr = "White";
		for (int i = 6; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece newPiece = null;
				if (i == 6) {
					newPiece = new Pawn(clr, board[i][j]);
				} else {
					switch (j) {
					case 0 :
						newPiece = new Rook(clr, board[i][j]);
						break;
					case 1 : 
						newPiece = new Knight(clr, board[i][j]);
						break;
					case 2 :
						newPiece = new Bishop(clr, board[i][j]);
						break;
					case 3 : 
						newPiece = new Queen(clr, board[i][j]);
						break;
					case 4 : 
						newPiece = new King(clr, board[i][j]);
						break;
					case 5 :
						newPiece = new Bishop(clr, board[i][j]);
						break;
					case 6 :
						newPiece = new Knight(clr, board[i][j]);
						break;
					case 7 : 
						newPiece = new Rook(clr, board[i][j]);
						break;
					}
				}
				whitePieces.add(newPiece);
				board[i][j].setPiece(newPiece);
			}
		}
	}

	/**
	 * @param color
	 * @return boolean value for whether or not the king is in check
	 */
	public boolean kingCheck(String color) {
		Piece king = null;
		String enemyColor;
		if (color == BLACK) {
			enemyColor = WHITE;
		} else {
			enemyColor = BLACK;
		}
		int kingRow = -1;
		int kingColumn = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].getPiece() != null) {
					if (board[i][j].getPiece().getColor() == color && board[i][j].getPiece().getType() == "king") {		// finds the king
						king = board[i][j].getPiece();		
						kingRow = i;
						kingColumn = j;
					}
				}
			}
		}
		
		int checkRow = 0;
		int checkColumn = 0;
		int multiplier = 1;
		boolean foundPiece = false;
		
		//checks for queens and bishops
		for (int i = 0; i < 4; i++) {
			foundPiece = false;
			multiplier = 1;
			switch(i) {
			case 0 :
				checkRow = -1;
				checkColumn = -1;
				break;
			case 1 :
				checkRow = -1;
				checkColumn = 1;
				break;
			case 2 :
				checkRow = 1;
				checkColumn = 1;
				break;
			case 3 :
				checkRow = 1;
				checkColumn = -1;
				break;
			}
			while ((!foundPiece) && kingRow + (multiplier * checkRow) >= 0 && kingRow + (multiplier * checkRow) <= 7 &&
					kingColumn + (multiplier * checkColumn) >= 0 && kingColumn + (multiplier * checkColumn) <= 7) {
				int rindex = kingRow + (checkRow * multiplier);
				int cindex = kingColumn + (checkColumn * multiplier);
				if (board[rindex][cindex].getPiece() != null) {
					foundPiece = true;
					if (board[rindex][cindex].getPiece().getColor().equals(enemyColor) && 
							(board[rindex][cindex].getPiece().getType().equals("queen") || 
							board[rindex][cindex].getPiece().getType().equals("bishop"))) {
						return true;
					}
				}
				multiplier++;
			}
		}
		
		//checks for queens and rooks
		for (int i = 0; i < 8; i++) {
			foundPiece = false;
			multiplier = 1;
			switch(i) {
			case 0 :
				checkRow = -1;
				checkColumn = 0;
				break;
			case 1 :
				checkRow = 0;
				checkColumn = 1;
				break;
			case 2 :
				checkRow = 1;
				checkColumn = 0;
				break;
			case 3 :
				checkRow = 0;
				checkColumn = -1;
				break;
			}
			while ((!foundPiece) && kingRow + (multiplier * checkRow) >= 0 && kingRow + (multiplier * checkRow) <= 7 &&
					kingColumn + (multiplier * checkColumn) >= 0 && kingColumn + (multiplier * checkColumn) <= 7) {
				int rindex = kingRow + (checkRow * multiplier);
				int cindex = kingColumn + (checkColumn * multiplier);
				if (board[rindex][cindex].getPiece() != null) {
					foundPiece = true;
					if (board[rindex][cindex].getPiece().getColor().equals(enemyColor) && 
							(board[rindex][cindex].getPiece().getType().equals("queen") || 
							board[rindex][cindex].getPiece().getType().equals("rook"))) {
						return true;
					}
				}
				multiplier++;
			}
		}
		
		//checks for knights
		for (int i = 0; i < 4; i++) {
			switch(i) {
			case 0 :
				checkRow = -1;
				checkColumn = -2;
				break;
			case 1 :
				checkRow = -2;
				checkColumn = -1;
				break;
			case 2 :
				checkRow = -2;
				checkColumn = 1;
				break;
			case 3 :
				checkRow = -1;
				checkColumn = 2;
				break;
			case 4 :
				checkRow = 1;
				checkColumn = 2;
				break;
			case 5 :
				checkRow = 2;
				checkColumn = 1;
				break;
			case 6 :
				checkRow = 2;
				checkColumn = -1;
				break;
			case 7 :
				checkRow = 1;
				checkColumn = -2;
				break;
			}
			int rindex = kingRow + checkRow;
			int cindex = kingColumn + checkColumn;
			if (kingRow + rindex >= 0 && kingRow + rindex <= 7 && kingColumn + cindex >= 0 && kingColumn + cindex <= 7) {
				if (board[rindex][cindex].getPiece() != null) {
					foundPiece = true;
					if (board[rindex][cindex].getPiece().getColor().equals(enemyColor) && 
							board[rindex][cindex].getPiece().getType().equals("knight")) {
						return true;
					}
				}
			}
		}
		
		//checks for pawns
		for (int i = 0; i < 4; i++) {
			if (enemyColor == BLACK) {
				switch(i) {
				case 0 :
					checkRow = -1;
					checkColumn = -1;
					break;
				case 1 :
					checkRow = -1;
					checkColumn = 1;
					break;
				case 2 :
					checkRow = 0;
					checkColumn = -1;
					break;
				case 3 :
					checkRow = 0;
					checkColumn = 1;
					break;
				}
			} else {
				switch(i) {
				case 0 :
					checkRow = 1;
					checkColumn = -1;
					break;
				case 1 :
					checkRow = 1;
					checkColumn = 1;
					break;
				case 2 :
					checkRow = 0;
					checkColumn = -1;
					break;
				case 3 :
					checkRow = 0;
					checkColumn = 1;
					break;
				}
			}
			int rindex = kingRow + checkRow;
			int cindex = kingColumn + checkColumn;
			if (kingRow + rindex >= 0 && kingRow + rindex <= 7 && kingColumn + cindex >= 0 && kingColumn + cindex <= 7) {
				if (board[rindex][cindex].getPiece() != null) {
					if (board[rindex][cindex].getPiece().getColor().equals(enemyColor) && 
							board[rindex][cindex].getPiece().getType().equals("pawn")) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @return true if checkmate.
	 */
	public boolean checkmate(String color) {
		if (color == WHITE) {
			for (int i = 0; i < blackPieces.size(); i++) {
				if (blackPieces.get(i).hasMoves(this))
					return false;
			}
		} else {
			for (int i = 0; i < whitePieces.size(); i++) {
				if (whitePieces.get(i).hasMoves(this))
					return false;
			}
		}
		return true;
		
	}
	
	/**
	 * @param moveArr takes in array of spaces as move.
	 * @return returns an int that indicates successfulness of move.
	 */
	public int attemptMove(Space[] moveArr) {
		if (moveArr == null) {
			return -1;
		} else {
			if (moveArr.length == 3)				//if draw is requested
				return 1;
			Space start = moveArr[0];
			Space dest = moveArr[1];
			Piece pce = start.getPiece();
			if (pce == null)						//if there is not a piece in starting position
				return -1;
			if (pce.getColor() != this.turn) {		//if the piece being moved is not the right color
				return -1;
			}
			boolean valid = pce.move(this, moveArr);
			if (valid) {
				if (this.turn == WHITE)
					this.turn = BLACK;
				else
					this.turn = WHITE;
				return 0;
			} else
				return -1;
		}
	}
	
	/**
	 * @param input String taken in from scanner during game.
	 * @return move in format Space array containing start and destination.
	 */
	public Space[] toMove(String input) {
		String move[] = input.split(" ");
		if (move.length != 2 && move.length != 3) {
			return null;
		}
		if (move[0].length() != 2 || move[1].length() != 2) {
			return null;
		}
		char column1letter = move[0].charAt(0);
		char column2letter = move[1].charAt(0);
		int column1 = -1;
		int column2 = -1;
		
		switch(column1letter) {
		case 'a' :
			column1 = 0;
			break;
		case 'b' :
			column1 = 1;
			break;
		case 'c' :
			column1 = 2;
			break;
		case 'd' :
			column1 = 3;
			break;
		case 'e' :
			column1 = 4;
			break;
		case 'f' :
			column1 = 5;
			break;
		case 'g' :
			column1 = 6;
			break;
		case 'h' :
			column1 = 7;
			break;
		default: 
			return null;
		}
		
		switch(column2letter) {
		case 'a' :
			column2 = 0;
			break;
		case 'b' :
			column2 = 1;
			break;
		case 'c' :
			column2 = 2;
			break;
		case 'd' :
			column2 = 3;
			break;
		case 'e' :
			column2 = 4;
			break;
		case 'f' :
			column2 = 5;
			break;
		case 'g' :
			column2 = 6;
			break;
		case 'h' :
			column2 = 7;
			break;
		default: 
			return null;
		}
		
		int row1 = -1;
		int row2 = -1;
		char row1number = move[0].charAt(1);
		char row2number = move[1].charAt(1);
		
		if (Character.isDigit(row1number) && Character.isDigit(row2number)) {
			row1 = 8 - Integer.parseInt(move[0].substring(1));
			row2 = 8 - Integer.parseInt(move[1].substring(1));
			if (row1 < 0 || row2 < 0)
				return null;
		} else
			return null;
		
		if (move.length == 3) {
			if (!move[2].equals("draw?"))
				return null;
			Space finalMove[] = {board[row1][column1], board[row2][column2], new Space(-1, 'x', false)};
			return finalMove;
		} else {
			Space finalMove[] = {board[row1][column1], board[row2][column2]};
			return finalMove;
		}
	}

	/**
	 * toString method for ChessBoard class.
	 * @return formatted chess board object with all positions filled in.
	 */
	@Override
	public String toString() {
		return board[0][0] + " " + board[0][1] + " " + board[0][2] + " " + board[0][3] + " " + board[0][4] + " " + board[0][5] + " " + board[0][6] + " " + board[0][7] + " 8\r\n"
				+ board[1][0] + " " + board[1][1] + " " + board[1][2] + " " + board[1][3] + " " + board[1][4] + " " + board[1][5] + " " + board[1][6] + " " + board[1][7] + " 7\r\n"
				+ board[2][0] + " " + board[2][1] + " " + board[2][2] + " " + board[2][3] + " " + board[2][4] + " " + board[2][5] + " " + board[2][6] + " " + board[2][7] + " 6\r\n"
				+ board[3][0] + " " + board[3][1] + " " + board[3][2] + " " + board[3][3] + " " + board[3][4] + " " + board[3][5] + " " + board[3][6] + " " + board[3][7] + " 5\r\n"
				+ board[4][0] + " " + board[4][1] + " " + board[4][2] + " " + board[4][3] + " " + board[4][4] + " " + board[4][5] + " " + board[4][6] + " " + board[4][7] + " 4\r\n"
				+ board[5][0] + " " + board[5][1] + " " + board[5][2] + " " + board[5][3] + " " + board[5][4] + " " + board[5][5] + " " + board[5][6] + " " + board[5][7] + " 3 \r\n"
				+ board[6][0] + " " + board[6][1] + " " + board[6][2] + " " + board[6][3] + " " + board[6][4] + " " + board[6][5] + " " + board[6][6] + " " + board[6][7] + " 2\r\n"
				+ board[7][0] + " " + board[7][1] + " " + board[7][2] + " " + board[7][3] + " " + board[7][4] + " " + board[7][5] + " " + board[7][6] + " " + board[7][7] + " 1\r\n"
				+ " a  b  c  d  e  f  g  h";
	}
	
}
