package chess.pieces;


import chess.Direction;

public class Blank extends Piece{
    public Blank() {
        super(Type.NO_PIECE, Color.NOCOLOR, null);
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {

    }

    @Override
    protected Direction findDirection(int dx, int dy) {
        return null;
    }
}
