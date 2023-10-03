package chess;

import java.util.Objects;

public class moveImple implements ChessMove{
    private ChessPosition startPosition;
    private ChessPosition endPosition;
    private ChessPiece.PieceType promotionPiece;
    public moveImple(ChessPosition start, ChessPosition end, ChessPiece.PieceType piece) {
        this.startPosition = start;
        this.endPosition = end;
        this.promotionPiece = piece;
    }
    @Override
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    @Override
    public ChessPosition getEndPosition() {
        return endPosition;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        moveImple moveImple = (moveImple) o;
        return Objects.equals(startPosition, moveImple.startPosition) && Objects.equals(endPosition, moveImple.endPosition) && promotionPiece == moveImple.promotionPiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition, promotionPiece);
    }
}
