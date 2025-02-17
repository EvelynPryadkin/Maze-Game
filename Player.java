import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle shape;
    private double x, y;

    public Player(double startX, double startY) {
        x = startX;
        y = startY;
        shape = new Circle(x, y, 10, Color.YELLOW);
    }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        shape.setCenterX(x);
        shape.setCenterY(y);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public Circle getShape() { return shape; }
}
