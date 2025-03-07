package chess.pieces;

import chess.Direction;

public class King extends Piece{

    public King(Type type, Color color) {
        super(type, color, Direction.everyDirection());
    }

    @Override
    public void verifyMovePosition(int startX, int startY, int endX, int endY) {
        for (Direction direction : directionList) {
            if (endX - startX == direction.getXDegree() && endY - startY == direction.getYDegree())
                return;
        }
        throw new IllegalArgumentException("킹은 자신을 중심으로 8방향으로 한칸만 움직일 수 있습니다.");
    }
}
