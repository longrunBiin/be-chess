package chess.pieces;

import chess.Direction;

public class Knight extends Piece{
    public Knight(Type type, Color color) {
        super(type, color, Direction.knightDirection());
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {
        int dx = endX - startX;
        int dy = endY - startY;

        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection);
    }
    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        return getDirectionByCurrentExactly(dx, dy);
    }
}
