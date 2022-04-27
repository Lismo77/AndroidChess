package objects;

/**
 * @author Liam Clarke and Manav Mistry
 * Space class. populates chess board.
 *
 */
public class Space {
	int row;
	char column;
	boolean filled;
	Piece piece;

	public Space(int row, char column, boolean filled) {
		this.row = row;
		this.column = column;
		this.filled = filled;
		this.piece = null;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@Override
	public String toString() {
		if (this.getPiece() != null) {
			return this.getPiece().toString();
		} 
		if (this.isFilled())
			return "##";
		return "  ";
	}
	
}
