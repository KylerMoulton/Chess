package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public abstract class pieceImple implements ChessPiece{
    private ChessGame.TeamColor color;
    private PieceType pieceType;
    public pieceImple(ChessGame.TeamColor teamColor,PieceType typeOPiece) {
        this.color = teamColor;
        this.pieceType = typeOPiece;
    }
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }
    protected Collection<ChessMove> lineMoves(ChessBoard board, ChessPosition position, int rowDir, int columnDir) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        boolean moreMoves = true;
        while (moreMoves) {
            calculateMoves(board, position, rowDir, columnDir, possibleMoves);
            moreMoves = false;
        }
        return possibleMoves;
    }
    protected Collection<ChessMove> otherMoves(ChessBoard board, ChessPosition position, int rowDir, int columnDir) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        calculateMoves(board, position, rowDir, columnDir, possibleMoves);

        return possibleMoves;
    }

    private void calculateMoves(ChessBoard board, ChessPosition position, int rowDir, int columnDir, Set<ChessMove> possibleMoves) {
        if (position.getRow()<=7 && position.getColumn()<=7) {
            if (board.getPiece(position).getTeamColor()!=getTeamColor()) {
                ChessPosition endPosition = new positionImple(position.getRow()+rowDir,position.getColumn()+columnDir);
                possibleMoves.add(new moveImple(position,endPosition,null));
            }
        }
    }
}
