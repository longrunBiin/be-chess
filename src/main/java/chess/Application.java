package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Rank> chessBoard = new ArrayList<>();
        Board board = new Board(chessBoard);
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessView chessView = new ChessView(chessBoard);

        System.out.println("게임을 시작하려면 start, 끝내려면 end를 입력하세요");
        while (true) {
            String input = in.nextLine();
            if (input.equals("end")) return;
            else if(input.equals("start")){
                board.initialize();
                chessView.print();
            } else if (input.startsWith("move")) {
                String[] splitInput = input.split(" ");
                chessGame.move(splitInput[1], splitInput[2]);
                chessView.print();
            }
            System.out.println("기물을 움직이려면 move start end 를 입력하세요");
        }
    }
}
