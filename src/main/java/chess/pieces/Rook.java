package chess.pieces;

import static chess.Board.MAX_BOARD;

import chess.Direction;
import chess.Position;
import chess.Rank;
import java.util.List;

public class Rook extends Piece{
    public Rook(Type type, Color color) {
        super(type, color, Direction.linearDirection());
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, List<Rank> chessBoard) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();

        if (dx == 0 && dy == 0) return;

        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection, sourcePiece);

        Position next = getNextPosition(startPos, moveDirection);
        Piece targetPiece = getTargetPiece(chessBoard, next);

        verifyNextPosition(next, endPos, sourcePiece, targetPiece);

        verifyMovePosition(next, endPos, sourcePiece, chessBoard);
    }

    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        // 직선 이동 체크
        if (dx == 0 || dy == 0) {
            return getDirectionByCurrent(dx, dy);
        }
        return null;
    }
}
