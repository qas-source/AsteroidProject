package src;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Handles the visual assets of gameObjects
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public class Asset {
    private final Line[] lines; // The visible lines
    private Vector[] vertices; // Points used for collision
    private Vector[] splitVectors; // Motion of lines after collision
    private Color color;
    private int splitDuration = 0;
    private final int splitTimeLimit = 30; // Time spent splitting
    private final double speed = 0.5; // Speed of splitting

    private boolean splitting = false;

    /**
     * Constructor for an asset
     * @param lines, the lines of the shape
     * @param color, the color of the shape
     */
    public Asset(Line[] lines, Color color) {
        this.lines = lines;
        this.color = color;
        vertices = new Vector[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            vertices[i] = new Vector(line.getStartX(), line.getStartY());
        }

    }

    /**
     * Constructor for an asset
     * @param lines, the lines of the shape
     * @param color, the color of the shape
     */
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

    /**
     * Updates the state of the asset every frame, stepping the splitting of the asset forward
     */
    public void update() {
        if (splitting) {
            splitDuration++;
            for (int i = 0; i < lines.length; i++) {
                lines[i].shift(splitVectors[i]); // Moves Line away from center
            }
            if (splitDuration >= splitTimeLimit) {
                splitting = false; // Stop splitting after duration
            }
        }
    }

    /**
     * Calculates the splitVectors of shape
     */
    public void splitObject() {
        vertices = new Vector[]{new Vector(0, 0), new Vector(0, 0)}; // Sets collision shape to a point
        splitting = true;
        splitDuration = 0;
        splitVectors = new Vector[lines.length];
        for (int i = 0; i < lines.length; i++) {
            Line line = lines[i];
            splitVectors[i] = new Vector((
                    line.getStartX() + line.getEndX()) / 2,
                    (line.getStartY() + line.getEndY()) / 2
            ).normalize().multiply(speed); // Calculates vectors pointing away from the center
        }
    }

    /**
     * Checks if the shape has split
     * @return True if the shape has split
     */
    public boolean isSplittingComplete() {
        return !splitting && splitDuration > 0;
    }
}
