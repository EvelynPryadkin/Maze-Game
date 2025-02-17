import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MazePane mazePane = new MazePane();

        Scene scene = new Scene(mazePane, 600, 600);
        primaryStage.setTitle("Maze Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Request focus so it can capture key presses
        mazePane.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
