import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Player extends Circle {

    public Player(double x, double y) {
        super(x, y, 20, Color.YELLOW); // Yellow circle as player
    }

    public void move(double deltaX, double deltaY) {
        setCenterX(getCenterX() + deltaX);
        setCenterY(getCenterY() + deltaY);
    }
}
