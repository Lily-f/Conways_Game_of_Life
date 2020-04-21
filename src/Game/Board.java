package Game;

/**
 * Represents the 2d array of cells
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
    
    /**
     * Find the number of alive adjacent cells from a given cell
     * @param x X coordinate of cell
     * @param y Y coordinate of cell
     * @return int number of alive adjacent cells
     */
    public int aliveAdjacentCells(int x, int y){
        int numAlive = 0;
        
        // Check not out of array bounds
        if(x > 0){
            if(getCell(x - 1, y).isAlive()) numAlive ++;
            if(y > 0 && getCell(x - 1, y - 1).isAlive())    numAlive ++;
            if(y < getHeight() - 1 && getCell(x - 1, y + 1).isAlive()) numAlive ++;
        }
        if(x < getWidth() -1){
            if(getCell(x + 1, y).isAlive()) numAlive ++;
            if(y > 0 && getCell(x + 1, y - 1).isAlive())    numAlive ++;
            if(y < getHeight() - 1 && getCell(x + 1, y + 1).isAlive()) numAlive ++;
        }
        if(y > 0 && getCell(x, y - 1).isAlive())   numAlive ++;
        if(y < getHeight() - 1 && getCell(x, y + 1).isAlive()) numAlive ++;
        
        return numAlive;
    }
    
    /**
     * Sets the life of a given cell
     * @param x X coordinate of cell
     * @param y Y coordinate of cell
     */
    public void setCellLife(int x, int y, int life){
        if(life > Cell.ALIVE || life < Cell.DEAD) throw new IllegalArgumentException("Life value has to be between [0,9] inclusive");
        cells[x][y].setLife(life);
    }
    
}
