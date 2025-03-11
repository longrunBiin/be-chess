package chess.pieces;

import static chess.Board.MAX_BOARD;

import chess.Direction;
import chess.Position;
import chess.Rank;
import java.util.List;

public class Pawn extends Piece{
    private boolean hasMoved;  // 폰이 이미 움직였는지 여부
    public Pawn(Type type, Color color) {
        super(type, color, getDirectionByColor(color));
        this.hasMoved = false;
    }

    private static List<Direction> getDirectionByColor(Color color) {
        if (color.equals(Color.WHITE)) return Direction.whitePawnDirection();
        return Direction.blackPawnDirection();
    }
    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, List<Rank> chessBoard) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();

        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection, sourcePiece);

        Position nextPosition = getNextPosition(startPos, moveDirection);
        Piece targetPiece = getTargetPiece(chessBoard, nextPosition);

        verifyPawnMeetEnemy(dx, targetPiece, dy);
        verifyNextPosition(nextPosition, endPos, sourcePiece, targetPiece);

        if (!hasMoved) {
            if (Math.abs(dy) == 2 && dx == 0) {
                // 2칸 전진 이동 (첫 번째만 가능)
                hasMoved = true;
            }
        }
    }

    private void verifyPawnMeetEnemy(int dx, Piece targetPiece, int dy) {
        // 정면 이동일 경우, 목표 위치에 적이 있으면 이동 불가
        if (dx == 0 && targetPiece != null && targetPiece.getColor() != Color.NOCOLOR) {
            throw new IllegalArgumentException("폰은 정면에 있는 적을 잡을 수 없습니다.");
        }

        // 대각선 이동일 경우, 목표 위치에 적이 반드시 있어야 함
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            if (targetPiece == null || targetPiece.getColor() == Color.NOCOLOR) {
                throw new IllegalArgumentException("폰은 대각선으로만 적을 잡을 수 있습니다.");
            }
        }
    }

    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        return getDirectionByCurrentExactly(dx, dy);
    }


}


