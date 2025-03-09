package chess.pieces;

import chess.Direction;

public class Bishop extends Piece{
    public Bishop(Type type, Color color) {
        super(type, color, Direction.diagonalDirection());
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {
        int dx = endX - startX;
        int dy = endY - startY;

        if (startX == endX && startY == endY) return;

        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection);

        verifyMovePosition(startX + moveDirection.getXDegree(), startY + moveDirection.getYDegree(), endX, endY);
    }
    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        // 대각선 이동 체크
        if (Math.abs(dx) == Math.abs(dy)) {
            return getDirectionByCurrent(dx, dy);
        }
        return null;
    }
}
