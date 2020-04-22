package Gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Gui extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    private static final int MIN_WIDTH = 260;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Game of Life");
        primaryStage.getIcons().add(new Image("file:res/icon.png"));
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(MIN_WIDTH);
        
        Button button = new Button();
        button.setText("Hello world");
        button.setOnAction((actionEvent)-> System.out.println("hello world"));
    
        StackPane root = new StackPane();
        root.getChildren().add(button);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }
}
