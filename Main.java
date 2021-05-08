package tictactoe;
import java.util.Random;
import java.util.Scanner;

public class Main {
    protected static char[][] field = new char[3][3];
    protected static Scanner scanner = new Scanner(System.in);


    public static void hardAI(char player) {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    field[i][j] = 'X';
                    int score = minimax(field, 0, false);
                    System.out.println("Score for [" + i + "][" + j + "] :" + score);
                    field[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }
        field[move[0]][move[1]] = 'X';
        printBattlefield();
    }

    public static int getScores(String ch) {
        if (ch.equals("X")) {
            return 10;
        } else if (ch.equals("O")) {
            return -10;
        } else {
            return 0;
        }
    }

    public static int minimax(char[][] field2, int depth, boolean isMaximazing) {
        String res = checkForWinner();

        if (!res.equals("Game not finished")) {
            return getScores(res);
        }

        if (isMaximazing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field2[i][j] == ' ') {
                        field2[i][j] = 'X';
                        int score = minimax(field2, depth + 1, false);
                        System.out.println("Score for [" + i + "][" + j + "] :" + score);
                        printBattlefield();

                        field2[i][j] = ' ';

                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field2[i][j] == ' ') {
                        field2[i][j] = 'O';
                        int score = minimax(field2, depth + 1, true);
                        field2[i][j] = ' ';
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }

    public static int count(char[] arr, char ch) {
        int res =0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch) {
                res++;
            }
        }
        return res;

    }

    public static int[] mediumPossibility() {
        int[] possition = new int[2];
        char[] arr = new char[3];
        possition[0] = -1;
        possition[1] = -1;
        for (int i = 0; i < 3; i++) {



            for (int j = 0; j < 3; j++) {
                arr[j] = field[i][j];
            }

            if (count(arr, 'X') == 2 || count(arr, 'O') == 2) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == ' ') {
                        return new int[]{i, j};
                    }
                }
            }

            for (int j = 0; j < 3; j++) {
                arr[j] = field[j][i];
            }

            if (count(arr, 'X') == 2 || count(arr, 'O') == 2) {
                for (int j = 0; j < 3; j++) {
                    if (field[j][i] == ' ') {
                        return new int[]{j, i};
                    }
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            arr[j] = field[j][j];
        }
        if (count(arr, 'X') == 2 || count(arr, 'O') == 2) {
            for (int j = 0; j < 3; j++) {
                if (field[j][j] == ' ') {
                    return new int[]{j, j};
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            arr[j] = field[j][2 - j];
        }
        if (count(arr, 'X') == 2 || count(arr, 'O') == 2) {
            for (int j = 0; j < 3; j++) {
                if (field[j][2 - j] == ' ') {
                    return new int[]{j, 2 - j};
                }
            }
        }

        return possition;
    }

    public static void mediumAIMove() {
        int x = mediumPossibility()[0];
        int y = mediumPossibility()[1];
        System.out.println("Making move level \"medium\"");
        if (x == -1 && y == -1) {
            Random random = new Random();
            x = random.nextInt((9) + 1) % 3;
            y = random.nextInt((9) + 1) % 3;

            while (field[x][y] != ' ') {
                x = random.nextInt((9) + 1) % 3;
                y = random.nextInt((9) + 1) % 3;
            }
        }

        if (amountOfCharacters() % 2 == 0) {
            field[x][y] = 'X';
        } else {
            field[x][y] = 'O';
        }

        printBattlefield();
    }

    public static void easyAIMove() {
        Random random = new Random();
        int x = random.nextInt((9) + 1) % 3;
        int y = random.nextInt((9) + 1) % 3;
        System.out.println("Making move level \"easy\"");
        while (field[x][y] != ' ') {
            x = random.nextInt((9) + 1) % 3;
            y = random.nextInt((9) + 1) % 3;
        }
        if (amountOfCharacters() % 2 == 0) {
            field[x][y] = 'X';
        } else {
            field[x][y] = 'O';
        }

        printBattlefield();
    }

    public static void createBattlefield() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = ' ';
            }
        }
    }




    public static boolean checkInput(String x, String y) {

        if (!x.matches("\\d") || !y.matches("\\d") || x.isEmpty() || y.isEmpty()) {
            System.out.println("You should enter numbers!");
            System.out.println("Enter the coordinates: ");
            return true;
        }

        if (!x.matches("[1-3]") || !y.matches("[1-3]")) {
            System.out.println("Coordinates should be from 1 to 3!");
            System.out.println("Enter the coordinates: ");
            return true;
        }

        int xInt = Integer.parseInt(x);
        int yInt = Integer.parseInt(y);

        //field index 0 - 2 but userInput 1 - 3
        if (field[xInt - 1][yInt - 1] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            System.out.println("Enter the coordinates: ");
            return true;
        }

        return false;
    }

    public static void userMove() {

        System.out.println("Enter the coordinates: ");
        String[] coordinates = scanner.nextLine().split("\\s+");

        String x = coordinates[0];
        String y = "";
        if (coordinates.length != 1) {
            y = coordinates[1];
        }

        int xInt;
        int yInt;

        while (checkInput(x, y)) {
            coordinates = scanner.nextLine().split("\\s+");
            x = coordinates[0];
            y = coordinates[1];
        }

        xInt = Integer.parseInt(x);
        yInt = Integer.parseInt(y);

        //field index 0 - 2 but userInput 1 - 3
        if (amountOfCharacters() % 2 == 0) {
            field[xInt - 1][yInt - 1] = 'X';
        } else {
            field[xInt - 1][yInt - 1] = 'O';
        }

        printBattlefield();
    }

    public static int amountOfCharacters() {
        int chars = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X' || field[i][j] == 'O') {
                    chars++;
                }
            }
        }
        return chars;
    }

    public static void getResult() {
        if (checkForWinner().equals("X")) {
            System.out.println("X wins");
        } else if (checkForWinner().equals("O")) {
            System.out.println("O wins");
        } else if (checkForWinner().equals("Draw")) {
            System.out.println("Draw");
        }
    }

    public static String checkForWinner() {

        for (int i = 0; i < 3; i++) {

            if (field[i][0] == field[i][1] && field[i][1] == field[i][2]) {
                return field[i][0] + "";
            }

            if (field[0][i] == field[1][i] && field[1][i] == field[2][i]) {
                return field[0][i] + "";
            }

        }

        if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            return field[0][0] + "";
        }

        if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            return field[0][2] + "";
        }

        if (amountOfCharacters() == 9) {
            return "Draw";
        }
        return "Game not finished";
    }

    public static void printBattlefield() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void main(String[] args) {

        createBattlefield();

        System.out.println("Input command: ");
        String[] userInput = scanner.nextLine().split("\\s+");

        // && (!userInput[1].equals("easy") || !userInput[1].equals("user")) && (userInput[2] != "easy" || userInput[2] != "user")
        while (userInput.length != 3 || !userInput[0].equals("start")){
            if (userInput[0].equals("exit")) {
                return;
            }
            System.out.println("Bad parameters!");
            System.out.println("Input command: ");
            userInput = scanner.nextLine().split("\\s+");
        }
        printBattlefield();
        String XPlayer = userInput[1];
        String OPlayer = userInput[2];

        while (true) {
            if (OPlayer.equals("hard") && XPlayer.equals("user")) {
                TicTacToe t = new TicTacToe(2);
                t.play();
                break;
            } else if (XPlayer.equals("hard") && OPlayer.equals("user")) {
                TicTacToe t = new TicTacToe(1);
                t.play();
                break;
            } else if (XPlayer.equals("hard") && OPlayer.equals("hard")) {
                TicTacToe t = new TicTacToe(3);
                t.play();
                break;
            }

            if (checkForWinner().equals("X")) {
                break;
            } else if (checkForWinner().equals("Draw")) {
                break;
            } else if (checkForWinner().equals("O")) {
                break;
            }

            if (amountOfCharacters() % 2 == 0) {
                //x
                if (XPlayer.equals("user")) {
                    userMove();
                } else if (XPlayer.equals("easy")){
                    easyAIMove();
                } else if (XPlayer.equals("medium")) {
                    mediumAIMove();
                }

            } else {
                //0
                if (OPlayer.equals("user")) {
                    userMove();
                } else if (OPlayer.equals("easy")){
                    easyAIMove();
                } else if (OPlayer.equals("medium")) {
                    mediumAIMove();
                }
            }
        }
        getResult();
    }
}