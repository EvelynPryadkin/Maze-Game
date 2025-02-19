import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Wall {
    private Line line;

    public Wall(double x1, double y1, double x2, double y2) {
        line = new Line(x1, y1, x2, y2);
    }

    public Shape getShape() {
        return line;
    }

    public boolean intersects(double x, double y) {
        return line.getBoundsInParent().contains(x, y);
    }
}
