package src;

import java.util.ArrayList;

public class Ship extends GameObject{

    final private double acceletation = 0.2;
    final private double damping = 0.99;

     private double angularAcc = 0;
     private double angularVel = 0;



    public Ship(int x, int y) {
        super(x, y);
    }

    @Override
    public void controls(ArrayList<String> input){

        accY = 0;
        accX = 0;

        if (input.contains("UP")){
            setComponents(acceletation);
        } if (input.contains("DOWN")){
            setComponents(-acceletation);
        } if (input.contains("LEFT")){
            angularAcc -= 1;
        } if (input.contains("RIGHT")){
            angularAcc += 1;
        }
    }

    @Override
    public void update() {
        super.update();
        damp();

        angularVel += angularAcc;

        angle += angularVel;

    }

    private void damp(){
        velX *= damping;
        velY *= damping;
        angularVel *= 0.5;
    }

    private void setComponents(double scale){
        accX += Math.cos(Math.toRadians(angle))*scale;
        accY += Math.sin(Math.toRadians(angle))*scale;
    }
}
