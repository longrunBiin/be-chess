package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.utils.StringUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    public static final int MAX_BOARD = 8;
    private final List<Rank> chessBoard;

    public Board() {
        chessBoard = new ArrayList<>();
    }

    public int pieceCount() {
        return chessBoard.stream()
                .mapToInt(Rank::pieceCountPerRank)
                .sum();
    }

    public void addPiece(Rank rank) {
        chessBoard.add(rank);
    }
    public void initialize() {
        addBlackPiecesToBoard();
        addBlackPawnToBoard();
        addBlankToBoard();
        addWhitePawnToBoard();
        addWhitePiecesToBoard();
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

    private void addWhitePiecesToBoard() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(Piece.createWhite(Type.ROOK));
        whitePieces.add(Piece.createWhite(Type.KNIGHT));
        whitePieces.add(Piece.createWhite(Type.BISHOP));
        whitePieces.add(Piece.createWhite(Type.QUEEN));
        whitePieces.add(Piece.createWhite(Type.KING));
        whitePieces.add(Piece.createWhite(Type.BISHOP));
        whitePieces.add(Piece.createWhite(Type.KNIGHT));
        whitePieces.add(Piece.createWhite(Type.ROOK));

        chessBoard.add(new Rank(whitePieces));
    }

    private void addBlackPiecesToBoard() {
        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(Piece.createBlack(Type.ROOK));
        blackPieces.add(Piece.createBlack(Type.KNIGHT));
        blackPieces.add(Piece.createBlack(Type.BISHOP));
        blackPieces.add(Piece.createBlack(Type.QUEEN));
        blackPieces.add(Piece.createBlack(Type.KING));
        blackPieces.add(Piece.createBlack(Type.BISHOP));
        blackPieces.add(Piece.createBlack(Type.KNIGHT));
        blackPieces.add(Piece.createBlack(Type.ROOK));

        chessBoard.add(new Rank(blackPieces));
    }

    private void addBlackPawnToBoard() {
        addRankToChessBoard(1, Piece.createBlack(Type.PAWN));
    }

    private void addWhitePawnToBoard() {
        addRankToChessBoard(1, Piece.createWhite(Type.PAWN));
    }

    private void addBlankToBoard() {
        addRankToChessBoard(4, Piece.createBlank());
    }

    //보드 위에 존재하는 특정 색, 종류의 기물 갯수 리턴
    public int pieceCountOnBoard(Color color, Type type) {
        return chessBoard.stream()
                .mapToInt(rank -> rank.pieceCountPerColorAndType(color, type))
                .sum();
    }

    public Piece findPiece(String position) {
        Position pos= new Position(position);

        Rank rank = chessBoard.get(MAX_BOARD - pos.getYPos());
        return rank.getPieceByPosition(pos.getXPos());
    }

    public void initializeEmpty() {
        addRankToChessBoard(MAX_BOARD, Piece.createBlank());
    }

    public void move(String sourcePosition, String targetPosition) {
        Position startPos= new Position(sourcePosition);
        Position endPos= new Position(targetPosition);

        Rank startRank = chessBoard.get(MAX_BOARD - startPos.getYPos());
        Rank endRank = chessBoard.get(MAX_BOARD - endPos.getYPos());
        Piece sourcePiece = startRank.getPieceByPosition(startPos.getXPos());

        endRank.movePiece(endPos.getXPos(), sourcePiece);
        startRank.movePiece(startPos.getXPos(), Piece.createBlank());

    }

    private void addRankToChessBoard(int maxBoard, Piece piece) {
        for (int i = 0; i < maxBoard; i++) {
            List<Piece> pieces = addPieceToList(piece);
            chessBoard.add(new Rank(pieces));
        }
    }

    private List<Piece> addPieceToList(Piece piece) {
        List<Piece> rank = new ArrayList<>();
        for (int i = 0; i < MAX_BOARD; i++) {
            rank.add(piece);
        }
        return rank;
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

    public List<Piece> sortPiece(Color color) {
        return chessBoard.stream()
                .flatMap(rank -> rank.sortByScore(color).stream())
                .sorted(Comparator.comparingDouble(Piece::getScore).reversed())
                .collect(Collectors.toList());

    }
}
