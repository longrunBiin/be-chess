package chess.pieces;

import chess.Direction;

public class King extends Piece{

    public King(Type type, Color color) {
        super(type, color, Direction.everyDirection());
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {
        int dx = endX - startX;
        int dy = endY - startY;

        Direction moveDirection = findDirection(dx, dy);
        if (moveDirection == null || !directionList.contains(moveDirection)) {
            throw new IllegalArgumentException("킹은 자신을 중심으로 8방향으로 한칸씩 이동할 수 있습니다.");
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
