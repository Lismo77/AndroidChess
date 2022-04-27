package objects;

/**
 * @author Liam Clarke and Manav Mistry
 * Parent class for chess pieces.
 *
 */
public abstract class Piece {
	
	String color;
	String type;
	Space space;
	int moveCount;
	
	/**
	 * @param color describes color of chess piece.
	 * @param space indicates space object in which piece is located
	 */
	public Piece(String color, Space space) {
		this.color = color;
		this.space = space;
		this.moveCount = 0;
	}

	/**
	 * @param gameBoard the board being played on.
	 * @param moveArr array of space objects (start and destination).
	 * @return boolean value for whether or not the move is successful.
	 */
	public boolean move(ChessBoard gameBoard, Space[] moveArr) {
		boolean valid = this.isValid(gameBoard, moveArr);
		if (valid) {
			if (moveArr[1].getPiece() != null) {
				if (this.getColor().equals("White")) {
					gameBoard.blackPieces.remove(moveArr[1].getPiece());
				} else {
					gameBoard.whitePieces.remove(moveArr[1].getPiece());
				}
			}
			moveArr[0].setPiece(null);
			
			if (this.getType().equals("pawn")) {
				//if (moveArr[1].getRow() - moveArr[0].getRow() == 2 || moveArr[1].getRow() - moveArr[0].getRow() == -2)
					
				if ((this.getColor().equals("Black") && 8 - moveArr[1].getRow() == 7) || (this.getColor().equals("White") && 8 - moveArr[1].getRow() == 0)) {
					Piece newQueen = new Queen(this.getColor(), moveArr[1]);
					moveArr[1].setPiece(newQueen);
					newQueen.setSpace(moveArr[1]);
				} else {
					moveArr[1].setPiece(this);
					this.setSpace(moveArr[1]);
				}
			} else if (this.getType().equals("king")) {
				if ((int)(moveArr[1].getColumn() - 97) - (int)(moveArr[0].getColumn() - 97) == -2) {
					Piece movedRook = gameBoard.board[8 - moveArr[0].getRow()][(int)(moveArr[1].getColumn() - 97) - 2].getPiece();
					gameBoard.board[8 - moveArr[0].getRow()][(int)(moveArr[1].getColumn() - 97) - 2].setPiece(null);
					gameBoard.board[8 - moveArr[0].getRow()][(int)(moveArr[1].getColumn() - 97) + 1].setPiece(movedRook);
					moveArr[1].setPiece(this);
					this.setSpace(moveArr[1]);
				} else if ((int)(moveArr[1].getColumn() - 97) - (int)(moveArr[0].getColumn() - 97) == 2) {
					Piece movedRook = gameBoard.board[8 - moveArr[1].getRow()][(int)(moveArr[1].getColumn() - 97) + 1].getPiece();
					System.out.println(movedRook.getSpace().toString());
					gameBoard.board[8 - moveArr[0].getRow()][(int)(moveArr[1].getColumn() - 97) + 1].setPiece(null);
					gameBoard.board[8 - moveArr[0].getRow()][(int)(moveArr[1].getColumn() - 97) - 1].setPiece(movedRook);
					moveArr[1].setPiece(this);
					this.setSpace(moveArr[1]);
				} else {
					moveArr[1].setPiece(this);
					this.setSpace(moveArr[1]);
				}
			} else {
				moveArr[1].setPiece(this);
				this.setSpace(moveArr[1]);
			}
			this.setMoveCount(this.getMoveCount() + 1);
		}
		return valid;
	}
	
	/**
	 * @return true if this piece has legal moves remaining.
	 */
	public boolean hasMoves(ChessBoard gameBoard) {
		Space testSpace[] = new Space[2];
		testSpace[0] = this.getSpace();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				testSpace[1] = gameBoard.board[i][j];
				if (this.isValid(gameBoard, testSpace)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public abstract boolean isValid(ChessBoard gameBoard, Space[] moveArr);

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}
	
}
