import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player {
    private Circle player;
    private double x, y;

    public Player(double startX, double startY) {
        x = startX;
        y = startY;
        player = new Circle(x, y, 15, Color.YELLOW);
    }

    public Circle getShape() { return player; }

    public double getX() { return x; }
    public double getY() { return y; }

    public void move(double dx, double dy) {
        x += dx;
        y += dy;
        player.setCenterX(x);
        player.setCenterY(y);
    }
}
