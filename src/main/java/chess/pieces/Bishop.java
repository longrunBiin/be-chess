package chess.pieces;

import static chess.Board.MAX_BOARD;

import chess.Direction;
import chess.Position;
import chess.Rank;
import java.util.List;

public class Bishop extends Piece{
    public Bishop(Type type, Color color) {
        super(type, color, Direction.diagonalDirection());
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

        // 대각선 이동 체크
        if (Math.abs(dx) == Math.abs(dy)) {
            return getDirectionByCurrent(dx, dy);
        }
        return null;
    }
}
