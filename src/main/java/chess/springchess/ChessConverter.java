package chess.springchess;

import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import chess.springchess.ChessDto.GameResultDto;
import chess.springchess.ChessDto.initGameDto;
import chess.springchess.ChessDto.movePieceDto;
import java.util.List;

public class ChessConverter {
    public static ChessDto.initGameDto createInitGameDto(String board) {
        return new ChessDto.initGameDto(board);
    }

    public static ChessDto.movePieceDto createmovePieceDto(String startPos, String endPos, Piece movePiece) {
        return new ChessDto.movePieceDto(startPos, endPos, movePiece);
    }

    public static ChessDto.GameResultDto createGameResultDto(Color winnerColor, double winnerScore, double loserScore,
                                                    List<Piece> winnerPieces, List<Piece> loserPieces) {
        return new ChessDto.GameResultDto(winnerColor, winnerScore, loserScore, winnerPieces, loserPieces);
    }
}
