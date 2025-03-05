package chess;

import chess.pieces.Pawn;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Pawn> pawnList;
    private char[][] chessBoard;

    public Board() {
        pawnList = new ArrayList<>();
        chessBoard = new char[8][8];
    }

    public void add(Pawn pawn) {
        pawnList.add(pawn);
    }

    public int size() {
        return pawnList.size();
    }

    public Pawn findPawn(int index) {
        return pawnList.get(index);
    }

    public void initialize() {
        initializeEmptyBoard();
        addPawnToList();
        addPawnToBoard();
    }

    public String getPawnResult(String color){
        StringBuilder sb = new StringBuilder();
        for (Pawn pawn : pawnList) {
            if (pawn.getColor().equals(color)) sb.append(pawn.getRepresentation());
        }
        return sb.toString();
    }

    private void addPawnToBoard() {
        for (int i = 0; i < 8; i++) {
            chessBoard[1][i] = pawnList.get(i).getRepresentation();
            chessBoard[6][i] = pawnList.get(i).getRepresentation();
        }
    }

    private void addPawnToList() {
        for (int i = 0; i < 8; i++) {
            pawnList.add(new Pawn());
            pawnList.add(new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
        }
    }

    private void initializeEmptyBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = '.';
            }
        }
    }
}
