package src;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Asset {
    private final Line[] lines;
    private final Vector[] vertices;
    private Vector[] splitVectors;
    private Color color;
    private int splitDuration = 0;
    private final int splitTimeLimit = 30;
    private final double speed = 0.5;

    private boolean splitting = false;

    public Asset(Line[] lines, Color color) {
        this.lines = lines;
        this.color = color;
        vertices = new Vector[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            vertices[i] = new Vector(line.getStartX(), line.getStartY());
        }

    }

    public Asset(Vector[] lines, Color color) {
        ArrayList<Line> lineArray = new ArrayList<>();
        for (int vertex = 0; vertex < lines.length-1; vertex++) {
            lineArray.add(new Line(lines[vertex].x, lines[vertex].y, lines[vertex+1].x, lines[vertex+1].y));
        }
        this.lines = lineArray.toArray(new Line[0]);
        this.color = color;
        vertices = lines;
    }

    // Getters
    public Line[] getLines() {
        return lines;
    }

    public Color getColor() {
        return color;
    }

    // Setters for customization (implement in the future)

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector[] getVertices() {
        return vertices;
    }

    public void update() {
        if (splitting) {
            splitDuration++;
            for (int i = 0; i < lines.length; i++) {
                lines[i].shift(splitVectors[i]);
            }
            if (splitDuration >= splitTimeLimit) {
                splitting = false; // Stop splitting after duration
            }
        }
    }

    public void splitObject() {
        splitting = true;
        splitDuration = 0;
        splitVectors = new Vector[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            splitVectors[i] = new Vector((line.getStartX() + line.getEndX()) / 2, (line.getStartY() + line.getEndY()) / 2).normalize().multiply(speed);
        }
    }

    public boolean isSplittingComplete() {
        return !splitting && splitDuration > 0;
    }
}
