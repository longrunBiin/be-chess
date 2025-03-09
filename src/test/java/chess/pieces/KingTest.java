package chess.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.Board;
import chess.ChessGame;
import chess.ChessView;
import chess.Rank;
import chess.TestUtil;
import chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest extends TestUtil {

    @Test
    @DisplayName("킹은 8방향으로 한칸씩 이동할 수 있다.")
    void moveKing() {
        initEmptyBoardTest();

        String sourcePosition = "b8";
        String targetPosition = "c7";

        chessGame.move(sourcePosition, targetPosition);

        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlank());
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.KING));
        System.out.println(chessView.showBoard());
    }

    @Test
    @DisplayName("이동하려는 방향에 같은 색이 있는 경우 이동하지 않는다.")
    void moveKingToSameColor() {
        initEmptyBoardTest();

        String sourcePosition = "b8";
        String targetPosition = "c8";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.KING));
        assertThat(chessGame.findPiece(targetPosition)).isEqualTo(Piece.createBlack(Type.ROOK));
    }

    @Test
    @DisplayName("이동하려는 방향이 체스판을 벗어날경우 움직이지 않고 에러를 출력한다.")
    void moveKingToOutOfBoard() {
        initEmptyBoardTest();

        String sourcePosition = "b8";
        String targetPosition = "b9";

        assertThrows(IllegalArgumentException.class, () ->chessGame.move(sourcePosition, targetPosition));
        assertThat(chessGame.findPiece(sourcePosition)).isEqualTo(Piece.createBlack(Type.KING));
    }
}
