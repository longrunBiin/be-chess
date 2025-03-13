package chess.springchess;

import chess.game.Board;
import chess.game.ChessGame;
import chess.game.ChessView;
import chess.game.Rank;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChessConfig {

    @Bean
    public List<Rank> chessBoard() {
        return new ArrayList<>();
    }

    @Bean
    public Board board(List<Rank> chessBoard) {
        return new Board(chessBoard);
    }

    @Bean
    public ChessGame chessGame(List<Rank> chessBoard) {
        return new ChessGame(chessBoard);
    }

    @Bean
    public ChessView chessView(List<Rank> chessBoard) {
        return new ChessView(chessBoard);
    }
}
