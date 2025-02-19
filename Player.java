import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle circle;

    public Player(double x, double y) {
        circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(15);
        circle.setFill(Color.YELLOW); // Player is a yellow circle
    }

    public Circle getShape() { return circle; }

    public void move(double dx, double dy) {
        circle.setCenterX(circle.getCenterX() + dx);
        circle.setCenterY(circle.getCenterY() + dy);
    }

    public double getX() { return circle.getCenterX(); }
    public double getY() { return circle.getCenterY(); }

    public void setPosition(double x, double y) {
        circle.setCenterX(x);
        circle.setCenterY(y);
    }
}
