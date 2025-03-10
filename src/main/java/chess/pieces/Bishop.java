package chess.pieces;

import chess.Direction;
import chess.Position;

public class Bishop extends Piece{
    public Bishop(Type type, Color color) {
        super(type, color, Direction.diagonalDirection());
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, Piece targetPiece) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();

        if (dx == 0 && dy == 0) return;

        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection);

        verifyPieceAlreadyOnBoard(sourcePiece, targetPiece);
        Position next = new Position(startPos.getXPos() + moveDirection.getXDegree(),
                startPos.getYPos() + moveDirection.getYDegree());
        verifyMovePosition(next, endPos, sourcePiece, targetPiece);
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
