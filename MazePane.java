import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MazePane extends Pane {
    private ArrayList<Wall> walls;
    private ArrayList<Treasure> treasures;
    private Wall dragging;
    private Treasure placing;
    private Color color;
    private boolean drawingWalls;

    public MazePane() {
        walls = new ArrayList<>();
        treasures = new ArrayList<>();
        color = Color.RED;
        drawingWalls = true; // Start in wall-drawing mode

        // Create the grid of guide points
        for (int row = 0; row <= 11; row++) {
            for (int col = 0; col <= 16; col++) {
                Circle circle = new Circle();
                circle.setCenterX(col * 40);
                circle.setCenterY(row * 40);
                circle.setFill(Color.RED);
                circle.setRadius(2);
                this.getChildren().add(circle);
            }
        }

        this.setOnMousePressed(e -> startDrag(e));
        this.setOnMouseDragged(e -> dragSegment(e));
        this.setOnMouseReleased(e -> endDrag(e));
        this.setOnKeyPressed(e -> keyPress(e));
    }

    public void startDrag(MouseEvent e) {
        if (drawingWalls) {
            dragging = new Wall(e.getX(), e.getY());
            walls.add(dragging);
            this.getChildren().add(dragging.getShape());
        } else {
            placing = new Treasure(e.getX(), e.getY(), color);
            treasures.add(placing);
            this.getChildren().add(placing.getShape());
        }
    }

    public void dragSegment(MouseEvent e) {
        if (drawingWalls) {
            dragging.setEnd(e.getX(), e.getY());
        } else {
            placing.setLocation(e.getX(), e.getY());
        }
    }

    public void endDrag(MouseEvent e) {
        if (drawingWalls) {
            dragging.snapToGrid(e.getX(), e.getY());
            dragging = null;
        } else {
            placing.snapToGrid(e.getX(), e.getY());
            placing = null;
        }
    }

    public void keyPress(KeyEvent e) {
        switch (e.getCode()) {
            case R -> color = Color.RED;
            case G -> color = Color.GREEN;
            case B -> color = Color.BLUE;
            case BACK_SPACE -> {
                if (!walls.isEmpty()) {
                    Wall toRemove = walls.remove(walls.size() - 1);
                    this.getChildren().remove(toRemove.getShape());
                }
            }
            case P -> drawingWalls = !drawingWalls; // Toggle between walls/treasure
        }
    }

    public void save() {
        try {
            PrintWriter out = new PrintWriter(new File("maze.txt"));
            out.println(walls.size());
            for (Wall w : walls) {
                w.write(out);
            }
            out.println(treasures.size());
            for (Treasure t : treasures) {
                t.write(out);
            }
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
