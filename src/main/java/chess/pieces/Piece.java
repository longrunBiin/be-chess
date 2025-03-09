package chess.pieces;

import chess.Direction;
import java.util.List;
import java.util.Objects;

abstract public class Piece {
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
    protected List<Direction> directionList;

    Piece(Type name, Color color, List<Direction> directionList) {
        this.name = name;
        this.color = color;
        this.directionList = directionList;
    }

    private Piece() {
    }

    public static Piece createBlank() {
        return new Blank();
    }

    public static Piece createWhite(Type type) {
        return switch (type) {
            case KING -> new King(type, Color.WHITE);
            case QUEEN -> new Queen(type, Color.WHITE);
            case ROOK -> new Rook(type, Color.WHITE);
            case BISHOP -> new Bishop(type, Color.WHITE);
            case KNIGHT -> new Knight(type, Color.WHITE);
            case PAWN -> new Pawn(type, Color.WHITE);
            default -> new Blank();
        };
    }
    public static Piece createBlack(Type type) {
        return switch (type) {
            case KING -> new King(type, Color.BLACK);
            case QUEEN -> new Queen(type, Color.BLACK);
            case ROOK -> new Rook(type, Color.BLACK);
            case BISHOP -> new Bishop(type, Color.BLACK);
            case KNIGHT -> new Knight(type, Color.BLACK);
            case PAWN -> new Pawn(type, Color.BLACK);
            default -> new Blank();
        };
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
    abstract public void verifyMovePosition(int startX, int startY, int endX, int endY);

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
