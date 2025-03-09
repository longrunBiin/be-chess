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
        if (dx != 0 && dy != 0 && Math.abs(endY - startY) != Math.abs(endX - startX))
            throw new IllegalArgumentException("퀸은 직선과 대각선으로만 이동할 수 있습니다.");

        int xDirection = getDirection(endX, startX);
        int yDirection = getDirection(endY, startY);

        verifyMovePosition(startX + xDirection, startY + yDirection, endX, endY);
    }
    private int getDirection(int end, int start) {
        if (end-start > 0) return 1;
        else if (end - start < 0) return -1;
        return 0;
    }

}
