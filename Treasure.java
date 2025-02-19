import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.io.PrintWriter;

public class Treasure {
    private Rectangle rect;
    private Color color;

    public Treasure(double x, double y, Color c) {
        color = c;
        rect = new Rectangle(x, y, 30, 30);
        rect.setFill(color);
    }

    public Shape getShape() { return rect; }

    public void write(PrintWriter out) {
        String colorStr = (color == Color.RED) ? "red" : (color == Color.GREEN) ? "green" : "blue";
        out.println((int) rect.getX() + " " + (int) rect.getY() + " " + colorStr);
    }

    public boolean isCollected(double x, double y) {
        return rect.contains(x, y);
    }
}

