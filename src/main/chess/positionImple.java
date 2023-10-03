package chess;

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
}
