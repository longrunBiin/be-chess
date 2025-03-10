package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest extends TestUtil{
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
    @DisplayName("보드 위에 있는 기물들의 점수를 구한다.")
    void calculatePoint() {
        initEmptyBoardTest();

        assertThat(chessGame.calculatePoint(Color.BLACK)).isCloseTo(20.5, within(0.01));
        assertThat(chessGame.calculatePoint(Color.WHITE)).isCloseTo(8.5, within(0.01));

        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("보드 위에 남아있는 기물들을 점수 순으로 내림차순한다.")
    void sortPieces() {
        initEmptyBoardTest();

        List<Piece> sortedBlack = chessGame.sortPiece(Color.BLACK);
        List<Piece> sortedWhite = chessGame.sortPiece(Color.WHITE);

        assertThat(sortedBlack).extracting(Piece::getName).containsExactly(Type.QUEEN, Type.ROOK, Type.BISHOP, Type.KNIGHT, Type.PAWN, Type.KING);
        assertThat(sortedWhite).extracting(Piece::getName).containsExactly(Type.ROOK, Type.KNIGHT, Type.PAWN, Type.PAWN, Type.KING);
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
