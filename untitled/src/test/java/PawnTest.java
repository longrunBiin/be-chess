import org.example.Color;
import org.example.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {


    @DisplayName("하얀색, 검은색 폰을 생성한다.")
    @Test
    void test1(){

        veryify(Color.BLACK);
        veryify(Color.WHITE);

    }
        Pawn create(Color color){
        return new Pawn(color);
        }

        void veryify(final Color color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.color()).isEqualTo(color);
        }


}
