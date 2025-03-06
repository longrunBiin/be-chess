package chess;

import chess.pieces.Piece;
import chess.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Piece> pieceList;
    private char[][] chessBoard;

    public Board() {
        pieceList = new ArrayList<>();
        chessBoard = new char[8][8];
    }

    public void add(Piece piece) {
        pieceList.add(piece);
    }

    public int pieceCount() {
        return pieceList.size();
    }

    public Piece findPawn(int index) {
        return pieceList.get(index);
    }

    public void initialize() {
        initializeEmptyBoard();
        addPawnToList();
        addPieceToList();
        addPawnToBoard();
        addBlackPiecesToBoard();
        addWhitePiecesToBoard();

    }

    public String getPawnResult(String color){
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieceList) {
            if (piece.getColor().equals(color)) sb.append(piece.getRepresentation());
        }
        return sb.toString();
    }

    public void print() {
        System.out.println(showBoard());
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                sb.append(chessBoard[i][j]);
            }
            sb.append(StringUtils.appendNewLine(""));
        }
        return sb.toString();
    }

    private void addWhitePiecesToBoard() {
        chessBoard[7][0] = Piece.WHITE_ROOK_REPRESENTATION;
        chessBoard[7][1] = Piece.WHITE_KNIGHT_REPRESENTATION;
        chessBoard[7][2] = Piece.WHITE_BISHOP_REPRESENTATION;
        chessBoard[7][3] = Piece.WHITE_QUEEN_REPRESENTATION;
        chessBoard[7][4] = Piece.WHITE_KING_REPRESENTATION;
        chessBoard[7][5] = Piece.WHITE_BISHOP_REPRESENTATION;
        chessBoard[7][6] = Piece.WHITE_KNIGHT_REPRESENTATION;
        chessBoard[7][7] = Piece.WHITE_ROOK_REPRESENTATION;
    }

    private void addBlackPiecesToBoard() {
        chessBoard[0][0] = Piece.BLACK_ROOK_REPRESENTATION;
        chessBoard[0][1] = Piece.BLACK_KNIGHT_REPRESENTATION;
        chessBoard[0][2] = Piece.BLACK_BISHOP_REPRESENTATION;
        chessBoard[0][3] = Piece.BLACK_QUEEN_REPRESENTATION;
        chessBoard[0][4] = Piece.BLACK_KING_REPRESENTATION;
        chessBoard[0][5] = Piece.BLACK_BISHOP_REPRESENTATION;
        chessBoard[0][6] = Piece.BLACK_KNIGHT_REPRESENTATION;
        chessBoard[0][7] = Piece.BLACK_ROOK_REPRESENTATION;
    }

    private void addPieceToList() {
        pieceList.add(Piece.createWhiteKnight());
        pieceList.add(Piece.createWhiteKnight());
        pieceList.add(Piece.createBlackKnight());
        pieceList.add(Piece.createBlackKnight());

        pieceList.add(Piece.createWhiteBishop());
        pieceList.add(Piece.createWhiteBishop());
        pieceList.add(Piece.createBlackBishop());
        pieceList.add(Piece.createBlackBishop());

        pieceList.add(Piece.createWhiteRook());
        pieceList.add(Piece.createWhiteRook());
        pieceList.add(Piece.createBlackRook());
        pieceList.add(Piece.createBlackRook());

        pieceList.add(Piece.createWhiteQueen());
        pieceList.add(Piece.createBlackQueen());
        pieceList.add(Piece.createWhiteKing());
        pieceList.add(Piece.createBlackKing());

    }

    private void addPawnToBoard() {
        for (int i = 0; i < chessBoard.length; i++) {
            chessBoard[1][i] = Piece.BLACK_PAWN_REPRESENTATION;
            chessBoard[6][i] = Piece.WHITE_PAWN_REPRESENTATION;
        }
    }

    private void addPawnToList() {
        for (int i = 0; i < chessBoard.length; i++) {
            pieceList.add(Piece.createWhitePawn());
            pieceList.add(Piece.createBlackPawn());
        }
    }

    private void initializeEmptyBoard() {
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                chessBoard[i][j] = '.';
            }
        }
    }
}
