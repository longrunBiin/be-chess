package chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        color = WHITE_COLOR;
    }

    public String getColor() {
        return color;
    }
}
