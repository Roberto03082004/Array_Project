package minesweeper;

import java.util.Scanner;

public class UI {

    static void start() {

        Scanner scan = new Scanner(System.in);

        int broj = howManyMines(scan);
        Board board = new Board();
        board.createField(broj);
        board.drawBoard();

        while (true) {
            String[] part = moveInput(scan);
            int x = Integer.parseInt(part[0]);
            int y = Integer.parseInt(part[1]);
            x--;
            y--;
            if (part[2].equals("mark")) {
                board.mark(x, y);
            }
            if (part[2].equals("explore")) {
                if (board.explore(x, y)) {
                    board.gameOver();
                    System.out.println("[ !!! G A M E  O V E R !!! ]");
                    break;
                }
            }
            if(board.winConditionCheck()){
                board.drawBoard();
                System.out.println("[ !!! Y O U  W O N !!! ]");
            }
        }
    }

    static private String[] moveInput(Scanner scan) {
        while (true) {
            System.out.println("[ ! MARK/REMOVE MARK OR EXPLORE A FIELD ! ]");
            System.out.println("   [ TYPE: \"X Y mark\" OR \"X Y explore\" ]");
            String coord = scan.nextLine();
            String[] part = coord.split(" ");

            if (errorChecker(part)) {
                return part;
            }

        }
    }

    static private int howManyMines(Scanner scan){
        while(true){
            System.out.println("[ HOW MANY MINES ON THE FIELD ? ]");
            String input = scan.nextLine();
            if(!input.matches("[1-9]|[1-9][0-9]")){
                System.out.println("[ INPUT IS NOT A NUMBER ]");
            } else {
                int broj = Integer.parseInt(input);
                if (broj > 80) {
                    System.out.println("[ NUMBER TOO BIG ]");
                } else if ( broj < 1){
                    System.out.println("[ NUMBER TOO SMALL ]");
                }else{
                    return broj;
                }
            }
        }
    }

    static boolean errorChecker(String[] part) {
        if (part.length != 3) {
            System.out.println("[ ! INPUT ERROR ! ]\n");
            return false;
        }
        if (!part[0].matches("[1-9]") || !part[1].matches("[1-9]")) {
            System.out.println("[ ! INPUT ERROR - WRONG COORDINATIONS ! ]\n");
            return false;
        }
        if (Integer.parseInt(part[0]) > 9 || Integer.parseInt(part[1]) > 9) {
            System.out.println("[ ! INPUT ERROR - WRONG COORDINATIONS ! ]\n");
            return false;
        }
        if (!part[2].equals("mark") && !part[2].equals("explore")) {
            System.out.println("[ ! INPUT ERROR - " + part[2] + " IS INVALID ACTION ! ]\n");
            return false;
        }
        return true;
    }

}
