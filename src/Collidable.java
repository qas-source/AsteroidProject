package src;

public interface Collidable {


    Vector[] getVertecies();

    void collided(String indentification);

    String getIdentification();
}
