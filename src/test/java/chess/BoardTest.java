package chess;

import static chess.utils.StringUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;

    @BeforeEach
    void init() {
        board = new Board();
    }
    @Test
    @DisplayName("보드가 초기화 되면 모든 말들이 생성되어야 한다.")
    void create() {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine("........");
        assertThat(appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") +
                appendNewLine("♖♘♗♕♔♗♘♖")).isEqualTo(board.showBoard());
    }

    @Test
    @DisplayName("보드초기화시 같은 색의 폰들이 한줄에 있어야한다.")
    void initialize() {
        board.initialize();
        assertThat(board.getPawnResult(Color.WHITE)).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(board.getPawnResult(Color.BLACK)).isEqualTo("♟♟♟♟♟♟♟♟");
    }

    @Test
    @DisplayName("색과 종류에 따른 보드 위에 존재하는 기물 갯수를 리턴한다.")
    void pieceCountOnBoard() {
        initializeBoardByString();
        int pawnCount = board.pieceCountOnBoard(Color.BLACK, Type.PAWN);
        int knightCount = board.pieceCountOnBoard(Color.WHITE, Type.KNIGHT);

        assertThat(pawnCount).isEqualTo(3);
        assertThat(knightCount).isEqualTo(1);
    }

    @Test
    @DisplayName("초기화 후 특정위치에 기물이 존재하는지 확인합니다.")
    void findPiece() {
        board.initialize();

        assertThat(board.findPiece("a8").getName()).isEqualTo(Type.ROOK);
        assertThat(board.findPiece("a8").getColor()).isEqualTo(Color.BLACK);
        assertThat(board.findPiece("h8").getName()).isEqualTo(Type.ROOK);
        assertThat(board.findPiece("h8").getColor()).isEqualTo(Color.BLACK);
        assertThat(board.findPiece("a1").getName()).isEqualTo(Type.ROOK);
        assertThat(board.findPiece("a1").getColor()).isEqualTo(Color.WHITE);
        assertThat(board.findPiece("h1").getName()).isEqualTo(Type.ROOK);
        assertThat(board.findPiece("h1").getColor()).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("임의의 위치에 기물이 추가 되어야한다.")
    void move(){
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlack(Type.ROOK);
        board.move(position, piece);

        assertThat(board.findPiece(position)).isEqualTo(piece);
        System.out.println(board.showBoard());
    }
    @Test
    @DisplayName("보드 위에 있는 기물들의 점수를 구한다.")
    void calculatePoint() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlack(Type.PAWN));
        addPiece("e6", Piece.createBlack(Type.QUEEN));
        addPiece("b8", Piece.createBlack(Type.KING));
        addPiece("c8", Piece.createBlack(Type.ROOK));

        addPiece("g2", Piece.createWhite(Type.PAWN));
        addPiece("g3", Piece.createWhite(Type.PAWN));
        addPiece("e1", Piece.createWhite(Type.ROOK));
        addPiece("f1", Piece.createWhite(Type.KING));

        assertThat(board.calculatePoint(Color.BLACK)).isCloseTo(15.0, within(0.01));
        assertThat(board.calculatePoint(Color.WHITE)).isCloseTo(6.0, within(0.01));

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("보드 위에 남아있는 기물들을 점수 순으로 내림차순한다.")
    void sortPieces() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlack(Type.PAWN));
        addPiece("e6", Piece.createBlack(Type.QUEEN));
        addPiece("b8", Piece.createBlack(Type.KING));
        addPiece("c8", Piece.createBlack(Type.ROOK));

        addPiece("g2", Piece.createWhite(Type.PAWN));
        addPiece("g3", Piece.createWhite(Type.PAWN));
        addPiece("e1", Piece.createWhite(Type.ROOK));
        addPiece("f1", Piece.createWhite(Type.KING));

        List<Piece> sortedBlack = board.sortPiece(Color.BLACK);
        List<Piece> sortedWhite = board.sortPiece(Color.WHITE);

        assertThat(sortedBlack).extracting(Piece::getName).containsExactly(Type.QUEEN, Type.ROOK, Type.PAWN, Type.KING);
        assertThat(sortedWhite).extracting(Piece::getName).containsExactly(Type.ROOK, Type.PAWN, Type.PAWN, Type.KING);
    }

    private void addPiece(String position, Piece piece) {
        board.move(position, piece);
    }

    private void initializeBoardByString() {
        String boardText = ".♚♜.....\n"
                + "♟.♟♝....\n"
                + ".♟..♛...\n"
                + "........\n"
                + ".....♘♕.\n"
                + ".....♙..\n"
                + "......♙.\n"
                + "....♖♔..";

        String[] split = boardText.split("\n");
        for (String s : split) {
            List<Piece> rank = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                Piece piece = Piece.createPieceByRepresentation(s.charAt(i));
                rank.add(piece);
            }
            board.addPiece(new Rank(rank));
        }
    }

}