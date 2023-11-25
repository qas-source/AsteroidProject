package src;

import java.util.ArrayList;

public class Ship extends GameObject{

    private double acceletation = 0.1;

    public Ship(int x, int y) {
        super(x, y);
    }

    @Override
    public void controls(ArrayList<String> input){

        accY = 0;
        accX = 0;

        if (input.contains("UP")){
            accY -= 1*acceletation;
        } if (input.contains("DOWN")){
            accY += 1*acceletation;
        } if (input.contains("LEFT")){
            accX -= 1*acceletation;
        } if (input.contains("RIGHT")){
            accX += 1*acceletation;
        }
    }

    @Override
    public void update() {
        super.update();
    }

}
