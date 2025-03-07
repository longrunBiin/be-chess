package chess.pieces;

import java.util.Objects;

public class Piece {
    public enum Color {
        WHITE, BLACK, NOCOLOR;
    }

    public enum Type {
        PAWN('♙', 1.0),
        ROOK('♖', 5.0),
        KNIGHT('♘', 2.5),
        BISHOP('♗', 3.0),
        QUEEN('♕', 9.0),
        KING('♔', 0.0),
        NO_PIECE('.', 0.0);

        private char representation;
        private double defaultPoint;

        Type(char representation, double defaultPoint) {
            this.representation = representation;
            this.defaultPoint = defaultPoint;
        }

        public char getRepresentation(Color color) {
            if (color.equals(Color.BLACK)) {
                return getBlackRepresentation();
            }
            return representation;
        }

        private char getBlackRepresentation() {
            if (this == PAWN) {
                return '♟';  // U+265F (검은색 폰)
            }
            //폰을 제외한 문자들은 하얀색 + 6 -> 검은색
            return (char) (representation + 6);
        }

        public double getDefaultPoint() {
            return defaultPoint;
        }
    }

    private Type name;
    private Color color;

    private Piece(Type name, Color color) {
        this.name = name;
        this.color = color;
    }

    private Piece() {
    }

    public static Piece createBlank() {
        return new Piece(Type.NO_PIECE, Color.NOCOLOR);
    }

    public static Piece createWhite(Type type) {
        return new Piece(type, Color.WHITE);
    }
    public static Piece createBlack(Type type) {
        return new Piece(type, Color.BLACK);
    }
    public static Piece createPieceByRepresentation(char representation) {
        for (Type value : Type.values()) {
            if (value.getRepresentation(Color.WHITE) == representation){
                return Piece.createWhite(value);
            }
            if (value.getRepresentation(Color.BLACK) == representation){
                return Piece.createBlack(value);
            }
        }
        return Piece.createBlank();
    }
    public Type getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
    public double getScore(){
        return name.getDefaultPoint();
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }
    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Piece piece = (Piece) obj;
        return color == piece.color && name == piece.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}
