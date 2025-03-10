package chess;

import static chess.utils.StringUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest extends TestUtil{

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

}