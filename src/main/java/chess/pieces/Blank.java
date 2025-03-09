package chess.pieces;


public class Blank extends Piece{
    public Blank() {
        super(Type.NO_PIECE, Color.NOCOLOR, null);
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {

    }
}
