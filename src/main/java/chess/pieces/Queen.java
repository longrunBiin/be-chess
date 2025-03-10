package chess.pieces;

import chess.Direction;
import chess.Position;

public class Queen extends Piece{
    public Queen(Type type, Color color) {
        super(type, color, Direction.everyDirection());
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, Piece targetPiece) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();

        if (dx == 0 && dy == 0) return;
        //직선으로만 움직이거나 1씩 증가하는 대가선으로만 움직이는 경우
        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection);

        verifyPieceAlreadyOnBoard(sourcePiece, targetPiece);
        Position next = new Position(startPos.getXPos() + moveDirection.getXDegree(),
                startPos.getYPos() + moveDirection.getYDegree());
        verifyMovePosition(next, endPos, sourcePiece, targetPiece);
    }

    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0)
            return null; // 자기 위치 유지

        if (dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy)) {
            return getDirectionByCurrent(dx, dy);
        }

        return null;
    }
}
