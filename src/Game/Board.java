package Game;

/**
 *
 */
public class Board {
    
    /**
     * 2d array of cells
     */
    private final Cell[][] cells;
    
    /**
     * Create the 2d array of cells
     */
    public Board(int width, int height){
        cells = new Cell[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                cells[x][y] = new Cell();
            }
        }
    }
    
    /**
     * Clone a board
     * @param board Board to clone
     */
    public Board(Board board){
        Cell[][] originalCells = board.cells;
        int width = originalCells.length;
        int height = originalCells[0].length;
        
        // Copy the cells from the original board
        this.cells = new Cell[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                cells[x][y] = new Cell(originalCells[x][y]);
            }
        }
    }
    
    /**
     * Get the cell at a given coordinate. Note: Defensively cloned
     * @param x X coordinate
     * @param y Y coordinate
     * @return Cell from board at those coordinates
     */
    public Cell getCell(int x, int y){
        return new Cell(cells[x][y]);
    }
    
    /**
     * Get number of cells wide the board is
     * @return int width
     */
    public int getWidth(){
        return cells.length;
    }
    
    /**
     * Get number of cells high the board is
     * @return int height
     */
    public int getHeight(){
        return cells[0].length;
    }
    
}
