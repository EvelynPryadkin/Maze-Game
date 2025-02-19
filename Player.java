import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle shape;
    private double x, y;

    public Player(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        shape = new Circle(x, y, 15, Color.YELLOW);
    }

    public void moveTo(double newX, double newY) {
        this.x = newX;
        this.y = newY;
        shape.setCenterX(x);
        shape.setCenterY(y);
    }

    public boolean intersects(Treasure treasure) {
        return shape.getBoundsInParent().intersects(treasure.getShape().getBoundsInParent());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Circle getShape() {
        return shape;
    }
}
