package chess;

import static chess.utils.StringUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;
    private String[] colors;

    @BeforeEach
    void init() {
        board = new Board();
        colors = new String[]{Piece.WHITE_COLOR, Piece.BLACK_COLOR};
    }
    @Test
    @DisplayName("보드가 초기화 되면 모든 말들이 생성되어야 한다.")
    void create() throws Exception {
        board.initialize();
        assertThat(board.pieceCount()).isEqualTo(32);
        String blankRank = appendNewLine("........");
        assertThat(appendNewLine("♜♞♝♛♚♝♞♜") +
                appendNewLine("♟♟♟♟♟♟♟♟") +
                blankRank + blankRank + blankRank + blankRank +
                appendNewLine("♗♗♗♗♗♗♗♗") +
                appendNewLine("♖♘♗♕♔♗♘♖")).isEqualTo(board.showBoard());
    }

    @Test
    @DisplayName("보드초기화시 같은 색의 폰들이 한줄에 있어야한다.")
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertThat(board.getPawnResult(Piece.WHITE_COLOR)).isEqualTo("pppppppp");
        assertThat(board.getPawnResult(Piece.BLACK_COLOR)).isEqualTo("PPPPPPPP");
    }


}