package chess.pieces;

public class Pawn {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_REPRESENTATION = 'p';
    public static final char BLACK_REPRESENTATION = 'P';
    private final String color;
    private char representation;

    public Pawn(String color, char representation) {
        this.color = color;
        this.representation = representation;
    }
    public Pawn(String color) {
        this.color = color;
    }
    public Pawn() {
        color = WHITE_COLOR;
        representation = 'p';
    }


    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }
}
