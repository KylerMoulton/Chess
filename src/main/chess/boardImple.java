package chess;

import java.util.Set;

public class boardImple implements ChessBoard{
    ChessPiece[][] boardArray = new ChessPiece[8][8];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        boardArray[position.getColumn()][position.getRow()] = piece;
    }

    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return boardArray[position.getColumn()][position.getRow()];
    }

    @Override
    public void resetBoard() {
        boardArray = new ChessPiece[8][8];
        boardArray[0][0] = new rookImple(ChessGame.TeamColor.WHITE);
        boardArray[1][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        boardArray[2][0] = new bishopImple(ChessGame.TeamColor.WHITE);
        boardArray[3][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        boardArray[4][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        boardArray[5][0] = new bishopImple(ChessGame.TeamColor.WHITE);
        boardArray[6][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        boardArray[7][0] = new rookImple(ChessGame.TeamColor.WHITE);
        boardArray[0][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[2][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[3][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[4][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[5][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[6][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[7][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[0][7] = new rookImple(ChessGame.TeamColor.BLACK);
        boardArray[1][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        boardArray[2][7] = new bishopImple(ChessGame.TeamColor.BLACK);
        boardArray[3][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        boardArray[4][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        boardArray[5][7] = new bishopImple(ChessGame.TeamColor.BLACK);
        boardArray[6][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        boardArray[7][7] = new rookImple(ChessGame.TeamColor.BLACK);
        boardArray[0][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[1][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[2][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[3][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[4][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[5][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[7][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[0][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
//        boardArray[1][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
//        boardArray[2][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
//        boardArray[3][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
//        boardArray[4][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
//        boardArray[5][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
//        boardArray[6][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
//        boardArray[7][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
//        boardArray[0][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[1][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[2][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[3][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[4][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[5][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[6][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[7][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
//        boardArray[0][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
//        boardArray[1][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
//        boardArray[2][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
//        boardArray[3][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
//        boardArray[4][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
//        boardArray[5][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
//        boardArray[6][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
//        boardArray[7][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
//        boardArray[0][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[1][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[2][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[3][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[4][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[5][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[6][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
//        boardArray[7][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    }
}
