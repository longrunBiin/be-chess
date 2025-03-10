package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.TestUtil;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest extends TestUtil {

    @Test
    @DisplayName("폰은 정면방향으로 한칸 이동할 수 있다.")
    void movePawn() {
        initEmptyBoardTest();


        String sourcePosition = "b6";
        String targetPosition = "b5";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.PAWN));
        System.out.println(chessView.showBoard());
    }
    @Test
    @DisplayName("폰은 시작위치인 경우 정면방향으로 두칸 이동할 수 있다.")
    void movePawnAtStart() {
        initEmptyBoardTest();
        addPiece("c7", Piece.createBlack(Type.PAWN));

        String sourcePosition = "c7";
        String targetPosition = "c5";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.PAWN));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("이동하려는 방향에 같은 색이 있는 경우 이동하지 않는다.")
    void movePawnToSameColor() {
        initEmptyBoardTest();
        addPiece("a6", Piece.createBlack(Type.PAWN));

        String sourcePosition = "a6";
        String targetPosition = "a5";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.PAWN));
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.BISHOP));
    }

    @Test
    @DisplayName("폰이 잘못된 위치로 이동할 경우 에러를 발생시킨다.")
    void moveRookFail() {
        initEmptyBoardTest();

        String sourcePosition = "b6";
        String targetPosition = "a6";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.PAWN));

        System.out.println(chessView.showBoard());
    }
    @Test
    @DisplayName("폰이 적을 잡는경우 대각선으로 이동한다.")
    void pawnCaptureEnemy() {
        initEmptyBoardTest();
        addPiece("d2", Piece.createBlack(Type.PAWN));

        String sourcePosition = "d2";
        String targetPosition = "e1";

        chessGame.move(sourcePosition, targetPosition);
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.PAWN));

        System.out.println(chessView.showBoard());
    }
    @Test
    @DisplayName("적이 없는 경우 대각선으로 이동할 수 없다.")
    void pawnCaptureEnemyFail() {
        initEmptyBoardTest();
        addPiece("d2", Piece.createBlack(Type.PAWN));

        String sourcePosition = "d2";
        String targetPosition = "c1";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.PAWN));

        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("정면에 있는 적을 잡을 수 없다.")
    void pawnCaptureFrontalEnemyFail() {
        initEmptyBoardTest();
        addPiece("g4", Piece.createBlack(Type.PAWN));

        String sourcePosition = "g4";
        String targetPosition = "g3";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.PAWN));

        System.out.println(chessView.showBoard());
    }
}
