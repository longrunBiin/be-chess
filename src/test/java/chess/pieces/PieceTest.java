package chess.pieces;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class PieceTest {
    @Test
    @DisplayName("모든 체스말의 색과 출력문자를 검증한다.")
    void create_piece() {
        verifyPiece(Piece.createWhite(Type.PAWN), Color.WHITE, Type.PAWN.getRepresentation(Color.WHITE));
        verifyPiece(Piece.createBlack(Type.PAWN), Color.BLACK, Type.PAWN.getRepresentation(Color.BLACK));

        verifyPiece(Piece.createWhite(Type.KNIGHT), Color.WHITE, Type.KNIGHT.getRepresentation(Color.WHITE));
        verifyPiece(Piece.createBlack(Type.KNIGHT), Color.BLACK, Type.KNIGHT.getRepresentation(Color.BLACK));

        verifyPiece(Piece.createWhite(Type.ROOK), Color.WHITE, Type.ROOK.getRepresentation(Color.WHITE));
        verifyPiece(Piece.createBlack(Type.ROOK), Color.BLACK, Type.ROOK.getRepresentation(Color.BLACK));

        verifyPiece(Piece.createWhite(Type.BISHOP), Color.WHITE, Type.BISHOP.getRepresentation(Color.WHITE));
        verifyPiece(Piece.createBlack(Type.BISHOP), Color.BLACK, Type.BISHOP.getRepresentation(Color.BLACK));

        verifyPiece(Piece.createWhite(Type.QUEEN), Color.WHITE, Type.QUEEN.getRepresentation(Color.WHITE));
        verifyPiece(Piece.createBlack(Type.QUEEN), Color.BLACK, Type.QUEEN.getRepresentation(Color.BLACK));

        verifyPiece(Piece.createWhite(Type.KING), Color.WHITE, Type.KING.getRepresentation(Color.WHITE));
        verifyPiece(Piece.createBlack(Type.KING), Color.BLACK, Type.KING.getRepresentation(Color.BLACK));

        verifyPiece(Piece.createBlank(), Color.NOCOLOR, Type.NO_PIECE.getRepresentation(Color.NOCOLOR));

    }

    @Test
    @DisplayName("말이 검은색인지 흰색인지 확인한다.")
    void checkColor() {
        Piece whitePawn = Piece.createWhite(Type.PAWN);
        Piece blackPawn = Piece.createBlack(Type.PAWN);

        assertThat(whitePawn.isWhite()).isTrue();
        assertThat(whitePawn.isBlack()).isFalse();

        assertThat(blackPawn.isBlack()).isTrue();
        assertThat(blackPawn.isWhite()).isFalse();
    }

    @Test
    @DisplayName("각 기물의 출력문자를 검사한다.")
    public void getRepresentationPerPiece() {
        assertThat(Type.PAWN.getRepresentation(Color.WHITE)).isEqualTo('♙');
        assertThat(Type.PAWN.getRepresentation(Color.BLACK)).isEqualTo('♟');

        assertThat(Type.KNIGHT.getRepresentation(Color.WHITE)).isEqualTo('♘');
        assertThat(Type.KNIGHT.getRepresentation(Color.BLACK)).isEqualTo('♞');

        assertThat(Type.ROOK.getRepresentation(Color.WHITE)).isEqualTo('♖');
        assertThat(Type.ROOK.getRepresentation(Color.BLACK)).isEqualTo('♜');

        assertThat(Type.BISHOP.getRepresentation(Color.WHITE)).isEqualTo('♗');
        assertThat(Type.BISHOP.getRepresentation(Color.BLACK)).isEqualTo('♝');

        assertThat(Type.QUEEN.getRepresentation(Color.WHITE)).isEqualTo('♕');
        assertThat(Type.QUEEN.getRepresentation(Color.BLACK)).isEqualTo('♛');

        assertThat(Type.KING.getRepresentation(Color.WHITE)).isEqualTo('♔');
        assertThat(Type.KING.getRepresentation(Color.BLACK)).isEqualTo('♚');

        assertThat(Type.NO_PIECE.getRepresentation(Color.WHITE)).isEqualTo('.');
    }

    @Test
    @DisplayName("공백 문자는 어떤 색도 가지지 않는다")
    void blankColorTest() {
        Piece blank = Piece.createBlank();

        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.isBlack()).isFalse();
    }
    void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getName().getRepresentation(color)).isEqualTo(representation);
    }
}
