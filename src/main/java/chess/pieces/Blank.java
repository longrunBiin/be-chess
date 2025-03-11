package chess.pieces;


import chess.Direction;
import chess.Position;
import chess.Rank;
import java.util.List;

public class Blank extends Piece{
    public Blank() {
        super(Type.NO_PIECE, Color.NOCOLOR, null);
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, List<Rank> chessBoard) {}

    @Override
    protected Direction findDirection(int dx, int dy) {
        return null;
    }
}
