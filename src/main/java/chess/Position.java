package chess;

public class Position {
    private int xPos;
    private int yPos;

    public Position(String position) {
        xPos = position.charAt(0) - 'a';
        yPos = Character.getNumericValue(position.charAt(1));
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
