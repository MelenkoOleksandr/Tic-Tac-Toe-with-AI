package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static final Random RANDOM = new Random();
    public static int playing = 0;
    public TicTacToe(int playing) {
        TicTacToe.playing = playing;
    }

    public static void forOAI(){
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);

        while (!b.isGameOver()) {
            boolean moveOk = true;
            do {
                if (!moveOk) {
                    System.out.println("Cell is occupied");
                }
                System.out.println("Enter the coordinates: ");

                Point userMove = new Point(scanner.nextInt() - 1, scanner.nextInt() - 1);
                moveOk = b.placeAMove(userMove, Board.PLAYER_X);

            } while (!moveOk);
            b.displayBoard();
            if (b.isGameOver()) {
                break;
            }
            System.out.println("Making move level \"hard\"");
            b.minmaxO(0, Board.PLAYER_O);
            /*System.out.println("Computer chose position : " + b.computerMove);*/
            b.placeAMove(b.computerMove, Board.PLAYER_O);
            b.displayBoard();
        }
        if (b.hasPlayerWon(Board.PLAYER_O)) {
            System.out.println("O wins");
        } else if (b.hasPlayerWon(Board.PLAYER_X)) {
            System.out.println("X wins");
        } else {
            System.out.println("Draw");
        }
    }


    public static void forXAI(){
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Making move level \"hard\"");
        Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));
        b.placeAMove(p, Board.PLAYER_X);
        b.displayBoard();

        while (!b.isGameOver()) {
            boolean moveOk = true;
            do {
                if (!moveOk) {
                    System.out.println("Cell is occupied");
                }
                System.out.println("Enter the coordinates: ");
                Point userMove = new Point(scanner.nextInt() - 1, scanner.nextInt() - 1);
                moveOk = b.placeAMove(userMove, Board.PLAYER_O);

            } while (!moveOk);
            b.displayBoard();
            if (b.isGameOver()) {
                break;
            }
            System.out.println("Making move level \"hard\"");
            b.minmaxX(0, Board.PLAYER_X);
            /*System.out.println("Computer chose position : " + b.computerMove);*/
            b.placeAMove(b.computerMove, Board.PLAYER_X);
            b.displayBoard();
        }

        if (b.hasPlayerWon(Board.PLAYER_O)) {
            System.out.println("O wins");
        } else if (b.hasPlayerWon(Board.PLAYER_X)) {
            System.out.println("X wins");
        } else {
            System.out.println("Draw");
        }
    }
    public static void forBothAI(){
        Board b = new Board();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Making move level \"hard\"");
        Point p = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));
        b.placeAMove(p, Board.PLAYER_X);
        b.displayBoard();

        while (!b.isGameOver()) {
            System.out.println("Making move level \"hard\"");
            b.minmaxO(0, Board.PLAYER_O);
            /*System.out.println("Computer1 chose position : " + b.computerMove);*/
            b.placeAMove(b.computerMove, Board.PLAYER_O);
            b.displayBoard();
            if (b.isGameOver()) {
                break;
            }
            System.out.println("Making move level \"hard\"");
            b.minmaxX(0, Board.PLAYER_X);
            /*System.out.println("Computer2 chose position : " + b.computerMove);*/
            b.placeAMove(b.computerMove, Board.PLAYER_X);
            b.displayBoard();
        }

        if (b.hasPlayerWon(Board.PLAYER_O)) {
            System.out.println("O wins");
        } else if (b.hasPlayerWon(Board.PLAYER_X)) {
            System.out.println("X wins");
        } else {
            System.out.println("Draw");
        }
    }
    public static void play() {
        Board b = new Board();
        //if O
        if (playing == Board.PLAYER_O) {
            forOAI();
        } else if (playing == Board.PLAYER_X) {
            forXAI();
        } else if (playing == 3) {
            forBothAI();
        }

    }

}
