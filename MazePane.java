import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.File;
import java.io.PrintWriter;
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

        // Load the maze from a file
        loadMaze();

        // Add player to the scene
        player = new Player(40, 40); // Starting position
        this.getChildren().add(player.getShape());

        this.setOnKeyPressed(e -> handleKeyPress(e));
    }

    private void loadMaze() {
        try {
            Scanner scanner = new Scanner(new File("maze.txt"));
            int numWalls = scanner.nextInt();
            for (int i = 0; i < numWalls; i++) {
                double x1 = scanner.nextDouble();
                double y1 = scanner.nextDouble();
                double x2 = scanner.nextDouble();
                double y2 = scanner.nextDouble();
                Wall wall = new Wall(x1, y1);
                wall.setEnd(x2, y2);
                walls.add(wall);
                this.getChildren().add(wall.getShape());
            }

            int numTreasures = scanner.nextInt();
            for (int i = 0; i < numTreasures; i++) {
                double x = scanner.nextDouble();
                double y = scanner.nextDouble();
                String colorStr = scanner.next();
                Color color = switch (colorStr) {
                    case "blue" -> Color.BLUE;
                    case "green" -> Color.GREEN;
                    default -> Color.RED;
                };
                Treasure treasure = new Treasure(x, y, color);
                treasures.add(treasure);
                this.getChildren().add(treasure.getShape());
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        double dx = 0, dy = 0;

        switch (e.getCode()) {
            case LEFT -> dx = -40;
            case RIGHT -> dx = 40;
            case UP -> dy = -40;
            case DOWN -> dy = 40;
        }

        if (!collisionDetected(player.getX() + dx, player.getY() + dy)) {
            player.move(dx, dy);
            checkTreasureCollision();
        }
    }

    private boolean collisionDetected(double x, double y) {
        for (Wall wall : walls) {
            Line line = (Line) wall.getShape();
            double startX = line.getStartX(), startY = line.getStartY();
            double endX = line.getEndX(), endY = line.getEndY();

            if (x > Math.min(startX, endX) && x < Math.max(startX, endX) &&
                y > Math.min(startY, endY) && y < Math.max(startY, endY)) {
                return true;
            }
        }
        return false;
    }

    private void checkTreasureCollision() {
        Treasure toRemove = null;

        for (Treasure t : treasures) {
            if (Math.abs(player.getX() - t.getShape().getLayoutX()) < 30 &&
                Math.abs(player.getY() - t.getShape().getLayoutY()) < 30) {
                toRemove = t;
                break;
            }
        }

        if (toRemove != null) {
            treasures.remove(toRemove);
            this.getChildren().remove(toRemove.getShape());
            updateScore(toRemove);
        }
    }

    private void updateScore(Treasure t) {
        if (t.getShape().getFill() == Color.RED) score += 100;
        else if (t.getShape().getFill() == Color.GREEN) score += 40;
        else score += 10;
        System.out.println("Score: " + score);
    }
}

