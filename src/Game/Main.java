package Game;

public class Main {
    
    /**
     * Board is 2d array of cells
     */
    private final Cell[][] board;
    
    /**
     * Number of cells wide the board is
     */
    private static final int BOARD_WIDTH = 20;
    
    /**
     * Number of cells high the board is
     */
    private static final int BOARD_HEIGHT = 20;
    
    /**
     * Create board and start game
     */
    public Main(){
        
        // Create and fill board with cells
        board = new Cell[BOARD_WIDTH][BOARD_HEIGHT];
        for(int x = 0; x < BOARD_WIDTH; x++){
            for(int y = 0; y < BOARD_HEIGHT; y++){
                board[x][y] = new Cell();
            }
        }
    }
    
    /**
     * Defensively clone board and return
     * @return 2D array of cells
     */
    public Cell[][] getBoard() {
        return board;
    }
    
    /**
     * Process start
     * @param args start arguments
     */
    public static void main(String[] args) {
        new Main();
    }

}
