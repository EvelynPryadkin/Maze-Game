import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import java.io.PrintWriter;

public class Wall {
    private Line line;

    private double snap(double x) {
        return Math.round(x / 40) * 40.0;
    }

    public Wall(double x, double y) {
        line = new Line();
        line.setStartX(snap(x));
        line.setStartY(snap(y));
        line.setEndX(x);
        line.setEndY(y);
    }

    public Shape getShape() { return line; }

    public void setEnd(double x, double y) {
        line.setEndX(x);
        line.setEndY(y);
    }

    public void snapToGrid(double x, double y) {
        double deltaX = Math.abs(x - line.getStartX());
        double deltaY = Math.abs(y - line.getStartY());
        if (deltaX > deltaY) {
            line.setEndX(snap(x));
            line.setEndY(line.getStartY());
        } else {
            line.setEndX(line.getStartX());
            line.setEndY(snap(y));
        }
    }

    public void write(PrintWriter out) {
        out.println(Math.round(line.getStartX()) + " " + Math.round(line.getStartY())
                + " " + Math.round(line.getEndX()) + " " + Math.round(line.getEndY()));
    }
}
