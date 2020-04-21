package Game;

/**
 * Represents a cell, which has a life value
 */
public class Cell {
    
    /**
     * int value of life where cell is completely alive
     */
    public static final int ALIVE = 9;
    
    /**
     * int value of life where cell is dead
     */
    public static final int DEAD = 0;
    
    /**
     * Life value range [0,9] where 0 is dead and 9 is completely healthy
     */
    private int life;
    
    /**
     * Creates a new cell, starts dead
     */
    public Cell(){
        this.life = DEAD;
    }
    
    /**
     * Clone a cell
     * @param cell cell to clone
     */
    public Cell(Cell cell){
        this.life = cell.life;
    }
    
    /**
     * Set new value for cells life
     * @param life int New life value
     */
    public void setLife(int life) {
        this.life = life;
    }
    
    /**
     * Increase the cells life value
     */
    public void increaseLife(){
        if(life != ALIVE) life ++;
    }
    
    /**
     * Decrease the cells life value
     */
    public void decreaseLife(){
        if(life != DEAD) life --;
    }
    
    /**
     * Check if cell is Alive
     * @return boolean, true is life isn't dead
     */
    public boolean isAlive(){
        return life != DEAD;
    }
    
    /**
     * Check if cell is dead
     * @return boolean, true if life is dead
     */
    public boolean isDead(){
        return life == DEAD;
    }
    
    @Override
    public String toString() {
        return "Cell{" + "life=" + life + '}';
    }
}
