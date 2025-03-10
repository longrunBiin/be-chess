package chess;

import static chess.Board.MAX_BOARD;

import chess.pieces.Piece.Color;
import chess.utils.StringUtils;
import java.util.List;

public class ChessView {
    private final List<Rank> chessBoard;

    public ChessView(List<Rank> chessBoard) {
        this.chessBoard = chessBoard;
    }
    public String getPawnResult(Color color){
        StringBuilder result = new StringBuilder();
        for (Rank rank : chessBoard) {
            result.append(rank.getPawnString(color));
        }
        return result.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (Rank rank : chessBoard) {
            for (int j = 0; j < MAX_BOARD; j++) {
                sb.append(rank.getRepresentationByRank(j));
            }
            sb.append(StringUtils.appendNewLine(""));
        }
        return sb.toString();
    }
}
