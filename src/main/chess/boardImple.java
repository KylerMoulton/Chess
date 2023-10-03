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
        boardArray[0][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        boardArray[0][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        boardArray[0][2] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        boardArray[0][3] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        boardArray[0][4] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        boardArray[0][5] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        boardArray[0][6] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        boardArray[0][7] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        boardArray[1][0] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][1] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][2] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][3] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][4] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][5] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][6] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[1][7] = new pieceImple(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        boardArray[7][0] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        boardArray[7][1] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        boardArray[7][2] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        boardArray[7][3] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        boardArray[7][4] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        boardArray[7][5] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        boardArray[7][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        boardArray[7][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        boardArray[6][0] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][1] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][2] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][3] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][4] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][5] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][6] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        boardArray[6][7] = new pieceImple(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
    }
}
