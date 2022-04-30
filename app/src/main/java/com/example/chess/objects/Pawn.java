package com.example.chess.objects;

import android.util.Log;

/**
 * @author Liam Clarke and Manav Mistry
 * King piece, extends Piece abstract class.
 *
 */
public class Pawn extends Piece {
	
	boolean enpassant;

	public Pawn(String color, Space space) {
		super(color, space);
		this.type = "pawn";
		this.enpassant = false;
		// TODO Auto-generated constructor stub
	}

	public boolean isEnpassant() {
		return enpassant;
	}

	public void setEnpassant(boolean enpassant) {
		this.enpassant = enpassant;
	}



	@Override
	public String toString() {
		char clr = 'a';
		if (this.color == "White") {
			clr = 'w';
		} else {
			clr = 'b';
		}
		return clr + "p";
	}

	@Override
	public boolean isValid(ChessBoard gameBoard, Space[] moveArr)  {

		int startRow = 8 - moveArr[0].getRow();
		int startColumn = moveArr[0].getColumn() - 97;
		int endRow = 8 - moveArr[1].getRow();
		int endColumn = moveArr[1].getColumn() - 97;
		
		if (this.getColor().equals("Black")) {
			if (startColumn == endColumn && (startRow + 1 == endRow || (startRow + 2 == endRow && this.getMoveCount() == 0))) {
				if (gameBoard.board[endRow][endColumn].getPiece() != null) {
					return false;
				}
				Piece temp = null;
				if (moveArr[1].getPiece() != null) {
					if (moveArr[1].getPiece().getColor().equals(this.getColor())) {
						return false;
					}
					temp = moveArr[1].getPiece();
				}
				moveArr[0].setPiece(null);
				moveArr[1].setPiece(this);
				boolean check = !gameBoard.kingCheck(this.getColor());
				moveArr[0].setPiece(this);
				moveArr[1].setPiece(temp);
				return check;
			} else if ((startColumn - 1 == endColumn || startColumn + 1 == endColumn) && startRow + 1 == endRow) {
				if (gameBoard.board[endRow][endColumn].getPiece() == null) {
					return false;
				}
				Piece temp = null;
				if (moveArr[1].getPiece() != null) {
					if (moveArr[1].getPiece().getColor().equals(this.getColor()))
						return false;
					temp = moveArr[1].getPiece();
				}
				moveArr[0].setPiece(null);
				moveArr[1].setPiece(this);
				boolean check = !gameBoard.kingCheck(this.getColor());
				moveArr[0].setPiece(this);
				moveArr[1].setPiece(temp);
				return check;
			} //enpassant condition here
			
		} else {
			if (startColumn == endColumn && (startRow - 1 == endRow || (startRow - 2 == endRow && this.getMoveCount() == 0))) {
				if (gameBoard.board[endRow][endColumn].getPiece() != null) {
					return false;
				}
				Piece temp = null;
				if (moveArr[1].getPiece() != null) {
					if (moveArr[1].getPiece().getColor().equals(this.getColor()))
						return false;
					temp = moveArr[1].getPiece();
				}
				moveArr[0].setPiece(null);
				moveArr[1].setPiece(this);
				boolean check = !gameBoard.kingCheck(this.getColor());
				moveArr[0].setPiece(this);
				moveArr[1].setPiece(temp);
				return check;
			} else if ((startColumn - 1 == endColumn || startColumn + 1 == endColumn) && startRow - 1 == endRow) {
				if (gameBoard.board[endRow][endColumn].getPiece() == null) {
					return false;
				}
				Piece temp = null;
				if (moveArr[1].getPiece() != null) {
					if (moveArr[1].getPiece().getColor().equals(this.getColor()))
						return false;
					temp = moveArr[1].getPiece();
				}
				moveArr[0].setPiece(null);
				moveArr[1].setPiece(this);
				boolean check = !gameBoard.kingCheck(this.getColor());
				moveArr[0].setPiece(this);
				moveArr[1].setPiece(temp);
				return check;
			} //enpassant condition here
		}
		return false;
	}
}
