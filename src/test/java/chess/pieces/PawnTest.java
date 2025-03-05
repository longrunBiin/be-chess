package chess.pieces;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class PawnTest {
    @Test
    @DisplayName("흰색, 검은색 폰이 생성되어야 한다")
    void create() {
        String[] colors = new String[]{Pawn.WHITE_COLOR, Pawn.BLACK_COLOR};
        char[] representations = new char[]{Pawn.WHITE_REPRESENTATION, Pawn.BLACK_REPRESENTATION};
        for (int i = 0; i < colors.length; i++) {
            verifyPawn(colors[i], representations[i]);
        }

    }
    @Test
    @DisplayName("기본생성자로 생성시 흰색 폰이 생성되어야 한다.")
    void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertThat(pawn.getColor()).isEqualTo(Pawn.WHITE_COLOR);
        assertThat(pawn.getRepresentation()).isEqualTo(Pawn.WHITE_REPRESENTATION);
    }
    void verifyPawn(final String color, final char representation) {
        Pawn pawn = new Pawn(color, representation);
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}
