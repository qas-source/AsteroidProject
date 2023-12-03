package src;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Asset {
    private Line[] lines;
    private Vector[] vertecies;
    private Color color;
   
    public Asset(Line[] lines, Color color) {
        this.lines = lines;
        this.color = color;
        vertecies = new Vector[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            vertecies[i] = new Vector(line.getStartX(), line.getStartY());
        }

    }

    public Asset(Vector[] lines, Color color) {
        ArrayList<Line> lineArray = new ArrayList<>();
        for (int vertex = 0; vertex < lines.length-1; vertex++) {
            lineArray.add(new Line(lines[vertex].x, lines[vertex].y, lines[vertex+1].x, lines[vertex+1].y));
        }
        this.lines = lineArray.toArray(new Line[0]);
        this.color = color;
        vertecies = lines;
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

    public Vector[] getVertecies() {


        return vertecies;
    }
}
