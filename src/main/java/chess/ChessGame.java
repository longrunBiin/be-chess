package chess;

import static chess.Board.MAX_BOARD;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChessGame {
    private final List<Rank> chessBoard;

    public ChessGame(List<Rank> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void move(String sourcePosition, String targetPosition) {
        Position startPos= new Position(sourcePosition);
        Position endPos= new Position(targetPosition);

        Rank startRank = chessBoard.get(MAX_BOARD - startPos.getYPos());
        Rank endRank = chessBoard.get(MAX_BOARD - endPos.getYPos());
        //시작위치에 있는 기물 가져오기
        Piece sourcePiece = startRank.getPieceByPosition(startPos.getXPos());

        //기물을 옮긴 후 시작 위치를 공백으로 설정
        endRank.movePiece(endPos.getXPos(), sourcePiece);
        startRank.movePiece(startPos.getXPos(), Piece.createBlank());

    }
    public double calculatePoint(Color color) {
        double sum = chessBoard.stream()
                .mapToDouble(rank -> rank.getPointByColor(color))
                .sum();

        int pawnCount = 0;
        for (int i = 0; i < MAX_BOARD; i++) {
            int count = 0;
            for (Rank rank : chessBoard) {
                count += rank.checkPawnCount(i, color);
            }
            if (count >= 2) pawnCount += count;
        }
        return sum - (pawnCount * 0.5);
    }

    public Piece findPiece(String position) {
        Position pos= new Position(position);

        Rank rank = chessBoard.get(MAX_BOARD - pos.getYPos());
        return rank.getPieceByPosition(pos.getXPos());
    }

    public List<Piece> sortPiece(Color color) {
        return chessBoard.stream()
                .flatMap(rank -> rank.sortByScore(color).stream())
                .sorted(Comparator.comparingDouble(Piece::getScore).reversed())
                .collect(Collectors.toList());

    }

    public void addPiece(String position, Piece piece) {
        Position pos= new Position(position);

        Rank rank = chessBoard.get(MAX_BOARD - pos.getYPos());
        rank.movePiece(pos.getXPos(), piece);
    }
    public int pieceCount() {
        return chessBoard.stream()
                .mapToInt(Rank::pieceCountPerRank)
                .sum();
    }
    //보드 위에 존재하는 특정 색, 종류의 기물 갯수 리턴
    public int pieceCountOnBoard(Color color, Type type) {
        return chessBoard.stream()
                .mapToInt(rank -> rank.pieceCountPerColorAndType(color, type))
                .sum();
    }
}
