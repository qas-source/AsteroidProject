package src;

import javafx.scene.paint.Color;

public class Bullet extends GameObject {



    public Bullet(int x, int y, double screenWidth, double screenHeight, double angle) {
        super(x, y, screenWidth, screenHeight);

        this.angle = angle;

        Line[] lines = {
                new Line(0, -10, 0, 10)//Single line for now TODO make it a circle or oval
        };

        Color color = Color.WHITE;

        setAsset(new Asset(lines, color));//TODO CIRCLE
    }

}
