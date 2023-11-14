package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class kingImple extends pieceImple{
    public kingImple(ChessGame.TeamColor teamColor) {
        super(teamColor, PieceType.KING);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        possibleMoves.addAll(otherMoves(board,myPosition,1,0));
        possibleMoves.addAll(otherMoves(board,myPosition,-1,0));
        possibleMoves.addAll(otherMoves(board,myPosition,0,1));
        possibleMoves.addAll(otherMoves(board,myPosition,0,-1));
        possibleMoves.addAll(otherMoves(board,myPosition,1,1));
        possibleMoves.addAll(otherMoves(board,myPosition,1,-1));
        possibleMoves.addAll(otherMoves(board,myPosition,-1,1));
        possibleMoves.addAll(otherMoves(board,myPosition,-1,-1));
        return possibleMoves;
    }
}
