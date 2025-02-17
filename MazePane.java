import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazePane extends Pane {

    public MazePane() {
        try {
            // Load the maze from a file (maze.txt)
            BufferedReader reader = new BufferedReader(new FileReader("maze.txt"));
            String line;
            int wallCount = Integer.parseInt(reader.readLine());  // First line: number of walls
            for (int i = 0; i < wallCount; i++) {
                line = reader.readLine();
                String[] coordinates = line.split(",");
                int x1 = Integer.parseInt(coordinates[0]);
                int y1 = Integer.parseInt(coordinates[1]);
                int x2 = Integer.parseInt(coordinates[2]);
                int y2 = Integer.parseInt(coordinates[3]);

                // Create a wall
                Rectangle wall = new Rectangle(x1, y1, x2 - x1, y2 - y1);
                wall.setFill(Color.GRAY);
                this.getChildren().add(wall);
            }

            // Read and place treasure items
            int treasureCount = Integer.parseInt(reader.readLine());  // Next line: number of treasures
            for (int i = 0; i < treasureCount; i++) {
                line = reader.readLine();
                String[] treasureCoordinates = line.split(",");
                int x = Integer.parseInt(treasureCoordinates[0]);
                int y = Integer.parseInt(treasureCoordinates[1]);
                Rectangle treasure = new Rectangle(x, y, 20, 20);
                treasure.setFill(Color.RED); // Example color for treasure
                this.getChildren().add(treasure);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
