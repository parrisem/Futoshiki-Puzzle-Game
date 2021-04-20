package futoshikipuzzlegui;

/**
 *
 * @author pe79
 */
public class FutoshikiSquare {

    private boolean edit = true;
    private int square = 0;
    private int row, column;

    public FutoshikiSquare(FutoshikiSquare toCopy) {
        this.edit = toCopy.edit;
        this.square = toCopy.square;
        this.row = toCopy.row;
        this.column = toCopy.column;
    }

    public FutoshikiSquare(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getSquare() {
        return square;
    }
    
    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isEmpty() {
        return this.square == 0;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        hash = 53 * hash + this.square;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FutoshikiSquare other = (FutoshikiSquare) obj;
        if (this.row != other.row) {
            return false;
        }
        if (this.column != other.column) {
            return false;
        }
        if (this.square != other.square) {
            return false;
        }
        return true;
    }
}
