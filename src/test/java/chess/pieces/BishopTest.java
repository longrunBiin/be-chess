package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.TestUtil;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest extends TestUtil {

    @Test
    @DisplayName("비숍은 대각선방향으로 이동할 수 있다.")
    void moveBishop() {
        initEmptyBoardTest();

        String sourcePosition = "a5";
        String targetPosition = "d2";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.BISHOP));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("이동하려는 방향에 같은 색이 있는 경우 이동하지 않는다.")
    void moveBishopToSameColor() {
        initEmptyBoardTest();

        String sourcePosition = "a5";
        String targetPosition = "b6";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.BISHOP));
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.PAWN));
    }

    @Test
    @DisplayName("이동하려는 방향이 체스판을 벗어날경우 움직이지 않고 에러를 출력한다.")
    void moveBishopToOutOfBoard() {
        initEmptyBoardTest();

        String sourcePosition = "a5";
        String targetPosition = "g0";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.BISHOP));
    }

    @Test
    @DisplayName("비숍이 잘못된 위치로 이동할 경우 에러를 발생시킨다.")
    void moveBishopFail() {
        initEmptyBoardTest();

        String sourcePosition = "a5";
        String targetPosition = "c5";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.BISHOP));

        System.out.println(chessView.showBoard());
    }
}
