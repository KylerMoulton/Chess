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
//        possibleMoves.addAll(bishopMoves(board,myPosition,1,1));
//        possibleMoves.addAll(bishopMoves(board,myPosition,1,-1));
//        possibleMoves.addAll(bishopMoves(board,myPosition,-1,1));
//        possibleMoves.addAll(bishopMoves(board,myPosition,-1,-1));
        return possibleMoves;
    }

    private Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition myPosition, int rowDir, int colDir) {
        Set<ChessMove> foundMoves = new HashSet<>();
        moreMoves = true;
        int curRow = myPosition.getRow() + 1;
        int curColumn = myPosition.getColumn() + 1;
        while (moreMoves) {
            if (rowDir == 1) curRow += 1;
            if (rowDir == -1) curRow -= 1;
            if (colDir == 1) curColumn += 1;
            if (colDir == -1) curColumn -= 1;

            ChessPosition endPosition = new positionImple(curRow,curColumn);
            if ((endPosition.getRow()>=0&&endPosition.getRow()<=7)&&(endPosition.getColumn()>=0&&endPosition.getColumn()<=7)) {
                if (board.getPiece(myPosition)==null) {
                    foundMoves.add(new moveImple(myPosition,endPosition,null));
                }
                else if (board.getPiece(endPosition).getTeamColor() != getTeamColor()) {
                    foundMoves.add(new moveImple(myPosition,endPosition,null));
                }else if (board.getPiece(endPosition).getTeamColor()!=getTeamColor()) {moreMoves=false;}

            }else moreMoves=false;
        }
        return foundMoves;
    }
}
