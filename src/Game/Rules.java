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
        
        Board newBoard = new Board(board.getWidth(), board.getHeight());
    
        // loop through each cell
        for(int x = 0; x < board.getWidth(); x ++){
            for(int y = 0; y < board.getHeight(); y ++){
            
                //todo
            }
        }
        
        return newBoard;
    }
    
}
