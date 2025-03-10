package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.TestUtil;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest extends TestUtil {

    @Test
    @DisplayName("나이트는 직선으로 이동 후 대각선으로 이동한다.")
    void moveKnight() {
        initEmptyBoardTest();

        String sourcePosition = "a4";
        String targetPosition = "c3";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.KNIGHT));
        chessGame.endTurn();

        sourcePosition = "c3";
        targetPosition = "d1";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.KNIGHT));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("이동하려는 방향에 같은 색이 있는 경우 이동하지 않는다.")
    void moveKnightToSameColor() {
        initEmptyBoardTest();

        String sourcePosition = "a4";
        String targetPosition = "b6";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.KNIGHT));
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.PAWN));
    }

    @Test
    @DisplayName("이동하려는 방향이 체스판을 벗어날경우 움직이지 않고 에러를 출력한다.")
    void moveKnightToOutOfBoard() {
        initEmptyBoardTest();

        String sourcePosition = "h2";
        String targetPosition = "i3";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createWhite(Type.KNIGHT));
    }

    @Test
    @DisplayName("나이트가 잘못된 위치로 이동할 경우 에러를 발생시킨다.")
    void moveKnightFail() {
        initEmptyBoardTest();

        String sourcePosition = "a4";
        String targetPosition = "a1";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.KNIGHT));

        System.out.println(chessView.showBoard());
    }
}
