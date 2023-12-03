package src;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Collision system based on this code: https://github.com/ClaymoreAdrendamar/Separating-Axis-Theorem
 * And this Article: https://dyn4j.org/2010/01/sat/
 */

public class CollisionManager {

    public void collide(ArrayList<GameObject> objects){
        for (GameObject objectA: objects) {
            for (GameObject objectB: objects) {
                if (!objectA.equals(objectB)){
                    boolean collided = collide(objectA, objectB);
                    if (collided) {
                        objectA.collided();
                        objectB.collided();
                    }
                }
            }
        }
    }

    public boolean collide(Collidable objectA, Collidable objectB){
        ArrayList<Vector> axes = new ArrayList<>();
        axes.addAll(Arrays.stream(getAxis(objectA)).toList());
        axes.addAll(Arrays.stream(getAxis(objectB)).toList());

        for (Vector axis: axes) {

            Projection p1 = new Projection(objectA, axis);
            Projection p2 = new Projection(objectB, axis);

            if (!p1.overlap(p2)) {
                // then we can guarantee that the shapes do not overlap
                return false;
            }
        }

        return true;


    }

    private Vector[] getAxis(Collidable object) {
        Vector[] vertices = object.getVertecies();
        Vector[] axis = new Vector[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            axis[i] = vertices[i].sub(vertices[(i+1)% vertices.length]).normal().normalize();
        }
        
        return axis;
    }

}
