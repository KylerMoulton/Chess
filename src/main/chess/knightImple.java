package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class knightImple extends pieceImple{
    public knightImple(ChessGame.TeamColor teamColor) {
        super(teamColor, PieceType.KNIGHT);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        possibleMoves.addAll(knightMoves(board,myPosition,2,1));
        possibleMoves.addAll(knightMoves(board,myPosition,1,2));
        possibleMoves.addAll(knightMoves(board,myPosition,-1,2));
        possibleMoves.addAll(knightMoves(board,myPosition,-2,1));
        possibleMoves.addAll(knightMoves(board,myPosition,-2,-1));
        possibleMoves.addAll(knightMoves(board,myPosition,-1,-2));
        possibleMoves.addAll(knightMoves(board,myPosition,1,-2));
        possibleMoves.addAll(knightMoves(board,myPosition,2,-1));
        return possibleMoves;
    }
}
