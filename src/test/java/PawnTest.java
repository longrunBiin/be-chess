import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("지정된 색상의 폰이 생성되어야 한다.")
    void create() {
        final String white = "white";
        final String black = "black";

        verifyPawn(white);
        verifyPawn(black);
    }

    void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}