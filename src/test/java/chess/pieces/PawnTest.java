package chess.pieces;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.pieces.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class PawnTest {
    @Test
    @DisplayName("흰색, 검은색 폰이 생성되어야 한다")
    public void create() {
        String[] colors = new String[]{Pawn.WHITE_COLOR, Pawn.BLACK_COLOR};
        for (String color : colors) {
            verifyPawn(color);
        }
    }
    @Test
    @DisplayName("기본생성자로 생성시 흰색 폰이 생성되어야 한다.")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
    }
    private void verifyPawn(String color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}
