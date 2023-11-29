package chess;

public class boardImple implements ChessBoard {
    ChessPiece[][] boardArray = new ChessPiece[8][8];

    public boardImple(ChessBoard tempBoard) {
        ChessPiece[][] newBoard = new ChessPiece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPosition curPos = new positionImple(i, j);
                ChessPiece curPiece = tempBoard.getPiece(curPos);
                if (curPiece != null) {
                    newBoard[i][j] = curPiece;
                }
            }
        }
    }

    public boardImple() {
    }

    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        boardArray[position.getRow()][position.getColumn()] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return boardArray[position.getRow()][position.getColumn()];
    }

    @Override
    public void resetBoard() {
        boardArray = new ChessPiece[8][8];
//        boardArray[0][0] = new rookImple(ChessGame.TeamColor.WHITE);
//        boardArray[1][0] = new knightImple(ChessGame.TeamColor.WHITE);
//        boardArray[2][0] = new bishopImple(ChessGame.TeamColor.WHITE);
//        boardArray[3][0] = new queenImple(ChessGame.TeamColor.WHITE);
//        boardArray[4][0] = new kingImple(ChessGame.TeamColor.WHITE);
//        boardArray[5][0] = new bishopImple(ChessGame.TeamColor.WHITE);
//        boardArray[6][0] = new knightImple(ChessGame.TeamColor.WHITE);
//        boardArray[7][0] = new rookImple(ChessGame.TeamColor.WHITE);
//        boardArray[0][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[1][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[2][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[3][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[4][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[5][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[6][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[7][1] = new pawnImple(ChessGame.TeamColor.WHITE);
//        boardArray[0][7] = new rookImple(ChessGame.TeamColor.BLACK);
//        boardArray[1][7] = new knightImple(ChessGame.TeamColor.BLACK);
//        boardArray[2][7] = new bishopImple(ChessGame.TeamColor.BLACK);
//        boardArray[3][7] = new queenImple(ChessGame.TeamColor.BLACK);
//        boardArray[4][7] = new kingImple(ChessGame.TeamColor.BLACK);
//        boardArray[5][7] = new bishopImple(ChessGame.TeamColor.BLACK);
//        boardArray[6][7] = new knightImple(ChessGame.TeamColor.BLACK);
//        boardArray[7][7] = new rookImple(ChessGame.TeamColor.BLACK);
//        boardArray[0][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[1][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[2][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[3][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[4][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[5][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[6][6] = new pawnImple(ChessGame.TeamColor.BLACK);
//        boardArray[7][6] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[0][0] = new rookImple(ChessGame.TeamColor.WHITE);
        boardArray[0][1] = new knightImple(ChessGame.TeamColor.WHITE);
        boardArray[0][2] = new bishopImple(ChessGame.TeamColor.WHITE);
        boardArray[0][3] = new queenImple(ChessGame.TeamColor.WHITE);
        boardArray[0][4] = new kingImple(ChessGame.TeamColor.WHITE);
        boardArray[0][5] = new bishopImple(ChessGame.TeamColor.WHITE);
        boardArray[0][6] = new knightImple(ChessGame.TeamColor.WHITE);
        boardArray[0][7] = new rookImple(ChessGame.TeamColor.WHITE);
        boardArray[1][0] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][1] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][2] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][3] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][4] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][5] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][6] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[1][7] = new pawnImple(ChessGame.TeamColor.WHITE);
        boardArray[7][0] = new rookImple(ChessGame.TeamColor.BLACK);
        boardArray[7][1] = new knightImple(ChessGame.TeamColor.BLACK);
        boardArray[7][2] = new bishopImple(ChessGame.TeamColor.BLACK);
        boardArray[7][3] = new queenImple(ChessGame.TeamColor.BLACK);
        boardArray[7][4] = new kingImple(ChessGame.TeamColor.BLACK);
        boardArray[7][5] = new bishopImple(ChessGame.TeamColor.BLACK);
        boardArray[7][6] = new knightImple(ChessGame.TeamColor.BLACK);
        boardArray[7][7] = new rookImple(ChessGame.TeamColor.BLACK);
        boardArray[6][0] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][1] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][2] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][3] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][4] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][5] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][6] = new pawnImple(ChessGame.TeamColor.BLACK);
        boardArray[6][7] = new pawnImple(ChessGame.TeamColor.BLACK);
    }
}
