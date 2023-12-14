package chess;


import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class gameImple implements ChessGame {
    public TeamColor teamTurn;
    public ChessBoard gameBoard;
    public boolean gameOver;

    public gameImple() {
        setBoard(new boardImple());
        setTeamTurn(TeamColor.WHITE);
        gameOver = false;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void setIsGameOver() {
        gameOver = true;
    }

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

//        ChessBoard tempBoard = gameBoard;
        ChessPiece curPiece = getBoard().getPiece(startPosition);
        if (curPiece != null) {
            for (ChessMove move : curPiece.pieceMoves(gameBoard, startPosition)) {
//                System.out.println(move);
                ChessPiece tempPiece = null;
                if (gameBoard.getPiece(move.getEndPosition()) != null) {
                    if (gameBoard.getPiece(move.getEndPosition()).getTeamColor() != curPiece.getTeamColor()) {
                        tempPiece = gameBoard.getPiece(move.getEndPosition());
                    }
                }
                gameBoard.addPiece(startPosition, null);
                gameBoard.addPiece(move.getEndPosition(), curPiece);
                if (!isInCheck(curPiece.getTeamColor())) {
                    movesValid.add(move);
                }
                gameBoard.addPiece(startPosition, curPiece);
                gameBoard.addPiece(move.getEndPosition(), null);
                if (tempPiece != null) {
                    gameBoard.addPiece(move.getEndPosition(), tempPiece);
                }
            }
        }
        return movesValid;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        for (ChessMove chessMove : validMoves) {
            if (chessMove.getEndPosition().equals(move.getEndPosition())) {
                if (getTeamTurn() == gameBoard.getPiece(move.getStartPosition()).getTeamColor()) {
                    gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
                    gameBoard.addPiece(move.getStartPosition(), null);
                    if (move.getPromotionPiece() != null) {
                        ChessPiece newPiece = piecePromotion(move.getPromotionPiece());
                        gameBoard.addPiece(move.getEndPosition(), newPiece);
                    }
                    setTeamTurn(this.teamTurn == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE);
                    return;
                } //else throw new InvalidMoveException("Invalid Move");
            } //else throw new InvalidMoveException("Invalid Move");
        }
        throw new InvalidMoveException("Invalid Move");

//        gameBoard.addPiece(move.getEndPosition(), gameBoard.getPiece(move.getStartPosition()));
//        gameBoard.addPiece(move.getStartPosition(), null);
    }

    public ChessPiece piecePromotion(ChessPiece.PieceType pieceType) {
        return switch (pieceType) {
            case KING, PAWN -> null;
            case QUEEN -> new queenImple(teamTurn);
            case BISHOP -> new bishopImple(teamTurn);
            case KNIGHT -> new knightImple(teamTurn);
            case ROOK -> new rookImple(teamTurn);
        };
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition curPos = new positionImple(i, j);
                ChessPiece curPiece = getBoard().getPiece(curPos);
                if (curPiece != null) {
                    if (curPiece.getTeamColor() != teamColor) {
                        for (ChessMove move : curPiece.pieceMoves(gameBoard, curPos)) {
                            if (gameBoard.getPiece(move.getEndPosition()) != null) {
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
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 8; j++) {
                    ChessPosition curPos = new positionImple(i, j);
                    ChessPiece curPiece = getBoard().getPiece(curPos);
                    if (curPiece != null) {
                        if (curPiece.getTeamColor() == teamColor) {
                            if (!validMoves(curPos).isEmpty()) {
                                return false;
                            }
                        }
                    }
                }
            }
            gameOver = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    ChessPosition curPos = new positionImple(i, j);
                    ChessPiece curPiece = getBoard().getPiece(curPos);
                    if (curPiece != null) {
                        if (curPiece.getTeamColor() == teamColor) {
                            if (!validMoves(curPos).isEmpty()) {
                                return false;
                            }
                        }
                    }
                }
            }
            gameOver = true;
            return true;
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

    public static Gson serialization() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessGame.class, new adapterChessGame());
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new adapterChessBoard());
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new adapterPiece());
        return gsonBuilder.create();
    }

    public static class adapterChessGame implements JsonDeserializer<ChessGame> {
        public ChessGame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return jsonDeserializationContext.deserialize(jsonElement, gameImple.class);
        }
    }

    public static class adapterChessBoard implements JsonDeserializer<ChessBoard> {
        public ChessBoard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return jsonDeserializationContext.deserialize(jsonElement, boardImple.class);
        }
    }

    public static class adapterPiece implements JsonDeserializer<ChessPiece> {
        public ChessPiece deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String pieceType = jsonElement.getAsJsonObject().get("pieceType").getAsString();
            switch (pieceType) {
                case "ROOK" -> {
                    return jsonDeserializationContext.deserialize(jsonElement, rookImple.class);
                }
                case "PAWN" -> {
                    return jsonDeserializationContext.deserialize(jsonElement, pawnImple.class);
                }
                case "QUEEN" -> {
                    return jsonDeserializationContext.deserialize(jsonElement, queenImple.class);
                }
                case "KING" -> {
                    return jsonDeserializationContext.deserialize(jsonElement, kingImple.class);
                }
                case "KNIGHT" -> {
                    return jsonDeserializationContext.deserialize(jsonElement, knightImple.class);
                }
                case "BISHOP" -> {
                    return jsonDeserializationContext.deserialize(jsonElement, bishopImple.class);
                }
            }

            return null;
        }
    }
}
