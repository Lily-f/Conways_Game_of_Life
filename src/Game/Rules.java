package Game;

/**
 * Represents each rule and can be applied to a board
 */
public class Rules {
    
    public static Board applyRules(Board board){
        Board newBoard = new Board(board);
    
        // loop through each cell, changing the new board based of alive adjacent cells and the current cell
        for(int x = 0; x < board.getWidth(); x ++){
            for(int y = 0; y < board.getHeight(); y ++){
                int aliveNeighbours = board.aliveAdjacentCells(x, y);
                Cell cell = board.getCell(x, y);
                
                // Rule of births
                if(cell.isDead() && aliveNeighbours == 3)   newBoard.setCellLife(x, y, Cell.ALIVE);
                // Rule of deaths by isolation
                if(cell.isAlive() && aliveNeighbours <= 1)  newBoard.setCellLife(x, y, Cell.DEAD);
                // Rule of deaths by overpopulation
                if(cell.isAlive() && aliveNeighbours >= 4)  newBoard.setCellLife(x, y, Cell.DEAD);
            }
        }
        return newBoard;
    }
    
}
