package chess.pieces;

import static chess.Board.MAX_BOARD;

import chess.Direction;
import chess.Position;
import chess.Rank;
import java.util.List;

public class King extends Piece{

    public King(Type type, Color color) {
        super(type, color, Direction.everyDirection());
    }

    @Override
    public void verifyMovePosition(Position startPos, Position endPos, Piece sourcePiece, List<Rank> chessBoard) {
        int dx = endPos.getXPos() - startPos.getXPos();
        int dy = endPos.getYPos() - startPos.getYPos();

        Direction moveDirection = findDirection(dx, dy);
        checkPieceCanMove(moveDirection, sourcePiece);

        Position next = getNextPosition(startPos, moveDirection);
        Piece targetPiece = getTargetPiece(chessBoard, next);

        verifyNextPosition(next, endPos, sourcePiece, targetPiece);
    }
    @Override
    protected Direction findDirection(int dx, int dy) {
        if (dx == 0 && dy == 0) return null; // 자기 위치 유지

        return getDirectionByCurrentExactly(dx, dy);
    }
}
