package passoffTests;

import chess.*;

import java.util.Collection;

/**
 * Used for testing your code
 * Add in code using your classes for each method for each FIXME
 */
public class TestFactory {

    //Chess Functions
    //------------------------------------------------------------------------------------------------------------------
    public static ChessBoard getNewBoard(){
        return new boardImple();
    }

    public static ChessGame getNewGame(){
		return new gameImple();
    }

    public static ChessPiece getNewPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type){
        if (ChessPiece.PieceType.BISHOP==type) {
            return new bishopImple(pieceColor);
        }
        if (ChessPiece.PieceType.KNIGHT==type) {
            return new knightImple(pieceColor);
        }
        if (ChessPiece.PieceType.ROOK==type) {
            return new rookImple(pieceColor);
        }
        if (ChessPiece.PieceType.KING==type) {
            return new kingImple(pieceColor);
        }
        if (ChessPiece.PieceType.QUEEN==type) {
            return new queenImple(pieceColor);
        }
        if (ChessPiece.PieceType.PAWN==type) {
            return new pawnImple(pieceColor);
        }
        return null;
    }

    public static ChessPosition getNewPosition(Integer row, Integer col){
		return new positionImple(row,col);
    }

    public static ChessMove getNewMove(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece){
		return new moveImple(startPosition,endPosition,promotionPiece);
    }
    //------------------------------------------------------------------------------------------------------------------


    //Server API's
    //------------------------------------------------------------------------------------------------------------------
    public static String getServerPort(){
        return "8080";
    }
    //------------------------------------------------------------------------------------------------------------------


    //Websocket Tests
    //------------------------------------------------------------------------------------------------------------------
    public static Long getMessageTime(){
        /*
        Changing this will change how long tests will wait for the server to send messages.
        3000 Milliseconds (3 seconds) will be enough for most computers. Feel free to change as you see fit,
        just know increasing it can make tests take longer to run.
        (On the flip side, if you've got a good computer feel free to decrease it)
         */
        return 3000L;
    }
    //------------------------------------------------------------------------------------------------------------------
}
