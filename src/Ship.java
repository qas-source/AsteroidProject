package src;

import java.util.ArrayList;

public class Ship extends GameObject{

    final private double acceletation = 0.1;
    final private double damping = 0.99;

     private double angularAcc = 0;
     private double angularVel = 0;



    public Ship(int x, int y, double screenWidth, double screenHeight) {
        super(x, y, screenWidth, screenHeight);
    }

    @Override
    public void controls(ArrayList<String> input){

        accY = 0;
        accX = 0;
        angularAcc = 0;

        if (input.contains("UP")){
            setComponents(acceletation);
        }// if (input.contains("DOWN")){
           // setComponents(-acceletation);
        //}
        if (input.contains("LEFT")){
            angularAcc -= 0.5;
        } if (input.contains("RIGHT")){
            angularAcc += 0.5;
        }
    }

    @Override
    public void update() {
        super.update();
        angularVel += angularAcc;

        angle += angularVel;
        damp();


    }

    private void damp(){
        velX *= damping;
        velY *= damping;
        angularVel *= 0.9;
    }

    private void setComponents(double scale){
        accX += Math.sin(Math.toRadians(angle))*scale;
        accY += -Math.cos(Math.toRadians(angle))*scale;
    }
}
