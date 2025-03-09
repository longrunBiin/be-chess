package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;

public abstract class TestUtil {
    protected Board board;
    protected ChessGame chessGame;
    protected ChessView chessView;

    @BeforeEach
    void init() {
        List<Rank> chessBoard = new ArrayList<>();
        board = new Board(chessBoard);
        chessGame = new ChessGame(chessBoard);
        chessView = new ChessView(chessBoard);
    }
    protected void initEmptyBoardTest() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlack(Type.PAWN));
        addPiece("e6", Piece.createBlack(Type.QUEEN));
        addPiece("b8", Piece.createBlack(Type.KING));
        addPiece("c8", Piece.createBlack(Type.ROOK));
        addPiece("a5", Piece.createBlack(Type.BISHOP));
        addPiece("a4", Piece.createBlack(Type.KNIGHT));

        addPiece("h2", Piece.createWhite(Type.KNIGHT));
        addPiece("g2", Piece.createWhite(Type.PAWN));
        addPiece("g3", Piece.createWhite(Type.PAWN));
        addPiece("e1", Piece.createWhite(Type.ROOK));
        addPiece("f1", Piece.createWhite(Type.KING));
    }
    protected void addPiece(String position, Piece piece) {
        chessGame.addPiece(position, piece);
    }
}
