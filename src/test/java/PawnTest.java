import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class PawnTest {
    @Test
    @DisplayName("흰색, 검은색 폰이 생성되어야 한다")
    public void create() {
        String[] colors = new String[]{"white", "black"};
        for (String color : colors) {
            verifyPawn(color);
        }
    }

    private void verifyPawn(String color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo("color");
    }
}
