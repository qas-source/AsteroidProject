package src;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Collision system based on this code: https://github.com/ClaymoreAdrendamar/Separating-Axis-Theorem
 * And this Article: https://dyn4j.org/2010/01/sat/
 * Implementation of SAT
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */

public class CollisionManager {

    /**
     * Checks for collision in gameObjects
     * @param objects, list of gameObjects that are checked
     */
    public void collide(ArrayList<GameObject> objects){
        for (GameObject objectA: objects) {
            for (GameObject objectB: objects) {
                if (!objectA.equals(objectB)){
                    boolean collided = collide(objectA, objectB); // If same object, skip
                    if (collided) {
                        objectA.collided(objectB.getIdentification()); // Informs objects that they have collided
                        objectB.collided(objectA.getIdentification());
                    }
                }
            }
        }
    }

    /**
     * Checks if two objects have collided
     * @param objectA, first object that is checked for collisions
     * @param objectB, second object that is checked for collisions
     * @return Returns true if the two objects overlap
     */
    public boolean collide(Collidable objectA, Collidable objectB){

        ArrayList<Vector> axes = new ArrayList<>(); // List of axis to check for collision on
        axes.addAll(Arrays.stream(getAxis(objectA)).toList());
        axes.addAll(Arrays.stream(getAxis(objectB)).toList());

        for (Vector axis: axes) {

            Projection p1 = new Projection(objectA, axis); // Projects shape onto collision axis
            Projection p2 = new Projection(objectB, axis);

            if (!p1.overlap(p2)) { // Checks if they overlap, if there is a gab, they don't overlap
                return false;
            }
        }

        return true;


    }

    /**
     * Gets the axis of collision for an object
     * @param object, object that axis are grabbed from
     * @return an array of axis
     */
    private Vector[] getAxis(Collidable object) {
        Vector[] vertices = object.getVertices();
        Vector[] axis = new Vector[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            axis[i] = vertices[i].sub(vertices[(i+1)% vertices.length]).normal().normalize(); // Returns a vector parallel to a side
        }
        
        return axis;
    }

}
