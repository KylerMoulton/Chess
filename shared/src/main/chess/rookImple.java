package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class rookImple extends pieceImple{
    public rookImple(ChessGame.TeamColor teamColor) {
        super(teamColor, PieceType.ROOK);
    }
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        possibleMoves.addAll(lineMoves(board,myPosition,1,0));
        possibleMoves.addAll(lineMoves(board,myPosition,-1,0));
        possibleMoves.addAll(lineMoves(board,myPosition,0,1));
        possibleMoves.addAll(lineMoves(board,myPosition,0,-1));
        return possibleMoves;
    }
}
