package src;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;


/**
 * Manages Drawing to the canvas through the drawable interface
 */
public class ScreenManager {

    private GraphicsContext draw;
    private Canvas canvas;

    public ScreenManager(GraphicsContext draw, Canvas canvas) {
        this.draw = draw;
        this.canvas = canvas;
    }

// run drawable function. polymorphism!
    public void draw(Drawable object) {
        Asset asset = object.getAsset();
        double x = object.getX();
        double y = object.getY();
        double angle = object.getAngle();
     

        draw.save(); //Saves current state of the graphics context
    
        //Rotate the coordinate system
        draw.translate(x, y);
        draw.rotate(angle);
       
//Draw the object with the center at the new origin

//Draw the lines
        draw.setStroke(asset.getColor());
        draw.setFill(asset.getColor());
        for (Line line : asset.getLines()) { //for each line in the asset line array
            draw.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }

        draw.restore(); //restores
    }
    
    public void draw(ArrayList<GameObject> objects){
        for (Drawable object: objects) {
            draw(object);
        }
    }

//refresh
    public void clear() {
        draw.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        draw.setFill(Color.BLACK);
        draw.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
