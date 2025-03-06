package chess.pieces;

public class Piece {
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";
    public static final char WHITE_PAWN_REPRESENTATION = '♗';
    public static final char BLACK_PAWN_REPRESENTATION = '♟';
    public static final char WHITE_KNIGHT_REPRESENTATION = '♘';
    public static final char BLACK_KNIGHT_REPRESENTATION = '♞';
    public static final char WHITE_ROOK_REPRESENTATION = '♖';
    public static final char BLACK_ROOK_REPRESENTATION = '♜';
    public static final char WHITE_BISHOP_REPRESENTATION = '♗';
    public static final char BLACK_BISHOP_REPRESENTATION = '♝';
    public static final char WHITE_QUEEN_REPRESENTATION = '♕';
    public static final char BLACK_QUEEN_REPRESENTATION = '♛';
    public static final char WHITE_KING_REPRESENTATION = '♔';
    public static final char BLACK_KING_REPRESENTATION = '♚';
    public static final String CHESS_PAWN = "pawn";
    public static final String CHESS_KNIGHT = "knight";
    public static final String CHESS_ROOK = "rook";
    public static final String CHESS_BISHOP = "bishop";
    public static final String CHESS_QUEEN = "queen";
    public static final String CHESS_KING = "king";
    private String name;
    private String color;
    private char representation;

    private Piece(String name, String color, char representation) {
        this.name = name;
        this.color = color;
        this.representation = representation;
    }

    private Piece() {
    }

    public static Piece createWhitePawn() {
        return new Piece(CHESS_PAWN, WHITE_COLOR, WHITE_PAWN_REPRESENTATION);
    }
    public static Piece createBlackPawn() {
        return new Piece(CHESS_PAWN, BLACK_COLOR, BLACK_PAWN_REPRESENTATION);
    }
    public static Piece createWhiteKnight() {
        return new Piece(CHESS_KNIGHT, WHITE_COLOR, WHITE_KNIGHT_REPRESENTATION);
    }
    public static Piece createBlackKnight() {
        return new Piece(CHESS_KNIGHT, BLACK_COLOR, BLACK_KNIGHT_REPRESENTATION);
    }
    public static Piece createWhiteRook() {
        return new Piece(CHESS_ROOK, WHITE_COLOR, WHITE_ROOK_REPRESENTATION);
    }
    public static Piece createBlackRook() {
        return new Piece(CHESS_ROOK, BLACK_COLOR, BLACK_ROOK_REPRESENTATION);
    }
    public static Piece createWhiteBishop() {
        return new Piece(CHESS_BISHOP, WHITE_COLOR, WHITE_BISHOP_REPRESENTATION);
    }
    public static Piece createBlackBishop() {
        return new Piece(CHESS_BISHOP, BLACK_COLOR, BLACK_BISHOP_REPRESENTATION);
    }
    public static Piece createWhiteQueen() {
        return new Piece(CHESS_QUEEN, WHITE_COLOR, WHITE_QUEEN_REPRESENTATION);
    }
    public static Piece createBlackQueen() {
        return new Piece(CHESS_QUEEN, BLACK_COLOR, BLACK_QUEEN_REPRESENTATION);
    }
    public static Piece createWhiteKing() {
        return new Piece(CHESS_KING, WHITE_COLOR, WHITE_KING_REPRESENTATION);
    }
    public static Piece createBlackKing() {
        return new Piece(CHESS_KING, BLACK_COLOR, BLACK_KING_REPRESENTATION);
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public boolean isWhite() {
        return color.equals(WHITE_COLOR);
    }
    public boolean isBlack() {
        return color.equals(BLACK_COLOR);
    }
}
