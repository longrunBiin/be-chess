import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class PawnTest {
    @Test
    @DisplayName("흰색, 검은색 폰이 생성되어야 한다")
    public void create() {
        String white = "white";
        String black = "black";
        Pawn pawn = new Pawn(white);
        Pawn pawn2 = new Pawn(black);
        assertThat(pawn.getColor()).isEqualTo(white);
        assertThat(pawn2.getColor()).isEqualTo(black);
    }
}
