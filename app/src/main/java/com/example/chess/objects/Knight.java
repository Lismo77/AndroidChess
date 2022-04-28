package com.example.chess.objects;

/**
 * @author Liam Clarke and Manav Mistry
 * Knight piece, extends Piece abstract class.
 *
 */
public class Knight extends Piece {

	public Knight(String color, Space space) {
		super(color, space);
		this.type = "knight";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		char clr = 'a';
		if (this.color == "White") {
			clr = 'w';
		} else {
			clr = 'b';
		}
		return clr + "N";
	}

	@Override
	public boolean isValid(ChessBoard gameBoard, Space[] moveArr)  {
		int startRow = 8 - moveArr[0].getRow();
		int startColumn = moveArr[0].getColumn() - 97;
		int endRow = 8 - moveArr[1].getRow();
		int endColumn = moveArr[1].getColumn() - 97;
		if ((startRow - 1 == endRow && startColumn - 2 == endColumn) || (startRow - 2 == endRow && startColumn - 1 == endColumn) || 
				(startRow - 2 == endRow && startColumn + 1 == endColumn) || (startRow - 1 == endRow && startColumn + 2 == endColumn) || 
				(startRow + 1 == endRow && startColumn + 2 == endColumn) || (startRow + 2 == endRow && startColumn + 1 == endColumn) || 
				(startRow + 2 == endRow && startColumn - 1 == endColumn) || (startRow + 1 == endRow && startColumn - 2 == endColumn)) {
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
		}
		return false;
	}

}
