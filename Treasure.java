import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.io.PrintWriter;

public class Treasure {
    private Rectangle rect;
    private Color color;

    private double snap(double x) {
        double n = Math.floor(x / 40);
        return n * 40.0;
    }

    public Treasure(double x, double y, Color c) {
        this.color = c;
        rect = new Rectangle();
        rect.setX(x - 15.0);
        rect.setY(y - 15.0);
        rect.setWidth(30.0);
        rect.setHeight(30.0);
        rect.setFill(color);
    }

    public Shape getShape() {
        return rect;
    }

    public void setLocation(double x, double y) {
        rect.setX(x - 15.0);
        rect.setY(y - 15.0);
    }

    public void snapToGrid(double x, double y) {
        rect.setX(snap(x) + 5.0);
        rect.setY(snap(y) + 5.0);
    }

    public void write(PrintWriter out) {
        String colorStr = "red";
        if (color == Color.BLUE) colorStr = "blue";
        else if (color == Color.GREEN) colorStr = "green";

        out.println(Math.round(rect.getX()) + " " + Math.round(rect.getY()) + " " + colorStr);
    }
}

