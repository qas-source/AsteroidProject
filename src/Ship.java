package src;

import java.util.ArrayList;

public class Ship extends GameObject{

    final private double acceletation = 0.1;
    final private double damping = 0.99;

     private double angularAcc = 0;
     private double angularVel = 0;

     private boolean previousShooting = false;
     private Gun gun;



    public Ship(int x, int y, double screenWidth, double screenHeight, GameManager gameManager) {
        super(x, y, screenWidth, screenHeight, gameManager);
        gun = new Gun(gameManager);
    }

    @Override
    public void controls(ArrayList<String> input){

        accY = 0;
        accX = 0;
        angularAcc = 0;



        if (input.contains("UP")){
            setComponents(acceletation);
        }
        if (input.contains("LEFT")){
            angularAcc -= 0.5;
        } if (input.contains("RIGHT")){
            angularAcc += 0.5;
        } if (input.contains("SPACE")){
            if (!previousShooting){
                gun.shoot(x, y, angle, screenWidth, screenHeight, "Bullet");
                previousShooting = true; //TODO make it only shoot once per click
            } else {
                previousShooting = false;
            }
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

    private void setComponents(double value){
        accX += Math.sin(Math.toRadians(angle))*value;
        accY += -Math.cos(Math.toRadians(angle))*value;
    }

    @Override
    public String getIdentification() {
        return "Ship";
    }

    @Override
    public void collided(String indentification) {
        if (indentification.matches("Bullet")){
            return;
        }
        super.collided(indentification);
    }
}
