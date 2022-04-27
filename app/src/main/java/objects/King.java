package objects;

/**
 * @author Liam Clarke and Manav Mistry
 * King piece, extends Piece abstract class.
 *
 */
public class King extends Piece {
	
	public King(String color, Space space) {
		super(color, space);
		this.type = "king";
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
		return clr + "K";
	}

	@Override
	public boolean isValid(ChessBoard gameBoard, Space[] moveArr)  {
		int startRow = 8 - moveArr[0].getRow();
		int startColumn = moveArr[0].getColumn() - 97;
		int endRow = 8 - moveArr[1].getRow();
		int endColumn = moveArr[1].getColumn() - 97;
		
		//for castling
		if (this.getMoveCount() == 0) {
			if ((startColumn - 2 == endColumn && startRow == endRow)) {
				
				if (gameBoard.board[startRow][startColumn - 4].getPiece() == null)
					return false;
				if (!gameBoard.board[startRow][startColumn - 4].getPiece().getType().equals("rook") || 
						gameBoard.board[startRow][startColumn - 4].getPiece().getMoveCount() != 0)
					return false;
				for (int i = 1; i < 4; i++) {
					if (gameBoard.board[startRow][startColumn - i].getPiece() != null) {
						return false;
					}
				}
				Piece movedRook = gameBoard.board[startRow][startColumn - 4].getPiece();
				gameBoard.board[startRow][startColumn - 4].setPiece(null);
				gameBoard.board[startRow][startColumn - 1].setPiece(movedRook);
				moveArr[0].setPiece(null);
				moveArr[1].setPiece(this);
				boolean check = !gameBoard.kingCheck(this.getColor());
				moveArr[0].setPiece(this);
				moveArr[1].setPiece(null);
				gameBoard.board[startRow][startColumn - 4].setPiece(movedRook);
				gameBoard.board[startRow][startColumn - 1].setPiece(null);
				return check;
				
				
			} else if ((startColumn + 2 == endColumn && startRow == endRow)) {
				if (gameBoard.board[startRow][startColumn + 3].getPiece() == null) {
					return false;
				}
				if (!gameBoard.board[startRow][startColumn + 3].getPiece().getType().equals("rook") || 
						gameBoard.board[startRow][startColumn + 3].getPiece().getMoveCount() != 0) {
					return false;
				}
				for (int i = 1; i < 3; i++) {
					if (gameBoard.board[startRow][startColumn + i].getPiece() != null) {
						return false;
					}
				}
				Piece movedRook = gameBoard.board[startRow][startColumn + 3].getPiece();
				gameBoard.board[startRow][startColumn + 3].setPiece(null);
				gameBoard.board[startRow][startColumn + 1].setPiece(movedRook);
				moveArr[0].setPiece(null);
				moveArr[1].setPiece(this);
				boolean check = !gameBoard.kingCheck(this.getColor());
				moveArr[0].setPiece(this);
				moveArr[1].setPiece(null);
				gameBoard.board[startRow][startColumn + 3].setPiece(movedRook);
				gameBoard.board[startRow][startColumn + 1].setPiece(null);
				return check;	
			}
		}
		
		//regular movement
		if ((startRow - 1 == endRow || startRow + 1 == endRow) && (startColumn - 1 == endColumn || startColumn == endColumn || startColumn + 1 == endColumn)
				|| (startRow == endRow && (startColumn - 1 == endColumn || startColumn + 1 == endColumn))) {
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
