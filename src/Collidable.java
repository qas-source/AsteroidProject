package src;

/**
 * Interface for Collidable objects
 *
 * @author Qasim Ebsim and Riley So
 * @version Mon Dec 4, 2023
 */
public interface Collidable {


    Vector[] getVertices(); // Gets shape for collision

    void collided(String identification); // Called when collided with

    String getIdentification(); // Get collision identification
}
