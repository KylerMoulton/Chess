package chess;

import java.util.Collection;

public class pieceImple implements ChessPiece{
    private ChessGame.TeamColor color;
    private PieceType pieceType;
    private Collection<ChessMove> possibleMoves;
    public pieceImple(ChessGame.TeamColor teamColor,PieceType typeOPiece) {
        this.color = teamColor;
        this.pieceType = typeOPiece;
    }
    public pieceImple(){
    }
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return null;
    }
}
