package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class pieceImple implements ChessPiece{
    private ChessGame.TeamColor color;
    private PieceType pieceType;
    boolean moreMoves = true;

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
        Set<ChessMove> foundMoves = new HashSet<>();
        moreMoves = true;
        int curRow = position.getRow();
        int curColumn = position.getColumn();
        while (moreMoves) {
            if (rowDir == 1) curRow += 1;
            if (rowDir == -1) curRow -= 1;
            if (columnDir == 1) curColumn += 1;
            if (columnDir == -1) curColumn -= 1;

            ChessPosition endPosition = new positionImple(curRow,curColumn);
            if ((endPosition.getRow()>=0&&endPosition.getRow()<=7)&&(endPosition.getColumn()>=0&&endPosition.getColumn()<=7)) {
                if (board.getPiece(endPosition)==null) {
                    foundMoves.add(new moveImple(position,endPosition,null));
                }
                else if (board.getPiece(endPosition).getTeamColor() != getTeamColor()) {
                    foundMoves.add(new moveImple(position,endPosition,null));
                    moreMoves=false;
                }else if (board.getPiece(endPosition).getTeamColor()==getTeamColor()) {moreMoves=false;}

            }else moreMoves=false;
        }
        return foundMoves;
    }
    protected Collection<ChessMove> otherMoves(ChessBoard board, ChessPosition position, int rowDir, int columnDir) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        calculateMoves(board, position, rowDir, columnDir, possibleMoves);

        return possibleMoves;
    }
    protected Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition position, int rowDir, int columnDir) {
        Set<ChessMove> possibleMoves = new HashSet<>();
        ChessPosition endPosition = new positionImple(position.getRow()+rowDir,position.getColumn()+columnDir);
        if (endPosition.getRow()<=7 && endPosition.getColumn()<=7) {
            if (board.getPiece(endPosition).getTeamColor() != getTeamColor()) {
                if (board.getPiece(position).getTeamColor()== ChessGame.TeamColor.WHITE){
                    if (columnDir==2) {
                        if (position.getColumn() == 1) {
                            addWhitePawnMoves(position, possibleMoves, endPosition);
                        }
                    }
                    if (columnDir==1&&rowDir==0){
                        addWhitePawnMoves(position, possibleMoves, endPosition);
                    }
                    if (columnDir==1&&rowDir==1) {
                        addWhitePawnMoves(position, possibleMoves, endPosition);
                    }
                    if (columnDir==1&&rowDir==-1) {
                        addWhitePawnMoves(position, possibleMoves, endPosition);
                    }
                }
                else if (board.getPiece(position).getTeamColor()== ChessGame.TeamColor.BLACK){
                    if (columnDir==-2) {
                        if (position.getColumn() == 1) {
                            addBlackPawnMoves(position, possibleMoves, endPosition);
                        }
                    }
                    if (columnDir==-1&&rowDir==0){
                        addBlackPawnMoves(position, possibleMoves, endPosition);
                    }
                    if (columnDir==-1&&rowDir==1) {
                        addBlackPawnMoves(position, possibleMoves, endPosition);
                    }
                    if (columnDir==-1&&rowDir==-1) {
                        addBlackPawnMoves(position, possibleMoves, endPosition);
                    }
                }
            }
        }
        return possibleMoves;
    }

    private void addWhitePawnMoves(ChessPosition position, Set<ChessMove> possibleMoves, ChessPosition endPosition) {
        if (endPosition.getColumn() == 7) {
            possibleMoves.add(new moveImple(position, endPosition, PieceType.ROOK));
            possibleMoves.add(new moveImple(position, endPosition, PieceType.KNIGHT));
            possibleMoves.add(new moveImple(position, endPosition, PieceType.BISHOP));
            possibleMoves.add(new moveImple(position, endPosition, PieceType.QUEEN));
        } else {
            possibleMoves.add(new moveImple(position, endPosition, null));
        }
    }

    private void addBlackPawnMoves(ChessPosition position, Set<ChessMove> possibleMoves, ChessPosition endPosition) {
        if (endPosition.getColumn() == 0) {
            possibleMoves.add(new moveImple(position, endPosition, PieceType.ROOK));
            possibleMoves.add(new moveImple(position, endPosition, PieceType.KNIGHT));
            possibleMoves.add(new moveImple(position, endPosition, PieceType.BISHOP));
            possibleMoves.add(new moveImple(position, endPosition, PieceType.QUEEN));
        } else {
            possibleMoves.add(new moveImple(position, endPosition, null));
        }
    }

    private void calculateMoves(ChessBoard board, ChessPosition position, int rowDir, int columnDir, Set<ChessMove> possibleMoves) {
        int curRow = position.getRow();
        int curColumn = position.getColumn();
        while (moreMoves) {
            if (rowDir == 1) curRow += 1;
            if (rowDir == -1) curRow -= 1;
            if (columnDir == 1) curColumn += 1;
            if (columnDir == -1) curColumn -= 1;

            ChessPosition endPosition = new positionImple(curRow,curColumn);
            if ((endPosition.getRow()>=0&&endPosition.getRow()<=7)&&(endPosition.getColumn()>=0&&endPosition.getColumn()<=7)) {
                if (board.getPiece(endPosition)==null) {
                    possibleMoves.add(new moveImple(position,endPosition,null));
                }
                else if (board.getPiece(endPosition).getTeamColor() != getTeamColor()) {
                    possibleMoves.add(new moveImple(position,endPosition,null));
                    moreMoves=false;
                }else if (board.getPiece(endPosition).getTeamColor()==getTeamColor()) {moreMoves=false;}

            }else moreMoves=false;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        pieceImple that = (pieceImple) o;
        return color == that.color && pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pieceType);
    }
}
