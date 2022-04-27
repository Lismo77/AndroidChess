package objects;

/**
 * @author Liam Clarke and Manav Mistry
 * King piece, extends Piece abstract class.
 *
 */
public class Bishop extends Piece{

	public Bishop(String color, Space space) {
		super(color, space);
		this.type = "bishop";
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
		return clr + "B";
	}

	@Override
	public boolean isValid(ChessBoard gameBoard, Space[] moveArr)  {
		int endRow = 8 - moveArr[1].getRow();
		int endColumn = moveArr[1].getColumn() - 97;
		
		int checkRow = 0;
		int checkColumn = 0;
		int multiplier = 1;
		boolean foundPiece = false;
		
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
			while ((!foundPiece) && endRow + (multiplier * checkRow) >= 0 && endRow + (multiplier * checkRow) <= 7 &&
					endColumn + (multiplier * checkColumn) >= 0 && endColumn + (multiplier * checkColumn) <= 7) {
				int rindex = endRow + (checkRow * multiplier);
				int cindex = endColumn + (checkColumn * multiplier);
				if (gameBoard.board[rindex][cindex].getPiece() != null) {
					foundPiece = true;
					if (gameBoard.board[rindex][cindex].getPiece() == this) {
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
				}
				multiplier++;
			}
		}
		
		return false;
	}

}
