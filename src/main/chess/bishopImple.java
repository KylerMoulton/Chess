package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class bishopImple extends pieceImple{
    public bishopImple(ChessGame.TeamColor teamColor) {
        super(teamColor, PieceType.BISHOP);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        possibleMoves.addAll(lineMoves(board,myPosition,1,1));
        possibleMoves.addAll(lineMoves(board,myPosition,1,-1));
        possibleMoves.addAll(lineMoves(board,myPosition,-1,1));
        possibleMoves.addAll(lineMoves(board,myPosition,-1,-1));
        return possibleMoves;
    }
}
