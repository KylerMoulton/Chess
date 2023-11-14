package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class pawnImple extends pieceImple{
    public pawnImple(ChessGame.TeamColor teamColor) {
        super(teamColor, PieceType.PAWN);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> possibleMoves = new HashSet<>();
//        possibleMoves.addAll(pawnMoves(board,myPosition,0,2));
//        possibleMoves.addAll(pawnMoves(board,myPosition,0,-2));
//        possibleMoves.addAll(pawnMoves(board,myPosition,0,1));
//        possibleMoves.addAll(pawnMoves(board,myPosition,0,-1));
//        possibleMoves.addAll(pawnMoves(board,myPosition,1,-1));
//        possibleMoves.addAll(pawnMoves(board,myPosition,1,1));
//        possibleMoves.addAll(pawnMoves(board,myPosition,-1,-1));
//        possibleMoves.addAll(pawnMoves(board,myPosition,-1,1));
        possibleMoves.addAll(pawnMoves(board,myPosition,2,0));
        possibleMoves.addAll(pawnMoves(board,myPosition,-2,0));
        possibleMoves.addAll(pawnMoves(board,myPosition,1,0));
        possibleMoves.addAll(pawnMoves(board,myPosition,-1,0));
        possibleMoves.addAll(pawnMoves(board,myPosition,-1,1));
        possibleMoves.addAll(pawnMoves(board,myPosition,1,1));
        possibleMoves.addAll(pawnMoves(board,myPosition,-1,-1));
        possibleMoves.addAll(pawnMoves(board,myPosition,1,-1));
        return possibleMoves;
    }
}
