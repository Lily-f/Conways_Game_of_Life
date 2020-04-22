package Gui;

import Game.Board;
import Game.Cell;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
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
    private static final int MIN_WINDOW_HEIGHT = 200;
    
    /**
     * Minimum height of menu bar
     */
    private static final int MIN_MENU_HEIGHT = 30;
    
    /**
     * Initial height of game board
     */
    private static final int DEFAULT_BOARD_SIZE = 50;
    
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
     * Width of cells on gui
     */
    private double cellWidth;
    
    /**
     * Height of cells on gui
     */
    private double cellHeight;
    
    @Override
    public void start(Stage primaryStage){
        // Format stage
        primaryStage.setTitle("Game of Life");
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(MIN_WINDOW_WIDTH);
        primaryStage.setMinHeight(MIN_WINDOW_HEIGHT);
        primaryStage.setOnCloseRequest((e)->System.exit(0));
        
        // Scale the cells to fit the size of the screen
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            cellHeight = (primaryStage.getHeight() - MIN_MENU_HEIGHT) / board.getHeight() - 140.0/board.getHeight();
            cellWidth = primaryStage.getWidth() / board.getWidth() - 100.0/board.getHeight();
            updateBoard();
        };
        
        board = new Board(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
        
        // Create Panes
        VBox rootPane = new VBox();
        GridPane menu = new GridPane();
        boardGrid = new GridPane();
        Scene scene = new Scene(rootPane, 300, 300);
        
        // Populate and format panes
        rootPane.getChildren().addAll(menu, boardGrid);
        menu.setMinHeight(MIN_MENU_HEIGHT);
        setupGUIButtons(menu);
        createBoard();
    
        primaryStage.widthProperty().addListener(stageSizeListener);
        primaryStage.heightProperty().addListener(stageSizeListener);
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
        boardGrid.getChildren().removeAll();
        for(int y = 0; y < board.getHeight(); y ++){
            for(int x = 0; x < board.getWidth(); x ++){
                Rectangle cellBox = new Rectangle(0, 0, cellWidth, cellHeight);
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
                if(running)    cellBox.setStyle("-fx-fill: black; -fx-stroke: black; -fx-stroke-width: 1;");
                else    cellBox.setStyle("-fx-fill: black; -fx-stroke: pink; -fx-stroke-width: 1;");
                cellsAlive = true;
            }
            else{
                if(running)    cellBox.setStyle("-fx-fill: white; -fx-stroke: lightgray; -fx-stroke-width: 1;");
                else    cellBox.setStyle("-fx-fill: white; -fx-stroke: pink; -fx-stroke-width: 1;");
            }
            
            // update size of cells if needed
            if(cellWidth != ((Rectangle) cellBox).getWidth())   ((Rectangle) cellBox).setWidth(cellWidth);
            if(cellHeight != ((Rectangle) cellBox).getHeight())   ((Rectangle) cellBox).setHeight(cellHeight);
        }
        
        // Pause the game if no cells are alive
        if(!cellsAlive) running = false;
    }
}
