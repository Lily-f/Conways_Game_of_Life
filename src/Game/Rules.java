package Game;

/**
 * Represents each rule and can be applied to a board
 */
public class Rules {
    
    /**
     * Applies the growth and decay rules to a given board and returns the new array of cells
     * @param board Board to apply rules to
     * @return New 2d array of cells
     */
    public static Cell[][] applyRules(Board board){
        Cell[][] newCells = new Cell[board.getWidth()][board.getHeight()];
    
        // loop through each cell, determining new state based on alive adjacent cells and the current cell
        for(int x = 0; x < board.getWidth(); x ++){
            for(int y = 0; y < board.getHeight(); y ++){
                int aliveNeighbours = board.aliveAdjacentCells(x, y);
                Cell cell = board.getCell(x, y);
                
                // Rule of births
                if(cell.isDead() && aliveNeighbours == 3)   newCells[x][y] = new Cell(Cell.ALIVE);
                // Rule of deaths by isolation
                else if(cell.isAlive() && aliveNeighbours <= 1)  newCells[x][y] = new Cell(Cell.DEAD);
                // Rule of deaths by overpopulation
                else if(cell.isAlive() && aliveNeighbours >= 4)  newCells[x][y] = new Cell(Cell.DEAD);
                // Rule of survival
                else if(cell.isAlive() && (aliveNeighbours == 2 || aliveNeighbours == 3))    newCells[x][y] = new Cell(Cell.ALIVE);
                // Else is dead and doesn't have 3 alive neighbours to grow from
                else    newCells[x][y] = new Cell(Cell.DEAD);
            }
        }
        return newCells;
    }
    
}
