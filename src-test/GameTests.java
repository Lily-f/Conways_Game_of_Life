import Game.Cell;
import Game.Main;
import org.junit.jupiter.api.Test;

/**
 *
 */
public class GameTests {
    
    /**
     * Create the game and check all cells in the board are dead
     */
    @Test
    void correctBoardCreation(){
        Main main = new Main();
        Cell[][] board = main.getBoard();
        for(Cell[] column : board){
            for(Cell cell : column){
                assert cell.isDead();
            }
        }
        
    }
    
}
