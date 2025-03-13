package chess.springchess;

import chess.game.Board;
import chess.game.ChessGame;
import chess.game.ChessView;
import chess.pieces.Piece;
import chess.pieces.Piece.Color;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessController {
    private static final String INIT_BOARD = "보드판이 초기화 되었습니다.";
    private static final String MOVE_PIECE_SUCCESS = "기물이 정상적으로 이동하였습니다.";
    private static final String CATCH_KING_SUCCESS = "킹을 잡았습니다.";
    private static final String GAME_RESULT = "게임 결과입니다.";
    private final ChessGame chessGame;
    private final Board board;
    private final ChessView chessView;

    public ChessController(ChessGame chessGame, Board board, ChessView chessView) {
        this.chessGame = chessGame;
        this.board = board;
        this.chessView = chessView;
    }

    @GetMapping(value = "/api/start")
    public Result<ChessDto.initGameDto> startGame() {
        // 보드 초기화 후 게임 상태 초기화
        board.initialize(); // 보드 초기화
        chessGame.reset();  // ChessGame 상태 초기화
        return Result.onSuccess(ChessConverter.createInitGameDto(chessView.showBoard()), INIT_BOARD);
    }

    @PostMapping("api/move")
    public Result<ChessDto.movePieceDto> movePiece(@RequestBody ChessDto.movePieceRequestDto request) {
        Piece move = Piece.createBlank();
        try {
            move = chessGame.move(request.getStartPos(), request.getEndPos());
            boolean kingOnBoard = chessGame.checkKingOnBoard(move);
            if (!kingOnBoard)
                return Result.onSuccess(ChessConverter.createmovePieceDto(request.getStartPos(), request.getEndPos(), move), CATCH_KING_SUCCESS);
            return Result.onSuccess(ChessConverter.createmovePieceDto(request.getStartPos(), request.getEndPos(), move), MOVE_PIECE_SUCCESS);
        } catch (IllegalArgumentException e) {
            // 예외가 발생하면 Result 객체로 에러 메시지 반환
            return Result.onFailure(ChessConverter.createmovePieceDto(request.getStartPos(), request.getEndPos(), move), e.getMessage());
        }
    }

    @GetMapping(value = "/api/result")
    public Result<ChessDto.GameResultDto> showResult(@RequestParam String color) {
        Color winnerColor = Color.getColorByString(color);
        Color loserColor = winnerColor.getEnemyColor();

        double winnerScore = chessGame.calculatePoint(winnerColor);
        double loserScore = chessGame.calculatePoint(loserColor);
        List<Piece> winnerPieces = chessGame.sortPiece(winnerColor);
        List<Piece> loserPieces = chessGame.sortPiece(loserColor);

        return Result.onSuccess(ChessConverter.createGameResultDto(winnerColor, winnerScore, loserScore, winnerPieces, loserPieces), GAME_RESULT);
    }

}
