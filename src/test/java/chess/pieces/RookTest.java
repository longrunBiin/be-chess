package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.TestUtil;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest extends TestUtil {

    @Test
    @DisplayName("룩은 직선방향으로 이동할 수 있다.")
    void moveRook() {
        initEmptyBoardTest();

        String sourcePosition = "c8";
        String targetPosition = "c5";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.ROOK));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("이동하려는 방향에 같은 색이 있는 경우 이동하지 않는다.")
    void moveRookToSameColor() {
        initEmptyBoardTest();

        String sourcePosition = "c8";
        String targetPosition = "b8";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.ROOK));
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.KING));
    }

    @Test
    @DisplayName("이동하려는 방향이 체스판을 벗어날경우 움직이지 않고 에러를 출력한다.")
    void moveRookToOutOfBoard() {
        initEmptyBoardTest();

        String sourcePosition = "c8";
        String targetPosition = "c9";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.ROOK));
    }

    @Test
    @DisplayName("룩이 잘못된 위치로 이동할 경우 에러를 발생시킨다.")
    void moveRookFail() {
        initEmptyBoardTest();

        String sourcePosition = "c8";
        String targetPosition = "a1";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.ROOK));

        System.out.println(chessView.showBoard());
    }
}
