package chess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.pieces.Piece.Type;
import chess.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int MAX_BOARD = 8;
    private final List<Rank> chessBoard;

    public Board(List<Rank> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void addRank(Rank rank) {
        chessBoard.add(rank);
    }
    public void initialize() {
        addBlackPiecesToBoard();
        addBlackPawnToBoard();
        addBlankToBoard();
        addWhitePawnToBoard();
        addWhitePiecesToBoard();
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

    public void initializeEmpty() {
        addRankToChessBoard(MAX_BOARD, Piece.createBlank());
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
}
