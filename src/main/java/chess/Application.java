package chess;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board();

        System.out.println("게임을 시작하려면 start, 끝내려면 end를 입력하세요");
        while (true) {
            String input = in.nextLine();
            if (input.equals("end")) return;
            else if(input.equals("start")){
                board.initialize();
                board.print();
            } else if (input.startsWith("move")) {
                String[] splitInput = input.split(" ");
                board.move(splitInput[1], splitInput[2]);
                board.print();
            }
            System.out.println("기물을 움직이려면 move start end 를 입력하세요");
        }
    }
}
