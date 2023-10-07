package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class gameImple implements ChessGame{
    public TeamColor teamTurn;
    public ChessBoard gameBoard;
    @Override
    public TeamColor getTeamTurn() {
        return teamTurn;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        teamTurn = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        Set<ChessMove> movesValid = new HashSet<>();
        ChessBoard tempBoard = new boardImple();
        for (int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                ChessPosition curPos = new positionImple(i, j);
                ChessPiece currentPiece = gameBoard.getPiece(curPos);
                if (currentPiece!=null) {
                    tempBoard.addPiece(curPos,currentPiece);
                }
            }
        }
//        ChessBoard tempBoard = gameBoard;
        ChessPiece curPiece = getBoard().getPiece(startPosition);
        if (curPiece != null) {
            for (ChessMove move : curPiece.pieceMoves(tempBoard, startPosition)) {
//                System.out.println(move);
                ChessPiece tempPiece = null;
                if (gameBoard.getPiece(move.getEndPosition())!=null) {
                    if (gameBoard.getPiece(move.getEndPosition()).getTeamColor() != curPiece.getTeamColor()) {
                        tempPiece = gameBoard.getPiece(move.getEndPosition());
                    }
                }
                gameBoard.addPiece(startPosition,null);
                gameBoard.addPiece(move.getEndPosition(),curPiece);
                if (!isInCheck(curPiece.getTeamColor())){
                    movesValid.add(move);
                }
                gameBoard.addPiece(startPosition,curPiece);
                gameBoard.addPiece(move.getEndPosition(),null);
                if (tempPiece!=null) {
                    gameBoard.addPiece(move.getEndPosition(), tempPiece);
                }
            }
        }
        return movesValid;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        if (validMoves(move.getStartPosition()).contains(move)) {
             if (getTeamTurn()==gameBoard.getPiece(move.getStartPosition()).getTeamColor()) {
                gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
                gameBoard.addPiece(move.getStartPosition(), null);
                setTeamTurn(this.teamTurn == TeamColor.WHITE ? TeamColor.BLACK:TeamColor.WHITE);
            }else throw new InvalidMoveException("Invalid Move");
        }else throw new InvalidMoveException("Invalid Move");

//        gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
//        gameBoard.addPiece(move.getStartPosition(), null);
    }

    @Override
    public boolean isInCheck(TeamColor teamColor){
        for (int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                ChessPosition curPos = new positionImple(i, j);
                ChessPiece curPiece = getBoard().getPiece(curPos);
                if (curPiece != null) {
                    if (curPiece.getTeamColor() != teamColor) {
                        for (ChessMove move : curPiece.pieceMoves(gameBoard, curPos)) {
                            if (gameBoard.getPiece(move.getEndPosition())!=null) {
                                if (gameBoard.getPiece(move.getEndPosition()).getPieceType() == ChessPiece.PieceType.KING) {
                                    if (gameBoard.getPiece(move.getEndPosition()).getTeamColor() == teamColor) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ChessPosition curPos = new positionImple(i, j);
                    ChessPiece curPiece = getBoard().getPiece(curPos);
                    if (curPiece != null) {
                        if (curPiece.getTeamColor() == teamColor) {
                            if (validMoves(curPos).isEmpty()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ChessPosition curPos = new positionImple(i, j);
                    ChessPiece curPiece = getBoard().getPiece(curPos);
                    if (curPiece != null) {
                        if (curPiece.getTeamColor() == teamColor) {
                            if (validMoves(curPos).isEmpty()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void setBoard(ChessBoard board) {
        gameBoard = board;
    }

    @Override
    public ChessBoard getBoard() {
        return gameBoard;
    }
}
