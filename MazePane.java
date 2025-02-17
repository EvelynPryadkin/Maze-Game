import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Iterator;

public class MazePane extends Pane {
    private Player player;
    private ArrayList<Wall> walls;
    private ArrayList<Treasure> treasures;
    private int score = 0;

    public MazePane() {
        setStyle("-fx-background-color: lightgray;");
        
        player = new Player(50, 50); // Starting position
        walls = new ArrayList<>();
        treasures = new ArrayList<>();

        // Sample walls (X, Y, width, height)
        walls.add(new Wall(100, 100, 100, 10)); // Horizontal wall
        walls.add(new Wall(200, 100, 10, 100)); // Vertical wall

        // Sample treasures
        treasures.add(new Treasure(150, 150, Color.RED));   // 100 points
        treasures.add(new Treasure(250, 250, Color.GREEN)); // 40 points
        treasures.add(new Treasure(350, 350, Color.BLUE));  // 10 points

        // Add elements to the pane
        getChildren().add(player.getShape());
        for (Wall wall : walls) {
            getChildren().add(wall.getShape());
        }
        for (Treasure treasure : treasures) {
            getChildren().add(treasure.getShape());
        }

        // Handle keyboard input
        setOnKeyPressed(event -> {
            double dx = 0, dy = 0;
            if (event.getCode() == KeyCode.LEFT) dx = -40;
            if (event.getCode() == KeyCode.RIGHT) dx = 40;
            if (event.getCode() == KeyCode.UP) dy = -40;
            if (event.getCode() == KeyCode.DOWN) dy = 40;

            movePlayer(dx, dy);
        });
    }

    private void movePlayer(double dx, double dy) {
        double newX = player.getX() + dx;
        double newY = player.getY() + dy;

        // Check for wall collisions
        for (Wall wall : walls) {
            if (wall.intersects(newX, newY)) {
                return; // Stop movement if there's a collision
            }
        }

        // Move the player if no collision
        player.move(dx, dy);

        // Check for treasure collection
        Iterator<Treasure> iterator = treasures.iterator();
        while (iterator.hasNext()) {
            Treasure treasure = iterator.next();
            if (treasure.intersects(player.getX(), player.getY())) {
                score += treasure.getPoints();
                getChildren().remove(treasure.getShape());
                iterator.remove();
                System.out.println("Score: " + score);
            }
        }
    }
}
