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
    private ChessGame chessGame;
    private ChessView chessView;

    @BeforeEach
    void init() {
        List<Rank> chessBoard = new ArrayList<>();
        board = new Board(chessBoard);
        chessGame = new ChessGame(chessBoard);
        chessView = new ChessView(chessBoard);
    }
    @Test
    @DisplayName("보드가 초기화 되면 모든 말들이 생성되어야 한다.")
    void create() {
        board.initialize();
        assertThat(chessGame.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine("........");
        assertThat(appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♙♙♙♙♙♙♙♙") +
                appendNewLine("♖♘♗♕♔♗♘♖")).isEqualTo(chessView.showBoard());
    }

    @Test
    @DisplayName("보드초기화시 같은 색의 폰들이 한줄에 있어야한다.")
    void initialize() {
        board.initialize();
        assertThat(chessView.getPawnResult(Color.WHITE)).isEqualTo("♙♙♙♙♙♙♙♙");
        assertThat(chessView.getPawnResult(Color.BLACK)).isEqualTo("♟♟♟♟♟♟♟♟");
    }

    @Test
    @DisplayName("색과 종류에 따른 보드 위에 존재하는 기물 갯수를 리턴한다.")
    void pieceCountOnBoard() {
        initializeBoardByString();
        int pawnCount = chessGame.pieceCountOnBoard(Color.BLACK, Type.PAWN);
        int knightCount = chessGame.pieceCountOnBoard(Color.WHITE, Type.KNIGHT);

        assertThat(pawnCount).isEqualTo(3);
        assertThat(knightCount).isEqualTo(1);
    }

    @Test
    @DisplayName("초기화 후 특정위치에 기물이 존재하는지 확인합니다.")
    void findPiece() {
        board.initialize();

        assertThat(chessGame.findPiece("a8")).isEqualTo(Piece.createBlack(Type.ROOK));
        assertThat(chessGame.findPiece("h8")).isEqualTo(Piece.createBlack(Type.ROOK));
        assertThat(chessGame.findPiece("a1")).isEqualTo(Piece.createWhite(Type.ROOK));
        assertThat(chessGame.findPiece("h1")).isEqualTo(Piece.createWhite(Type.ROOK));
    }

    @Test
    @DisplayName("시작 위치에 있는 기물이 목표 위치로 이동되어야한다..")
    void move(){
        board.initialize();

        String sourcePosition = "b2";
        String targetPosition = "b3";
        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createWhite(Type.PAWN));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("킹은 8방향으로 한칸씩 이동할 수 있다.")
    void moveKing() {
        initEmptyBoardTest();

        String sourcePosition = "b8";
        String targetPosition = "c7";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createWhite(Type.KING));
    }

    @Test
    @DisplayName("이동하려는 방향에 같은 색이 있는 경우 이동하지 않는다.")
    void moveKingToSameColor() {
        initEmptyBoardTest();

        String sourcePosition = "b8";
        String targetPosition = "c8";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.KING));
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createWhite(Type.ROOK));
    }


    private void initEmptyBoardTest() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlack(Type.PAWN));
        addPiece("e6", Piece.createBlack(Type.QUEEN));
        addPiece("b8", Piece.createBlack(Type.KING));
        addPiece("c8", Piece.createBlack(Type.ROOK));

        addPiece("g2", Piece.createWhite(Type.PAWN));
        addPiece("g3", Piece.createWhite(Type.PAWN));
        addPiece("e1", Piece.createWhite(Type.ROOK));
        addPiece("f1", Piece.createWhite(Type.KING));
    }

    @Test
    @DisplayName("보드 위에 있는 기물들의 점수를 구한다.")
    void calculatePoint() {
        initEmptyBoardTest();

        assertThat(chessGame.calculatePoint(Color.BLACK)).isCloseTo(15.0, within(0.01));
        assertThat(chessGame.calculatePoint(Color.WHITE)).isCloseTo(6.0, within(0.01));

        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("보드 위에 남아있는 기물들을 점수 순으로 내림차순한다.")
    void sortPieces() {
        initEmptyBoardTest();

        List<Piece> sortedBlack = chessGame.sortPiece(Color.BLACK);
        List<Piece> sortedWhite = chessGame.sortPiece(Color.WHITE);

        assertThat(sortedBlack).extracting(Piece::getName).containsExactly(Type.QUEEN, Type.ROOK, Type.PAWN, Type.KING);
        assertThat(sortedWhite).extracting(Piece::getName).containsExactly(Type.ROOK, Type.PAWN, Type.PAWN, Type.KING);
    }

    private void addPiece(String position, Piece piece) {
        chessGame.addPiece(position, piece);
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
            board.addRank(new Rank(rank));
        }
    }

}