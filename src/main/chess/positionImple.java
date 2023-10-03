package chess;

import java.util.Objects;

public class positionImple implements ChessPosition{
    private int row;
    private int column;
    public positionImple(int rowPos, int columnPos) {
        this.row = rowPos-1;
        this.column = columnPos-1;
    }
    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        positionImple that = (positionImple) o;
        return row == that.row && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
