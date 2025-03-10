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

        Position next = new Position(startPos.getXPos() + moveDirection.getXDegree(),
                startPos.getYPos() + moveDirection.getYDegree());

        Rank endRank = chessBoard.get(MAX_BOARD - next.getYPos());
        Piece targetPiece = endRank.getPieceByPosition(next.getXPos());
        verifyPieceAlreadyOnBoard(sourcePiece, targetPiece);

        if (!hasMoved) {
            if (Math.abs(dy) == 2 && dx == 0) {
                // 2칸 전진 이동 (첫 번째만 가능)
                hasMoved = true;
                return;
            }
        }

        // 이후 이동은 한 칸씩만 가능
        if (Math.abs(dy) == 1 && dx == 0) {
            return;
        }

        // 대각선 이동은 적을 잡을 때만 가능
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            return;
        }

        throw new IllegalArgumentException("폰의 이동 규칙을 위반했습니다.");
    }
    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        return getDirectionByCurrentExactly(dx, dy);
    }


}


