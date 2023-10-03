package chess;

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
}
