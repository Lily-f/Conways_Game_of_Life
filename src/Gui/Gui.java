package Gui;

import Game.Board;
import Game.Cell;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Gui extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Minimum width of the window
     */
    private static final int MIN_WINDOW_WIDTH = 300;
    
    /**
     * Min height of the window
     */
    private static final int MIN_WINDOW_HEIGHT = 70;
    
    /**
     * Minimum height of menu bar
     */
    private static final int MIN_MENU_HEIGHT = 30;
    
    /**
     * Size or gap between cells
     */
    private static final int CELL_MARGIN_SIZE = 1;
    
    /**
     * Initial height of game board
     */
    private static final int DEFAULT_BOARD_SIZE = 10;
    
    /**
     * Delay between steps for board animation
     */
    private static final int STEP_DELAY = 200;
    
    /**
     * Game board
     */
    private Board board;
    
    /**
     * GridPane holding cells on the board
     */
    private GridPane boardGrid;
    
    /**
     * Flag for if game is running or paused
     */
    private boolean running = false;
    
    /**
     * Size of cells on the grid
     */
    private double cellSize = 15;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Format stage
        primaryStage.setTitle("Game of Life");
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(MIN_WINDOW_WIDTH);
        primaryStage.setMinHeight(MIN_WINDOW_HEIGHT);
        
        primaryStage.setOnCloseRequest((e)->System.exit(0));
        
        // Scale the cells to fit the size of the screen
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            if(primaryStage.getHeight() - MIN_MENU_HEIGHT < primaryStage.getWidth()){
                cellSize = (primaryStage.getHeight() - MIN_MENU_HEIGHT) / board.getHeight() - (board.getHeight() * CELL_MARGIN_SIZE);
            }
            else{
                cellSize = primaryStage.getWidth() / board.getWidth() - (board.getWidth() * CELL_MARGIN_SIZE);
                System.out.println();
            }
            updateBoard();
        };
        primaryStage.widthProperty().addListener(stageSizeListener);
        primaryStage.heightProperty().addListener(stageSizeListener);
        
        
        
        board = new Board(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
        
        // Create Panes
        VBox rootPane = new VBox();
        GridPane menu = new GridPane();
        boardGrid = new GridPane();
        Scene scene = new Scene(rootPane, 300, 300);
        
        // Populate and format panes
        rootPane.getChildren().addAll(menu, boardGrid);
        menu.setMinHeight(MIN_MENU_HEIGHT);
        boardGrid.setVgap(CELL_MARGIN_SIZE);
        boardGrid.setHgap(CELL_MARGIN_SIZE);
        setupGUIButtons(menu);
        createBoard();
        
        primaryStage.setScene(scene);
        primaryStage.show();
        updateBoard();
    }
    
    /**
     * Put the menu buttons onto a given Pane
     *
     * @param menu GridPane to place buttons on
     */
    private void setupGUIButtons(GridPane menu) {
        
        // Next step button
        Button nextStepBtn = new Button("Next Step");
        nextStepBtn.setOnAction((e) -> {
            board.step();
            updateBoard();
        });
        menu.add(nextStepBtn, 0, 0);
        
        // Run button to loop the game
        Button runBtn = new Button("Run");
        runBtn.setOnAction((e) -> {
            // Do nothing if already running
            if(running) return;
            
            // Else start a reoccurring event
            running = true;
            new Timer().schedule(
                new TimerTask() {
                    
                    @Override
                    public void run() {
                        if (!running) this.cancel();
                        board.step();
                        updateBoard();
                    }
                }, 0, STEP_DELAY);
        });
        menu.add(runBtn, 1, 0);
        
        // Pause button
        Button pauseBtn = new Button("Pause");
        pauseBtn.setOnAction((e) -> running = false);
        menu.add(pauseBtn, 2, 0);
        
        // Clear board
        Button clearBtn = new Button("Clear Board");
        clearBtn.setOnAction((e) -> {
            board.clearCells();
            updateBoard();
        });
        menu.add(clearBtn, 3, 0);
        
        // todo Change size
        
        // todo Load?
    }
    
    /**
     * Display the game cells onto the board
     */
    private void createBoard() {
        
        // Loop through each cell in the board, adding them to the grid
        for(int y = 0; y < board.getHeight(); y ++){
            for(int x = 0; x < board.getWidth(); x ++){
    
                Rectangle cellBox = new Rectangle(0, 0, cellSize, cellSize);
                if(board.getCell(x, y).isAlive())   cellBox.setFill(Color.BLACK);
                else    cellBox.setFill(Color.WHITE);
                boardGrid.add(cellBox, x, y);
            }
        }
        
        // Set clicked cells to alive
        boardGrid.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Node clickedNode = event.getPickResult().getIntersectedNode();
            if(clickedNode == null || clickedNode == boardGrid) return;
            int x = GridPane.getColumnIndex(clickedNode);
            int y = GridPane.getRowIndex(clickedNode);
            if(board.getCell(x,y).isAlive()) board.setCellLife(x,y, Cell.DEAD);
            else board.setCellLife(x, y, Cell.ALIVE);
            
            updateBoard();
        });
    }
    
    /**
     * Update the board display and check that a cell is alive
     */
    private void updateBoard(){
        
        boolean cellsAlive = false;
        ObservableList<Node> cellBoxes = boardGrid.getChildren();
        for (Node cellBox : cellBoxes) {
            int x = GridPane.getColumnIndex(cellBox);
            int y = GridPane.getRowIndex(cellBox);
            
            // Change cell color based of cell state
            if(board.getCell(x, y).isAlive()){
                ((Rectangle)cellBox).setFill(Color.BLACK);
                cellsAlive = true;
            }
            else    ((Rectangle)cellBox).setFill(Color.WHITE);
            
            // update size of cells if needed
            if(cellSize != ((Rectangle) cellBox).getWidth()){
                ((Rectangle) cellBox).setWidth(cellSize);
                ((Rectangle) cellBox).setHeight(cellSize);
            }
        }
        
        // Pause the game if no cells are alive
        if(!cellsAlive) running = false;
        
        // Show if game is paused
        if(!running)    boardGrid.setStyle("-fx-background-color: #FFB0B0");
        else    boardGrid.setStyle("-fx-background-color: #A0A0A0");
    }
}
