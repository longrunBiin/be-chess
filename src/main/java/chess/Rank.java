package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Rank {
    private final List<Piece> rank;

    public Rank(List<Piece> rank) {
        this.rank = rank;
    }

    //랭크 당 기물의 갯수 리턴
    public int pieceCountPerRank() {
        return (int) rank.stream()
                .filter(piece -> !piece.getName().equals(Type.NO_PIECE))
                .count();
    }
    public int pieceCountPerColorAndType(Color color, Type type) {
        return (int) rank.stream()
                .filter(piece -> piece.getColor().equals(color))
                .filter(piece -> piece.getName().equals(type))
                .count();
    }
    public String getPawnString(Color color) {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : rank) {
            if (sb.length() == 8) break;
            if (piece.getColor().equals(color) && piece.getName().equals(Type.PAWN))
                sb.append(piece.getName().getRepresentation(color));
        }
        return sb.toString();
    }

    //랭크 특정 위치의 출력문자를 리턴
    public char getRepresentationByRank(int index) {
        Color color = rank.get(index).getColor();
        Type name = rank.get(index).getName();

        return name.getRepresentation(color);
    }

    public Piece getPieceByPosition(int pos) {
        return rank.get(pos);
    }

    public void movePiece(int pos, Piece piece) {
        rank.set(pos, piece);
    }

    public double getPointByColor(Color color) {
        return rank.stream()
                .filter(piece -> piece.getColor().equals(color))
                .mapToDouble(piece -> piece.getName().getDefaultPoint())
                .sum();
    }

    public int checkPawnCount(int index, Color color) {
        int count = 0;
        if (rank.get(index).getName() == Type.PAWN && rank.get(index).getColor().equals(color))
            count++;
        return count;
    }

    public List<Piece> sortByScore(Color color) {
        return rank.stream()
                .filter(piece -> piece.getColor().equals(color))
                .sorted(Comparator.comparingDouble(Piece::getScore).reversed())
                .collect(Collectors.toList());
    }
}
