package Game;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    
    /**
     * Board is 2d array of cells
     */
    private Board board;
    
    /**
     * Create board and start game
     */
    public Main(int width, int height){
        board = new Board(width, height);
    }
    
    /**
     * Apply the rules and modify the board
     */
    public void step(){
        board = Rules.applyRules(board);
    }
    
    /**
     * Get game board
     * @return 2D array of cells
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Process start
     * @param args start arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the board width");
        int width = scanner.nextInt();
        System.out.println("Enter the board height");
        int height = scanner.nextInt();
        new Main(width, height);
    }

}
