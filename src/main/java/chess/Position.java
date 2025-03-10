package chess;

import java.util.Objects;

public class Position {
    private int xPos;
    private int yPos;

    public Position(String position) {
        xPos = position.charAt(0) - 'a';
        yPos = Character.getNumericValue(position.charAt(1));
        verifyBoardPosition(xPos, yPos);
    }

    public Position(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    private void verifyBoardPosition(int xPos, int yPos) {
        if (xPos < 0 || xPos > Board.MAX_BOARD - 1 || yPos <= 0 || yPos > Board.MAX_BOARD)
            throw new IllegalArgumentException("이동 위치가 체스판을 벗어납니다");
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Position position = (Position) object;
        return xPos == position.xPos && yPos == position.yPos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos);
    }
}
