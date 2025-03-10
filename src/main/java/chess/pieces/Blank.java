package chess.pieces;


import chess.Direction;
import chess.Position;

public class Blank extends Piece{
    public Blank() {
        super(Type.NO_PIECE, Color.NOCOLOR, null);
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, Piece targetPiece) {}

    @Override
    protected Direction findDirection(int dx, int dy) {
        return null;
    }
}
