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
        if (moveDirection == null || !directionList.contains(moveDirection)) {
            throw new IllegalArgumentException("나이트는 한칸 전진 후 대각선으로 이동할 수 있습니다.");
        }
    }
    private Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        return directionList.stream()
                .filter(d -> (d.getXDegree() == dx && d.getYDegree() == dy))
                .findFirst()
                .orElse(null);
    }
}
