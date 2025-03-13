package chess.pieces;

import chess.game.Direction;
import chess.game.Position;
import chess.game.Rank;
import java.util.List;

public class Knight extends Piece{
    public Knight(Type type, Color color) {
        super(type, color, Direction.knightDirection());
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, List<Rank> chessBoard) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();


        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection, sourcePiece);

        Position next = getNextPosition(startPos, moveDirection);
        Piece targetPiece = getTargetPiece(chessBoard, next);

        verifyNextPosition(next, endPos, sourcePiece, targetPiece);
    }
    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        return getDirectionByCurrentExactly(dx, dy);
    }
}
