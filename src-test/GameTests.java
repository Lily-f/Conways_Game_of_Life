import Game.Board;
import Game.Cell;
import Game.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class GameTests {
    
    /**
     * Create the game and check all cells in the board are dead
     */
    @Test
    void correctBoardCreation(){
        Main main = new Main(5,5);
        Board board = main.getBoard();
        for(int x = 0; x < board.getWidth(); x ++){
            for(int y = 0; y < board.getHeight(); y ++){
                assert board.getCell(x, y).isDead();
            }
        }
        
    }
    
    /**
     * Test that the growth by births and death by underpopulation are correct
     */
    @Test
    void correctCellGrowth(){
        Main main = new Main(3, 3);
        Board board = main.getBoard();
        
        //set a horizontal line 3,1 size of alive cells
        board.setCellLife(0, 1, Cell.ALIVE);
        board.setCellLife(1, 1, Cell.ALIVE);
        board.setCellLife(2, 1, Cell.ALIVE);
        
        assert board.getCell(0, 1).isAlive();
        assert board.getCell(1, 1).isAlive();
        assert board.getCell(2, 1).isAlive();
        
        //Display the old board
        System.out.println("===Step 0===\n" + board.toString());
        
        // Run the rules on the board
        main.step();
        Board newBoard = main.getBoard();
        
        //display the new board
        System.out.println("\n===Step 1===\n" + newBoard.toString());
        
        // Check the rules haven't changed the old board
        assert board.getCell(0, 1).isAlive();
        assert board.getCell(1, 1).isAlive();
        assert board.getCell(2, 1).isAlive();
        assert board.getCell(1, 0).isDead();
        assert board.getCell(1, 2).isDead();
        
        // Check death by isolation is correct
        assert newBoard.getCell(0, 1).isDead();
        assert newBoard.getCell(2, 1).isDead();
        
        // Check growth is correct
        assert newBoard.getCell(1, 0).isAlive();
        assert newBoard.getCell(1, 2).isAlive();
        
        // Check survival is correct
        assert newBoard.getCell(1, 1).isAlive();
    }
    
}
