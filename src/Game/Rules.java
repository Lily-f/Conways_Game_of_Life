package Game;

/**
 * Represents each rule and can be applied to a board
 */
public class Rules {
    
    /*
    Births: Each dead cell adjacent to exactly three live neighbors will become live in the next generation.
    Death by isolation: Each live cell with one or fewer live neighbors will die in the next generation.
    Death by overcrowding: Each live cell with four or more live neighbors will die in the next generation.
    Survival: Each live cell with either two or three live neighbors will remain alive for the next generation.
     */
    
    public static Board applyRules(Board board){
        
        Board newBoard = new Board(board);
    
        // loop through each cell, changing the new board based of alive adjacent cells and the current cell
        for(int x = 0; x < board.getWidth(); x ++){
            for(int y = 0; y < board.getHeight(); y ++){
                
                int aliveNeighbours = board.aliveAdjacentCells(x, y);
                Cell cell = board.getCell(x, y);
                
                // Rule of births
                if(cell.isDead() && aliveNeighbours == 3){
                    newBoard.setCellLife(x, y, Cell.ALIVE);
                    break;
                }
                // Rule of deaths by isolation
                if(cell.isAlive() && aliveNeighbours <= 1){
                    newBoard.setCellLife(x, y, Cell.DEAD);
                    break;
                }
                // Rule of deaths by overpopulation
                if(cell.isAlive() && aliveNeighbours >= 4){
                    newBoard.setCellLife(x, y, Cell.DEAD);
                }
            }
        }
        
        return newBoard;
    }
    
}
