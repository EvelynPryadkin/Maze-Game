import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MazeGame extends Application {
    @Override
    public void start(Stage primaryStage) {
        MazePane pane = new MazePane();
        Scene scene = new Scene(pane, 680, 480);

        primaryStage.setTitle("Maze Drawing Program");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ensure the pane receives key events
        pane.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
