import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MazePane extends Pane {
    private ArrayList<Wall> walls;
    private ArrayList<Treasure> treasures;
    private Player player;
    private int score;

    public MazePane() {
        walls = new ArrayList<>();
        treasures = new ArrayList<>();
        score = 0;
        loadMaze();
        player = new Player(40, 40); // Set player start position
        this.getChildren().add(player.getShape());
        this.setOnKeyPressed(e -> keyPress(e));
        this.setFocusTraversable(true);
    }

    private void loadMaze() {
        try (Scanner scanner = new Scanner(new File("maze.txt"))) {
            int numWalls = scanner.nextInt();
            for (int i = 0; i < numWalls; i++) {
                double x1 = scanner.nextDouble();
                double y1 = scanner.nextDouble();
                double x2 = scanner.nextDouble();
                double y2 = scanner.nextDouble();
                Wall wall = new Wall(x1, y1, x2, y2);
                walls.add(wall);
                this.getChildren().add(wall.getShape());
            }

            int numTreasures = scanner.nextInt();
            for (int i = 0; i < numTreasures; i++) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                String color = scanner.next();
                Color c = (color.equals("red")) ? Color.RED : (color.equals("green")) ? Color.GREEN : Color.BLUE;
                Treasure treasure = new Treasure(x, y, c);
                treasures.add(treasure);
                this.getChildren().add(treasure.getShape());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void keyPress(KeyEvent e) {
        double dx = 0, dy = 0;
        switch (e.getCode()) {
            case LEFT: dx = -40; break;
            case RIGHT: dx = 40; break;
            case UP: dy = -40; break;
            case DOWN: dy = 40; break;
        }

        double newX = player.getX() + dx;
        double newY = player.getY() + dy;

        for (Wall w : walls) {
            if (w.intersects(player.getX(), player.getY(), newX, newY)) {
                return; // Prevent movement if colliding with a wall
            }
        }

        player.move(dx, dy);

        treasures.removeIf(t -> {
            if (t.isCollected(player.getX(), player.getY())) {
                this.getChildren().remove(t.getShape());
                score += (t.getShape().getFill() == Color.RED) ? 100 : (t.getShape().getFill() == Color.GREEN) ? 40 : 10;
                return true;
            }
            return false;
        });
    }
}

