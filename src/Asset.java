package src;

import javafx.scene.paint.Color;

public class Asset {
    private Line[] lines;
    private Color color;
   
    public Asset(Line[] lines, Color color) {
        this.lines = lines;
        this.color = color;
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

    
}
