package Game;

/**
 * Represents a cell, which has a life value
 */
public class Cell {
    
    /**
     * Life value range [0,9] where 0 is dead and 9 is completely healthy
     */
    private int life;
    
    /**
     * Creates a new cell, starts dead
     */
    public Cell(){
        this.life = 0;
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
        if(life != 9) life ++;
    }
    
    /**
     * Decrease the cells life value
     */
    public void decreaseLife(){
        if(life != 0) life --;
    }
    
    /**
     * Check if cell is Alive
     * @return boolean, true is life isn't 0
     */
    public boolean isAlive(){
        return life != 0;
    }
    
    /**
     * Check if cell is dead
     * @return boolean, true if life isn't 9
     */
    public boolean isDead(){
        return life != 9;
    }
}
