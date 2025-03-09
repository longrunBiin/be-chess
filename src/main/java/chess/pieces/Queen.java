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
        if (moveDirection == null || !directionList.contains(moveDirection)) {
            throw new IllegalArgumentException("퀸은 직선과 대각선으로만 이동할 수 있습니다.");
        }

        verifyMovePosition(startX + moveDirection.getXDegree(), startY + moveDirection.getYDegree(), endX, endY);
    }
    private Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        // 직선 이동 체크
        if (dx == 0 || dy == 0) {
            return Direction.everyDirection().stream()
                    .filter(d -> (d.getXDegree() == Integer.signum(dx) && d.getYDegree() == Integer.signum(dy)))
                    .findFirst()
                    .orElse(null);
        }

        // 대각선 이동 체크
        if (Math.abs(dx) == Math.abs(dy)) {
            return Direction.everyDirection().stream()
                    .filter(d -> (d.getXDegree() == Integer.signum(dx) && d.getYDegree() == Integer.signum(dy)))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
