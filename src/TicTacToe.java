import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char [] [] gameBoard = {{' ', '|', ' ', '|',' '},
                {'-', '+', '-', '+','-'},
                {' ', '|', ' ', '|',' '},
                {'-', '+', '-', '+','-'},
                {' ', '|', ' ', '|',' '}};

        printGameBoard(gameBoard);



        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your move (1-9): ");
            int playerPosition = scan.nextInt();
            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Position taken! Enter a correct position! ");
                playerPosition = scan.nextInt();
            }
            placeMarker(gameBoard, playerPosition, "player");

            Random rand = new Random();
            int cpuPosition = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                playerPosition = scan.nextInt();
                System.out.println("Thinking...");
                cpuPosition = rand.nextInt(9) + 1;
            }

            placeMarker(gameBoard, cpuPosition, "cpu");
            printGameBoard(gameBoard);
            String results = checkWinner();
            System.out.println(results);
        }


    }

    public static void printGameBoard(char [][] gameBoard) {
        for (char [] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeMarker(char [][] gameBoard, int position, String user ) {
        char symbol = ' ';
        if(user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("cpu")) {
            symbol = '0';
            cpuPositions.add(position);
        }

        switch(position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List rightToLeft = Arrays.asList(1, 5, 9);
        List leftToRight = Arrays.asList(7, 5, 3);

        List<List> winningMoves = new ArrayList<List>();

        winningMoves.add(topRow);
        winningMoves.add(midRow);
        winningMoves.add(botRow);
        winningMoves.add(rightCol);
        winningMoves.add(midCol);
        winningMoves.add(leftCol);
        winningMoves.add(leftToRight);
        winningMoves.add(rightToLeft);

        for (List l : winningMoves) {
            if(playerPositions.containsAll(l)) {
                return "Congratulations, you won!";
            } else if(cpuPositions.contains(l)) {
                return "CPU wins! Sorry...";
            } else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "DRAW!";
            }
        }

        return "";

    }
}
