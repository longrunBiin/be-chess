package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.TestUtil;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest extends TestUtil {

    @Test
    @DisplayName("퀸은 직선과 대각선 방향으로 움직인다.")
    void moveQueen() {
        initEmptyBoardTest();

        String sourcePosition = "e6";
        String targetPosition = "e1";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.QUEEN));

        System.out.println(chessView.showBoard());

        sourcePosition = "e1";
        targetPosition = "a5";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.QUEEN));

        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("퀸이 잘못된 위치로 이동할 경우 에러를 발생시킨다.")
    void moveQueenFail() {
        initEmptyBoardTest();

        String sourcePosition = "e6";
        String targetPosition = "a1";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.QUEEN));

        System.out.println(chessView.showBoard());
    }

}
