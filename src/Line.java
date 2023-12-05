package src;

public class Line {
    double startX, startY, endX, endY;

    // Constructor to initialize the line with start and end points.
    public Line(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    // Getters
    public double getStartX() { return startX; }
    public double getStartY() { return startY; }
    public double getEndX() { return endX; }
    public double getEndY() { return endY; }
    // Shifts the line by a given vector
    public void shift(Vector displacement){
        startX += displacement.x;
        endX += displacement.x;
        startY += displacement.y;
        endY += displacement.y;
    }
}
