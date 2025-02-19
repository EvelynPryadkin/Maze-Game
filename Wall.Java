import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import java.io.PrintWriter;

public class Wall {
    private Line line;
    
    private double snap(double x) {
        long n = Math.round(x / 40);
        return n * 40.0;
    }
    
    public Wall(double x1, double y1, double x2, double y2) {
        line = new Line(snap(x1), snap(y1), snap(x2), snap(y2));
    }

    public Shape getShape() { return line; }

    public void write(PrintWriter out) {
        out.println((int) line.getStartX() + " " + (int) line.getStartY() + " " +
                    (int) line.getEndX() + " " + (int) line.getEndY());
    }

    public boolean intersects(double x, double y, double newX, double newY) {
        Line moveLine = new Line(x, y, newX, newY);
        return line.getBoundsInLocal().intersects(moveLine.getBoundsInLocal());
    }
}

