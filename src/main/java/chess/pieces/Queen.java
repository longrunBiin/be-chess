package chess.pieces;

import static chess.Board.MAX_BOARD;

import chess.Direction;
import chess.Position;
import chess.Rank;
import java.util.List;

public class Queen extends Piece{
    public Queen(Type type, Color color) {
        super(type, color, Direction.everyDirection());
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, List<Rank> chessBoard) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();

        if (dx == 0 && dy == 0) return;
        //직선으로만 움직이거나 1씩 증가하는 대가선으로만 움직이는 경우
        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection, sourcePiece);

        Position next = new Position(startPos.getXPos() + moveDirection.getXDegree(),
                startPos.getYPos() + moveDirection.getYDegree());

        Rank endRank = chessBoard.get(MAX_BOARD - next.getYPos());
        Piece targetPiece = endRank.getPieceByPosition(next.getXPos());
        verifyPieceAlreadyOnBoard(sourcePiece, targetPiece);

        verifyMovePosition(next, endPos, sourcePiece, chessBoard);
    }

    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0)
            return null; // 자기 위치 유지

        if (dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy)) {
            return getDirectionByCurrent(dx, dy);
        }

        return null;
    }
}
