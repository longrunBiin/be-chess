package chess.pieces;

import chess.Direction;
public class Queen extends Piece{
    public Queen(Type type, Color color) {
        super(type, color, Direction.everyDirection());
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {
        int dx = endX - startX;
        int dy = endY - startY;

        if (startX == endX && startY == endY) return;
        //직선으로만 움직이거나 1씩 증가하는 대가선으로만 움직이는 경우
        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection);

        verifyMovePosition(startX + moveDirection.getXDegree(), startY + moveDirection.getYDegree(), endX, endY);
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
