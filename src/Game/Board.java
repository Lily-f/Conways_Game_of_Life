package Game;

import java.util.Arrays;

/**
 * Represents the 2d array of cells
 */
public class Board {
    
    /**
     * 2d array of cells
     */
    private Cell[][] cells;
    
    /**
     * Create the 2d array of cells
     */
    public Board(int width, int height){
        cells = new Cell[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                cells[x][y] = new Cell(Cell.DEAD);
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
        if(x > 0 && y > 0 && cells[x-1][y-1].isAlive()) numAlive ++;
        if(y > 0 && cells[x][y-1].isAlive()) numAlive ++;
        if(x < getWidth()-1 && y > 0 && cells[x+1][y-1].isAlive()) numAlive ++;
        
        if(x > 0 && cells[x-1][y].isAlive()) numAlive ++;
        if(x < getWidth()-1 && cells[x+1][y].isAlive()) numAlive ++;
        
        if(x > 0 && y < getHeight()-1 && cells[x-1][y+1].isAlive()) numAlive ++;
        if(y < getHeight()-1 && cells[x][y+1].isAlive()) numAlive ++;
        if(x < getWidth()-1 && y < getHeight()-1 && cells[x+1][y+1].isAlive()) numAlive ++;
        
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
    
    /**
     * Apply the rules and modify the board
     */
    public void step(){
        cells = Rules.applyRules(this);
    }
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Board{\n");
        
        for(int y = 0; y < getHeight(); y ++){
            for(int x = 0; x < getWidth(); x ++){
                string.append(cells[x][y].toString());
            }
            string.append('\n');
        }
        
        string.append("}");
        return string.toString();
    }
}
