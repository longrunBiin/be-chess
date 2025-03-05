package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    private Board board;
    private String[] colors;

    @BeforeEach
    void init() {
        board = new Board();
        colors = new String[]{Pawn.WHITE_COLOR, Pawn.BLACK_COLOR};
    }
    @Test
    @DisplayName("폰이 추가될 Pawn의 수와 마지막에 추가된 폰이 맞는지 확인")
    void create() throws Exception {
        for (int i = 0; i < colors.length; i++) {
            verifyBoard(i);
        }

//        board.add(new Integer(7));
    }

    @Test
    @DisplayName("보드초기화시 같은 색의 폰들이 한줄에 있어야한다.")
    public void initialize() throws Exception {
        Board board = new Board();
        board.initialize();
        assertThat(board.getWhitePawnsResult()).isEqualTo("pppppppp");
        assertThat(board.getBlackPawnsResult()).isEqualTo("PPPPPPPP");
    }

    private void verifyBoard(int i) {
        Pawn pawn = new Pawn(colors[i]);
        board.add(pawn);

        assertThat(board.size()).isEqualTo(i+1);
        assertThat(board.findPawn(i)).isEqualTo(pawn);
    }
}