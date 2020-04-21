import Game.Board;
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
        Board board = main.getBoard();
        for(int x = 0; x < board.getWidth(); x ++){
            for(int y = 0; y < board.getHeight(); y ++){
                assert board.getCell(x, y).isDead();
            }
        }
        
    }
    
}
