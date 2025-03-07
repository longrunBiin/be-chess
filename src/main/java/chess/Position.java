package chess;

public class Position {
    private int xPos;
    private int yPos;

    public Position(String position) {
        xPos = position.charAt(0) - 'a';
        yPos = Character.getNumericValue(position.charAt(1));
        verifyBoardPosition(xPos, yPos);
    }

    private void verifyBoardPosition(int xPos, int yPos) {
        if (xPos < 0 || xPos > Board.MAX_BOARD || yPos < 0 || yPos > Board.MAX_BOARD)
            throw new IllegalArgumentException("이동 위치가 체스판을 벗어납니다");
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
