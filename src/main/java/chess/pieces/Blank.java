package chess.pieces;


import chess.game.Direction;
import chess.game.Position;
import chess.game.Rank;
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
