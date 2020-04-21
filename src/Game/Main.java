package Game;

import java.util.concurrent.TimeUnit;

public class Main {
    
    /**
     * Board is 2d array of cells
     */
    private Board board;
    
    /**
     * Number of cells wide the board is
     */
    private static final int BOARD_WIDTH = 10;
    
    /**
     * Number of cells high the board is
     */
    private static final int BOARD_HEIGHT = 5;
    
    /**
     * Create board and start game
     */
    public Main(){
        board = new Board(BOARD_WIDTH, BOARD_HEIGHT);
    }
    
    /**
     * Apply the rules and modify the board
     */
    public void step(){
        board = Rules.applyRules(board);
    }
    
    /**
     * Defensively clone board and return. Note: Defensively cloned
     * @return 2D array of cells
     */
    public Board getBoard() {
        return new Board(board);
    }
    
    /**
     * Process start
     * @param args start arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}
