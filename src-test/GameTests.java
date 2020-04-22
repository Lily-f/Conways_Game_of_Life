import Game.Board;
import Game.Cell;
import org.junit.jupiter.api.Test;

/**
 * Tests on the integrity of game rules and other backend
 */
public class GameTests {
    
    /**
     * Create the game and check all cells in the board are dead
     */
    @Test
    void correctBoardCreation(){
        Board board = new Board(2,2);
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
        Board board = new Board(3,3);
        
        //set a horizontal line 3,1 size of alive cells
        board.setCellLife(0, 1, Cell.ALIVE);
        board.setCellLife(1, 1, Cell.ALIVE);
        board.setCellLife(2, 1, Cell.ALIVE);
        
        assert board.getCell(0, 1).isAlive();
        assert board.getCell(1, 1).isAlive();
        assert board.getCell(2, 1).isAlive();
        
        System.out.println(board.toString());
        
        // Run the rules on the board
        board.step();
        
        
        System.out.println("\n" + board.toString());
        // Check death by isolation is correct
        assert board.getCell(0, 1).isDead();
        assert board.getCell(2, 1).isDead();
        
        // Check growth is correct
        assert board.getCell(1, 0).isAlive();
        assert board.getCell(1, 2).isAlive();
        
        // Check survival is correct
        assert board.getCell(1, 1).isAlive();
    }
    
    /**
     * A cell with 4 or more neighbours should die from overpopulation
     */
    @Test
    void correctCellDeath(){
        Board board = new Board(3,3);
    
        //set a horizontal line 3,2 size of alive cells
        board.setCellLife(0, 0, Cell.ALIVE);
        board.setCellLife(1, 0, Cell.ALIVE);
        board.setCellLife(2, 0, Cell.ALIVE);
        board.setCellLife(0, 1, Cell.ALIVE);
        board.setCellLife(1, 1, Cell.ALIVE);
        board.setCellLife(2, 1, Cell.ALIVE);
        
        // Run the rules on the board
        board.step();
        
        // Check overpopulation deaths
        assert board.getCell(1, 1).isDead();
        assert board.getCell(1, 0).isDead();
        
        // Check growth
        assert board.getCell(1, 2).isAlive();
        
        // Check survival
        assert board.getCell(0, 0).isAlive();
        assert board.getCell(0, 1).isAlive();
        assert board.getCell(2, 0).isAlive();
        assert board.getCell(2, 1).isAlive();
    }
    
}
