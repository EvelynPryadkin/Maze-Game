import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class App extends Application {
    private static Stage stage; // Store the main stage

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        setRoot("primary"); // Start with primary.fxml
        stage.setTitle("Maze Game");
        stage.show();
    }

    /**
     * Switches the current scene to a different FXML file.
     * @param fxml The name of the FXML file (without ".fxml").
     */
    public static void setRoot(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 600, 600)); // Set scene size
    }

    public static void main(String[] args) {
        launch();
    }
}


