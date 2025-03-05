package chess;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        board.initialize();

        while (true) {
            String input = in.nextLine();
            if (input.equals("end")) return;
            if (input.equals("start"))
                board.print();
        }
    }
}
